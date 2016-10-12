package com.scd.batch.common.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class CodecUtils {

    /**
     * Encode Hex
     */
    public static char[] encodeHex(byte[] data) {
        return Hex.encodeHex(data);
    }

    public static byte[] decodeHex(char[] data) {
        try {
            return Hex.decodeHex(data);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encode/Decode Binary
     */
    public static byte[] encodeBinary(byte[] data) {
        return new BinaryCodec().encode(data);
    }

    public static byte[] decodeBinary(byte[] data) {
        return new BinaryCodec().decode(data);
    }

    /**
     * Encode/Decode Base64
     */
    public static byte[] encodeBase64(byte[] data, boolean chunked) {
        return Base64.encodeBase64(data, chunked);
    }

    public static byte[] decodeBase64(byte[] data) {
        return Base64.decodeBase64(data);
    }

    /**
     * MD5
     */
    public static String md5Hex(String str) {
        return md5Hex(str, "UTF-8");
    }

    public static String md5Hex(String str, String charsetName) {
        char[] md5chars = encodeHex(md5(getBytes(str, charsetName)));
        return new String(md5chars);
    }
    
    public static char[] md5Hex(InputStream is) throws IOException {
        return encodeHex(md5(is));
    }

    public static byte[] md5(byte[] data) {
        return getMd5Digest().digest(data);
    }

    public static byte[] md5(InputStream is) throws IOException {
        return digest(getMd5Digest(), is);
    }

    /**
     * SHA
     */
    public static byte[] sha(byte[] data) {
        return getShaDigest().digest(data);
    }

    public static byte[] sha(InputStream is) throws IOException {
        return digest(getShaDigest(), is);
    }

    public static char[] shaHex(byte[] data) {
        return encodeHex(sha(data));
    }

    public static char[] shaHex(InputStream is) throws IOException {
        return encodeHex(sha(is));
    }

    /**
     * Get Digest
     */
    private static MessageDigest getMd5Digest() {
        return getDigest("MD5");
    }

    private static MessageDigest getShaDigest() {
        return getDigest("SHA");
    }

    private static MessageDigest getDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] digest(MessageDigest md, InputStream is) throws IOException {
        final byte[] buffer = new byte[4096];
        for (int count = is.read(buffer); count > 0; count = is.read(buffer)) {
            md.update(buffer, 0, count);
        }
        return md.digest();
    }

    private static byte[] getBytes(String str, String charsetName) {
        try {
            return str.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
