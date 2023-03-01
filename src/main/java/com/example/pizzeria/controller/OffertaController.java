package com.example.pizzeria.controller;

import com.example.pizzeria.model.Offerta;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.repository.OffertaRepository;
import com.example.pizzeria.repository.PizzaRepository;
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

import jakarta.validation.Valid;


@Controller
@RequestMapping("/offerta")
public class OffertaController {
	
	
	@Autowired
	PizzaRepository pizzarep;
	
	@Autowired
	OffertaRepository offertarep;
	
	
	@GetMapping("/create")		
	public String create(
		@RequestParam(name="id", required = true) Integer id,
		Model model) throws Exception {
		
		Offerta offerta =new Offerta();	
			
		
		Pizza pizza = pizzarep.getReferenceById(id);
		offerta.setPizza(pizza);
		
		model.addAttribute("offerta", offerta);
		
		return "createOfferta"; 
	}
	
	@PostMapping("/create")  	
	public String store(
		@Valid @ModelAttribute("offerta") Offerta formOfferta, 
		BindingResult bindingResult,
		Model model){
		
		if (bindingResult.hasErrors())
			return "createOfferta"; 
		
		Pizza pizza =formOfferta.getPizza();
		offertarep.save(formOfferta);
		
		return "redirect:/"; 
		
	}
	
	@GetMapping("/edit/{id}") 
	public String edit(@PathVariable("id") Integer id,Model model ) {
		
		Offerta offerta;
		offerta = offertarep.getReferenceById(id);
		model.addAttribute("offerta", offerta);
		
		return "editOfferta"; 
	}
	
	@PostMapping("/edit/{id}")
	public String update(
			@Valid @ModelAttribute Offerta formPizza,
			BindingResult bindingResult,
			Model model) {
		if(bindingResult.hasErrors())
			return "editOfferta"; 
		
		offertarep.save(formPizza);
		
		return "redirect:/pizza";
		
	}	
}