import javax.sound.midi.SysexMessage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage4 {
    public Stage4() {
        doors = new ArrayList<Door>();
        windows = new ArrayList<Window>();
        pirs = new ArrayList<PIR_Detector>();
        persons = new ArrayList<Person>();
    }
    public void readConfiguration(Scanner in, boolean ring){
        // reading <#_doors> <#_windows> <#_PIRs>
        central = new Central();
        int numDoors = in.nextInt();
        for (int i = 0; i < numDoors; i++) {
            Door d = new Door();
            doors.add(d);
            if(i == 0){
                central.addNewSensorZone0(d.getMagneticSensor());
            }else{
                central.addNewSensorZone1(d.getMagneticSensor());
            }
        }
        int numWindows = in.nextInt();
        for (int j = 0; j < numWindows; j++) {
            Window w = new Window();
            windows.add(w);
            central.addNewSensorZone1(w.getMagneticSensor());
        }
        int numPIR_Detector = in.nextInt();
        int direction, angle, range;
        float x, y;
        String[] line;

        in.nextLine();
        for(int p = 0; p < numPIR_Detector; p++){
            line = in.nextLine().split(" ");

            x = Float.parseFloat(line[0]);
            y = Float.parseFloat(line[1]);
            direction = Integer.parseInt(line[2]);
            angle = Integer.parseInt(line[3]);
            range = Integer.parseInt(line[4]);

            PIR_Detector pir = new PIR_Detector(x,y,direction,angle,range);
            pirs.add(pir);
            central.addNewSensorZone2(pir);
        }
        String soundFile = in.next();
        siren = new Siren(soundFile, ring);
        central.setSiren(siren);
        in.close();
    }
    public void executeUserInteraction (Scanner in, PrintStream out){
        String command;
        char parameter;
        float x, y;
        int i;
        int step=0;
        boolean person=false;
        boolean done=false;
        printHeader(out);
        while (!done) {
            printState(step++, person, out);
            person=false;
            command = in.next();
            if (command.charAt(0)=='x') break;
            switch (command.charAt(0)) {
                case 'd':
                    i = Integer.parseInt(command.substring(1));
                    parameter = in.next().charAt(0);
                    if (parameter== 'o')
                        doors.get(i).open();
                    else
                        doors.get(i).close();
                    break;
                case 'w':
                    i = Integer.parseInt(command.substring(1));
                    parameter = in.next().charAt(0);
                    if (parameter== 'o')
                        windows.get(i).open();
                    else
                        windows.get(i).close();
                    break;
                case 'k':
                    parameter = in.next().charAt(0);
                    switch (parameter) {
                        case 'a':
                            central.arm();
                            break;
                        case 'p':
                            central.armPerimeter();
                            break;
                        case 'd':
                            central.disarm();
                            break;
                    }
                    break;
                case 'c':
                    x =  Float.parseFloat(in.next());
                    y =  Float.parseFloat(in.next());
                    Person p = new Person(x,y);
                    person = true;
                    persons.add(p);
                    break;
                case 'p':
                    i = Integer.parseInt(command.substring(1));
                    parameter = in.next().charAt(0);
                    switch (parameter) {
                        case 'w': //norte
                            persons.get(i).northMove();
                            break;
                        case 'z': //sur
                            persons.get(i).southMove();
                            break;
                        case 's': //este
                            persons.get(i).eastMove();
                            break;
                        case 'a': //oeste
                            persons.get(i).westMove();
                            break;
                    }
                    break;
            }
            //Si se encuentra armada la central se verifica la posición de la persona respecto a los pirs
            if(central.getState() == 1){
                for(int j=0; j < pirs.size(); j++) {
                    pirs.get(j).checkPerson(persons);
                }
            }
            central.checkZone();
        }
    }
    public void printHeader(PrintStream out){
        out.print("Step");
        for (int i=0; i < doors.size(); i++)
            out.print("\t"+doors.get(i).getHeader());
        for (int i=0; i < windows.size(); i++)
            out.print("\t"+windows.get(i).getHeader());
        for (int i=0; i < pirs.size(); i++)
            out.print("\t"+pirs.get(i).getHeader());
        out.print("\t"+siren.getHeader());
        out.print("\t"+central.getHeader());
        out.println();
    }
    public void printState(int step, boolean person, PrintStream out){
        out.print(step);
        for (int i=0; i < doors.size(); i++)
            out.print("\t"+doors.get(i).getState());
        for (int i=0; i < windows.size(); i++)
            out.print("\t"+windows.get(i).getState());
        for (int i=0; i < pirs.size(); i++)
            out.print("\t"+pirs.get(i).toString());
       out.print("\t"+siren.getState());
       out.print("\t"+central.getState());
       if (person){
           out.print("\t"+persons.get(persons.size()-1).getX());
           out.print("\t"+persons.get(persons.size()-1).getY());
       }
       out.println();
    }
    public static void main(String [] args) throws IOException {
        boolean ring= true;
        if (args.length < 1) {
            System.out.println("Usage: java Stage1 <configurationFile.txt>");
            System.exit(-1);
        } else if (args.length > 1) {
            ring= false;
        }
        Scanner in = new Scanner(new File(args[0]));
        //System.out.println("File: " + args[0]);
        Stage4 stage = new Stage4();
        stage.readConfiguration(in, ring);
        stage.executeUserInteraction(new Scanner(System.in), new PrintStream(new File("output.csv")));
    }

    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private ArrayList<PIR_Detector> pirs;
    private Central central;
    private Siren siren;
    private ArrayList<Person> persons;
}
