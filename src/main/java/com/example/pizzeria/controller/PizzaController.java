package com.example.pizzeria.controller;

import java.util.List;
import java.util.Optional;
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
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.repository.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {
	
	
	@GetMapping()
	public String home(@RequestParam(name="keyword", required = false) String keyword, Model model) {
		
		List<Pizza> listPizza;
		
		if (keyword!=null && !keyword.isEmpty()) {
			
			 listPizza = pizzarepository.findByNameLike("%" + keyword + "%");
			
		} else {
			
			 listPizza = pizzarepository.findAll();
		}
		model.addAttribute("pizze", listPizza);
		return "index";
	}
	
	 @Autowired
	 PizzaRepository pizzarepository;
	 
	 // SEARCH PIZZA
	 @GetMapping("/pizza/{id}")
	 public String detail (@PathVariable("id") Integer id , Model model ) {
		 
		 Optional<Pizza> k = pizzarepository.findById(id);
		 
		 if (k.isPresent()) {
			 model.addAttribute("pizza", k.get());
		 } else {
			 return "404";
		 }
		 
		 return "/detail";
	 }
	 
	 
	     // CREATE PIZZA
         @GetMapping("/create")
         public String create(Model model) {
        	 Pizza pizza = new Pizza();
            model.addAttribute("pizza", pizza);
            return "/create";
            
         }
		
		@PostMapping("/create")  	
		public String store(
			@Valid @ModelAttribute("pizza") Pizza formPizza, 
			BindingResult bindingResult,
			Model model){
			
			if (bindingResult.hasErrors())
				return "/create";
			
			pizzarepository.save(formPizza);
			
			return "redirect:/"; 
			
		}
		
		
		// EDIT PIZZA
		@GetMapping("/edit/{id}")		
		public String edit(@PathVariable("id") Integer id, Model model) {		
			Pizza pizza=pizzarepository.getReferenceById(id); 
			
			model.addAttribute("pizza", pizza);
			return "/edit";
		}
		
		@PostMapping("/edit/{id}")		
		public String update(
				@Valid @ModelAttribute Pizza formPizza,
				BindingResult bindingResult,
				Model model) {
			
			
			 if (bindingResult.hasErrors())
				return "edit";
			
			    pizzarepository.save(formPizza);
			
			    return "redirect:/";
		} 
		
		
		
		// DELETE PIZZA
		@PostMapping("/delete/{id}")
		public String delete(@PathVariable("id") Integer id) {
		 
		   pizzarepository.deleteById(id);
		   
		   return "redirect:/";
		}

}
