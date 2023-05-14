import java.util.ArrayList;

public abstract class Sensor {
    public Sensor(int z){
        this(z, SwitchState.CLOSE);
    }
    public Sensor(int z, SwitchState s){
        zone = z;
        state = s;
    }

    public SwitchState getState(){
        return state;
    }
    public int getZone() {
        return zone;
    }
    protected void setState(SwitchState s) {
        state = s;
    }

    public void setZone(int zone){
        this.zone = zone;
    }
    private SwitchState state;
    private int zone;
}
