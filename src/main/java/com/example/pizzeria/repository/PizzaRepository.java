package com.example.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pizzeria.model.Pizza;


public interface PizzaRepository extends JpaRepository<Pizza , Integer>{
	
	public List<Pizza> findByNameLike(String name);
    // public List<Pizza> findByNameOrDescription(String name, String description);

}
