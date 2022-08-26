package org.igogrzeg.recipes.basic;

import java.util.Map;

public class ErrorsMapDto {

    Map<String, String> errors;

    private String errorMessage; //zawartosc errora wyswietlana dla danego fieldName
    private String fieldName; //nazwy pola pod ktorymi wyswietlane beda errory

    public ErrorsMapDto(Map<String, String> errors) {
        this.errors = errors;
    }

    public boolean isListOfErrorsEmpty(){
        return this.errors.size()==0;
    }

    public void add(String errorMessage, String fieldName){
        errors.put(fieldName, errorMessage);
    }

}
