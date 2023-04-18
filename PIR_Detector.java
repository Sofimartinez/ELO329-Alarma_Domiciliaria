import java.util.ArrayList;
import java.lang.Math;

public class PIR_Detector extends Sensor {
    public PIR_Detector(float x, float y, int direction, int angle, int range){
        this.x=x;
        this.y=y;
        direction_angle= direction;
        sensing_angle= angle;
        sensing_range= range;
    }
    {
        id = nextId++; 
    }
    public void detectedMovementSwitch() {
        setState(SwitchState.OPEN);
    }
    public void notMovementSwitch() {
        setState(SwitchState.CLOSE);
    }
    public String getHeader(){
        return "Pir"+id;
    }
    public void checkPerson(ArrayList<Person> persons){
        float x_person, y_person;
        Double range_person, angle_person;
        float inicial_angle = direction_angle - (sensing_angle/2);
        float final_angle =  direction_angle + (sensing_angle/2);
        boolean detected_person = false;

        for (int i=0; i < persons.size(); i++){
            x_person = persons.get(i).getX();
            y_person = persons.get(i).getY();
            Double a = (double) (y_person-y);
            Double b = (double) (x_person-x);

            angle_person = Math.toDegrees(Math.atan2(a,b));
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

    private float x;
    private float y;
    private int direction_angle; //Angulo de dirección
    private int sensing_angle; //Angulo de deteccion
    private int sensing_range; //Rango de deteccion
    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
}