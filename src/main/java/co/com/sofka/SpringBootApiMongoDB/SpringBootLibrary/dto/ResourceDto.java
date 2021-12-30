package co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.dto;

import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.enums.ResourceCategory;
import co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto implements Serializable {

    private String Id;
    private String author;
    private String name;
    private Date returnDate;
    private ResourceCategory resourceCategory;
    private ResourceType resourceType;
    private boolean isAvailable;

}
