package ccom.siqueira76.vuttr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import ccom.siqueira76.vuttr.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	DBService dbService;
	
//	@Bean
//	public boolean instatiateDatabase() throws ParseException {
//		dbService.instatiateTestDatabase();
//		return true;
//	}

}
