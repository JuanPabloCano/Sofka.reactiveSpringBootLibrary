package co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.model;

import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.enums.ResourceCategory;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.enums.ResourceType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "resource")
public class Resource {

    @Id
    private String Id;
    private String author;
    private String name;
    private Date returnDate = null;
    private ResourceCategory resourceCategory;
    private ResourceType resourceType;
    private boolean isAvailable = true;

}
