package com.ijpay.demo.controller.wxpay;

import com.fasterxml.jackson.databind.JsonNode;
import com.ijpay.core.IJPayHttpResponse;
import com.ijpay.core.enums.RequestMethod;
import com.ijpay.core.kit.CypherKit;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.PayKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.core.utils.PayDateUtil;
import com.ijpay.core.utils.PayJsonUtil;
import com.ijpay.demo.entity.WxPayV3Bean;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.enums.WxApiType;
import com.ijpay.wxpay.enums.WxDomain;
import com.ijpay.wxpay.model.v3.Amount;
import com.ijpay.wxpay.model.v3.Payer;
import com.ijpay.wxpay.model.v3.RefundAmount;
import com.ijpay.wxpay.model.v3.RefundGoodsDetail;
import com.ijpay.wxpay.model.v3.RefundModel;
import com.ijpay.wxpay.model.v3.UnifiedOrderModel;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>微信支付 v3 接口示例</p>
 *
 * @author Javen
 */
@Controller
@RequestMapping("/v3")
public class WxPayV3Controller {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final static int OK = 200;

    @Resource
    WxPayV3Bean wxPayV3Bean;

    String serialNo;
    String platSerialNo;


    @RequestMapping("")
    @ResponseBody
    public String index() {
        log.info(wxPayV3Bean.toString());
        try {
//            String absolutePath = PayKit.getAbsolutePath("classpath:/dev/apiclient_cert.p12");
//            log.info("absolutePath:{}", absolutePath);
        } catch (Exception e) {
            log.error("文件不存在", e);
        }
        return ("欢迎使用 IJPay 中的微信支付 Api-v3 -By Javen  <br/><br>  交流群：723992875");
    }

    @RequestMapping("/getSerialNumber")
    @ResponseBody
    public String serialNumber() {
        return getSerialNumber();
    }

    @RequestMapping("/getPlatSerialNumber")
    @ResponseBody
    public String platSerialNumber() {
        return getPlatSerialNumber();
    }

    private String getSerialNumber() {
        if (StringUtils.isEmpty(serialNo)) {
            // 获取证书序列号
            X509Certificate certificate = PayKit.getCertificate(openInputStream(wxPayV3Bean.getCertPath()));
            serialNo = certificate.getSerialNumber().toString(16).toUpperCase();
        }
        return serialNo;
    }

