package org.igogrzeg.recipes.basic;

import java.util.HashMap;
import java.util.Map;

public class ErrorsMapDto {

    Map<String, Exception> errors;

    public ErrorsMapDto(Map<String, Exception> errors) {
        this.errors = errors;
    }

    public boolean isEmpty(){
        return errors.isEmpty();
    }

    public void add(String fieldName, Exception exception){
        errors.put(fieldName, exception);
    }

    public Map<String, String> getErrors() {
        var fieldNameAndExceptionMessageMap = new HashMap<String, String>();
        this.errors.forEach((fieldName, exception) -> {
            fieldNameAndExceptionMessageMap.put(fieldName, exception.getMessage());
        });
        return fieldNameAndExceptionMessageMap;
    }

}


