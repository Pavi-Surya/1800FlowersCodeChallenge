package com.wipro.codingtest.endpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.wipro.codingtest.dtos.LineItemDto;
import com.wipro.codingtest.service.CodeService;

@RestController
@Component
public class CodeController {

	private static final Logger LOG = LoggerFactory.getLogger(CodeController.class);

	private static int counter = 0;

	@Autowired
	private CodeService service;

	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		applicationContext.getBean(RequestMappingHandlerMapping.class)
		.getHandlerMethods().forEach((key, value) -> {
			counter++;
			LOG.info("{} --- {}", key, value);
		});
	}
	
	@GetMapping("/getNumberofEndpoints")
	public String index() {
		return "Number of EndPoints... " + counter;
	}

	@PostMapping("/getUniqueIdCount")
	public Map<String, Object> getUniqueIdCountForJson(@RequestBody List<LineItemDto> lineItemDOList) {
		try {
			LOG.info("Inside CodeController -> getUniqueIdCountForJson Method...");
			return service.getUniqueIdCountForJson(lineItemDOList);
		} catch (Exception e) {
			LOG.info("Exception occurred at CodeController -> getUniqueIdCountForJson Method : " + e.toString());
			Map<String, Object> result = new HashMap<>();
			result.put("exceptionMessage", e.toString());
			return result;
		}
	}

	@PutMapping("/updateTitle/{id}/{newTitle}")
	public Map<String, Object> updateTitle(@RequestBody List<LineItemDto> lineItemDOList, @PathVariable String id,
			@PathVariable String newTitle) {
		try {
			LOG.info("Inside CodeController -> updateTitle Method...");
			return service.updateTitle(lineItemDOList, id, newTitle);
		} catch (Exception e) {
			LOG.info("Exception occurred at CodeController -> updateTitle Method : " + e.toString());
			Map<String, Object> result = new HashMap<>();
			result.put("exceptionMessage", e.toString());
			return result;
		}
	}
	
	@PutMapping("/updateFourthElement")
	public Map<String, Object> updateFourthElement(@RequestBody List<LineItemDto> lineItemDOList) {
		try {
			LOG.info("Inside CodeController -> updateFourthElement Method...");
			return service.updateTitle(lineItemDOList, "4", "1800Flowers");
		} catch (Exception e) {
			LOG.info("Exception occurred at CodeController -> updateFourthElement Method : " + e.toString());
			Map<String, Object> result = new HashMap<>();
			result.put("exceptionMessage", e.toString());
			return result;
		}
	}
}