	private InputStream openInputStream(String path){
		File file = new File(path);
		try {
			return FileUtils.openInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    private String getPlatSerialNumber() {
        if (StringUtils.isEmpty(platSerialNo)) {
            // 获取平台证书序列号
            X509Certificate certificate = PayKit.getCertificate(openInputStream(wxPayV3Bean.getPlatformCertPath()));
            platSerialNo = certificate.getSerialNumber().toString(16).toUpperCase();
        }
        return platSerialNo;
    }

    private String savePlatformCert(String associatedData, String nonce, String cipherText, String certPath) {
        try {
            // 平台证书密文解密
            // encrypt_certificate 中的  associated_data nonce  ciphertext
            String publicKey = CypherKit.decryptToCipher(wxPayV3Bean.getApiKey3(), associatedData, nonce, cipherText);
            // 保存证书
			FileUtils.write(new File(certPath), publicKey, Charset.defaultCharset());
            // 获取平台证书序列号
            X509Certificate certificate = PayKit.getCertificate(new ByteArrayInputStream(publicKey.getBytes()));
            return certificate.getSerialNumber().toString(16).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @RequestMapping("/platformCert")
    @ResponseBody
    public String platformCert() {
        try {
            String associatedData = "certificate";
            String nonce = "80d28946a64a";
            String cipherText = "DwAqW4+4TeUaOEylfKEXhw+XqGh/YTRhUmLw/tBfQ5nM9DZ9d+9aGEghycwV1jwo52vXb/t6ueBvBRHRIW5JgDRcXmTHw9IMTrIK6HxTt2qiaGTWJU9whsF+GGeQdA7gBCHZm3AJUwrzerAGW1mclXBTvXqaCl6haE7AOHJ2g4RtQThi3nxOI63/yc3WaiAlSR22GuCpy6wJBfljBq5Bx2xXDZXlF2TNbDIeodiEnJEG2m9eBWKuvKPyUPyClRXG1fdOkKnCZZ6u+ipb4IJx28n3MmhEtuc2heqqlFUbeONaRpXv6KOZmH/IdEL6nqNDP2D7cXutNVCi0TtSfC7ojnO/+PKRu3MGO2Z9q3zyZXmkWHCSms/C3ACatPUKHIK+92MxjSQDc1E/8faghTc9bDgn8cqWpVKcL3GHK+RfuYKiMcdSkUDJyMJOwEXMYNUdseQMJ3gL4pfxuQu6QrVvJ17q3ZjzkexkPNU4PNSlIBJg+KX61cyBTBumaHy/EbHiP9V2GeM729a0h5UYYJVedSo1guIGjMZ4tA3WgwQrlpp3VAMKEBLRJMcnHd4pH5YQ/4hiUlHGEHttWtnxKFwnJ6jHr3OmFLV1FiUUOZEDAqR0U1KhtGjOffnmB9tymWF8FwRNiH2Tee/cCDBaHhNtfPI5129SrlSR7bZc+h7uzz9z+1OOkNrWHzAoWEe3XVGKAywpn5HGbcL+9nsEVZRJLvV7aOxAZBkxhg8H5Fjt1ioTJL+qXgRzse1BX1iiwfCR0fzEWT9ldDTDW0Y1b3tb419MhdmTQB5FsMXYOzqp5h+Tz1FwEGsa6TJsmdjJQSNz+7qPSg5D6C2gc9/6PkysSu/6XfsWXD7cQkuZ+TJ/Xb6Q1Uu7ZB90SauA8uPQUIchW5zQ6UfK5dwMkOuEcE/141/Aw2rlDqjtsE17u1dQ6TCax/ZQTDQ2MDUaBPEaDIMPcgL7fCeijoRgovkBY92m86leZvQ+HVbxlFx5CoPhz4a81kt9XJuEYOztSIKlm7QNfW0BvSUhLmxDNCjcxqwyydtKbLzA+EBb2gG4ORiH8IOTbV0+G4S6BqetU7RrO+/nKt21nXVqXUmdkhkBakLN8FUcHygyWnVxbA7OI2RGnJJUnxqHd3kTbzD5Wxco4JIQsTOV6KtO5c960oVYUARZIP1SdQhqwELm27AktEN7kzg/ew/blnTys/eauGyw78XCROb9F1wbZBToUZ7L+8/m/2tyyyqNid+sC9fYqJoIOGfFOe6COWzTI/XPytCHwgHeUxmgk7NYfU0ukR223RPUOym6kLzSMMBKCivnNg68tbLRJHEOpQTXFBaFFHt2qpceJpJgw5sKFqx3eQnIFuyvA1i8s2zKLhULZio9hpsDJQREOcNeHVjEZazdCGnbe3Vjg7uqOoVHdE/YbNzJNQEsB3/erYJB+eGzyFwFmdAHenG5RE6FhCutjszwRiSvW9F7wvRK36gm7NnVJZkvlbGwh0UHr0pbcrOmxT81xtNSvMzT0VZNLTUX2ur3AGLwi2ej8BIC0H41nw4ToxTnwtFR1Xy55+pUiwpB7JzraA08dCXdFdtZ72Tw/dNBy5h1P7EtQYiKzXp6rndfOEWgNOsan7e1XRpCnX7xoAkdPvy40OuQ5gNbDKry5gVDEZhmEk/WRuGGaX06CG9m7NfErUsnQYrDJVjXWKYuARd9R7W0aa5nUXqz/Pjul/LAatJgWhZgFBGXhNr9iAoade/0FPpBj0QWa8SWqKYKiOqXqhfhppUq35FIa0a1Vvxcn3E38XYpVZVTDEXcEcD0RLCu/ezdOa6vRcB7hjgXFIRZQAka0aXnQxwOZwE2Rt3yWXqc+Q1ah2oOrg8Lg3ETc644X9QP4FxOtDwz/A==";
            return savePlatformCert(associatedData, nonce, cipherText, wxPayV3Bean.getPlatformCertPath());
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public String v3Get() {
        // 获取平台证书列表
        try {
            IJPayHttpResponse response = WxPayApi.v3(
                    RequestMethod.GET,
                    WxDomain.CHINA.toString(),
                    WxApiType.GET_CERTIFICATES.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    ""
            );

            String timestamp = response.getHeader("Wechatpay-Timestamp");
            String nonceStr = response.getHeader("Wechatpay-Nonce");
            String serialNumber = response.getHeader("Wechatpay-Serial");
            String signature = response.getHeader("Wechatpay-Signature");

            String body = response.getBody();
            int status = response.getStatus();

            log.info("serialNumber: {}", serialNumber);
            log.info("status: {}", status);
            log.info("body: {}", body);
            int isOk = 200;
            if (status == isOk) {
				JsonNode nodes = PayJsonUtil.toNode(body, "data");
				JsonNode node = nodes.get(0).get("encrypt_certificate");
                // 默认认为只有一个平台证书
                String associatedData = node.get("associated_data").asText();
                String cipherText = node.get("ciphertext").asText();
                String nonce = node.get("nonce").asText();
                String serialNo = node.get("serial_no").asText();
                final String platSerialNo = savePlatformCert(associatedData, nonce, cipherText, wxPayV3Bean.getPlatformCertPath());
                log.info("平台证书序列号: {} serialNo: {}", platSerialNo, serialNo);
            }
            // 根据证书序列号查询对应的证书来验证签名结果
            boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());

            return body;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/nativePay")
    @ResponseBody
    public String nativePay() {
        try {
            String timeExpire = PayDateUtil.addSecondsXxx(60 * 3);
            UnifiedOrderModel model = new UnifiedOrderModel();
			model.setAppid(wxPayV3Bean.getAppId());
			model.setMchid(wxPayV3Bean.getMchId());
			model.setDescription("IJPay 让支付触手可及");
			model.setOut_trade_no(PayKit.generateStr());
			model.setTime_expire(timeExpire);
			model.setAttach("微信系开发脚手架 https://gitee.com/javen205/TNWX");
			model.setNotify_url(wxPayV3Bean.getDomain().concat("/v3/payNotify"));
			model.setAmount(new Amount(1));

			String json = PayJsonUtil.toJson(model);
            log.info("统一下单参数 {}", json);
            IJPayHttpResponse response = WxPayApi.v3(
                    RequestMethod.POST,
                    WxDomain.CHINA.toString(),
                    WxApiType.NATIVE_PAY.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
				json
            );
            log.info("统一下单响应 {}", response);
            // 根据证书序列号查询对应的证书来验证签名结果
            boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
            log.info("verifySignature: {}", verifySignature);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/jsApiPay")
    @ResponseBody
    public String jsApiPay(@RequestParam(value = "openId", required = false, defaultValue = "o-_-itxuXeGW3O1cxJ7FXNmq8Wf8") String openId) {
        try {
            String timeExpire = PayDateUtil.addSecondsXxx(60 * 3);
            UnifiedOrderModel model = new UnifiedOrderModel();
			model.setAppid(wxPayV3Bean.getAppId());
			model.setMchid(wxPayV3Bean.getMchId());
			model.setDescription("IJPay 让支付触手可及");
			model.setOut_trade_no(PayKit.generateStr());
			model.setTime_expire(timeExpire);
			model.setAttach("微信系开发脚手架 https://gitee.com/javen205/TNWX");
			model.setNotify_url(wxPayV3Bean.getDomain().concat("/v3/payNotify"));
			model.setAmount(new Amount(1));
			model.setPayer(new Payer(openId));
			String json = PayJsonUtil.toJson(model);
            log.info("统一下单参数 {}", json);
            IJPayHttpResponse response = WxPayApi.v3(
                    RequestMethod.POST,
                    WxDomain.CHINA.toString(),
                    WxApiType.JS_API_PAY.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
					json
            );
            log.info("统一下单响应 {}", response);

            if (response.getStatus() == OK) {
                // 根据证书序列号查询对应的证书来验证签名结果
                boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
                log.info("verifySignature: {}", verifySignature);
                if (verifySignature) {
                    String body = response.getBody();
                    String prepayId = PayJsonUtil.key2Val(body, "prepay_id");
                    Map<String, String> map = WxPayKit.jsApiCreateSign(wxPayV3Bean.getAppId(), prepayId, wxPayV3Bean.getKeyPath());
                    log.info("唤起支付参数:{}", map);
                    return PayJsonUtil.toJson(map);
                }
            }
            return PayJsonUtil.toJson(response);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @RequestMapping("/put")
    @ResponseBody
    public String put() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("url", "https://gitee.com/javen205/IJPay");

            IJPayHttpResponse response = WxPayApi.v3(
                    RequestMethod.PUT,
                    WxDomain.CHINA.toString(),
                    WxApiType.MERCHANT_SERVICE_COMPLAINTS_NOTIFICATIONS.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
				PayJsonUtil.toJson(params)
            );
            // 根据证书序列号查询对应的证书来验证签名结果
            boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
            log.info("verifySignature: {}", verifySignature);
            log.info("响应 {}", response);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @RequestMapping("/getParams")
    @ResponseBody
    public String payScoreUserServiceState() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("service_id", "500001");
            params.put("appid", "wxd678efh567hg6787");
            params.put("openid", "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");

            IJPayHttpResponse result = WxPayApi.v3(
                    RequestMethod.GET,
                    WxDomain.CHINA.toString(),
                    WxApiType.PAY_SCORE_USER_SERVICE_STATE.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    params
            );
            return PayJsonUtil.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String v3Delete() {
        // 创建/查询/更新/删除投诉通知回调
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("url", "https://qq.com");
            IJPayHttpResponse result = WxPayApi.v3(
                    RequestMethod.POST,
                    WxDomain.CHINA.toString(),
                    WxApiType.MERCHANT_SERVICE_COMPLAINTS_NOTIFICATIONS.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
				PayJsonUtil.toJson(hashMap)
            );

            result = WxPayApi.v3(
                    RequestMethod.DELETE,
                    WxDomain.CHINA.toString(),
                    WxApiType.MERCHANT_SERVICE_COMPLAINTS_NOTIFICATIONS.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    ""
            );
            // 根据证书序列号查询对应的证书来验证签名结果
            boolean verifySignature = WxPayKit.verifySignature(result, wxPayV3Bean.getPlatformCertPath());

            // 如果返回的为 204 表示删除成功
            return PayJsonUtil.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @RequestMapping("/post")
    @ResponseBody
    public String payGiftActivity() {
        // 支付有礼-终止活动
        try {
            String urlSuffix = String.format(WxApiType.PAY_GIFT_ACTIVITY_TERMINATE.toString(), "10028001");

            IJPayHttpResponse result = WxPayApi.v3(
                    RequestMethod.POST,
                    WxDomain.CHINA.toString(),
                    urlSuffix,
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    ""
            );
            return PayJsonUtil.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/sensitive")
    @ResponseBody
    public String sensitive() {
        // 带有敏感信息接口
        try {
            String body = "处理请求参数";

            IJPayHttpResponse result = WxPayApi.v3(
                    RequestMethod.POST,
                    WxDomain.CHINA.toString(),
                    WxApiType.APPLY_4_SUB.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    getPlatSerialNumber(),
                    wxPayV3Bean.getKeyPath(),
                    body
            );
            return PayJsonUtil.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/cipher")
    @ResponseBody
    public String cipher() {
        try {
            // 敏感信息加密
            X509Certificate certificate = PayKit.getCertificate(openInputStream(wxPayV3Bean.getPlatformCertPath()));
            String encrypt = PayKit.rsaEncryptOAEP("IJPay", certificate);

            // 敏感信息解密
            String encryptStr = "";
            PrivateKey privateKey = PayKit.getPrivateKey(wxPayV3Bean.getKeyPath());
            String decrypt = PayKit.rsaDecryptOAEP(encryptStr, privateKey);

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }

    /**
     * 申请交易账单
     *
     * @param billDate 2020-06-14 当天账单后一天出，不然会出现「账单日期格式不正确」错误
     * @return 交易账单下载地址
     */
    @RequestMapping("/tradeBill")
    @ResponseBody
    public String tradeBill(@RequestParam(value = "billDate", required = false) String billDate) {
        try {
            if (StringUtils.isEmpty(billDate)) {
                billDate = PayDateUtil.addDate(-1, PayDateUtil.DATE_PATTERN);
            }
            Map<String, String> params = new HashMap<>();
            params.put("bill_date", billDate);
            params.put("bill_type", "ALL");
            params.put("tar_type", "GZIP");

            IJPayHttpResponse result = WxPayApi.v3(
                    RequestMethod.GET,
                    WxDomain.CHINA.toString(),
                    WxApiType.TRADE_BILL.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    params
            );
            // 根据证书序列号查询对应的证书来验证签名结果
            boolean verifySignature = WxPayKit.verifySignature(result, wxPayV3Bean.getPlatformCertPath());
            log.info("verifySignature: {}", verifySignature);
            log.info("result:{}", result);
            return PayJsonUtil.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 申请资金账单
     *
     * @param billDate 2020-06-14 当天账单后一天出，不然会出现「账单日期格式不正确」错误
     * @return 资金账单下载地址
     */
    @RequestMapping("/fundFlowBill")
    @ResponseBody
    public String fundFlowBill(@RequestParam(value = "billDate", required = false) String billDate) {
        try {
            if (StringUtils.isEmpty(billDate)) {
                billDate = PayDateUtil.addDate(-1, PayDateUtil.DATE_PATTERN);
            }
            Map<String, String> params = new HashMap<>();
            params.put("bill_date", billDate);
            params.put("account_type", "BASIC");

            IJPayHttpResponse result = WxPayApi.v3(
                    RequestMethod.GET,
                    WxDomain.CHINA.toString(),
                    WxApiType.FUND_FLOW_BILL.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    params
            );
            // 根据证书序列号查询对应的证书来验证签名结果
            boolean verifySignature = WxPayKit.verifySignature(result, wxPayV3Bean.getPlatformCertPath());
            log.info("verifySignature: {}", verifySignature);
            log.info("result:{}", result);
            return PayJsonUtil.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/billDownload")
    @ResponseBody
    public String billDownload(@RequestParam(value = "token") String token,
                               @RequestParam(value = "tarType", required = false) String tarType) {
        try {

            Map<String, String> params = new HashMap<>();
            params.put("token", token);
            if (StringUtils.isNotEmpty(tarType)) {
                params.put("tartype", tarType);
            }

            IJPayHttpResponse result = WxPayApi.v3(
                    RequestMethod.GET,
                    WxDomain.CHINA.toString(),
                    WxApiType.BILL_DOWNLOAD.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    params
            );
            log.info("result:{}", result);
            return PayJsonUtil.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/refund")
    @ResponseBody
    public String refund(@RequestParam(required = false) String transactionId,
                         @RequestParam(required = false) String outTradeNo) {
        try {
            String outRefundNo = PayKit.generateStr();
            log.info("商户退款单号: {}", outRefundNo);

            List<RefundGoodsDetail> list = new ArrayList<>();
            RefundGoodsDetail detail = new RefundGoodsDetail();
			detail.setMerchant_goods_id("123");
			detail.setGoods_name("IJPay 测试");
			detail.setUnit_price(1);
			detail.setRefund_amount(1);
			detail.setRefund_quantity(1);
            list.add(detail);

            RefundModel model = new RefundModel();
			model.setOut_refund_no(outRefundNo);
			model.setReason("IJPay 测试退款");
			model.setNotify_url(wxPayV3Bean.getDomain().concat("/v3/refundNotify"));

			RefundAmount refund = new RefundAmount();
			refund.setRefund(1);
			refund.setTotal(1);
			refund.setCurrency("CNY");

			model.setAmount(refund);
			model.setGoods_detail(list);

            if (StringUtils.isNotEmpty(transactionId)) {
				model.setTransaction_id(transactionId);
            }
            if (StringUtils.isNotEmpty(outTradeNo)) {
				model.setOut_trade_no(outTradeNo);
            }
			String json = PayJsonUtil.toJson(model);
            log.info("退款参数 {}", json);
            IJPayHttpResponse response = WxPayApi.v3(
                    RequestMethod.POST,
                    WxDomain.CHINA.toString(),
                    WxApiType.DOMESTIC_REFUNDS.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
				json
            );
            // 根据证书序列号查询对应的证书来验证签名结果
            boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
            log.info("verifySignature: {}", verifySignature);
            log.info("退款响应 {}", response);

            if (verifySignature) {
                return response.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }

    @RequestMapping(value = "/payNotify", method = {org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public void payNotify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        try {
            String timestamp = request.getHeader("Wechatpay-Timestamp");
            String nonce = request.getHeader("Wechatpay-Nonce");
            String serialNo = request.getHeader("Wechatpay-Serial");
            String signature = request.getHeader("Wechatpay-Signature");

            log.info("timestamp:{} nonce:{} serialNo:{} signature:{}", timestamp, nonce, serialNo, signature);
            String result = HttpKit.readData(request);
            log.info("支付通知密文 {}", result);

            // 需要通过证书序列号查找对应的证书，verifyNotify 中有验证证书的序列号
            String plainText = WxPayKit.verifyNotify(serialNo, result, signature, nonce, timestamp,
                    wxPayV3Bean.getApiKey3(), wxPayV3Bean.getPlatformCertPath());

            log.info("支付通知明文 {}", plainText);

            if (StringUtils.isNotEmpty(plainText)) {
                response.setStatus(200);
                map.put("code", "SUCCESS");
                map.put("message", "SUCCESS");
            } else {
                response.setStatus(500);
                map.put("code", "ERROR");
                map.put("message", "签名错误");
            }
            response.setHeader("Content-type", "application/json");
            response.getOutputStream().write(PayJsonUtil.toJson(map).getBytes(StandardCharsets.UTF_8));
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
