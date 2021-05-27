package com.rrc.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Created by liuchenlong on 2018/1/31.
 */
public class CommonSignUtil {


    /**
     * 获取body加密
     * @param bodyStr
     * @return
     */
    public static String MD5Base64(List<String> bodyStr) {
        String[] sortArr = new String[bodyStr.size()];
        bodyStr.toArray(sortArr);
        Arrays.sort(sortArr);//对数组进行排序
        String fmtstr = String.join("", sortArr);
        String md5str = null;//MD5Util.md5Hex(fmtstr);//首先MD5加密
        String sign = null;
        try {
            sign = Base64.getEncoder().encodeToString(md5str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

    public static String GetAuthorization(String method, String accept, String bodybase64, String contet_type){
        String fmtStr = String.format("%s%s%s%s", method, accept, bodybase64, contet_type);
        String authorization = SHA1(fmtStr);
        return authorization;
    }


    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes("UTF-8"));
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte message : messageDigest) {
                String shaHex = Integer.toHexString(message & 0xFF);
                if (shaHex.length() < 2)
                    hexString.append(0);

                hexString.append(shaHex);
            }
            return hexString.toString().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
