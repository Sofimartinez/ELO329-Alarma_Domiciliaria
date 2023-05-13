import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.ArrayList;

public class Central {
    public Central(Siren siren){
        view = new CentralView(this);
        zones = new ArrayList<Sensor>();
        state = CentralState.DISARMED;
        this.siren = siren;
        periodicCheck = new Timeline(new KeyFrame(Duration.millis(200),
                e-> checkZones()));
        periodicCheck.setCycleCount(Animation.INDEFINITE);
    }
    public VBox getView (){
        return view;
    }
    public void armAll() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"-") + (!close[2]?",2":"");

        if(state != CentralState.ALL_ARMED) { //Se evitar armar por completo de nuevo si es que esta hecho
            if(close[0] && close[1] && close[2] ) {
                state = CentralState.ALL_ARMED;
                periodicCheck.play();
                view.setDisplay("Armed");
            }
            else view.setDisplay(msg);
        }

    }
    public void armPerimeter() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"");
        if(state != CentralState.PERIMETER_ARMED) { //Se evitar armar de nuevo el perimetro si es que esta hecho
            if (close[0] && close[1]) {
                state = CentralState.PERIMETER_ARMED;
                periodicCheck.play();
                view.setDisplay("Armed perimeter");
            } else view.setDisplay(msg);
        }
    }
    public void disarm() {
        state = CentralState.DISARMED;
        siren.stop();
        periodicCheck.stop();
        view.setDisplay("Disarmed");
    }
    private boolean[] checkCloseZones() {
        boolean[] close = {true, true, true};
        for (Sensor sensor : zones) {
            close[sensor.getZone()] &= (sensor.getState() == SwitchState.OPEN) ? false : true ;
        }
        return close;
    }
    public void addNewSensor(Sensor s){
        zones.add(s);
    }
    private void checkZones(){
        boolean[] close = checkCloseZones();
        switch (state) {
            case ALL_ARMED -> {
                if(!(close[0] && close[1] && close[2])) {
                    siren.play();
                }
            }
            case PERIMETER_ARMED -> {
                if(!(close[0] && close[1])) {
                    siren.play();
                }
            }
        }
    }
    enum CentralState {
        ALL_ARMED, PERIMETER_ARMED, DISARMED
    }
    private final ArrayList<Sensor> zones;
    private CentralState state;
    private final Siren siren;
    private final Timeline periodicCheck;
    private final CentralView view;
}
