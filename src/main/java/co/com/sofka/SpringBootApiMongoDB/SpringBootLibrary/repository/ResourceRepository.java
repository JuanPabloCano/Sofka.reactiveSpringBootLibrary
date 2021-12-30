package co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.repository;

import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.enums.ResourceCategory;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.enums.ResourceType;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.model.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends MongoRepository<Resource, String> {

    Resource findByName(String bookName);
    List<Resource> findByResourceCategoryAndResourceType(ResourceCategory resourceCategory, ResourceType resourceType);
}
