package utils;

import com.recipes.ejb.CategoryFacade;
import com.recipes.ejb.IngredientFacade;
import com.recipes.ejb.LevelFacade;
import com.recipes.ejb.UserFacade;
import com.recipes.entities.Category;
import com.recipes.entities.Ingredient;
import com.recipes.entities.Recipe;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Named
public class RecipeSupport {
    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private IngredientFacade ingredientFacade;
    @EJB
    private LevelFacade levelFacade;
    @EJB
    private UserFacade userFacade;

    public Recipe getRecipe(HttpServletRequest request) {
        return Recipe.builder()
                .name(request.getParameter("name"))
                .description(request.getParameter("description"))
                .preparation(request.getParameter("preparation"))
                .bPublic(nonNull(request.getParameter("public")))
                .userId(userFacade.findByUsername(request.getParameter("username")))
                .levelId(levelFacade.findByName(request.getParameter("level")))
                .ingredientList(getIngredients(request.getParameter("ingredients")))
                .categoryList(getCategories(request.getParameter("categories")))
                .build();
    }

    private List<Category> getCategories(String categories) {
        return toEntities(categories, this::getCategory);
    }

    private List<Ingredient> getIngredients(String ingredients) {
        return toEntities(ingredients, this::getIngredient);
    }

    private <T> List<T> toEntities(String string, Function<String, T> mapper) {
        return Optional.ofNullable(string)
                .map(s -> s.split(","))
                .map(Arrays::stream)
                .map(ingredientsStream -> ingredientsStream.filter(StringUtils::isNotBlank).map(mapper).collect(toList()))
                .orElse(null);
    }

    private Category getCategory(String category) {
        return Optional.ofNullable(categoryFacade.findByName(category))
                .orElseGet(() -> {
                    categoryFacade.create(Category.builder().name(category).build());
                    return categoryFacade.findByName(category);
                });
    }

    private Ingredient getIngredient(String ingredient) {
        return Optional.ofNullable(ingredientFacade.findByName(ingredient))
                .orElseGet(() -> {
                    ingredientFacade.create(Ingredient.builder().name(ingredient).build());
                    return ingredientFacade.findByName(ingredient);
                });
    }
}
