package com.dicicip.starter.util.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class QueryHelpers {

    public static Class<?> getData(Map requestParams, Class<?> outputObject, DB db) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println("getData() ==> " + objectMapper.writeValueAsString(db.select("SELECT * FROM public.users")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return outputObject;
    }

}
