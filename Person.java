public class Person {
    public Person( PersonView person){

            pView = person;
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
    private double x;
    private double y;
    private PersonView pView;
    public PersonView getView(){return pView;}

}
