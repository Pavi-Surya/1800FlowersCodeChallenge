package com.wipro.codingtest.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.codingtest.dtos.LineItemDto;

@Service
public class CodeServiceImpl implements CodeService {

	private static final Logger LOG = LoggerFactory.getLogger(CodeServiceImpl.class);

	/**
	 * @param lineItemDOList
	 * @return Map Counts and Returns the unique id count for the input json or else
	 *         returns error message
	 */
	@Override
	public Map<String, Object> getUniqueIdCountForJson(String url) {
		Map<String, Object> result = new HashMap<>();
		try {
			LOG.info("Inside CodeServiceImpl -> getUniqueIdCountForJson Method...");
			List<LineItemDto> lineItemDOList = getJsonthroughRestTemplateCall(url);
			if (CollectionUtils.isEmpty(lineItemDOList)) {
				result.put("errorMessage", "Input List is Empty");
				return result;
			}
			Map<Integer, List<LineItemDto>> groupedByUserIdMap = lineItemDOList.stream()
					.collect(Collectors.groupingBy(LineItemDto::getUserId));
			result.put("Unique User Id Count", groupedByUserIdMap.size());
		} catch (Exception e) {
			LOG.error("Exception occurred at CodeServiceImpl -> getUniqueIdCountForJson Method : " + e);
			result.put("exceptionMessage", e.toString());
		}
		return result;
	}

	/**
	 * @param lineItemDOList, id, newTitle
	 * @return Map Updates the input json as per the input parameter values
	 */
	@Override
	public Map<String, Object> updateTitle(String url, String id, String newTitle) {
		Map<String, Object> result = new HashMap<>();
		try {
			LOG.info("Inside CodeServiceImpl -> updateTitle Method...");
			List<LineItemDto> lineItemDOList = getJsonthroughRestTemplateCall(url);
			if (CollectionUtils.isEmpty(lineItemDOList) || id == null || newTitle == null) {
				result.put("errorMessage", "Input Param is Empty");
				return result;
			}
			if (id.isEmpty() || id.equals(" ")) {
				result.put("errorMessage", "Id value missing from input Param");
				return result;
			}
			if (id.matches("[a-zA-Z]+")) {
				result.put("errorMessage", "Id value Invalid");
				return result;
			}
			if (newTitle.isEmpty()) {
				result.put("errorMessage", "New String is Empty");
				return result;
			}
			Integer idVal = Integer.valueOf(id);
			if (idVal > 100) {
				result.put("errorMessage", "Id is out of limit. Max id should be 100");
				return result;
			}
			lineItemDOList.forEach((item) -> {
				if (item.getId().equals(idVal))
					item.setTitle(newTitle);
			});
			result.put("resultData", lineItemDOList);
		} catch (Exception e) {
			LOG.error("Exception occurred at CodeServiceImpl -> updateTitle Method : " + e);
			result.put("exceptionMessage", e.toString());
		}
		return result;
	}

	public List<LineItemDto> getJsonthroughRestTemplateCall(String url) throws Exception {
		try {
			LOG.info("Inside CodeServiceImpl -> getJsonthroughRestTemplateCall Method...");
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(url);
			ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
			List<LineItemDto> lineItemDtos = new ArrayList<>();
			JSONArray jsonArray = new JSONArray();
			JSONParser parser = new JSONParser();
			Object object = (Object) parser.parse(result.getBody());
			jsonArray = (JSONArray) object;
			ObjectMapper mapper = new ObjectMapper();
			if (jsonArray != null) {
				int len = jsonArray.size();
				for (int i = 0; i < len; i++) {
					LineItemDto item = mapper.readValue(jsonArray.get(i).toString(), LineItemDto.class);
					lineItemDtos.add(item);
				}
			}
			return lineItemDtos;
		} catch (Exception e) {
			LOG.error("Exception occurred at CodeServiceImpl -> getJsonthroughRestTemplateCall Method : " + e);
			throw e;
		}
	}
	
}
