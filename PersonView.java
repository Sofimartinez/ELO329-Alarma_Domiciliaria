import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class PersonView extends Group {
    public PersonView(){
        makePerson();
    }
    public void makePerson() {
        Ellipse body = new Ellipse(350, 100, 25, 15);
        body.setFill(Color.ORANGE);
        body.setStroke(Color.web("F57B50"));
        Ellipse armLeft = new Ellipse(325, 100, 6,8);
        armLeft.setFill(Color.ORANGE);
        armLeft.setStroke(Color.web("F57B50"));
        Ellipse armRight = new Ellipse(375, 100, 6,8);
        armRight.setFill(Color.ORANGE);
        armRight.setStroke(Color.web("F57B50"));
        Ellipse head = new Ellipse(350, 100, 13,15);
        head.setFill(Color.BLACK);
        head.setStroke(Color.BLACK);
        person = new Group(body, armLeft, armRight, head);

        //Person Event
        makeDraggable(person);
        getChildren().addAll(person);
    }

    private void makeDraggable(Group node){
        Delta delta = new Delta();

        node.setOnMousePressed((e ->{
            //Se obtiene la posición del mouse con respecto a la imagen
            delta.x = e.getSceneX() - node.getLayoutX();
            delta.y = e.getSceneY() - node.getLayoutY();
        }));

        node.setOnMouseDragged(e -> {
            Double sceneX = e.getSceneX();
            Double sceneY = e.getSceneY();
            //Se mueve la vista persona y además se modifica las propiedades de Person para conectarlo con el PIR
            node.setLayoutX(sceneX - delta.x);
            node.setLayoutY(sceneY - delta.y);
            personModel.horizontalMove(sceneX-10);
            personModel.verticalMove(sceneY-10);
        });


    }
    public void  setPersonModel(Person model){
        personModel = model;
    }
    protected class Delta{double x,y;}
    private Person personModel;
    private Group person;
}
