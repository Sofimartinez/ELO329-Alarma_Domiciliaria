public class Window {
    public Window() {
        magneticSensor = new MagneticSensor();
        // las ventanas se crean cerradas y no activadas
        magneticSensor.putMagnetNearSwitch();
        state = State.CLOSE;
    }
    {
        id = nextId++;
    }
    public void open() {
        state = State.OPEN;
        magneticSensor.moveMagnetAwayFromSwitch();
    }
    public void close() {
        state = State.CLOSE;
        magneticSensor.putMagnetNearSwitch();
    }
    public String getHeader(){
        return "w"+id;
    }
    public int getState(){
        if (state == State.CLOSE)
            return 1;
        else
            return 0;
    }
    private MagneticSensor magneticSensor;
    private State state;
    private final int id;
    private static int nextId=0;
}
