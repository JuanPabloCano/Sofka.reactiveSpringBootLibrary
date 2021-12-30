package co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessage {

    private boolean state;
    private String message;

    public NotificationMessage showResourceAvailability(Boolean state , Date date){
        if(Boolean.TRUE.equals(state)){
            return new NotificationMessage(true,  "El material esta disponible");
        }
        return new NotificationMessage(false,("El material fue prestado el: "+date));
    }
    public NotificationMessage showResourceLending(Boolean state , Date date){
        if(Boolean.TRUE.equals(state)){
            return new NotificationMessage(true,  "El material esta disponible y te fue prestado el: " + date);
        }
        return new NotificationMessage(false,("El material no esta disponible y te fue prestado el: "+ date));
    }
    public NotificationMessage showResourceDevolution(Boolean state , Date date){
        if(Boolean.FALSE.equals(state)){
            return new NotificationMessage(true,  "El material fue entregado con éxito");
        }
        return new NotificationMessage(false,("El material no esta prestado" ));
    }
}
