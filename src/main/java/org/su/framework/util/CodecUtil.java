package org.su.framework.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

public final class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);
    //将url编码
    public static String encodeURL(String source){
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        }catch (Exception e){
            LOGGER.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    //将url解码
    public static String decodeURL(String source){
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        }catch (Exception e){
            LOGGER.error("decode url failure", "UTF-8");
            throw new RuntimeException(e);
        }
        return target;
    }
    //md5加密
    public static String md5(String source){
        return DigestUtils.md5Hex(source);
    }

}
