package com.ijpay.jdpay.util;

import com.ijpay.core.kit.CypherKit;

import java.io.UnsupportedEncodingException;


public class VerifySignatureUtl {

    public static String encryptMerchant(String sourceSignString, String rsaPriKey) {
        String result = "";

        try {
            String sha256SourceSignString = SHAUtil.Encrypt(sourceSignString, null);

            byte[] newsks = RSACoder.encryptByPrivateKey(sha256SourceSignString.getBytes("UTF-8"), rsaPriKey);
            result = CypherKit.encode(newsks);
        } catch (Exception e) {
            throw new RuntimeException("verify signature failed.", e);
        }
        return result;
    }


    public static boolean decryptMerchant(String strSourceData, String signData, String rsaPubKey) {
        if (signData == null || signData.isEmpty()) {
            throw new IllegalArgumentException("Argument 'signData' is null or empty");
        }
        if (rsaPubKey == null || rsaPubKey.isEmpty()) {
            throw new IllegalArgumentException("Argument 'key' is null or empty");
        }

        try {
            String sha256SourceSignString = SHAUtil.Encrypt(strSourceData, null);

            byte[] signByte = CypherKit.decodeToBytes(signData);

            byte[] decryptArr = RSACoder.decryptByPublicKey(signByte, rsaPubKey);

            String decryptStr = RSACoder.bytesToString(decryptArr);
            if (sha256SourceSignString.equals(decryptStr)) {
                return true;
            } else {
                throw new RuntimeException("Signature verification failed.");
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("verify signature failed.", e);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("verify signature failed.", e);
        }
    }
}
