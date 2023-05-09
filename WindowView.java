import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class WindowView extends Group {
    public WindowView(int x, int y, int angle){
        makeWindowViewWithoutSensor();
        setRotate(angle);  // to rotate at the geometric center.
       // getTransforms().add(new Rotate(angle,40,50));  // to rotate at anchor pivot (40,50)
        relocate(x,y);
        prepareOpen_CloseTransition();
    }

    private void makeWindowViewWithoutSensor(){
        Rectangle origenPillar = new Rectangle(0, 0, 20, 20);
        origenPillar.setFill(Color.BLUE);
        origenPillar.setStroke(Color.BLUE);
        switchPillar = new Rectangle(180, 0, 20, 20);
        switchPillar.setFill(Color.BLUE);
        switchPillar.setStroke(Color.BLUE);
        Rectangle fixedGlass = new Rectangle(21, 4, 82, 6);
        fixedGlass.setFill(Color.LIGHTBLUE);
        slidingGlass = new Rectangle(97,11,82,6);
        slidingGlass.setFill(Color.LIGHTBLUE);
        Rectangle border = new Rectangle(0, 0, 200, 20);
        border.setFill(Color.WHITE);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(1);
        border.getStrokeDashArray().addAll(4d,4d );
        getChildren().add(border);

        //Slinding glass Event
        slidingGlass.setOnMouseClicked(e -> {
            switch (winModel.getState()){
                case OPEN, OPENING -> {
                    startClosing();
                    break;
               }
               case CLOSE, CLOSING -> {
                   startOpening();
                   break;
               }
            }
            winModel.changeState();
        });
        getChildren().addAll(origenPillar, switchPillar, fixedGlass,slidingGlass);
    }
    public void setWindowModel(Window model) {
        winModel = model;
    }
    public void addMagneticSensorView(MagneticSensorView msView){
        placeMagneticSensor(msView);
        getChildren().add(msView);
    }
    private void placeMagneticSensor( MagneticSensorView mv){
        //Magnetic sensor position
        mv.getMagnetView().setX(slidingGlass.getX()+slidingGlass.getWidth()-mv.getMagnetView().getWidth());
        mv.getMagnetView().setY(slidingGlass.getY()+slidingGlass.getHeight());
        mv.getMagnetView().translateXProperty().bind(slidingGlass.translateXProperty()); // so it moves along with window

        //Switch position
        mv.getSwitchView().setX(switchPillar.getX());
        mv.getSwitchView().setY(switchPillar.getY()+switchPillar.getHeight());

    }
    private void prepareOpen_CloseTransition(){
        transition = new TranslateTransition(Duration.millis(2000), slidingGlass);
        transition.setCycleCount(1);
        transition.setOnFinished(e -> winModel.finishMovement());
    }
    public void startOpening(){
        transition.stop();
        transition.setFromX(slidingGlass.getTranslateX());// in case the user decides to close before it opens.
        transition.setToX(-(switchPillar.getX()-switchPillar.getWidth()-slidingGlass.getWidth())); // switch pillar position
        transition.play();
    }
    public void startClosing(){
        transition.stop();
        transition.setFromX(slidingGlass.getTranslateX());  // in case the user decides to open before it closes.
        transition.setToX(0); // original position
        transition.play();
    }


    private TranslateTransition transition;
    private Window winModel;
    private Rectangle switchPillar;
    private Rectangle slidingGlass;
}
