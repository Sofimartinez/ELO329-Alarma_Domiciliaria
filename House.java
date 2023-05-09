import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Scanner;

public class House extends Pane {
    public House(Scanner in, Central central) {
        // reading <#_doors> <#_windows> <#_PIRs>
        int numDoors = in.nextInt();
        int numWindows = in.nextInt();
        int numPIR = in.nextInt();
        for (int i = 0; i < numDoors; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int angle = in.nextInt();
            int zone = in.nextInt();
            DoorView doorView = new DoorView(x,y,angle);
            MagneticSensor sensor = new MagneticSensor(zone);
            central.addNewSensor(sensor);
            Door door = new Door(sensor, doorView);
            getChildren().add(door.getView());
        }
        for (int i = 0; i < numWindows; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int angle = in.nextInt();
            int zone = in.nextInt();
            WindowView windowView= new WindowView(x, y, angle);
            MagneticSensor sensor = new MagneticSensor(zone);
            central.addNewSensor(sensor);
            Window window = new Window(sensor, windowView);
            getChildren().add(window.getView());
        }
        for (int i = 0; i < numPIR; i++){
            float x = Float.parseFloat(in.next());
            float y = Float.parseFloat(in.next());
            int direction_angle = in.nextInt();
            int sensing_angle = in.nextInt();
            int sensing_range = in.nextInt();
            int zone = in.nextInt();
            PIR_DetectorView pirView= new PIR_DetectorView(x, y, direction_angle, sensing_angle,sensing_range);
            MagneticSensor sensor = new MagneticSensor(zone);
            central.addNewSensor(sensor);
            PIR_Detector pir = new PIR_Detector(sensor,pirView);
            getChildren().add(pir.getView());
        }
        PersonView personview = new PersonView();
        Person persona = new Person(personview);
        getChildren().add(persona.getView());
        setMinWidth(700);
    }
}
