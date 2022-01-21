package com.yj.monitor.api.util;


import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author gaolei
 */
public class Rc4Util {

    /**
     * DES加密算法生成密钥
     */
    private static final String ENCRYPT_KEY = "CR2hd0freFwcGEoIAltXHabejlT+iBnJ4TrSJcf3ri0=";

    /**
     * RC4加密，将加密后的数据进行哈希
     *
     * @param data     需要加密的数据
     * @param key      加密密钥
     * @param chartSet 编码方式
     * @return 返回加密后的数据
     */
    private static String encryptString(String data, String key, String chartSet) {
        if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
            return null;
        }
        try {
            return bytesToHex(encryptByte(data, key, chartSet));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("数据加密异常", e);
        }
    }

    /**
     * RC4加密，将加密后的字节数据
     *
     * @param data     需要加密的数据
     * @param key      加密密钥
     * @param chartSet 编码方式
     * @return 返回加密后的数据
     * @throws UnsupportedEncodingException 不支持的编码异常
     */
    private static byte[] encryptByte(String data, String key, String chartSet) throws UnsupportedEncodingException {
        if (data == null || key == null) {
            return null;
        }
        byte[] bData;
        if (chartSet == null || chartSet.isEmpty()) {
            bData = data.getBytes();
        } else {
            bData = data.getBytes(chartSet);
        }
        return rC4Base(bData, key);
    }

    /**
     * RC4解密
     *
     * @param data     需要解密的数据
     * @param key      加密密钥
     * @param chartSet 编码方式
     * @return 返回解密后的数据
     */
    public static String decryptRc4(String data, String key, String chartSet) {
        if (data == null || key == null) {
            return null;
        }
        try {
            return new String(rC4Base(hexToByte(data), key), chartSet);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("数据解密异常", e);
        }
    }

    /**
     * RC4加密初始化密钥
     *
     * @param aKey 密钥
     * @return 密钥处理后的字节数组
     */
    private static byte[] initKey(String aKey) {
        byte[] bKey = aKey.getBytes();
        byte[] state = new byte[256];

        for (int i = 0; i < 256; i++) {
            state[i] = (byte) i;
        }
        int index1 = 0;
        int index2 = 0;
        if (bKey.length == 0) {
            return null;
        }
        for (int i = 0; i < 256; i++) {
            index2 = ((bKey[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;
            byte tmp = state[i];
            state[i] = state[index2];
            state[index2] = tmp;
            index1 = (index1 + 1) % bKey.length;
        }
        return state;
    }


    /**
     * 字节数组转十六进制
     *
     * @param bytes 字节数组
     * @return 十六进制串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder buffer = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() < 2) {
                buffer.append(0);
            }
            buffer.append(hex);
        }
        return buffer.toString();
    }

    /**
     * 十六进制转字节数组
     *
     * @param inHex 十六进制串
     * @return 字节数组
     */
    private static byte[] hexToByte(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = (byte) Integer.parseInt(inHex.substring(i, i + 2), 16);
            j++;
        }
        return result;
    }

    /**
     * RC4解密
     *
     * @param input 字节数组
     * @param mKkey 密钥
     * @return 解密
     */
    private static byte[] rC4Base(byte[] input, String mKkey) {
        int x = 0;
        int y = 0;
        byte[] key = initKey(mKkey);
        int xorIndex;
        byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            x = (x + 1) & 0xff;
            assert key != null;
            y = ((key[x] & 0xff) + y) & 0xff;
            byte tmp = key[x];
            key[x] = key[y];
            key[y] = tmp;
            xorIndex = ((key[x] & 0xff) + (key[y] & 0xff)) & 0xff;
            result[i] = (byte) (input[i] ^ key[xorIndex]);
        }
        return result;
    }

    public static String encrypt(String data) {
        return encryptString(data, ENCRYPT_KEY, StandardCharsets.UTF_8.name());
    }

    public static String encrypt(String data, String key) {
        return encryptString(data, key, StandardCharsets.UTF_8.name());
    }

    public static String encrypt(String data, String key, String chartSet) {
        return encryptString(data, key, chartSet);
    }

    public static String decrypt(String data) {
        return decryptRc4(data, ENCRYPT_KEY, StandardCharsets.UTF_8.name());
    }

    public static String decrypt(String data, String key) {
        return decryptRc4(data, key, StandardCharsets.UTF_8.name());
    }

    public static String decrypt(String data, String key, String chartSet) {
        return decryptRc4(data, key, chartSet);
    }

}