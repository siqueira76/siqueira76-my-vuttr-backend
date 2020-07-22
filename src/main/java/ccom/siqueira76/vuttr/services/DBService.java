package ccom.siqueira76.vuttr.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccom.siqueira76.vuttr.domain.Tool;
import ccom.siqueira76.vuttr.repositories.ToolRepository;

@Service
public class DBService {
	
	@Autowired
	ToolRepository toolRepository;
	
	public void instatiateTestDatabase() throws ParseException {
		
		Tool tool1 = new Tool(null, "Notion", "https://notion.so", "All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized. ");
		tool1.getTags().addAll(Arrays.asList("organization", "planning", "collaboration", "writing", "calendar"));
		
		Tool tool2 = new Tool(null, "json-server", "https://github.com/typicode/json-server", "Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.");
		tool2.getTags().addAll(Arrays.asList("api", "json", "schema", "node", "github", "rest"));
		
		toolRepository.saveAll(Arrays.asList(tool1, tool2));
		
	}

}
