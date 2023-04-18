public class Sensor {
    public Sensor(){
        this(SwitchState.CLOSE);
    }
    public Sensor(SwitchState s){
        state = s;
    }
    public SwitchState getState(){
        return state;
    }
    protected void setState(SwitchState s) {
        state = s;
    }
    public String toString(){
        if (state == SwitchState.CLOSE)
            return "1";
        else
            return "0";
    }
    private SwitchState state;
}
