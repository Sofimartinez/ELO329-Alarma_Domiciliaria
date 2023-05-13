import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class SirenView extends Polygon {
    public SirenView() {
        getPoints().addAll(0d,30d,
                0d,50d,
                40d, 80d,
                40d, 0d);
        setFill(Color.web("4FEC4C"));
        setStroke(Color.GREEN);
        timeline = new Timeline(new KeyFrame(Duration.millis(250),e -> startBlinking()));
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    public void startBlinking(){
        timeline.play();
        setFill(Color.web("F03B3B"));
        setStroke(Color.RED);
    }
    public void stopBlinking() {
        timeline.stop();
        setFill(Color.web("4FEC4C"));
        setStroke(Color.GREEN);
    }
    private final Timeline timeline;
}
