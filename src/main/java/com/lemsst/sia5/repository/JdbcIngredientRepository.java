package com.lemsst.sia5.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lemsst.sia5.data.Ingredient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Iterable<Ingredient> findAll() {
		log.info("The value of this.jdbc is null? " + this.jdbc);
		return jdbc.query("SELECT id, name, type FROM Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findById(String id) {
		return jdbc.queryForObject("SELECT id, name, type FROM ingredient WHERE id=?", this::mapRowToIngredient, id);
	}
	
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type"))
				);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update("INSERT INTO ingredient (id, name, type) values(?. ?. ?)",
		ingredient.getId(),
		ingredient.getName(),
		ingredient.getType().toString());
		return ingredient;
	}

}
