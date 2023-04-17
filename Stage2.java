import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage2 {
    public Stage2() {
        doors = new ArrayList<Door>();
        windows = new ArrayList<Window>();
    }
    public void readConfiguration(Scanner in, boolean ring){
        // reading <#_doors> <#_windows> <#_PIRs>
        central = new Central();
        int numDoors = in.nextInt();
        for (int i = 0; i < numDoors; i++) {
            Door d = new Door();
            doors.add(d);
            central.addNewSensor(d.getMagneticSensor());
        }
        int numWindows = in.nextInt();
        for (int i = 0; i < numWindows; i++) {
            Window w = new Window();
            windows.add(w);
            central.addNewSensor(w.getMagneticSensor());
        }
        in.nextLine();
        String soundFile = in.next();
        siren = new Siren(soundFile, ring);
        central.setSiren(siren);
        in.close();
    }
    public void executeUserInteraction (Scanner in, PrintStream out){
        String command;
        char parameter;
        int i;
        int step=0;
        boolean done=false;
        printHeader(out);
        while (!done) {
            printState(step++, out);
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
                        case 'd':
                            central.disarm();
                            break;
                    }
                    break;
                case 'x': done=true;
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
        out.print("\t"+siren.getHeader());
        out.print("\t"+central.getHeader());
        out.println();
    }
    public void printState(int step, PrintStream out){
        out.print(step);
        for (int i=0; i < doors.size(); i++)
            out.print("\t"+doors.get(i).getState());
        for (int i=0; i < windows.size(); i++)
            out.print("\t"+windows.get(i).getState());
       out.print("\t"+siren.getState());
       out.print("\t"+central.getState());
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
        Stage2 stage = new Stage2();
        stage.readConfiguration(in, ring);
        stage.executeUserInteraction(new Scanner(System.in), new PrintStream(new File("output.csv")));
    }

    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private Central central;
    private Siren siren;
}
