package org.igogrzeg.recipes.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.igogrzeg.recipes.basic.BasicEntity;
import org.igogrzeg.recipes.favorite_recipe.FavoriteRecipeEntity;
import org.igogrzeg.recipes.recipe.RecipeEntity;
import org.igogrzeg.recipes.user.valueObjects.EmailValidator;
import org.igogrzeg.recipes.user.valueObjects.PasswordValidator;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UserEntity extends BasicEntity {

    @Embedded
    @Column(unique = true)
    private EmailValidator email;
    @Embedded
    private PasswordValidator password;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RecipeEntity> recipes;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<FavoriteRecipeEntity> favoriteRecipes;

    @Builder
    public UserEntity(EmailValidator email, PasswordValidator password) {
        this.email = email;
        this.password = password;
    }

    public void changePassword(String changedPassword){ this.password = new PasswordValidator(changedPassword); }
    public void changeEmail(String changedEmail){ this.email = new EmailValidator(changedEmail); }

}
