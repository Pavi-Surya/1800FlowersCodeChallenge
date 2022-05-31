package com.wipro.codingtest.endpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.codingtest.dtos.LineItemDto;
import com.wipro.codingtest.service.CodeService;

@RestController
public class CodeController {

    private static final Logger LOG = LoggerFactory.getLogger(CodeController.class);
    
    @Autowired
    private CodeService service;
    
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
    public Map<String, Object> updateTitle(@RequestBody List<LineItemDto> lineItemDOList, @PathVariable String id, @PathVariable String newTitle) {
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
}
