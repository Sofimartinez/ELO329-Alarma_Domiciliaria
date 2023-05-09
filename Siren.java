import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;

public class Siren {
    public Siren (String mediaURL){
        Media media = new Media(mediaURL);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        view = new SirenView();
    }
    public void play(){
        view.startBlinking();
        mediaPlayer.play();
    }
    public void stop(){
        view.stopBlinking();
        mediaPlayer.stop();
    }
    public Polygon getView() {
        return view;
    }
    private SirenView view;
    private MediaPlayer mediaPlayer;
}
