package ccom.siqueira76.vuttr.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ccom.siqueira76.vuttr.domain.Tool;
import ccom.siqueira76.vuttr.domain.User;
import ccom.siqueira76.vuttr.domain.enums.Perfil;
import ccom.siqueira76.vuttr.repositories.ToolRepository;
import ccom.siqueira76.vuttr.repositories.UserRepository;

@Service
public class DBService {
	
	@Autowired
	ToolRepository toolRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder pswEncoder;
	
	public void instatiateTestDatabase() throws ParseException {
		
		Tool tool1 = new Tool(null, "Notion", "https://notion.so", "All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized. ");
		tool1.getTags().addAll(Arrays.asList("organization", "planning", "collaboration", "writing", "calendar"));
		
		Tool tool2 = new Tool(null, "json-server", "https://github.com/typicode/json-server", "Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.");
		tool2.getTags().addAll(Arrays.asList("api", "json", "schema", "node", "github", "rest"));
		
		Tool tool3 = new Tool(null, "fastify", "https://www.fastify.io/", "Extremely fast and simple, low-overhead web framework for NodeJS. Supports HTTP2.");
		tool3.getTags().addAll(Arrays.asList("web", "framework", "node", "http2", "https", "localhost"));
		
		User user = new User(null, "admin@mail.com", pswEncoder.encode("123"));
		user.addPerfil(Perfil.ADMIN);
		
		toolRepository.saveAll(Arrays.asList(tool1, tool2,tool3));
		userRepository.save(user);
		
	}

}
