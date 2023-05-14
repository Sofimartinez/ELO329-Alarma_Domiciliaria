import java.util.ArrayList;

public class Person {
    public Person( PersonView person, Central central, House house){
        pView = person;
        this.central = central;
        this.house = house;
        pView.setPersonModel(this);
    }
    public void verticalMove(double Mouse){
            y = Mouse;
        }
    public void horizontalMove(double Mouse){
        x = Mouse;
    }
    public double getX(){
        return x;
    }
    public double getY() {
        return y;
    }
    public PersonView getView(){return pView;}
    public House getHouse(){
        return house;
    }
    public Central getCentral(){
        return central;
    }
    private double x;
    private double y;
    private PersonView pView;
    private Central central;
    private House house;


}
