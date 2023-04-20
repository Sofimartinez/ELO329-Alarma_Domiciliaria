import java.util.ArrayList;

public class Central {
    public Central(){
        zone0 = new ArrayList<Sensor>();
        zone1 = new ArrayList<Sensor>();
        zone2 = new ArrayList<Sensor>();
        isArmed = 0;
        siren = null;
    }
    public void arm() {
        int zonesOpen = ZoneOpen('a');
        if( zonesOpen == 0){
            isArmed=1;
            System.out.println("Alarma armada");
        }else{
            isArmed=0;
            System.out.println("No se pudo armada la alarma porque hay " + zonesOpen + " zonas abiertas");
        }
    }
    public void armPerimeter() {
        int zonesOpen = ZoneOpen('p');
        if( zonesOpen == 0){
            isArmed=2;
            System.out.println("Alarma nocturna armada");
        }else{
            isArmed=0;
            System.out.println("No se puede armar la alarma porque hay " + zonesOpen + " zona(s) abierta(s)");
        }
    }
    public void disarm() {
        isArmed=0;
    }
    public void setSiren(Siren s) {
        siren=s;
    }
    public void addNewSensorZone0(Sensor s){
        zone0.add(s);
    }
    public void addNewSensorZone1(Sensor s){
        zone1.add(s);
    }
    public void addNewSensorZone2(Sensor s){
        zone2.add(s);
    }

    private int ZoneOpen(char type){
        int zoneOpen = 0;

        switch (type){
            case 'p':
                for(int i=0; i < zone0.size(); i++){
                    if(zone0.get(i).getState() == SwitchState.OPEN){
                        zoneOpen++;
                        break;
                    }
                }
                for(int i=0; i < zone1.size(); i++){
                    if(zone1.get(i).getState() == SwitchState.OPEN){
                        zoneOpen++;
                        break;
                    }
                }
                break;
            case 'a':
                for(int i=0; i < zone0.size(); i++){
                    if(zone0.get(i).getState() == SwitchState.OPEN){
                        zoneOpen++;
                        break;
                    }
                }
                for(int i=0; i < zone1.size(); i++){
                    if(zone1.get(i).getState() == SwitchState.OPEN){
                        zoneOpen++;
                        break;
                    }
                }
                for(int i=0; i < zone2.size(); i++){
                    if(zone2.get(i).getState() == SwitchState.OPEN){
                        zoneOpen++;
                        break;
                    }
                }
                break;
        }
        return zoneOpen;
    }

    public void checkZone(){
        int zoneOpen = ZoneOpen(isArmed==2?'p':'a');
        if(isArmed != 0){
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
        return isArmed;
    }
    private ArrayList<Sensor> zone0; // Puerta principal
    private ArrayList<Sensor> zone1; // Puertas y ventanas perimetrales
    private ArrayList<Sensor> zone2; // Detectores PIR
    private int isArmed; // 0 = no armado, 1 = armado, 2 = armado nocturno
    private Siren siren;
}
