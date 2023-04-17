public class Person{
    public Person(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void northMove(){
        y += 0.5;
    }
    public void southMove(){
        y -= 0.5;
    }
    public void eastMove(){
        x += 0.5;
    }
    public void westMove(){
        x -= 0.5;
    }

    public float getX(){
        return x;
    }

    public float getY() {
        return y;
    }

    private float x;
    private float y;
}