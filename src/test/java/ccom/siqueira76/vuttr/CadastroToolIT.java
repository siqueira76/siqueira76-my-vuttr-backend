package ccom.siqueira76.vuttr;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import ccom.siqueira76.vuttr.domain.Tool;
import ccom.siqueira76.vuttr.services.ToolService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroToolIT {

	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();

	@Autowired
	ToolService service;

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void deveRetornarStatus200_QuandoConsultarTool() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/tools"), HttpMethod.GET, entity,
				String.class);

		String actual = response.getStatusCode().toString();
		assertTrue(actual.contains("200 OK"));
	}

	@Test
	public void deveRetornarTileNotion_QuandoConsultarToolPorId() {
		
		ResponseEntity<Tool> response = restTemplate.getForEntity(createURLWithPort("/tools/1"), Tool.class);

		String title = response.getBody().getTitle();
		assertTrue(title.contains("Notion"));
	}

	@Test
	public void deveRetornar1_QuantoConsultarPorTag() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/tools/tag/node"), HttpMethod.GET, entity,
				String.class);
		
		Gson g = new Gson();
		Tool obj[] = g.fromJson(response.getBody().toString(), Tool[].class);
		List<Tool> list = new ArrayList<>();
		for (Tool t : obj) {
			list.add(t);
		}
		
		String size = Integer.toString(list.size());
		assertTrue(size.contains("1"));
	}
	
	@Test
	public void deveRetornar404_QuantoConsultarPorTagInesistente() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/tools/tag/swagger"), HttpMethod.GET, entity,
				String.class);

		String actual = response.getStatusCode().toString();
		assertTrue(actual.contains("404"));
	}
	
	@Test
	public void deveRetornar404_QuantoConsultarToolPorIdInesistente() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/tools/20"), HttpMethod.GET, entity,
				String.class);

		String actual = response.getStatusCode().toString();
		assertTrue(actual.contains("404"));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
