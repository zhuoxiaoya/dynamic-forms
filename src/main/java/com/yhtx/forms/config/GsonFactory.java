package com.yhtx.forms.config;

import com.google.gson.*;
import com.yhtx.forms.util.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * gson序列化
 */
public class GsonFactory {

    private final static GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat(DateUtil.DATE_TIME)
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context)
                    -> new JsonPrimitive(src.format(DateTimeFormatter.ofPattern(DateUtil.DATE_TIME))))
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context)
                    -> new JsonPrimitive(src.format(DateTimeFormatter.ofPattern(DateUtil.DATE))))
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext)
                    -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(DateUtil.DATE_TIME)))
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext)
                    -> LocalDate.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(DateUtil.DATE)))
            .setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .serializeNulls().setExclusionStrategies(new EruptGsonExclusionStrategies());

    private static final Gson gson = gsonBuilder.create();

    public static Gson getGson() {
        return gson;
    }

    public static GsonBuilder getGsonBuilder() {
        return gsonBuilder;
    }

    private GsonFactory() {
    }
}
