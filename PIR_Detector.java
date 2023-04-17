import java.util.ArrayList;
import java.util.Math;
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
        float range_person, x_person, y_person, range_person;
        float inicial_angle = direction_angle - (sensing_angle/2);
        float final_angle =  direction_angle + (sensing_angle/2);
        boolean detected_person = false;

        for (int i=0; i < persons.size(); i++){
            x_person = persons.get(i).getX();
            y_person = persons.get(i).getY();
            range_person = Math.sqrt((x_person - x)^2 + (y_person - y)^2);

            if(x_person >= inicial_angle && y_person <= final_angle && range} <= sensing_range){
                detected_person = true;
                break; //Dado que se detecto una persona en el rango del detector no se necesita buscar si mas personas estan dentro del area de este PIR
            }
        }
        if(detected_person){
            System.out.println("persona detectada");
            detectedMovementSwitch();
        }else {
            //Para cambiar el switch en caso que en step anterior haya estado activado
            System.out.println("no hay persona en el rango");
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