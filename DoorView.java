import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ContextMenu;

public class DoorView extends Group {
    public DoorView(int x, int y, int angle){
        makeDoorViewWithoutSensor();
        setRotate(angle);  // to rotate at the geometric center.
        relocate(x,y);
        // getTransforms().add(new Rotate(angle,40,50));  // to rotate at anchor pivot (40,50)
    }
    private void makeDoorViewWithoutSensor(){
        Polygon origenPillar = new Polygon();
        origenPillar.getPoints().addAll(0d,0d,
                0d,20d,
                10d, 20d,
                10d, 10d,
                20d, 10d,
                20d, 0d);
        origenPillar.setFill(Color.BLUE);
        origenPillar.setStroke(Color.BLUE);
        switchPillar = new Polygon(
                160d,0d,
                160d, 10d,
                170d, 10d,
                170d, 20d,
                180d, 20d,
                180d, 0d );
        switchPillar.setFill(Color.BLUE);
        switchPillar.setStroke(Color.BLUE);
        slidingSheet = new Rectangle(10,10,160,10);
        slidingSheet.setFill(Color.BURLYWOOD);
        Rectangle border = new Rectangle(0,0 ,180, 180);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.GRAY);
        border.getStrokeDashArray().addAll(4d,4d );
        getChildren().addAll(border);

        //Menu zone
        ContextMenu contextMenu = new ContextMenu();
        Menu parentMenu = new Menu("Change Zone");
        MenuItem childMenuItem0 = new MenuItem("zone 0");
        MenuItem childMenuItem1 = new MenuItem("zone 1");
        MenuItem childMenuItem2 = new MenuItem("zone 2");
        parentMenu.getItems().addAll(childMenuItem0, childMenuItem1, childMenuItem2);
        contextMenu.getItems().addAll(parentMenu);

        setOnContextMenuRequested( e -> {
            contextMenu.show(slidingSheet, e.getScreenX(), e.getScreenY());
        });

        childMenuItem0.setOnAction(e -> {
            doorModel.getMagneticSensor().setZone(0);
        });
        childMenuItem1.setOnAction(e -> {
            doorModel.getMagneticSensor().setZone(1);
        });
        childMenuItem2.setOnAction(e -> {
            doorModel.getMagneticSensor().setZone(2);
        });

        //Slinding sheet Event
        slidingSheet.setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.PRIMARY){
                contextMenu.hide();
                if(doorModel.getState() == State.CLOSE){
                    slidingSheet.getTransforms().add(new Rotate((slidingSheet.getRotate()-90),170,20));
                    doorModel.getMagneticSensor().getView().getMagnetView().getTransforms().add(new Rotate((-90),170,20));
                }else{
                    slidingSheet.getTransforms().add(new Rotate((slidingSheet.getRotate()+90),170,20));
                    doorModel.getMagneticSensor().getView().getMagnetView().getTransforms().add(new Rotate((90),170,20));
                }
                doorModel.changeState();
            }

        });

        //color change slidingSheet so that the user knows when it can be opened or closed
        slidingSheet.setOnMouseEntered( e -> {
            slidingSheet.setFill(Color.CHOCOLATE);
        });
        slidingSheet.setOnMouseExited(e ->{
            slidingSheet.setFill(Color.BURLYWOOD);
        });

        getChildren().addAll(origenPillar, switchPillar,slidingSheet);
    }
    public void setDoorModel(Door model) {
        doorModel = model;
    }
    public void addMagneticSensorView(MagneticSensorView msView){
        placeMagneticSensor(msView);
        getChildren().add(msView);
    }
    private void placeMagneticSensor( MagneticSensorView mv){
        //Magnetic sensor position
        mv.getMagnetView().setX(slidingSheet.getX());
        mv.getMagnetView().setY(slidingSheet.getY()+slidingSheet.getHeight());

        //Switch position
        mv.getSwitchView().setY(switchPillar.getBoundsInParent().getHeight());
    }

    private Door doorModel;
    private Polygon switchPillar;
    private Rectangle slidingSheet;
}