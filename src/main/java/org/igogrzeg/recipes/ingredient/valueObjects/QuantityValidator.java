package org.igogrzeg.recipes.ingredient.valueObjects;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class QuantityValidator {

    @Column
    private Integer quantity;

    public QuantityValidator(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer toInteger() {
        return quantity;
    }
}
