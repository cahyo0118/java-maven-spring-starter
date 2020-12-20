package com.dicicip.starter.util.validator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Validator<D> {

    private D requestBody;
    private List<ValidatorItem> validatorItems;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private HashMap<String, Object> errorsList = new HashMap<>();

    public Validator(D requestBody, ValidatorItem... validatorItems) {
        this.requestBody = requestBody;
        this.validatorItems = Arrays.asList(validatorItems);
    }

    public boolean valid() {

        for (ValidatorItem validatorItem : this.validatorItems) {

            HashMap<String, Object> requestBody = objectMapper.convertValue(this.requestBody, HashMap.class);

            if (requestBody.get(validatorItem.name) == null || requestBody.get(validatorItem.name).equals("")) {
                this.errorsList.put(validatorItem.name, new ArrayList<>());
            }

        }

        return this.errorsList.size() < 1;
    }

    public HashMap<String, Object> getErrorsList() {

        return this.errorsList;

    }


}
