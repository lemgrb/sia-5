package com.lemsst.sia5.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lemsst.sia5.data.Ingredient;
import com.lemsst.sia5.data.Ingredient.Type;
import com.lemsst.sia5.data.Taco;
import com.lemsst.sia5.repository.IngredientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("/order")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	/**
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP), new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE), new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

		Type[] types = Ingredient.Type.values();

		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	
	@GetMapping
	public String showDesignForm(Model model) {
		model.addAttribute("design", new Taco());
		return "design";
	}
	**/
	
	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
		Type[] types = Ingredient.Type.values();
		
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		
		model.addAttribute("design", new Taco());
		
		return "design";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
		return ingredients.stream().filter(i -> i.getType().equals(type)).collect(Collectors.toList());
	}
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
		log.info("The design is : " + design);
		
		if(errors.hasErrors()) {
			log.info("Process design errors: " + errors);
			return "design";
		}
		// Save the taco design
		log.info("Saving the design: " + design);
		
		return "redirect:/orders/current";
	}

}
