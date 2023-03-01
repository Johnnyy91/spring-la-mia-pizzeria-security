package com.example.pizzeria.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="Pizza")
public class Pizza {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="name must be not null")
	@NotEmpty(message="name must be not empty")
	private String name;
	
	@NotNull(message="description must be not null")
	@NotEmpty(message="description must be not empty")
	private String description;
	
	@NotNull(message="imgPath must be not null")
	@NotEmpty(message="imgPath must be not empty")
	private String imgPath;
	
	@NotNull(message="price must be not null")
	private int price;
	
	
	// ADD LIST/OFFERTA
	
	@OneToMany (mappedBy = "pizza") // si riferisce al ManytoOne di pizza presente in Offerta
	private List<Offerta> Offerte;
	
	public List<Offerta> getOfferte() {
		return Offerte;
	}

	public void setOfferte(List<Offerta> offerte) {
		Offerte = offerte;
	}	
	
	//CLOSE LIST/OFFERTA
	
	
	// ADD LIST/INGREDIENTI
	
 
	     @ManyToMany(cascade = CascadeType.ALL)
	     private List<Ingredienti> ingredienti;
		
		public List<Ingredienti> getIngredienti() {
			return ingredienti;
		}

		public void setOInigredienti(List<Ingredienti> ingredienti) {
			ingredienti = ingredienti;
		}	
		
		//CLOSE LIST/INGREDIENTI
		
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}


