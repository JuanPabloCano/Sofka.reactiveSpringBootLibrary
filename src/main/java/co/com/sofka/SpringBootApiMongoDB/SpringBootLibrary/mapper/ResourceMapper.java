package co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.mapper;

import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.dto.ResourceDto;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.model.Resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceMapper {

    public Resource fromDtoToResource(ResourceDto dto) {
        Resource resource = new Resource();
        resource.setId(dto.getId());
        resource.setAuthor(dto.getAuthor());
        resource.setName(dto.getName());
        resource.setReturnDate(dto.getReturnDate());
        resource.setResourceCategory(dto.getResourceCategory());
        resource.setResourceType(dto.getResourceType());
        resource.setAvailable(dto.isAvailable());
        return resource;
    }

    public ResourceDto fromResourceToDto(Resource resource) {
        ResourceDto resourceDto = new ResourceDto();
        resourceDto.setId(resource.getId());
        resourceDto.setAuthor(resource.getAuthor());
        resourceDto.setName(resource.getName());
        resourceDto.setReturnDate(resource.getReturnDate());
        resourceDto.setResourceCategory(resource.getResourceCategory());
        resourceDto.setResourceType(resource.getResourceType());
        resourceDto.setAvailable(resourceDto.isAvailable());
        return resourceDto;
    }

    public List<ResourceDto> fromResourcesList(List<Resource> resources) {
        if (resources == null) {
            return null;
        }
        List<ResourceDto> list = new ArrayList(resources.size());

        for (Resource resource : resources) {
            list.add(fromResourceToDto(resource));
        }
        return list;
    }

}
