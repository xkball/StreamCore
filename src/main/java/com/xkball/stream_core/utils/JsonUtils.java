package com.xkball.stream_core.utils;

import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.StringWriter;

public class JsonUtils {
//    public static String autoCreateNewLine(String json){
//        String[] part1 = json.split("(?<=\\{)");
//        StringBuilder builder = new StringBuilder();
//        for(String s : part1){
//            builder.append(s);
//            builder.append("/n");
//        }
//        String[] part2 = builder.toString().split("(?<=})");
//        builder = new StringBuilder();
//        for(String s : part1){
//            builder.append(s);
//            builder.append("\n");
//        }
//        return builder.toString();
//    }
    public static String jsonToString(JsonElement element){
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            jsonWriter.setLenient(true);
            jsonWriter.setIndent("  ");
            Streams.write(element, jsonWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
