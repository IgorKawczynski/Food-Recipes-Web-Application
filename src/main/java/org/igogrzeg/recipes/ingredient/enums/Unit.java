package org.igogrzeg.recipes.ingredient.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Unit {

    LITRES, MILILITERS, GRAMS, KILOGRAMS, PIECE
//
//    LITRES("litres"),
//    MILILITERS("mililiters"),
//    GRAMS("grams"),
//    KILOGRAMS("kilograms"),
//    PIECE("piece");
//
//    private String code;
//
//    private Unit (String code) {
//        this.code = code;
//    }
//
//    public String getCode() {
//        return this.code;
//    }
//
//    @JsonCreator
//    public static Unit getUnitFromCode(String value) {
//
//        for(Unit u: Unit.values()) {
//            if( u.getCode().equals(value)){
//                return u;
//            }
//        }
//        return null;
//    }
}
