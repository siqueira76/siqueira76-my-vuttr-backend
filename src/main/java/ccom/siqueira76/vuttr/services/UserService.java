package ccom.siqueira76.vuttr.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ccom.siqueira76.vuttr.domain.Tool;
import ccom.siqueira76.vuttr.domain.User;
import ccom.siqueira76.vuttr.repositories.UserRepository;
import ccom.siqueira76.vuttr.services.exceptions.ObjectNotFondException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	BCryptPasswordEncoder pswEncoder;
	
	public User findById(Integer id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFondException("Objeto não encontrado id: " 
				+ id + ", Tipo: " + Tool.class.getName()));
	}
	
	@Transactional
	public User insert(User obj) {
		obj.setId(null);
		obj.setSenha(pswEncoder.encode(obj.getSenha()));
		User newObj = repo.save(obj);
		return newObj;
	}
	
	public User findByEmail(String email) {
//		UserSS user = UserService.authenticated();
//		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
//			throw new AuthorizationExeption("Acesso negado");
//		}
		
		User obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFondException("Objeto não encontrado email: " + email + ", Tipo: " + User.class.getName());
		}
		
		return obj;
	}
}
