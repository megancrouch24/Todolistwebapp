package com.example.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.Items;
import com.example.service.ItemService;



@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemsController {
	
	private ItemService service;
	
	@Autowired
	public ItemsController(ItemService service) {
		this.service = service;
	}
	
	@GetMapping("/todolist")
	public ResponseEntity<String> sayToDo(){
		return new ResponseEntity<String>("to do list", HttpStatus.OK);
	}
	
	//CREATE
	@PostMapping("/createItem")
	public ResponseEntity<Items> createItem(@RequestBody Items userItem){
		return new ResponseEntity<>(this.service.create(userItem), HttpStatus.CREATED);
	}
	
	//READ ALL
	@GetMapping("/readAll")
	public ResponseEntity<List<Items>> readAll(){
		return new ResponseEntity<>(this.service.read(), HttpStatus.OK);
	}

	
	//DELETE
	@DeleteMapping("/deleteItem/{id}")
	public ResponseEntity<String> getItemById(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.NO_CONTENT );
	
	}

	//UPDATE
    @PutMapping("/update/{id}")
	public ResponseEntity<Items> updateItems(@PathVariable Long id, @RequestBody Items item){
	return new ResponseEntity<Items>(this.service.update(id, item), HttpStatus.ACCEPTED);
		
		
}
	
	
	
	
}
