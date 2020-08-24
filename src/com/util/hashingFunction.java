package com.util;

public class hashingFunction {

    //creating a byte value from the string
    public static byte[] createHash(String password) throws Exception{
        java.security.MessageDigest byteArray = null;
        byteArray = java.security.MessageDigest.getInstance("SHA-1");
        byteArray.reset();
        byteArray.update(password.getBytes());
        return byteArray.digest();
    }

    //converting the byte value to a hex string to be saved in the database
    public static String convertByteToString(byte[] byteArray){
        StringBuffer stringBuffer = new StringBuffer(byteArray.length *2);
        for (int i=0; i < byteArray.length; i++){
            int value = byteArray[i] & 0xff;
            if (value<16){
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(value));
        }
        return stringBuffer.toString().toUpperCase();
    }
}
