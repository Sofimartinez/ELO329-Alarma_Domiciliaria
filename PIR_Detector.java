public class PIR_Detector{
    public PIR_Detector(MagneticSensor sensor, PIR_DetectorView view){
        magneticSensor = sensor;
        pView = view;
        pView.addMagneticSensorView(magneticSensor.getView());
        pView.setPIRModel(this);
    }

    public PIR_DetectorView getView(){
        return pView;
    }
    private final MagneticSensor magneticSensor;
    private final PIR_DetectorView pView;

}
