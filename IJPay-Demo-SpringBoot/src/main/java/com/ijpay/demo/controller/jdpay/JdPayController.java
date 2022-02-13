package com.ijpay.demo.controller.jdpay;

import com.ijpay.core.kit.CypherKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.core.utils.PayDateUtil;
import com.ijpay.demo.entity.JdPayBean;
import com.ijpay.demo.vo.AjaxResult;
import com.ijpay.jdpay.JdPayApi;
import com.ijpay.jdpay.kit.JdPayKit;
import com.ijpay.jdpay.model.CustomerPayModel;
import com.ijpay.jdpay.model.FkmModel;
import com.ijpay.jdpay.model.QueryBaiTiaoFqModel;
import com.ijpay.jdpay.model.QueryOrderModel;
import com.ijpay.jdpay.model.RefundModel;
import com.ijpay.jdpay.model.UniOrderModel;
import com.ijpay.jdpay.model.UserRelationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
 * <p>微信支付 Demo</p>
 *
 * @author Javen
 */
@Controller
@RequestMapping("/JDPay")
public class JdPayController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdPayBean jdPayBean;

    @RequestMapping("")
    @ResponseBody
    public String index() {
        log.info("欢迎使用 IJPay 中的京东支付 -By Javen  <br/><br>  交流群：723992875");
        log.info(jdPayBean.toString());
        return ("欢迎使用 IJPay 中的京东支付 -By Javen  <br/><br>  交流群：723992875");
    }

    @GetMapping("/test")
    @ResponseBody
    public JdPayBean test() {
        return jdPayBean;
    }

    /**
     * App 支付
     *
     * @return
     */
    @RequestMapping(value = "/appPay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult appPay() {
		UniOrderModel model = new UniOrderModel();
		model.setVersion("V2.0");
		model.setMerchant(jdPayBean.getMchId());
		model.setTradeNum(System.currentTimeMillis() + "");
		model.setTradeName("IJPay 让支付触手可及");
		model.setTradeDesc("https://gitee.com/javen205/IJPay");

		model.setTradeTime(PayDateUtil.formatLocalDateTime(PayDateUtil.ALL_NON_PATTERN));
		model.setAmount("1");
		model.setOrderType("1");
		model.setCurrency("CNY");
		model.setNote("备注");

		model.setNotifyUrl("http://jdpaydemo.jd.com/asynNotify.htm");
		model.setTradeType("GEN");

        String reqXml = model.genReqXml(jdPayBean.getRsaPrivateKey(), jdPayBean.getDesKey(), "V2.0", jdPayBean.getMchId());

        // 执行请求
        String resultData = JdPayApi.uniOrder(reqXml);

        log.info("resultData:" + resultData);

        // 解析响应的 xml 数据
        Map<String, String> map = JdPayKit.parseResp(resultData);

        String code = map.get("code");
        String encrypt = map.get("encrypt");
        if (!"000000".equals(code)) {
            String desc = map.get("desc");
            return new AjaxResult().addError(desc);
        }
        // 解密并验证签名
        String decrypt = JdPayKit.decrypt(jdPayBean.getRsaPublicKey(), jdPayBean.getDesKey(), encrypt);

        log.info("decrypt>" + decrypt);

        // 将 xml 转化为 map
        Map<String, String> toMap = WxPayKit.xmlToMap(decrypt);

        log.info("result toMap>" + toMap);


        String orderId = toMap.get("orderId");

        StringBuilder sb = new StringBuilder();
        sb.append("merchant=").append(jdPayBean.getMchId());
        sb.append("&orderId=").append(orderId);
        sb.append("&key=").append("test");
        String sign = CypherKit.md5(sb.toString()).toLowerCase();

        return new AjaxResult().success(new AppParams(orderId, jdPayBean.getMchId(), "123456789",
                sign, "123456789"));
    }

    /**
     * PC H5 支付
     *
     * @param payType
     * @return
     */
    @RequestMapping(value = "/saveOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView saveOrder(@RequestParam("payType") String payType) {

        ModelAndView mv = new ModelAndView();
		UniOrderModel model = new UniOrderModel();
		model.setVersion("V2.0");
		model.setMerchant(jdPayBean.getMchId());
		model.setTradeNum(System.currentTimeMillis() + "");
		model.setTradeName("IJPay");
		model.setTradeDesc("IJPay 让支付触手可及");

		model.setTradeTime(PayDateUtil.formatLocalDateTime(PayDateUtil.ALL_NON_PATTERN));
		model.setAmount("10000");
		model.setOrderType("0");
		model.setCurrency("CNY");
		model.setNote("IJPay 了解一下");

		model.setCallbackUrl("https://jdpay.com");
		model.setNotifyUrl("https://jdpay.com");
		model.setUserId("IJPay001");

        Map<String, String> map = model.createSign(jdPayBean.getRsaPrivateKey(), jdPayBean.getDesKey());

        mv.addObject("map", map);
        mv.addObject("payUrl", payType.equals("pc") ? JdPayApi.PC_SAVE_ORDER_URL : JdPayApi.H5_SAVE_ORDER_URL);
        mv.setViewName("jd_pc_h5.html");
        return mv;
    }

    /**
     * 商户二维码支付
     */
    @RequestMapping(value = "/customerPay", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView customerPay() {

        ModelAndView mv = new ModelAndView();
		CustomerPayModel model = new CustomerPayModel();
		model.setVersion("V2.0");
		model.setMerchant(jdPayBean.getMchId());
		model.setTradeNum(System.currentTimeMillis() + "");
		model.setTradeName("IJPay");
		model.setTradeDesc("IJPay 让支付触手可及");

		model.setTradeTime(PayDateUtil.formatLocalDateTime(PayDateUtil.ALL_NON_PATTERN));
		model.setOrderType("0");
		model.setCurrency("CNY");
		model.setNote("IJPay 了解一下");
		model.setNotifyUrl("https://jdpay.com");


        Map<String, String> map = model.createSign(jdPayBean.getRsaPrivateKey(), jdPayBean.getDesKey());

        mv.addObject("map", map);
        mv.addObject("payUrl", JdPayApi.CUSTOMER_PAY_URL);
        mv.setViewName("jd_customer_pay.html");
        return mv;
    }

    @RequestMapping(value = "/queryOrder", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult queryOrder(@RequestParam("tradeType") String tradeType,
                                 @RequestParam("oTradeNum") String oTradeNum,
                                 @RequestParam("tradeNum") String tradeNum) {
		QueryOrderModel model = new QueryOrderModel();
		model.setVersion("V2.0");
		model.setMerchant(jdPayBean.getMchId());
		model.setTradeNum(tradeNum);
		model.setTradeType(tradeType);
		model.setoTradeNum(oTradeNum);
        String reqXml = model.genReqXml(jdPayBean.getRsaPrivateKey(), jdPayBean.getDesKey(), "V2.0", jdPayBean.getMchId());
        String queryResult = JdPayApi.queryOrder(reqXml);
        log.info("queryResult:" + queryResult);

        // 解析响应的 xml 数据
        Map<String, String> map = JdPayKit.parseResp(queryResult);

        String code = map.get("code");
        String encrypt = map.get("encrypt");
        if (!"000000".equals(code)) {
            String desc = map.get("desc");
            return new AjaxResult().addError(desc);
        }
        // 解密并验证签名
        String decrypt = JdPayKit.decrypt(jdPayBean.getRsaPublicKey(), jdPayBean.getDesKey(), encrypt);

        log.info("decrypt>" + decrypt);

        return new AjaxResult().success(decrypt);
    }

    @RequestMapping(value = "/fkmPay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult fkmPay(@RequestParam("token") String token,
                             @RequestParam("amount") String amount) {
		FkmModel model = new FkmModel();
		model.setVersion("V2.0");
		model.setMerchant(jdPayBean.getMchId());
		model.setTradeNum(System.currentTimeMillis() + "");
		model.setTradeName("IJPay");
		model.setTradeDesc("IJPay 让支付触手可及");

		model.setTradeTime(PayDateUtil.formatLocalDateTime(PayDateUtil.ALL_NON_PATTERN));
		model.setAmount(amount);
		model.setCurrency("CNY");
		model.setNote("备注");
		model.setNotifyUrl("https://gitee.com/javen205/IJPay");

		model.setToken(token);

        String reqXml = model.genReqXml(jdPayBean.getRsaPrivateKey(), jdPayBean.getDesKey(), "V2.0", jdPayBean.getMchId());
        String queryResult = JdPayApi.fkmPay(reqXml);
        log.info("queryResult:" + queryResult);

        // 解析响应的 xml 数据
        Map<String, String> map = JdPayKit.parseResp(queryResult);

        String code = map.get("code");
        String encrypt = map.get("encrypt");
        if (!"000000".equals(code)) {
            String desc = map.get("desc");
            return new AjaxResult().addError(desc);
        }
        // 解密并验证签名
        String decrypt = JdPayKit.decrypt(jdPayBean.getRsaPublicKey(), jdPayBean.getDesKey(), encrypt);

        log.info("decrypt>" + decrypt);

        return new AjaxResult().success(decrypt);
    }

    @RequestMapping(value = "/userRelation", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult userRelation(@RequestParam("userId") String userId,
                                   @RequestParam("type") String type) {
		UserRelationModel model = new UserRelationModel();
		model.setVersion("V2.0");
		model.setMerchant(jdPayBean.getMchId());
		model.setUserId(userId);

        String reqXml = model.genReqXml(jdPayBean.getRsaPrivateKey(), jdPayBean.getDesKey(), "V2.0", jdPayBean.getMchId());

        String result = null;

        if ("get".equals(type)) {
            result = JdPayApi.getUserRelation(reqXml);
        } else {
            result = JdPayApi.cancelUserRelation(reqXml);
        }

        log.info(result);

        // 解析响应的 xml 数据
        Map<String, String> map = JdPayKit.parseResp(result);

        String code = map.get("code");
        String encrypt = map.get("encrypt");
        if (!"000000".equals(code)) {
            String desc = map.get("desc");
            return new AjaxResult().addError(desc);
        }
        // 解密并验证签名
        String decrypt = JdPayKit.decrypt(jdPayBean.getRsaPublicKey(), jdPayBean.getDesKey(), encrypt);
        log.info("decrypt>" + decrypt);
        return new AjaxResult().success(decrypt);
    }

    @RequestMapping(value = "/refund", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult refund(@RequestParam("amount") String amount,
                             @RequestParam("oTradeNum") String oTradeNum,
                             @RequestParam("tradeNum") String tradeNum) {
		RefundModel model = new RefundModel();
		model.setVersion("V2.0");
		model.setMerchant(jdPayBean.getMchId());
		model.setTradeNum(tradeNum);
		model.setoTradeNum(oTradeNum);
		model.setAmount(amount);

		model.setCurrency("CNY");

        String reqXml = model.genReqXml(jdPayBean.getRsaPrivateKey(), jdPayBean.getDesKey(), "V2.0", jdPayBean.getMchId());
        String queryResult = JdPayApi.refund(reqXml);
        log.info("queryResult:" + queryResult);

        // 解析响应的 xml 数据
        Map<String, String> map = JdPayKit.parseResp(queryResult);

        String code = map.get("code");
        String encrypt = map.get("encrypt");
        if (!"000000".equals(code)) {
            String desc = map.get("desc");
            return new AjaxResult().addError(desc);
        }
        // 解密并验证签名
        String decrypt = JdPayKit.decrypt(jdPayBean.getRsaPublicKey(), jdPayBean.getDesKey(), encrypt);

        log.info("decrypt>" + decrypt);

        return new AjaxResult().success(decrypt);
    }


    @RequestMapping(value = "/queryBaiTiaoFq", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult queryBaiTiaoFq(@RequestParam("amount") String amount) {
		QueryBaiTiaoFqModel model = new QueryBaiTiaoFqModel();
		model.setVersion("V2.0");
		model.setMerchant(jdPayBean.getMchId());
		model.setTradeNum(System.currentTimeMillis() + "");
		model.setAmount(amount);


		String reqXml = model.genReqXml(jdPayBean.getRsaPrivateKey(), jdPayBean.getDesKey(), "V2.0", jdPayBean.getMchId());

        String baiTiaoResult = JdPayApi.queryBaiTiaoFq(reqXml);

        log.info(baiTiaoResult);

        // 解析响应的 xml 数据
        Map<String, String> map = JdPayKit.parseResp(baiTiaoResult);

        String code = map.get("code");
        String encrypt = map.get("encrypt");
        if (!"000000".equals(code)) {
            String desc = map.get("desc");
            return new AjaxResult().addError(desc);
        }
        // 解密并验证签名
        String decrypt = JdPayKit.decrypt(jdPayBean.getRsaPublicKey(), jdPayBean.getDesKey(), encrypt);
        log.info("decrypt>" + decrypt);
        return new AjaxResult().success(decrypt);
    }
}
