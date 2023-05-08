import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

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
        border = new Rectangle(0,0 ,180, 180);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.GRAY);
        border.getStrokeDashArray().addAll(4d,4d );
        getChildren().addAll(border);
        getChildren().addAll(origenPillar, switchPillar,slidingSheet);
    }
    public void setDoorModel(Door model) {
        doorModel = model;
        //Slinding sheet Event
        setOnMouseClicked(e -> {
            if(doorModel.getState() == State.CLOSE){
                slidingSheet.getTransforms().add(new Rotate((slidingSheet.getRotate()-90),170,20));
            }else{
                slidingSheet.getTransforms().add(new Rotate((slidingSheet.getRotate()+90),170,20));
            }

            doorModel.changeState();
        });
    }
    public void addMagneticSensorView(MagneticSensorView msView){
        placeMagneticSensor(msView);
        getChildren().add(msView);
    }
    private void placeMagneticSensor( MagneticSensorView mv){
        //Magnetic sensor position
        mv.getMagnetView().setX(slidingSheet.getX());
        mv.getMagnetView().setY(slidingSheet.getY()+slidingSheet.getHeight());
        
        border.setOnMouseClicked(e -> {

            if(doorModel.getState() == State.CLOSE){
                mv.getMagnetView().getTransforms().add(new Rotate((-90),170,20));

            }else{
                mv.getMagnetView().getTransforms().add(new Rotate((+90),170,20));   //En el momento que se haga click en el borde, hará la misma rotación que la puerta
            }

     
        });
        
        
        //Switch position
        mv.getSwitchView().setY(switchPillar.getBoundsInParent().getHeight());
    }

    private Door doorModel;
    private Polygon switchPillar;
    private Rectangle slidingSheet;
    private Rectangle border;
}
