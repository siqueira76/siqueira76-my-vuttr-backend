package ccom.siqueira76.vuttr;

import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import ccom.siqueira76.vuttr.domain.User;
import ccom.siqueira76.vuttr.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroUserIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	UserService service;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void deveRetornarStatus202_QuandoCadastrarUser() {
		
		User obj = new User(null, "admin-test@email.com", "1234");
		String json = null;

		try {
			json = "{\"email\":\"admin-test@email.com\",\"senha\":\"1234\"}";
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8);
		headers.setContentType(mediaType);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/user"), HttpMethod.POST, entity,
				String.class);

		String actual = response.getStatusCode().toString();
		assertTrue(actual.contains("202 ACCEPTED"));
	}
	
	public String jsonFormat(User lines) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(lines);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
