package com.java.main.nodatabasetest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//todo: still these kind of tests in "No_Database" Profile need to be implemented
@RunWith(SpringJUnit4ClassRunner.class)
@SprintBootTestWithoutDatabase
public class CoreApplicationNoDatabaseTest {

	@Test
	public void testCoreApplicationContext() {
		// This test make sure MES Core could be load the Spring context.
	}

}