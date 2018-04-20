package gov.dhs.uscis.odos2.inventory.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.dhs.uscis.odos2.inventory.model.Building;
import gov.dhs.uscis.odos2.inventory.repository.BuildingRepository;

/**
 * Business service for Room entity related operations
 */
@Service
public class BuildingServiceImpl implements BuildingService{

    private static final Logger logger = LoggerFactory.getLogger(BuildingServiceImpl.class);

    @Autowired
    private BuildingRepository buildingRepository;
    
    @Transactional(readOnly = true)
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }
    
    
}
