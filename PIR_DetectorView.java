import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

public class PIR_DetectorView extends Group{
    public PIR_DetectorView(float x, float y, int direction_angle, int sensing_angle, int sensing_range){
        this.x = x;
        this.y = y;
        this.sensing_angle = sensing_angle;
        this.sensing_range = sensing_range;
        this.direction_angle = direction_angle;
        makePIRViewWithoutSensor(sensing_angle, sensing_range, direction_angle);
        setRotate(direction_angle);  // to rotate at the geometric center.
        relocate(x,y);
    }

    private void makePIRViewWithoutSensor(int sensing_angle, int sensing_range, int direction_angle){
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
        detector.setStartAngle(direction_angle + direction_angle - (sensing_angle/2));
        detector.setLength(sensing_angle);
        detector.setType(ArcType.ROUND);
        detector.setFill(Color.web("EE6F6F",0.5));
        detector.setStroke(Color.web("EE6F6F",0.5));
        getChildren().addAll(pir, detector);
    }

    public void setPIRModel(PIR_Detector model) {
        pirModel= model;
    }

    public float getDirection_angle(){
        return direction_angle;
    }

    public int getSensing_angle(){
        return sensing_angle;
    }
    public int getSensing_range(){
        return sensing_range;
    }

    public float getY() {
        return y;
    }

    public float getX(){
        return x;
    }

    private PIR_Detector pirModel;
    private float x;
    private float y;
    private float direction_angle; //Angulo de orientacion
    private int sensing_angle; //Angulo de deteccion
    private int sensing_range; //Rango de deteccion
}
