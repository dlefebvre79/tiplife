package us.lefebvre.tiplife;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class TipLifeApplicationTests {

	@Autowired
	private WebApplicationContext webAppContext;
	
	private MockMvc mockMvc;
	
	@BeforeAll
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
		
	}
	
	@Test
	void contextLoads() {
	}

//	@Test
//	public void testEmployee() throws Exception {
//		mocMvc.perform(get(""))
//	}
	
}
