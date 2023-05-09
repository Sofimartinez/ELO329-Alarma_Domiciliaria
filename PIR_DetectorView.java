import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

public class PIR_DetectorView extends Group{
    public PIR_DetectorView(float x, float y, int direction_angle, int sensing_angle, int sensing_range){
        float angle = direction_angle + direction_angle - (sensing_angle/2);
        makePIRViewWithoutSensor(sensing_angle, sensing_range, angle);
        setRotate(direction_angle);  // to rotate at the geometric center.
        relocate(x,y);
    }

    private void makePIRViewWithoutSensor(int sensing_angle, int sensing_range, float angle){
        Polygon pir = new Polygon();
        pir.getPoints().addAll(0d,0d,
                30d, 0d,
                30d, 15d,
                0d, 15d);
        pir.setFill(Color.GREEN);
        pir.setStroke(Color.GREEN);
        Arc detector = new Arc();
        detector.setCenterX(15);
        detector.setCenterY(0);
        detector.setRadiusX(sensing_range);
        detector.setRadiusY(sensing_range);
        detector.setStartAngle(angle);
        detector.setLength(sensing_angle);
        detector.setType(ArcType.ROUND);
        detector.setFill(Color.web("EE6F6F",0.5));
        detector.setStroke(Color.web("EE6F6F",0.5));
        getChildren().addAll(pir, detector);
    }

    public void setPIRModel(PIR_Detector model) {
        pirModel= model;
    }

    public void addMagneticSensorView(MagneticSensorView msView){
        placeMagneticSensor(msView);
        getChildren().add(msView);
    }
    private void placeMagneticSensor( MagneticSensorView mv){

    }

    private PIR_Detector pirModel;
    private Polygon pir;
    private Arc detector;
}
