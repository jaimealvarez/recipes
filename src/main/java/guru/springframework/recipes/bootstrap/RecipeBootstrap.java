package guru.springframework.recipes.bootstrap;

import guru.springframework.recipes.domain.*;
import guru.springframework.recipes.repositories.CategoryRepository;
import guru.springframework.recipes.repositories.RecipeRepository;
import guru.springframework.recipes.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                           RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        loadCategories();
        loadUom();

//        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");
    }

    private void loadCategories() {
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setDescription("Italian");
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setDescription("Mexican");
        categoryRepository.save(cat3);

        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepository.save(cat4);
    }

    private void loadUom() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription("Pint");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescription("Ounce");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescription("Each");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setDescription("Dash");
        unitOfMeasureRepository.save(uom7);
    }

    private Category getCategory(String description) {
        return categoryRepository.findByDescription(description)
                .orElseThrow(() -> new RuntimeException("Category " + description + " not found"));
    }

    private UnitOfMeasure getUnitOfMeasure(String description) {
        return unitOfMeasureRepository.findByDescription(description)
                .orElseThrow(() -> new RuntimeException("UOM " + description + " not found"));
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        UnitOfMeasure eachUom = getUnitOfMeasure("Each");
        UnitOfMeasure tablespoonUom = getUnitOfMeasure("Tablespoon");
        UnitOfMeasure teaspoonUom = getUnitOfMeasure("Teaspoon");
        UnitOfMeasure dashUom = getUnitOfMeasure("Dash");
        UnitOfMeasure pintUom = getUnitOfMeasure("Pint");
        UnitOfMeasure cupUom = getUnitOfMeasure("Cup");

        Category americanCategory = getCategory("American");
        Category mexicanCategory = getCategory("Mexican");

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setServings(4);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections(
                "1 Cut avocado, remove flesh: Cut the avocados in half. " +
                        "Remove seed. " +
                        "Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                        "Place in a bowl." +
                        "\n" +
                        "2 Mash with a fork: Using a fork, roughly mash the avocado. " +
                        "(Don't overdo it! The guacamole should be a little chunky.)" +
                        "\n" +
                        "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. " +
                        "The acid in the lime juice will provide some balance to the richness of the avocado and " +
                        "will help delay the avocados from turning brown.\n" +
                        "\n" +
                        "Add the chopped onion, cilantro, black pepper, and chiles. " +
                        "Chili peppers vary individually in their hotness. " +
                        "So, start with a half of one chili pepper and add to the guacamole to your desired " +
                        "degree of hotness.\n" +
                        "\n" +
                        "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
                        "Start with this recipe and adjust to your taste.\n" +
                        "\n" +
                        "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole " +
                        "cover it and to prevent air reaching it. (The oxygen in the air causes oxidation " +
                        "which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                        "\n" +
                        "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, " +
                        "add it just before serving."
        );

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes(
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                        "\n" +
                        "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches " +
                        "in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries \n" +
                        "\n" +
                        "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of " +
                        "availability of other ingredients stop you from making guacamole.\n" +
                        "\n" +
                        "To extend a limited supply of avocados, add either sour cream or cottage cheese to your " +
                        "guacamole dip. Purists may be horrified, but so what? It tastes great.");
        guacamole.setNotes(guacamoleNotes);

        guacamole.addIngredient(new Ingredient("Avocado", new BigDecimal(2), eachUom));
        guacamole.addIngredient(new Ingredient("Kosher Salt", new BigDecimal(0.5), teaspoonUom));
        guacamole.addIngredient(new Ingredient("Fresh lime juice", new BigDecimal(1), tablespoonUom));
        guacamole.addIngredient(new Ingredient("Minced red onion", new BigDecimal(2), tablespoonUom));
        guacamole.addIngredient(new Ingredient("Serrano chile", new BigDecimal(2), eachUom));
        guacamole.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUom));
        guacamole.addIngredient(new Ingredient("Freshly grated black pepper", new BigDecimal(1), dashUom));
        guacamole.addIngredient(new Ingredient("Ripe tomato", new BigDecimal(0.5), eachUom));

        guacamole.getCategories().add(americanCategory);
        guacamole.getCategories().add(mexicanCategory);

        recipes.add(guacamole);

        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setPrepTime(20);
        tacos.setCookTime(15);
        tacos.setDifficulty(Difficulty.MODERATE);
        tacos.setServings(6);
        tacos.setSource("Simply Recipes");
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setDirections(
                "1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                        "\n" +
                        "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, " +
                        "oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and " +
                        "olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                        "\n" +
                        "Set aside to marinate while the grill heats and you prepare the rest of the toppings." +
                        "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a " +
                        "thermometer inserted into the thickest part of the meat registers 165F. " +
                        "Transfer to a plate and rest for 5 minutes.\n" +
                        "\n" +
                        "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over " +
                        "medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, " +
                        "turn it with tongs and heat for a few seconds on the other side.\n" +
                        "\n" +
                        "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                        "\n" +
                        "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small " +
                        "handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, " +
                        "and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes(
                "We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                        "\n" +
                        "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy " +
                        "dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma " +
                        "of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                        "\n" +
                        "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                        "\n" +
                        "\n" +
                        "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, " +
                        "cumin, and sweet orange juice while the grill is heating. " +
                        "You can also use this time to prepare the taco toppings.\n" +
                        "\n" +
                        "Grill the chicken, then let it rest while you warm the tortillas. " +
                        "Now you are ready to assemble the tacos and dig in. " +
                        "The whole meal comes together in about 30 minutes!");

        tacos.setNotes(tacosNotes);

        tacos.addIngredient(new Ingredient("Dried oregano", new BigDecimal(1), teaspoonUom));
        tacos.addIngredient(new Ingredient("Ancho chili powder", new BigDecimal(2), tablespoonUom));
        tacos.addIngredient(new Ingredient("Dried cumin", new BigDecimal(1), teaspoonUom));
        tacos.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoonUom));
        tacos.addIngredient(new Ingredient("Salt", new BigDecimal(0.5), teaspoonUom));
        tacos.addIngredient(new Ingredient("Clove garlic, finely chopped", new BigDecimal(1), eachUom));
        tacos.addIngredient(new Ingredient("Finely grated orange zest", new BigDecimal(1), tablespoonUom));
        tacos.addIngredient(new Ingredient("Fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom));
        tacos.addIngredient(new Ingredient("Olive oil", new BigDecimal(2), tablespoonUom));
        tacos.addIngredient(new Ingredient("Skinless, boneless chicken thigh", new BigDecimal(4), eachUom));
        tacos.addIngredient(new Ingredient("Corn tortilla", new BigDecimal(8), eachUom));
        tacos.addIngredient(new Ingredient("Packed baby arugula", new BigDecimal(3), cupUom));
        tacos.addIngredient(new Ingredient("Ripe avocado, sliced", new BigDecimal(2), eachUom));
        tacos.addIngredient(new Ingredient("Radish, thinly sliced", new BigDecimal(4), eachUom));
        tacos.addIngredient(new Ingredient("Cherry tomatoes, halved", new BigDecimal(0.5), pintUom));
        tacos.addIngredient(new Ingredient("Red onion, thinly sliced", new BigDecimal(0.25), eachUom));
        tacos.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(1), eachUom));
        tacos.addIngredient(new Ingredient("Sour cream thinned with 1/4 cup milk", new BigDecimal(0.5), cupUom));
        tacos.addIngredient(new Ingredient("Lime, cut into wedges", new BigDecimal(1), eachUom));

        tacos.getCategories().add(mexicanCategory);

        recipes.add(tacos);

        return recipes;
    }
}
