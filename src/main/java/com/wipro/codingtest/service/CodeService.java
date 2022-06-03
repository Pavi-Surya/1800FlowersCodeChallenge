package com.wipro.codingtest.service;

import java.util.Map;

public interface CodeService {
    Map<String, Object> getUniqueIdCountForJson(String url);

	Map<String, Object> updateTitle(String url, String id, String newTitle);
}
