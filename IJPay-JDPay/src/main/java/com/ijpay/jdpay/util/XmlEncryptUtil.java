package com.ijpay.jdpay.util;

import com.ijpay.core.kit.CypherKit;
import org.apache.commons.lang3.StringUtils;


public class XmlEncryptUtil {
    private static String XML_JDPAY_END = "</jdpay>";
    private static String XML_SIGN_START = "<sign>";
    private static String XML_SIGN_END = "</sign>";
    private static String SIGN = "sign";
    private static String RESULT = "result";


    public static String encrypt(String rsaPrivateKey, String strDesKey, String genSignStr) {
        String encrypt = null;
        if (StringUtils.isNotEmpty(rsaPrivateKey) && StringUtils.isNotEmpty(strDesKey) && StringUtils.isNotEmpty(genSignStr)) {

            try {
                genSignStr = JdPayXmlUtil.addXmlHeadAndElJdPay(genSignStr);

                genSignStr = JdPayXmlUtil.fomatXmlStr(genSignStr);

                genSignStr = JdPayXmlUtil.delXmlElm(genSignStr, SIGN);

                String sign = VerifySignatureUtl.encryptMerchant(genSignStr, rsaPrivateKey);
                String data = genSignStr.substring(0, genSignStr.length() - XML_JDPAY_END.length()) + XML_SIGN_START + sign + XML_SIGN_END + XML_JDPAY_END;

                encrypt = CypherKit.encode(ThreeDesUtil.encrypt2HexStr(CypherKit.decodeToBytes(strDesKey), data).getBytes("UTF-8"));
            } catch (Exception e) {
                throw new RuntimeException("signature failed");
            }
        }
        return encrypt;
    }

    public static String decrypt(String rsaPubKey, String strDesKey, String encrypt) {
        String reqBody = "";

        try {
            reqBody = ThreeDesUtil.decrypt4HexStr(CypherKit.decodeToBytes(strDesKey), CypherKit.decode(encrypt));

            String inputSign = JdPayXmlUtil.getXmlElm(reqBody, SIGN);

            reqBody = JdPayXmlUtil.addXmlHead(reqBody);

            reqBody = JdPayXmlUtil.fomatXmlStr(reqBody);

            String genSignStr = JdPayXmlUtil.delXmlElm(reqBody, SIGN);

            boolean verifyResult = VerifySignatureUtl.decryptMerchant(genSignStr, inputSign, rsaPubKey);
            if (!verifyResult) {
                throw new RuntimeException("verify signature failed");
            }
        } catch (Exception e) {
            throw new RuntimeException("data decrypt failed");
        }
        return reqBody;
    }


    public static String decrypt(String rsaPubKey, String reqBody) {
        String req = "";

        try {
            String inputSign = JdPayXmlUtil.getXmlElm(reqBody, SIGN);

            req = JdPayXmlUtil.addXmlHead(reqBody);

            req = JdPayXmlUtil.fomatXmlStr(req);

            String genSignStr = JdPayXmlUtil.delXmlElm(req, SIGN);

            boolean verifyResult = VerifySignatureUtl.decryptMerchant(genSignStr, inputSign, rsaPubKey);
            if (!verifyResult) {
                throw new RuntimeException("verify signature failed");
            }
        } catch (Exception e) {
            throw new RuntimeException("data decrypt failed");
        }
        return req;
    }
}
