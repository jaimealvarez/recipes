package guru.springframework.recipes.services;

import guru.springframework.recipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
