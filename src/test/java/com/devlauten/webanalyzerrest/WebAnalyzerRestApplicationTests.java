package com.devlauten.webanalyzerrest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebAnalyzerRestApplicationTests {

	private Logger LOG = LoggerFactory.getLogger(WebAnalyzerRestApplicationTests.class);

	@Test
	public void contextLoads() {
		LOG.debug("Spring", "APPLICATION BOOTED");
	}

}
