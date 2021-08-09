package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemrepo.ItemRepo;
import com.example.rest.Items;

@Service
public class ItemService {

	private ItemRepo repo;
	
	@Autowired
	public ItemService(ItemRepo repo) {
		this.repo = repo;
	}
	
	//CREATE
		public Items create(Items itemdata) {
		 return this.repo.saveAndFlush(itemdata);
	}
	
	//READ ALL
	public List<Items> read(){
		return this.repo.findAll();
	}
	
	//DELETE
	public String delete(Long id) {
		 this.repo.deleteById(id);
		 if(this.repo.existsById(id)) {
			 return "This id wasnt deleted!: " + id; 
		 } else {
			 return "This id was deleted!: " + id;
		 }
	}
	
	//UPDATE
	public Items update(Long id, Items item) {
		if(this.repo.findById(item.getId()).isPresent()) {
			Items existingitem = this.repo.findById(item.getId()).get();
			
			existingitem.setDescription(item.getDescription());
		existingitem.setPriority(item.getPriority());
		Items updateditem = this.repo.save(existingitem);
		return new Items(updateditem.getId(), updateditem.getDescription(), updateditem.getPriority());
		
	} else {
		return null;
		
	}
		}
	}
	
	
	

	
	
	

