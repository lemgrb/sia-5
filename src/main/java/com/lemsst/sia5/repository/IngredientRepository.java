package com.lemsst.sia5.repository;

import com.lemsst.sia5.data.Ingredient;

public interface IngredientRepository {

	Iterable<Ingredient> findAll();
	
	Ingredient findOne(String id);
	
	Ingredient save(Ingredient ingredient);
	
}
