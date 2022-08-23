package org.igogrzeg.recipes.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.basic.BasicEntity;
import org.igogrzeg.recipes.recipe.RecipeEntity;
import org.igogrzeg.recipes.user.valueObjects.EmailValidator;
import org.igogrzeg.recipes.user.valueObjects.PasswordValidator;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class UserEntity extends BasicEntity {

    @Embedded
    private EmailValidator email;
    @Embedded
    private PasswordValidator password;

    @OneToMany
    private List<RecipeEntity> recipes;

    public void changePassword(String changedPassword){ this.password = new PasswordValidator(changedPassword); }

}
