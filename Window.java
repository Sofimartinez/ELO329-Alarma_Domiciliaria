/**
 * A window with its magnetic sensor.
 */
public class Window {
    public Window(int zone, WindowView view) {
        magneticSensor = new MagneticSensor(zone);
        state = State.CLOSE;
        wView = view;
        wView.addMagneticSensorView(magneticSensor.getView());
        wView.setWindowModel(this);
    }
    public void changeState() {  // is called when this window's view is clicked
        switch (state) {
            case OPEN, OPENING: {
                state = State.CLOSING;
                break;
            }
            case CLOSE, CLOSING: {
                state = State.OPENING;
                magneticSensor.setSensorOpen();
                break;
            }
        }
    }
    public void finishMovement() { // is called when this window ends closing or opening
        switch (state){
            case OPENING: {
                state = State.OPEN;
                magneticSensor.setSensorOpen();
                break;
            }
            case CLOSING:{
                state = State.CLOSE;
                magneticSensor.setSensorClose();
                break;
            }
        }
    }
    public WindowView getView(){
        return wView;
    }
    public State getState(){
        return state;
    }
    private final WindowView wView;
    private final MagneticSensor magneticSensor;
    private State state;
}

