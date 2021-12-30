package co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.controller;

import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.dto.ResourceDto;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @GetMapping("")
    public ResponseEntity<List<ResourceDto>> findAllResources() {
        return new ResponseEntity(resourceService.findAllResources(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDto> findResourceById(@PathVariable String id) {
        try {
            resourceService.findResourceById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ResourceDto> saveResource(@RequestBody ResourceDto resourceDto) {
        return new ResponseEntity(resourceService.saveResource(resourceDto), HttpStatus.CREATED);
    }

    @GetMapping("/disponible/{id}")
    public ResponseEntity<ResourceDto> isResourceAvailable(@PathVariable("id") String id) {
        return new ResponseEntity(resourceService.isResourceAvailable(id), HttpStatus.OK);
    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity<ResourceDto> lendResource(@PathVariable String id) {
        return new ResponseEntity(resourceService.lendResource(id), HttpStatus.OK);

    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<ResourceDto> returnResource(@PathVariable String id) {
        return new ResponseEntity(resourceService.returnResource(id), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteResource(@PathVariable("id") String id) {
        try {
            resourceService.deleteResourceById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ResourceDto>> findByResourceCategoryAndResourceType(@RequestBody ResourceDto resourceDto) {
        return new ResponseEntity(resourceService.findByResourceCategoryAndResourceType(resourceDto), HttpStatus.OK);
    }
}
