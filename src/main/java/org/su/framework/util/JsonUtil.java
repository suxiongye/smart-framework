package org.su.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//json工具类
public final class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    //将POJO转为json
    public static <T> String toJson(T object){
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(object);
        }catch (Exception e){
            LOGGER.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    //将json转为POJO
    public static <T> T fromJson(String json, Class<T> type){
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        }catch (Exception e){
            LOGGER.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
