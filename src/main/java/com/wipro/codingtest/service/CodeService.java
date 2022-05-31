package com.wipro.codingtest.service;

import java.util.List;
import java.util.Map;

import com.wipro.codingtest.dtos.LineItemDto;

public interface CodeService {
    Map<String, Object> getUniqueIdCountForJson(List<LineItemDto> lineItemDOList);

	Map<String, Object> updateTitle(List<LineItemDto> lineItemDOList, String id, String newTitle);
}
