package org.igogrzeg.recipes.basic;

import lombok.NoArgsConstructor;

import java.util.List;

public class ErrorsListDto {

    List<String> errors;

    private String fieldName; //nazwy pola pod ktorymi wyswietlane beda errory

    public ErrorsListDto(List<String> errors) {
        this.errors = errors;
    }

    public boolean isListOfErrorsEmpty(){
        return this.errors.size()==0;
    }

    public void add(String error){
        errors.add(error);
    }

}
