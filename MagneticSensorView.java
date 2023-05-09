import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MagneticSensorView extends Group {
    public MagneticSensorView () {
        switchView = new Rectangle(6,6); //Fixed
        setCloseView();
        magnetView = new Rectangle(6,6); //Movable
        magnetView.setFill(Color.BLACK);
        getChildren().addAll(magnetView,switchView);
    }
    public void setCloseView(){
        switchView.setFill(Color.GREEN);
    }
    public void setOpenView(){
        switchView.setFill(Color.RED);
    }
    public Rectangle getSwitchView(){
        return switchView;
    }
    public Rectangle getMagnetView(){
        return magnetView;
    }

    private final Rectangle switchView;

    private final Rectangle magnetView;
}
