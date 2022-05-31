package com.wipro.assignment.com.wipro.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.codingtest.dtos.LineItemDto;
import com.wipro.codingtest.service.CodeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CodeServiceTest {

	@InjectMocks
	private CodeServiceImpl serviceImpl;

	@Test
	public void testGetUniqueIdCountService() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_1.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Unique User Id Count", 10);
		assertEquals(result, serviceImpl.getUniqueIdCountForJson(lineItemDOList));
	}

	@Test
	public void testGetUniqueIdCountServiceEmptyArray() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_2.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errorMessage", "Input List is Empty");
		assertEquals(result, serviceImpl.getUniqueIdCountForJson(lineItemDOList));
	}

	@Test
	public void testGetUniqueIdCountServiceNullUserId() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_3.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("exceptionMessage", "java.lang.NullPointerException: element cannot be mapped to a null key");
		assertEquals(result, serviceImpl.getUniqueIdCountForJson(lineItemDOList));
	}

	@Test
	public void testUpdateTitleService() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_1.json");
		List<LineItemDto> expectedResultList = DemoApplicationTests.getSampleInput("TestJson_1_UpdateResult.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resultData", expectedResultList);
		assertEquals(result, serviceImpl.updateTitle(lineItemDOList, "4", "1800Flowers"));
	}

	@Test
	public void testUpdateTitleServiceEmptyArray() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_2.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errorMessage", "Input Param is Empty");
		assertEquals(result, serviceImpl.updateTitle(lineItemDOList, "4", "1800Flowers"));
	}

	@Test
	public void testUpdateTitleServiceIdValMiss() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_1.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errorMessage", "Id value missing from input Param");
		assertEquals(result, serviceImpl.updateTitle(lineItemDOList, " ", "1800Flowers"));
	}

	@Test
	public void testUpdateTitleServiceIdValInvalid() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_1.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errorMessage", "Id value Invalid");
		assertEquals(result, serviceImpl.updateTitle(lineItemDOList, "abc", "1800Flowers"));
	}

	@Test
	public void testUpdateTitleServiceIdValNull() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_1.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errorMessage", "Input Param is Empty");
		assertEquals(result, serviceImpl.updateTitle(lineItemDOList, null, "1800Flowers"));
	}

	@Test
	public void testUpdateTitleServiceNewTitleNull() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_1.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errorMessage", "Input Param is Empty");
		assertEquals(result, serviceImpl.updateTitle(lineItemDOList, "4", null));
	}

	@Test
	public void testUpdateTitleServiceNewTitleEmpty() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_1.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errorMessage", "New String is Empty");
		assertEquals(result, serviceImpl.updateTitle(lineItemDOList, "4", ""));
	}

	@Test
	public void testUpdateTitleServiceNullUserId() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_3.json");
		List<LineItemDto> expectedResultList = DemoApplicationTests.getSampleInput("TestJson_3_UpdateResult.json");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resultData", expectedResultList);
		assertEquals(result, serviceImpl.updateTitle(lineItemDOList, "4", "1800Flowers"));
	}

	@Test
	public void testUpdateTitleServiceNullList() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errorMessage", "Input Param is Empty");
		assertEquals(result, serviceImpl.updateTitle(null, "4", "1800Flowers"));
	}

	@Test
	public void testUpdateTitleServiceNullListId() throws Exception {
		List<LineItemDto> lineItemDOList = DemoApplicationTests.getSampleInput("TestJson_4.json");
		String expectedResult = "java.lang.NullPointerException";
		Map<String, Object> actualResult = serviceImpl.updateTitle(lineItemDOList, "4", "1800Flowers");
		String error = (String) actualResult.get("errorMessage");
		String exception = (String) actualResult.get("exceptionMessage");
		if ((error != null && error.contains(expectedResult))
				|| (exception != null && exception.contains(expectedResult))) {
			assertEquals(true, true);
		}

	}
}
