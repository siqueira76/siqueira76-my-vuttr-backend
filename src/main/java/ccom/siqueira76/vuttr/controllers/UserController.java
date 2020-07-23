package ccom.siqueira76.vuttr.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ccom.siqueira76.vuttr.domain.User;
import ccom.siqueira76.vuttr.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> FindById(@PathVariable Integer id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<User> insert(@Valid @RequestBody User obj){
		obj = service.insert(obj);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(obj); // Retorna 201
	}
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable String email){
		User obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

}
