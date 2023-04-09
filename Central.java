import java.util.ArrayList;

public class Central {
    public Central(){
        zone0 = new ArrayList<Sensor>();
        isArmed = false;
        siren = null;
    }
    public void arm() {
        isArmed=checkZoneArm()?true:false;
    }
    public void disarm() {
        isArmed=false;
    }
    public void setSiren(Siren s) {
        siren=s;
        System.out.println(siren);
        System.out.println(s);
    }
    public void addNewSensor(Sensor s){
        zone0.add(s);
    }
    public boolean checkZoneArm(){
        int zoneOpen = 0;
        for(int i=0; i < zone0.size(); i++){
            System.out.println(zone0.get(i).getState());
            if(zone0.get(i).getState() == SwitchState.OPEN){
                zoneOpen++;
                break;
            }
        }
        return (zoneOpen > 0)?false:true;
    }

    public void checkZone(){
        int zoneOpen = 0;
        for(int i=0; i < zone0.size(); i++){
            if(zone0.get(i).getState() == SwitchState.OPEN){
                zoneOpen++;
                break;
            }
        }
        if(zoneOpen > 0){
            System.out.println(zoneOpen + " zonas no cerradas");
            if(isArmed){
                siren.play();
            }
        }
        else{
            System.out.println("Todas las zonas cerradas");
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
    private ArrayList<Sensor> zone0;
    private boolean isArmed;
    private Siren siren;
}
