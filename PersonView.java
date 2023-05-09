import javafx.scene.image.*;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
public class PersonView extends Group {
    public PersonView(){
        makePerson();
    }
    public void makePerson() {
        Image personaImg = new Image(getClass().getResourceAsStream("person-from-above.png"));
        personaView = new ImageView(personaImg);
        //Se ajusta el ancho de la imagen
        personaView.setFitWidth(personaImg.getWidth()*0.1);
        personaView.setFitHeight(personaImg.getHeight()*0.1);

        //Se establece la primera posición de la imagen
        personaView.setLayoutX(350);
        personaView.setLayoutY(100);
        getChildren().add(personaView);
    }
    public void  setPersonModel(Person model){
        Delta delta = new Delta();
        persona = model;
        personaView.setOnMousePressed((e ->{
            //Se obtiene la posición del mouse con respecto a la imagen
            delta.x = personaView.getLayoutX() - e.getSceneX();
            delta.y = personaView.getLayoutY() - e.getSceneY();
        }));

        personaView.setOnMouseDragged(e->{
            //Se mueve la imagen a donde está el Mouse y además se modifica las propiedades de Person para conectarlo con el PIR
            personaView.setLayoutX(e.getSceneX() + delta.x);
            personaView.setLayoutY(e.getSceneY() + delta.y);

            double x = e.getSceneX() - (personaView.getImage().getWidth()/2);
            double y = e.getSceneY() - (personaView.getImage().getHeight()/2);

            //Agregar linea de abajo para revisar las posciciones x e y
            //System.out.println("Pos x: "+ x + " Pos y: " + y);
            persona.horizontalMove(x);
            persona.verticalMove(y);
        });
    }
    protected class Delta{double x,y;}
    private Person persona;
    private ImageView personaView;
}
