package org.igogrzeg.recipes.recipe;

import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.basic.BasicEntity;
import org.igogrzeg.recipes.user.UserEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class RecipeEntity extends BasicEntity {

    @ManyToOne
    private UserEntity user;

}
