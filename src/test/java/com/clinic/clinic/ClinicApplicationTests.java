package com.clinic.clinic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClinicApplicationTests {

	@Test
	void contextLoads() {
		ClinicApplication.main(new String[] {});
		assertTrue(true);
	}
	

}
