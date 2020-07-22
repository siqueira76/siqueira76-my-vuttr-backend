package ccom.siqueira76.vuttr.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccom.siqueira76.vuttr.domain.Tool;
import ccom.siqueira76.vuttr.repositories.ToolRepository;
import ccom.siqueira76.vuttr.services.exceptions.ObjectNotFondException;

@Service
public class ToolService {

	@Autowired
	ToolRepository repo;
	
	public List<Tool> findAll() {
		return repo.findAll();
	}
	
	public Tool findById(Integer id) {
		Optional<Tool> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFondException("Objeto não emcontrado id: " 
				+ id + ", Tipo: " + Tool.class.getName()));
	}
	
	public List<Tool> findByTag(String tag) {
		List<Tool> objLst = repo.findToolsByTags(tag);
		if (objLst.isEmpty()) {
			throw new ObjectNotFondException("Objeto não emcontrado de tag: " 
					+ tag + ", Tipo: " + Tool.class.getName());
		}
		return objLst;
	}
	
	@Transactional
	public Tool insert(Tool obj) {
		obj.setId(null);
		Tool newObj = repo.save(obj);
		return newObj;
	}
	
	public void delete(Integer id) {
		findById(id);
		repo.deleteById(id);
	}
}
