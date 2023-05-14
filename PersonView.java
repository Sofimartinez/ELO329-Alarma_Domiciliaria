import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

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
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemDelete = new MenuItem("Delete");
        contextMenu.getItems().addAll(menuItemDelete);

        //Person Event
        makeDraggable(person, contextMenu);
        deletePerson(person, contextMenu);

        menuItemDelete.setOnAction( e -> {
            int indexView = personModel.getHouse().getChildren().indexOf(this);
            if(indexView != -1){
                if(!personModel.getHouse().getChildren().isEmpty()){
                    personModel.getHouse().getChildren().remove(indexView);
                }
            }
            int indexModel = personModel.getCentral().getPersons().indexOf(personModel);
            if(indexModel != -1){
                if(!personModel.getCentral().getPersons().isEmpty()) {
                    personModel.getCentral().deleteNewPerson(indexModel);
                }
            }
        });

        getChildren().addAll(person);
    }

    private void deletePerson(Group person, ContextMenu contextMenu){
        person.setOnContextMenuRequested( e -> {
            //pressed button right
            contextMenu.show(person, e.getScreenX(), e.getScreenY());
        });
    }

    /*private void makeDraggable(Group person, ContextMenu contextMenu){
        Delta delta = new Delta();

        person.setOnMousePressed((e ->{
            contextMenu.hide();
            //Se obtiene la posición del mouse con respecto a la imagen
            delta.x = e.getSceneX() - person.getLayoutX();
            delta.y = e.getSceneY() - person.getLayoutY();
        }));

        person.setOnMouseDragged(e -> {
            contextMenu.hide();
            Double sceneX = e.getSceneX();
            Double sceneY = e.getSceneY();
            //Se mueve la vista persona y además se modifica las propiedades de Person para conectarlo con el PIR
            person.setLayoutX(sceneX - delta.x);
            person.setLayoutY(sceneY - delta.y);
            personModel.horizontalMove(sceneX-10);
            personModel.verticalMove(sceneY-10);
        });

    }*/
    private void makeDraggable(Group node, ContextMenu contextMenu){
        //Delta delta = new Delta();
        Mouse mouse = new Mouse();

        node.setOnMousePressed((e ->{
            //Se obtiene la posición del mouse con respecto a la imagen
            mouse.x = e.getSceneX();
            mouse.y = e.getSceneY();

            //System.out.println(delta.x);
        }));

        node.setOnMouseDragged(e -> {
            contextMenu.hide();
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

            //System.out.println("Centro del grupo: (" + centerX + ", " + centerY + ")");
        });


    }

    public void  setPersonModel(Person model){
        personModel = model;
    }
    protected class Delta{double x,y;}
    private Person personModel;
    private Group person;
}
