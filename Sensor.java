import java.util.ArrayList;
/**
* @throws Clase abstracta que crea un Sensor definiendo su zona y estado. Posee métodos para actualizar y retornar los estados (OPEN y CLOSE) al igual que actualizar y retornar las zonas de los objetos.
* @author Sofía Martínez, Manuel Torres, Florencia Norambuena, Darael Badilla
*/
public abstract class Sensor {
    /**
    * Es el constructor del sensor. Solo se especifica la zona mientras que el estado del sensor será CLOSE por defecto.
    * @param z indica la zona en la que el sensor será creado
    */
    public Sensor(int z){
        this(z, SwitchState.CLOSE);
    }
    /**
    * Es un constructor en el que se le especifica tanto la zona como el estado que este tendrá.
    * @param z indica la zona en la que el sensor será creado
    * @param s indica el estado con la que el sensor será creado
    */
    public Sensor(int z, SwitchState s){
        zone = z;
        state = s;
    }
    /**
    * Entrega el estado del objeto de la clase SwitchState (OPEN, CLOSE) para saber si se debe de activar o no la sirena.
    * @return <tt>state</tt> que del switch. 
    */
    public SwitchState getState(){
        return state;
    }
    /**
    * Retorna la zona del objeto que permitirá saber si se activa o no la sirena en caso de que detecte algo.
    * @return <tt>zone</tt> retorna la zona del objeto
    */
    public int getZone() {
        return zone;
    }
    /**
    * Configura el estado del objeto en caso que se desactive o si se detecta algo.
    * @param s estado que varía entre OPEN y CLOSE
    */
    protected void setState(SwitchState s) {
        state = s;
    }
    /** 
    * Configura la zona en la que se encuentra el objeto.
    * @param zone número entero entre 0 y 2
    */
    public void setZone(int zone){
        this.zone = zone;
    }
    //Campos Privados
    /**
    * Estado del switch varía entre OPEN y CLOSE
    */
    private SwitchState state;
    /**
    * Entero que varía entre 0 y 2, donde 0 y 1 equivalen al perímetro mientras que el entero 2 representa la zona interior a la casa.
    */
    private int zone;
}
