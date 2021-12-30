package co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.service;

import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.dto.ResourceDto;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.mapper.ResourceMapper;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.model.Resource;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.repository.ResourceRepository;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.utils.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    ResourceMapper resourceMapper = new ResourceMapper();

    public List<ResourceDto> findAllResources() {
        List<Resource> resources = resourceRepository.findAll();
        return resourceMapper.fromResourcesList(resources);
    }

    public ResourceDto findResourceById(String id) {
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Recurso no " +
                "encontrado con Id: " + id));
        return resourceMapper.fromResourceToDto(resource);
    }

    public ResourceDto saveResource(ResourceDto resourceDTO) {
        Resource resource = resourceMapper.fromDtoToResource(resourceDTO);
        return resourceMapper.fromResourceToDto(resourceRepository.save(resource));
    }

    public void deleteResourceById(String id) {
        resourceRepository.deleteById(id);
    }

    public NotificationMessage isResourceAvailable(String id) {
        ResourceDto resourceDto = findResourceById(id);
        return new NotificationMessage().showResourceAvailability(resourceDto.isAvailable(), resourceDto.getReturnDate());
    }

    public NotificationMessage lendResource(String id) {
        ResourceDto resourceDto = findResourceById(id);
        NotificationMessage notificationMessage =
                new NotificationMessage().showResourceLending(resourceDto.isAvailable(), resourceDto.getReturnDate());
        if (resourceDto.isAvailable()) {
            resourceDto.setAvailable(false);
            resourceDto.setReturnDate(new Date());
            Resource resource = resourceMapper.fromDtoToResource(resourceDto);
            resourceMapper.fromResourceToDto(resourceRepository.save(resource));
        }
        return notificationMessage;
    }

    public NotificationMessage returnResource(String id) {
        ResourceDto resourceDto = findResourceById(id);
        NotificationMessage notificationMessage =
                new NotificationMessage().showResourceDevolution(resourceDto.isAvailable(), resourceDto.getReturnDate());
        if (!resourceDto.isAvailable()) {
            resourceDto.setAvailable(false);
            resourceDto.setReturnDate(new Date());
            Resource resource = resourceMapper.fromDtoToResource(resourceDto);
            resourceMapper.fromResourceToDto(resourceRepository.save(resource));
        }
        return notificationMessage;
    }

    public List<ResourceDto> findByResourceCategoryAndResourceType(ResourceDto resourceDto) {
        return resourceMapper.fromResourcesList(resourceRepository.findByResourceCategoryAndResourceType(
                resourceDto.getResourceCategory(), resourceDto.getResourceType()));
    }
}
