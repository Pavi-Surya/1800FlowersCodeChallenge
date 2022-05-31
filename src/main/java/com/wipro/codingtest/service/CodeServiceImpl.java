package com.wipro.codingtest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
	public Map<String, Object> getUniqueIdCountForJson(List<LineItemDto> lineItemDOList) {
		Map<String, Object> result = new HashMap<>();
		try {
			LOG.info("Inside CodeServiceImpl -> getUniqueIdCountForJson Method...");
			if (CollectionUtils.isEmpty(lineItemDOList)) {
				result.put("errorMessage", "Input List is Empty");
				return result;
			}
			Map<Integer, List<LineItemDto>> groupedByUserIdMap = lineItemDOList.stream()
					.collect(Collectors.groupingBy(LineItemDto::getUserId));
			result.put("Unique User Id Count", groupedByUserIdMap.size());
		} catch (Exception e) {
			LOG.info("Exception occurred at CodeServiceImpl -> getUniqueIdCountForJson Method : " + e);
			result.put("exceptionMessage", e.toString());
		}
		return result;
	}

	/**
	 * @param lineItemDOList, id, newTitle
	 * @return Map Updates the input json as per the input parameter values
	 */
	@Override
	public Map<String, Object> updateTitle(List<LineItemDto> lineItemDOList, String id, String newTitle) {
		Map<String, Object> result = new HashMap<>();
		try {
			LOG.info("Inside CodeServiceImpl -> updateTitle Method...");
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
			lineItemDOList.forEach((item) -> {
				if (item.getId().equals(idVal))
					item.setTitle(newTitle);
			});
			result.put("resultData", lineItemDOList);
		} catch (Exception e) {
			LOG.info("Exception occurred at CodeServiceImpl -> updateTitle Method : " + e);
			result.put("exceptionMessage", e.toString());
		}
		return result;
	}
}
