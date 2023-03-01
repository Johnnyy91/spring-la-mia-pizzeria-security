
package com.example.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pizzeria.model.Ingredienti;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.repository.IngredientiRepository;
import com.example.pizzeria.repository.PizzaRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

	@Autowired
	IngredientiRepository ingredientirep;
	

	@GetMapping("/create")		
	public String create(Model model) throws Exception {
		
		Ingredienti ingredienti =new Ingredienti();	
		
		model.addAttribute("ingredienti", ingredienti);
		
		return "createIngredienti"; 
	}
	
	
	@PostMapping("/create")  	
	public String store(
		@Valid @ModelAttribute("ingredienti") Ingredienti formIngredienti, 
		BindingResult bindingResult,
		Model model){
		
		if (bindingResult.hasErrors())
			return "createIngredienti"; 
	
		ingredientirep.save(formIngredienti);
		
		return "redirect:/"; 
		
	}
	
	
	@GetMapping("/edit/{id}") 
	public String edit(@PathVariable("id") Integer id,Model model ) {
		
		Ingredienti ingredienti;
		ingredienti = ingredientirep.getReferenceById(id);
		model.addAttribute("ingredienti", ingredienti);
		
		return "editIngredienti"; 
	}
	
	@PostMapping("/edit/{id}")
	public String update(
			@Valid @ModelAttribute Ingredienti formPizza,
			BindingResult bindingResult,
			Model model) {
		if(bindingResult.hasErrors())
			return "editIngredienti"; 
		
		ingredientirep.save(formPizza);
		
		return "redirect:/pizza";
		
	}	

}






