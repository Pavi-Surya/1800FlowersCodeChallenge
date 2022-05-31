package com.wipro.assignment.com.wipro.assignment;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.codingtest.dtos.LineItemDto;

@SpringBootTest
class DemoApplicationTests {

	public static List<LineItemDto> getSampleInput(String path) throws Exception {
		try {
			List<LineItemDto> lineItemDOList = new ArrayList<>();
			byte[] bytes = Files.readAllBytes(Paths.get(path));
			String fileContent = new String(bytes);
			JSONArray jsonArray = new JSONArray(fileContent);
			ObjectMapper mapper = new ObjectMapper();
			if (jsonArray != null) {
				int len = jsonArray.length();
				for (int i = 0; i < len; i++) {
					LineItemDto item = mapper.readValue(jsonArray.get(i).toString(), LineItemDto.class);
					lineItemDOList.add(item);
				}
			}
			return lineItemDOList;
		} catch (Exception e) {
			System.out.println("Exception occurred at Sample Input Read");
			throw e;
		}
	}

}
