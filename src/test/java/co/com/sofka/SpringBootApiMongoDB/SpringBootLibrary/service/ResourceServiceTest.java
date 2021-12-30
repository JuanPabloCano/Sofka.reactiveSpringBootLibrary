package co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.service;

import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.enums.ResourceCategory;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.enums.ResourceType;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.mapper.ResourceMapper;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.model.Resource;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.repository.ResourceRepository;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.utils.NotificationMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ResourceServiceTest {

    @MockBean
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourceService resourceService;

    @Test
    void findAllResources() {

        var resource1 = new Resource("1", "Fernando Trujillo", "La biblia de los caídos", new Date(),
                ResourceCategory.HORROR, ResourceType.BOOK, true);
        var resource2 = new Resource("1", "Fernando Trujillo", "La biblia de los caídos - la venganza de sombra",
                new Date(), ResourceCategory.HORROR, ResourceType.BOOK, true);

        var list = new ArrayList<Resource>();
        list.add(resource1);
        list.add(resource2);

        Mockito.when(resourceRepository.findAll()).thenReturn(list);

        var result = resourceService.findAllResources();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(resource1.getId(), result.get(0).getId());
        Assertions.assertEquals(resource2.getId(), result.get(1).getId());
        Assertions.assertEquals(resource1.getAuthor(), result.get(0).getAuthor());
        Assertions.assertEquals(resource2.getAuthor(), result.get(1).getAuthor());
        Assertions.assertEquals(resource1.getName(), result.get(0).getName());
        Assertions.assertEquals(resource2.getName(), result.get(1).getName());
        Assertions.assertEquals(resource1.getReturnDate(), result.get(0).getReturnDate());
        Assertions.assertEquals(resource2.getReturnDate(), result.get(1).getReturnDate());
        Assertions.assertEquals(resource1.getResourceType(), result.get(0).getResourceType());
        Assertions.assertEquals(resource2.getResourceType(), result.get(1).getResourceType());
        Assertions.assertEquals(resource1.getResourceCategory(), result.get(0).getResourceCategory());
        Assertions.assertEquals(resource1.getResourceCategory(), result.get(1).getResourceCategory());
        Mockito.verify(resourceRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findById() {

        var resource1 = new Resource("xxxx", "Animal Planet", "Reptil´s world", new Date(),
                ResourceCategory.NATURE, ResourceType.DOCUMENTARIES, true);

        Mockito.when(resourceRepository.findById(any())).thenReturn(java.util.Optional.of(resource1));

        var resultado = resourceService.findResourceById(resource1.getId());

        Assertions.assertEquals(resource1.getId(), resultado.getId());
        Assertions.assertEquals(resource1.getAuthor(), resultado.getAuthor());
        Assertions.assertEquals(resource1.getName(), resultado.getName());
        Mockito.verify(resourceRepository, Mockito.times(1)).findById("xxxx");
    }

    @Test
    void lendResource() {

        var resource1 = new Resource("1", "Fernando Trujillo", "La biblia de los caídos", new Date(),
                ResourceCategory.HORROR, ResourceType.BOOK, true);
        var resource2 = new Resource("1", "Fernando Trujillo", "La biblia de los caídos",
                new Date(), ResourceCategory.HORROR, ResourceType.BOOK, false);
        var message = new NotificationMessage(true, "El material no esta disponible y te fue prestado el: "
                + new Date());


        Mockito.when(resourceRepository.findById(any())).thenReturn(Optional.of(resource1));
        Mockito.when(resourceRepository.save(any())).thenReturn(resource2);
        var result = resourceService.lendResource(resource1.getId());

        Assertions.assertEquals(message.getMessage(), result.getMessage());
        Assertions.assertEquals(message.isState(), result.isState());

    }

    @Test
    void returnResource() {

        var resource1 = new Resource("1", "Fernando Trujillo", "La biblia de los caídos", new Date(),
                ResourceCategory.HORROR, ResourceType.BOOK, false);
        var resource2 = new Resource("1", "Fernando Trujillo", "La biblia de los caídos", new Date(),
                ResourceCategory.HORROR, ResourceType.BOOK, true);
        var message = new NotificationMessage(true, "El material fue entregado con éxito");


        Mockito.when(resourceRepository.findById(any())).thenReturn(java.util.Optional.of(resource1));
        Mockito.when(resourceRepository.save(any())).thenReturn(resource2);
        var result = resourceService.returnResource(resource1.getId());

        Assertions.assertEquals(message.getMessage(), result.getMessage());
        Assertions.assertEquals(message.isState(), result.isState());

    }

    @Test
    void findByCategoryAndType() {

        var resource = new Resource("1", "Fernando Trujillo", "La biblia de los caídos", new Date(),
                ResourceCategory.HORROR, ResourceType.BOOK, true);
        var list = new ArrayList<Resource>();
        list.add(resource);

        Mockito.when(resourceRepository.findByResourceCategoryAndResourceType(ResourceCategory.HORROR,
                ResourceType.BOOK)).thenReturn(list);

        ResourceMapper resourceMapper = new ResourceMapper();
        var result = resourceService.findByResourceCategoryAndResourceType(
                resourceMapper.fromResourceToDto(resource));

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(resource.getId(), result.get(0).getId());
        Assertions.assertEquals(resource.getName(), result.get(0).getName());

        Mockito.verify(resourceRepository, Mockito.times(1))
                .findByResourceCategoryAndResourceType(
                        ResourceCategory.HORROR,
                        ResourceType.BOOK);
    }
}