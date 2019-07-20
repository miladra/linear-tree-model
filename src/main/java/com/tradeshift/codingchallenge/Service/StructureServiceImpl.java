package com.tradeshift.codingchallenge.Service;

import com.tradeshift.codingchallenge.common.exception.NotFoundException;
import com.tradeshift.codingchallenge.repositoryapi.StructureRepository;
import com.tradeshift.codingchallenge.servieapi.StructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StructureServiceImpl implements StructureService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StructureServiceImpl.class);

    protected static final String STRUCTURE_NOT_FOUND_MESSAGE_KEY = "PROCESS.ERROR.NOT-FOUND";

    @Autowired
    protected StructureRepository structureRepository;

    @Override
    public String getStructure(String structureId) {
        String structure = structureRepository.get(structureId);

        if (structure == null) {
            NotFoundException structureNotFound = new NotFoundException("No structure found with the given id: " + structureId);
            throw structureNotFound;
        }

        return structure;
    }

    @Override
    public void updateStructure(String structureId) {
     try{
          structureRepository.save(structureId);
     } catch (Exception ex){
         NotFoundException structureNotFound = new NotFoundException("No structure found with the given id: " + structureId);
         throw structureNotFound;
       }
    }
}
