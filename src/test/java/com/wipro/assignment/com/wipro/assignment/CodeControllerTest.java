package com.wipro.assignment.com.wipro.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wipro.codingtest.endpoint.CodeController;
import com.wipro.codingtest.service.CodeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CodeController.class)
public class CodeControllerTest {

	@Autowired
	private CodeController codeController;

	@MockBean
	private CodeService service;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void contextLoads() {
		Assertions.assertThat(codeController).isNotNull();
	}

	@Test
	public void testGetUniqueIdCountController() throws Exception {
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("Unique User Id Count", 10);
		Mockito.when(service.getUniqueIdCountForJson("TestJson_1.json")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.getUniqueIdCountForJson("TestJson_1.json");
		mockMvc.perform(
				MockMvcRequestBuilders.post("http://localhost:8080/getUniqueIdCount").param("url", "TestJson_1.json"))
				.andReturn().getResponse().getStatus();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testGetUniqueIdCountControllerEmptyArray() throws Exception {
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("errorMessage", "Input List is Empty");
		Mockito.when(service.getUniqueIdCountForJson("TestJson_2.json")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.getUniqueIdCountForJson("TestJson_2.json");
		mockMvc.perform(
				MockMvcRequestBuilders.post("http://localhost:8080/getUniqueIdCount").param("url", "TestJson_2.json"))
				.andReturn().getResponse().getStatus();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testGetUniqueIdCountControllerNullUserId() throws Exception {
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("exceptionMessage",
				"java.lang.NullPointerException: element cannot be mapped to a null key");
		Mockito.when(service.getUniqueIdCountForJson("TestJson_3.json")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.getUniqueIdCountForJson("TestJson_3.json");
		mockMvc.perform(
				MockMvcRequestBuilders.post("http://localhost:8080/getUniqueIdCount").param("url", "TestJson_3.json"))
				.andReturn().getResponse().getStatus();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testUpdateTitleController() throws Exception {
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("resultData", DemoApplicationTests.getSampleInput("TestJson_1_UpdateResult.json"));
		Mockito.when(service.updateTitle("TestJson_1.json", "10", "Something")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateTitle("TestJson_1.json", "10", "Something");
		mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/updateTitle/10/Something").param("url",
				"TestJson_1.json")).andReturn().getResponse().getStatus();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testUpdateTitleControllerEmptyArray() throws Exception {
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("errorMessage", "Input Param is Empty");
		Mockito.when(service.updateTitle("TestJson_2.json", "99", "My_Text")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateTitle("TestJson_2.json", "99", "My_Text");
		mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/updateTitle/99/My_Text").param("url",
				"TestJson_2.json")).andReturn().getResponse().getStatus();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testUpdateTitleControllerNullUserId() throws Exception {
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("exceptionMessage",
				"java.lang.NullPointerException: element cannot be mapped to a null key");
		Mockito.when(service.updateTitle("TestJson_3.json", "50", "SampleText")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateTitle("TestJson_3.json", "50", "SampleText");
		mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/updateTitle/50/SampleText").param("url",
				"TestJson_3.json")).andReturn().getResponse().getStatus();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testUpdateFourthElementController() throws Exception {
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("resultData", DemoApplicationTests.getSampleInput("TestJson_1_UpdateResult.json"));
		Mockito.when(service.updateTitle("TestJson_1.json", "4", "1800Flowers")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateFourthElement("TestJson_1.json");
		mockMvc.perform(
				MockMvcRequestBuilders.put("http://localhost:8080/updateFourthElement").param("url", "TestJson_1.json"))
				.andReturn().getResponse().getStatus();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testUpdateFourthElementControllerEmptyArray() throws Exception {
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("errorMessage", "Input Param is Empty");
		Mockito.when(service.updateTitle("TestJson_2.json", "4", "1800Flowers")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateFourthElement("TestJson_2.json");
		mockMvc.perform(
				MockMvcRequestBuilders.put("http://localhost:8080/updateFourthElement").param("url", "TestJson_2.json"))
				.andReturn().getResponse().getStatus();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testUpdateFourthElementControllerNullUserId() throws Exception {
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("exceptionMessage",
				"java.lang.NullPointerException: element cannot be mapped to a null key");
		Mockito.when(service.updateTitle("TestJson_3.json", "4", "1800Flowers")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateFourthElement("TestJson_3.json");
		mockMvc.perform(
				MockMvcRequestBuilders.put("http://localhost:8080/updateFourthElement").param("url", "TestJson_3.json"))
				.andReturn().getResponse().getStatus();
		assertEquals(expectedResult, actualResult);
	}

}
