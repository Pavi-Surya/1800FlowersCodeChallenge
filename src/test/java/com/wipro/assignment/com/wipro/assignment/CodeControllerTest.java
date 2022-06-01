package com.wipro.assignment.com.wipro.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.codingtest.dtos.LineItemDto;
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
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_1.json");
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("Unique User Id Count", 10);
		Mockito.when(service.getUniqueIdCountForJson(lineItemDOList)).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.getUniqueIdCountForJson(lineItemDOList);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/getUniqueIdCount")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(lineItemDOList))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testGetUniqueIdCountControllerEmptyArray() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_2.json");
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("errorMessage", "Input List is Empty");
		Mockito.when(service.getUniqueIdCountForJson(lineItemDOList)).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.getUniqueIdCountForJson(lineItemDOList);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/getUniqueIdCount")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(lineItemDOList))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testGetUniqueIdCountControllerNullUserId() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_3.json");
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("exceptionMessage",
				"java.lang.NullPointerException: element cannot be mapped to a null key");
		Mockito.when(service.getUniqueIdCountForJson(lineItemDOList)).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.getUniqueIdCountForJson(lineItemDOList);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/getUniqueIdCount")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(lineItemDOList))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testUpdateTitleController() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_1.json");
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("resultData", DemoApplicationTests.getSampleInput("TestJson_1_UpdateResult.json"));
		Mockito.when(service.updateTitle(lineItemDOList, "10", "Something")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateTitle(lineItemDOList, "10", "Something");
		mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/updateTitle/10/Something")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(lineItemDOList))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testUpdateTitleControllerEmptyArray() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_2.json");
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("errorMessage", "Input Param is Empty");
		Mockito.when(service.updateTitle(lineItemDOList, "99", "My Text")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateTitle(lineItemDOList, "99", "My Text");
		mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/updateTitle/99/My Text")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(lineItemDOList))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertEquals(expectedResult, actualResult);
	}	

	@Test
	public void testUpdateTitleControllerNullUserId() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_3.json");
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("exceptionMessage",
				"java.lang.NullPointerException: element cannot be mapped to a null key");
		Mockito.when(service.updateTitle(lineItemDOList, "50", "SampleText")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateTitle(lineItemDOList, "50", "SampleText");
		mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/updateTitle/50/SampleText")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(lineItemDOList))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testUpdateFourthElementController() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_1.json");
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("resultData", DemoApplicationTests.getSampleInput("TestJson_1_UpdateResult.json"));
		Mockito.when(service.updateTitle(lineItemDOList, "4", "1800Flowers")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateFourthElement(lineItemDOList);
		mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/updateFourthElement")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(lineItemDOList))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testUpdateFourthElementControllerEmptyArray() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_2.json");
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("errorMessage", "Input Param is Empty");
		Mockito.when(service.updateTitle(lineItemDOList, "4", "1800Flowers")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateFourthElement(lineItemDOList);
		mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/updateFourthElement")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(lineItemDOList))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testUpdateFourthElementControllerNullUserId() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_3.json");
		Map<String, Object> expectedResult = new HashMap<String, Object>();
		expectedResult.put("exceptionMessage",
				"java.lang.NullPointerException: element cannot be mapped to a null key");
		Mockito.when(service.updateTitle(lineItemDOList, "4", "1800Flowers")).thenReturn(expectedResult);
		Map<String, Object> actualResult = codeController.updateFourthElement(lineItemDOList);
		mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/updateFourthElement")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(lineItemDOList))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertEquals(expectedResult, actualResult);
	}

}
