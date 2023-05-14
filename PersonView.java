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
        //Delta delta = new Delta();
        Mouse mouse = new Mouse();

        node.setOnMousePressed((e ->{
            //Se obtiene la posición del mouse con respecto a la imagen
            //delta.x = e.getSceneX() - node.getLayoutX();
            //delta.y = e.getSceneY() - node.getLayoutY();
            mouse.x = e.getSceneX();
            mouse.y = e.getSceneY();

            //System.out.println(delta.x);
        }));

        node.setOnMouseDragged(e -> {
            /*Double sceneX = e.getSceneX();
            Double sceneY = e.getSceneY();

            double centerX = node.getBoundsInParent().getWidth() / 2;
            double centerY = node.getBoundsInParent().getHeight() / 2;
            //System.out.println("Centro del grupo: (" + centerX + ", " + centerY + ")");

            //Se mueve la vista persona y además se modifica las propiedades de Person para conectarlo con el PIR
            node.setLayoutX(sceneX - delta.x);
            node.setLayoutY(sceneY - delta.y);

            personModel.horizontalMove(sceneX-centerX);
            personModel.verticalMove(sceneY-centerY);
            //System.out.println((sceneX-centerX) + ", " + (sceneY-centerY));
            */
            double deltaX = e.getSceneX() - mouse.x;
            double deltaY = e.getSceneY() - mouse.y;

            node.setLayoutX(node.getLayoutX() + deltaX);
            node.setLayoutY(node.getLayoutY() + deltaY);

            double centerX = node.getBoundsInParent().getMinX() + node.getBoundsInParent().getWidth()/2;
            double centerY = node.getBoundsInParent().getMinY() + node.getBoundsInParent().getHeight()/2;
            personModel.horizontalMove(centerX);
            personModel.verticalMove(centerY);

            mouse.x = e.getSceneX();
            mouse.y = e.getSceneY();

            System.out.println("Centro del grupo: (" + centerX + ", " + centerY + ")");
        });


    }
    public void  setPersonModel(Person model){
        personModel = model;
    }
    protected class Delta{double x,y;}
    protected class Mouse{double x,y;}
    private Person personModel;
    private Group person;
}
