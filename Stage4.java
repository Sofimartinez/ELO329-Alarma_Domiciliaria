import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Stage4 extends Application {
    @Override
    public void start(Stage primaryStage) {
        List<String> args = getParameters().getRaw();
        if (args.size() != 1) {
            System.out.println("Usage: java Stage4 <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = null;
        try {
            in = new Scanner(new File(args.get(0)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Siren siren = new Siren("http://profesores.elo.utfsm.cl/~agv/elo329/JavaProg/JavaFX/AudioMediaDemo/siren.wav");
        Central central = new Central(siren);
        House house = new House(in, central);
        in.close();
        VBox vBox = new VBox(20);
        Separator hSeparator = new Separator(Orientation.HORIZONTAL);
        //Menu
        MenuBar menuBar = new MenuBar();
        Menu menuInsert = new Menu("Insert");
        Menu menuDelete = new Menu("Delete");
        menuBar.getMenus().addAll(menuInsert, menuDelete);
        MenuItem menuInsertPerson = new MenuItem("Person");
        MenuItem menuDeletePerson = new MenuItem("Person");
        menuInsert.getItems().add(menuInsertPerson);
        menuDelete.getItems().addAll(menuDeletePerson);

        menuInsertPerson.setOnAction(e -> insertPerson(house,central));
        menuDeletePerson.setOnAction(e -> deletePerson(house,central));

        vBox.getChildren().addAll(siren.getView(),hSeparator, central.getView(),menuBar);
        vBox.setPadding(new Insets(10, 10,10,10));
        vBox.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(house);
        borderPane.setCenter(new Separator(Orientation.VERTICAL));
        borderPane.setRight(vBox);
        borderPane.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ELO329: T2 Stage 4");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private void insertPerson(House house, Central central){
        PersonView personView = new PersonView();
        Person person = new Person(personView, central, house);
        central.addNewPerson(person);
        house.getChildren().add(person.getView());
    }

    //Elimina la ultima persona insertada
    public void deletePerson(House house, Central central){
        central.deleteNewPerson();
        if(house.getConfigInitial() < house.getChildren().size()){
            house.getChildren().remove( house.getChildren().size()-1);
        }
    }
}

