package org.igogrzeg.recipes.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.basic.BasicEntity;
import org.igogrzeg.recipes.recipe.RecipeEntity;
import org.igogrzeg.recipes.user.valueObjects.EmailValidator;
import org.igogrzeg.recipes.user.valueObjects.PasswordValidator;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BasicEntity {

    @Embedded
    private EmailValidator email;
    @Embedded
    private PasswordValidator password;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<RecipeEntity> recipes;

    @Builder
    public UserEntity(EmailValidator email, PasswordValidator password, List<RecipeEntity> recipes) {
        this.email = email;
        this.password = password;
        this.recipes = recipes;
    }

    public void changePassword(String changedPassword){ this.password = new PasswordValidator(changedPassword); }
    public void changeEmail(String changedEmail){ this.email = new EmailValidator(changedEmail); }

}
