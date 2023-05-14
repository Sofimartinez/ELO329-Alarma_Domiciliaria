import java.util.ArrayList;

public class PIR_Detector extends Sensor{
    public PIR_Detector(PIR_DetectorView view, int zone){
        super(zone);
        pView = view;
        pView.setPIRModel(this);
    }
    public void detectedMovementSwitch() {
        setState(SwitchState.OPEN);
    }
    public void notMovementSwitch() {
        setState(SwitchState.CLOSE);
    }
    public PIR_DetectorView getView(){
        return pView;
    }

    public void checkPerson(ArrayList<Person> persons){
        Double x_person, y_person;
        Double range_person, angle_person;
        float x = pView.getX()+15;
        float y = pView.getY();
        float inicial_angle = pView.getDirection_angle() - pView.getSensing_angle()/2;
        float final_angle = pView.getDirection_angle() + pView.getSensing_angle()/2;

        int sensing_range = pView.getSensing_range();

        boolean detected_person = false;

        for (int i=0; i < persons.size(); i++) {
            x_person = persons.get(i).getX();
            y_person = persons.get(i).getY();

            angle_person = Math.toDegrees(Math.atan2((x_person-x),(y_person-y)));

            if(pView.getDirection_angle() > 180){
                angle_person = pView.getDirection_angle() + angle_person;
            }

            range_person = Math.sqrt(Math.pow((x_person - x), 2) + Math.pow((y_person - y), 2));

            if(angle_person < 0){
                angle_person = 360 + angle_person;
            }

            if(angle_person >= inicial_angle && angle_person <= final_angle && range_person <= sensing_range){
                detected_person = true;
                break; //Dado que se detecto una persona en el rango del detector no se necesita buscar si mas personas estan dentro del area de este PIR
            }
        }
        if(detected_person){
            detectedMovementSwitch();
        }else {
            //Para cambiar el switch en caso que en step anterior haya estado activado
            notMovementSwitch();
        }
    }
    private final PIR_DetectorView pView;

}
