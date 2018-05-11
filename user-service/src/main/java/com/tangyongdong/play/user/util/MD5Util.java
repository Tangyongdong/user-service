package com.tangyongdong.play.user.util;

import com.tangyongdong.base.common.exception.BusinessException;
import com.tangyongdong.play.user.constant.UserConstant;
import com.tangyongdong.play.user.config.BusinessErrorCode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author tangyongdong
 * @create 2018-05-07 12:47
 */
public class MD5Util {

    /**
     * 获取token
     *
     * @param userId
     * @return
     */
    public static String getToken(String userId){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(userId.getBytes());
            return addSalt(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BusinessException(BusinessErrorCode.MD5_SIGN_ERROR);
        }
    }

    /**
     * 加密
     *
     * @param token
     * @return
     */
    public static String sign(String token, String dateStr) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(token.concat(UserConstant.MD5_KEY).concat(dateStr).getBytes());
            return addSalt(digest).toUpperCase();
        } catch (NoSuchAlgorithmException   e) {
            e.printStackTrace();
            throw new BusinessException(BusinessErrorCode.MD5_SIGN_ERROR);
        }
    }

    /**
     * 验签
     *
     * @param sign
     * @param token
     * @return
     */
    public static Boolean verify(String sign,String token,String dateStr){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(token.concat(UserConstant.MD5_KEY).concat(dateStr).getBytes());
            String newSign = addSalt(digest);
            return sign.equalsIgnoreCase(newSign);
        } catch (NoSuchAlgorithmException   e) {
            e.printStackTrace();
            throw new BusinessException(BusinessErrorCode.MD5_VERIFY_ERROR);
        }
    }

    private static String addSalt(byte[] digest){
        StringBuffer buffer = new StringBuffer();
        for (byte b : digest) {
            int number = b & 0xff;
            String str = Integer.toHexString(number);
            if (str.length() == 1) {
                buffer.append("0");
            }
            buffer.append(str);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        String token = getToken("178206638316978176");
        System.out.println(token);//9c3ee003863f4362284797f17b561e11

        String dateStr = DateUtil.date2Long(new Date());
        String accessToken = sign(token, dateStr);
        System.out.println(accessToken);//F755741BF9DB382BC370DD85199C17EB

        Boolean verify = verify("29CACFB98A719C7EBE0789A613CC5FD4", "65cc76a5faf0222e0aa675d460de839f", "2018-05-08 11:29:56");
        System.out.println(verify);//true
    }

}
