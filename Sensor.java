public class Sensor {
    public Sensor(int z){
        this(z, true);
    }
    public Sensor(int z, boolean close){
        zone = z;
        isClose = close;
    }
    public boolean isClose(){
        return isClose;
    }
    public int getZone() {
        return zone;
    }
    protected void setClose(boolean close) {
        isClose = close;
        configState();
    }
    private void configState(){         //Para no modificar el código de ayuda ni tampoco el código del Stage1, se crea esta función que cambia el estado state dependiendo del booleano
        if (isClose == true){
            state = SwitchState.CLOSE;
        }
        else{state = SwitchState.OPEN;}
    }
    private void configBoolean() {      //Acá permite modificar el booleano según el estado (para no modificar el los .java del Stage1)
        if(state == SwitchState.OPEN){
            isClose = false;
        }
        else{isClose = true;}
    }
    public void setSensorOpen(){
        state = SwitchState.OPEN;
        configBoolean();
    }
    public void setSensorClose(){
        state  = SwitchState.CLOSE;
        configBoolean();
    }
    public SwitchState getState(){return state;}
    private SwitchState state;
    private boolean isClose;
    private int zone;
