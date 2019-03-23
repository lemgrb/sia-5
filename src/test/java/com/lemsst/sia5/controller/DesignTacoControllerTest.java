package com.lemsst.sia5.controller;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.lemsst.sia5.HomeController;
@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class DesignTacoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testShowDesignForm() {
		fail("Not yet implemented");
	}

	@Test
	public void testProcessDesign() {
		fail("Not yet implemented");
	}

}
