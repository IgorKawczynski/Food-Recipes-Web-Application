package org.igogrzeg.recipes.recipe;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.ingredient.IngredientRepository;
import org.igogrzeg.recipes.recipe.dtos.RecipeRequestDto;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.igogrzeg.recipes.recipe.valueObjects.InformationValidator;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;
import org.igogrzeg.recipes.recipe.valueObjects.PreparationTimeValidator;
import org.igogrzeg.recipes.user.UserMapper;
import org.igogrzeg.recipes.user.UserRepository;
import org.igogrzeg.recipes.user.valueObjects.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    @Autowired
    public RecipeMapper(UserMapper userMapper, UserRepository userRepository, IngredientRepository ingredientRepository){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
    }

    // to user probably
//    public RecipeEntity recipeRequestDtoToRecipeEntity (RecipeRequestDto recipeRequestDto) {
//        return RecipeEntity
//                .builder()
//                .userId(userRepository.findUserByEmail(new EmailValidator(recipeRequestDto.email())))
//                .ingredients(ingredientRepository.findAll())
//                .name(new NameValidator(recipeRequestDto.name()))
//                .description(new InformationValidator(recipeRequestDto.description()))
//                .instruction(new InformationValidator(recipeRequestDto.instruction()))
//                .preparationTime(new PreparationTimeValidator(recipeRequestDto.preparationTime()))
//                .difficulty(recipeRequestDto.difficulty())
//                .mealType(recipeRequestDto.mealType())
//                .cuisineType(recipeRequestDto.cuisineType())
//                .build();
//
//    }

    public RecipeRequestDto recipeEntityToRecipeRequestDto (RecipeEntity recipeEntity) {
        return RecipeRequestDto
                .builder()
                .email(recipeEntity.getUserId().getEmail().toString())
                .name(recipeEntity.getName().toString())
                .description(recipeEntity.getDescription().toString())
                .instruction(recipeEntity.getInstruction().toString())
                .preparationTime(recipeEntity.getPreparationTime().toInteger())
                .difficulty(recipeEntity.getDifficulty())
                .mealType(recipeEntity.getMealType())
                .cuisineType(recipeEntity.getCuisineType())
                .build();

    }

    public RecipeResponseDto recipeEntityToRecipeResponseDto (RecipeEntity recipeEntity) {
        String author = userMapper.setNickname(recipeEntity.getUserId().getEmail());
        return RecipeResponseDto
                .builder()
                .author(author)
                .name(recipeEntity.getName().toString())
                .description(recipeEntity.getDescription().toString())
                .instruction(recipeEntity.getInstruction().toString())
                .preparationTime(recipeEntity.getPreparationTime().toInteger())
                .difficulty(recipeEntity.getDifficulty())
                .mealType(recipeEntity.getMealType())
                .cuisineType(recipeEntity.getCuisineType())
                .build();


    }

    public List<RecipeResponseDto> recipeEntityListToRecipeResponseListDto (List<RecipeEntity> recipeEntityList) {
        return recipeEntityList.stream().map(this::recipeEntityToRecipeResponseDto).collect(Collectors.toList());
    }
}
