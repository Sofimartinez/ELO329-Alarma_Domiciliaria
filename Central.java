import java.util.ArrayList;

public class Central {
    public Central(){
        zone0 = new ArrayList<Sensor>();
        isArmed = false;
        siren = null;
    }
    public void arm() {
        int zonesOpen = ZoneOpen();
        if( zonesOpen == 0){
            isArmed=true;
            System.out.println("Alarma armada");
        }else{
            isArmed=false;
            System.out.println("No se puede armar la alarma porque hay " + zonesOpen + " zona(s) abierta(s)");
        }
    }
    public void disarm() {
        isArmed=false;
    }
    public void setSiren(Siren s) {
        siren=s;
    }
    public void addNewSensor(Sensor s){
        zone0.add(s);
    }

    private int ZoneOpen(){
        int zoneOpen = 0;
        for(int i=0; i < zone0.size(); i++){
            if(zone0.get(i).getState() == SwitchState.OPEN){
                zoneOpen++;
                break;
            }
        }
        return zoneOpen;
    }

    public void checkZone(){
        int zoneOpen = ZoneOpen();
        if(isArmed){
            if(zoneOpen > 0 && siren.getState() == 0){
                siren.play();
            }
        }
        else{
            if(siren.getState() == 1){
                siren.stop();
            }
        }

    }
    public String getHeader(){
        return "Central";
    }
    public int getState(){
        return isArmed?1:0;
    }
    private ArrayList<Sensor> zone0; //Puerta principal, Detectores PIR, Puertas y ventanas perimetrales
    private boolean isArmed;
    private Siren siren;
}
