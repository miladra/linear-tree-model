package com.tradeshift.codingchallenge.api;

import com.tradeshift.codingchallenge.servieapi.StructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiStructure {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiStructure.class);

    @Autowired
    protected StructureService structureService;

    @RequestMapping(value = "/structure/{structureId}", method = RequestMethod.GET, produces = "application/json")
    public String getStructure(@PathVariable String structureId) {
        return structureService.getStructure(structureId);
    }

}
