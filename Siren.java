import java.io.File;
import java.net.URL;

public class Siren {
    public Siren (String soundFileName, boolean ring){
        try {
            dir = new File(soundFileName).toURI().toURL();
        }
        catch (Exception exc){
            exc.printStackTrace(System.out);
        }
        isSounding=false;
        this.ring=ring;
    }
    public void play(){
        isSounding=true;
        if(ring){
            aWave= new AePlayWave(dir);
            aWave.start();
        }else{
            System.out.println("Sirena sonando");
        }

    }
    public void stop(){
        if(ring){
            isSounding=false;
            aWave.stopSounding();
        }else{
            isSounding=false;
            System.out.println("Sirena silenciada");
        }
    }
    public String getHeader() {
        return "Siren";
    }
    public int getState() {
        return isSounding?1:0;
    }
    private URL dir;
    private boolean isSounding;
    private AePlayWave aWave;
    private boolean ring;
}
