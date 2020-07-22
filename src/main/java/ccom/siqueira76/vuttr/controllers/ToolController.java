package ccom.siqueira76.vuttr.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ccom.siqueira76.vuttr.domain.Tool;
import ccom.siqueira76.vuttr.services.ToolService;

@RestController
@RequestMapping(value = "/tools")
public class ToolController {

	@Autowired
	ToolService service;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Tool> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> FindById(@PathVariable Integer id) {
		Tool obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("tag/{tag}")
	public ResponseEntity<?> FindByTag(@PathVariable String tag) {
		List<Tool> objList = service.findByTag(tag);
		return ResponseEntity.ok().body(objList);
	}
	
	@PostMapping
	public ResponseEntity<Tool> insert(@Valid @RequestBody Tool obj){
		obj = service.insert(obj);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(obj); // Retorna 201
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build(); // Retorna 204
	}

}
