import java.util.ArrayList;
/**
* Clase que crea un PIR y simula su comportamiento.
* @author Sofía Martínez, Manuel Torres, Florencia Norambuena, Darael Badilla
*/

public class PIR_Detector extends Sensor{
    /**
    * Constructor del PIR, inicializa un nuevo PIR.
    * @param view   Objeto visual del PIR.  
    * @param zone   zona a la que pertenece el objeto.
    */
    public PIR_Detector(PIR_DetectorView view, int zone){
        super(zone);
        pView = view;
        pView.setPIRModel(this);
    }
    /**
    * Al detectarse un movimiento, se actualiza el estado del switch a OPEN.
    */
    public void detectedMovementSwitch() {
        setState(SwitchState.OPEN);
    }
        /**
    * Si no se detecta movimiento, se actualiza el estado del switch a CLOSE.
    */
    public void notMovementSwitch() {
        setState(SwitchState.CLOSE);
    }
    /**
    * Recupera el objeto creado en PIR_DetectorView y que es enlazado al objeto creado en PIR_Detector.
    * @return el <tt>pView</tt> que fue enlazado con el PIR.
    */
    public PIR_DetectorView getView(){
        return pView;
    }
    /**
    * Busca si es que hay una persona dentro de la lista que está ubicada dentro del rango del PIR. Si es así, se activa el método detectedMovementSwitch(). Caso contrario se activa notMovementSwitch()
    * @param persons lista de la clase personas
    */
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
    // Campos Privados.
    /**
    *   Objeto de características visuales que se relaciona con el PIR.
    */
    private final PIR_DetectorView pView;

}
