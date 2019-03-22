package si.iitech.lib.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import si.iitech.lib.entity.impl.EtExampleUser;
import si.iitech.lib.security.impl.SecurityConstants;
import si.iitech.lib.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	private String token;

	@Autowired
	private MockMvc mvc;
	
	private static AtomicInteger counter = new AtomicInteger();

	@Test
	public void registerAndLoginTest() throws Exception {
		int count = counter.getAndIncrement();
		this.mvc.perform(
				MockMvcRequestBuilders
				.post("/users/register")
				.content(StringUtils.asJsonString(new EtExampleUser("test@gmail.com", "")))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
			    .accept(MediaType.APPLICATION_JSON_UTF8))
			    .andExpect(MockMvcResultMatchers.status().isBadRequest());
		this.mvc.perform(
				MockMvcRequestBuilders
				.post("/users/register")
				.content(StringUtils.asJsonString(new EtExampleUser(null, "test@gmail.com")))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		this.mvc.perform(
				MockMvcRequestBuilders
				.post("/users/register")
				.content(StringUtils.asJsonString(new EtExampleUser("test@gmail.com", "test")))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		this.mvc.perform(
				MockMvcRequestBuilders
				.post("/users/register")
				.content(StringUtils.asJsonString(new EtExampleUser("test", "test")))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		String validEmail = count + "test@gmail.com";
		this.mvc.perform(
				MockMvcRequestBuilders
				.post("/users/register")
				.content(StringUtils.asJsonString(new EtExampleUser(validEmail, "testtest")))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		token = this.mvc.perform(
				MockMvcRequestBuilders
					.post("/login")
					.content(StringUtils.asJsonString(new EtExampleUser(validEmail, "testtest1")))
					.contentType(MediaType.APPLICATION_JSON)
				    .accept(MediaType.APPLICATION_JSON))
				    .andExpect(status().isUnauthorized()).andReturn().getResponse().getHeader(SecurityConstants.TOKEN);
		assertNull(token);

		token = this.mvc.perform(
				MockMvcRequestBuilders
				.post("/login")
				.content(StringUtils.asJsonString(new EtExampleUser(validEmail, "testtest")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getHeader(SecurityConstants.TOKEN);
		
		assertNotNull(token);
	}
}