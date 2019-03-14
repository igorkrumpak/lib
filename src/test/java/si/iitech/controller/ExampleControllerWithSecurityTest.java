package si.iitech.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import si.iitech.entity.impl.EtExampleUser;
import si.iitech.security.impl.SecurityConstants;
import si.iitech.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true", "spring.jpa.show-sql=true", "spring.jpa.properties.hibernate.format_sql=true", "logging.level.org.hibernate.SQL=debug", "logging.level.org.hibernate.type.descriptor.sql=trace"})
@AutoConfigureMockMvc
public class ExampleControllerWithSecurityTest {

	private boolean registerAndLoginExecuted = false;
	private String token;

	@Autowired
	private MockMvc mvc;

	@Before
	public void registerAndLogin() throws Exception {
		if (registerAndLoginExecuted) return;
		this.mvc.perform(
				MockMvcRequestBuilders
					.post("/users/register")
					.content(StringUtils.asJsonString(new EtExampleUser("test@gmail.com", "test1234")))
					.contentType(MediaType.APPLICATION_JSON)
				    .accept(MediaType.APPLICATION_JSON))
				    .andExpect(status().isOk());
		
		token = this.mvc.perform(
				MockMvcRequestBuilders
					.post("/login")
					.content(StringUtils.asJsonString(new EtExampleUser("test@gmail.com", "test1234")))
					.contentType(MediaType.APPLICATION_JSON)
				    .accept(MediaType.APPLICATION_JSON))
				    .andExpect(status().isOk()).andReturn().getResponse().getHeader(SecurityConstants.HEADER_STRING);
		
		registerAndLoginExecuted = true;
	}

	@Test
	public void exampleControllerTest() throws Exception {
		this.mvc.perform(
				MockMvcRequestBuilders
					.get("/test")
					.header(SecurityConstants.HEADER_STRING, token)
					)
					.andExpect(status().isOk())
					.andExpect(content().string("test"));
	}
}
