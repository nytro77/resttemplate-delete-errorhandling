package com.example.resttemplatedeleteerrorhandling;

import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpStatusCodeException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResttemplateDeleteErrorhandlingApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private String exceptionResponseBodyAsString;

	@Before
	public void setUp() {
		testRestTemplate.getRestTemplate().setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				try {
					super.handleError(response);
				} catch (HttpStatusCodeException e) {
					exceptionResponseBodyAsString = e.getResponseBodyAsString();
				}
			}
		});
	}

	@Test
	public void testGet() {
		testRestTemplate.getForEntity("/test", Void.class);

		assertFalse("Error seems to be whitelabel HTML instead of JSON! Value: " + exceptionResponseBodyAsString,
				exceptionResponseBodyAsString.contains("Whitelabel"));
	}

	@Test
	public void testDelete() {
		testRestTemplate.delete("/test");

		assertFalse("Error seems to be whitelabel HTML instead of JSON! Value: " + exceptionResponseBodyAsString,
				exceptionResponseBodyAsString.contains("Whitelabel"));
	}

}
