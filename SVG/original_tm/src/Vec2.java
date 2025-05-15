public class Vec2 {
    private double x;
    private double y;

    public Vec2(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Vec2(){
        this.x = 0.f;
        this.y = 0.f;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return this.x+","+this.y;
    }

    public String toSVG(){
        return "<svg height=\"100\" width=\"100\" xmlns=\"http://www.w3.org/2000/svg\"> " +
                    "<circle r=\"45\" cx=\""+this.x+"\" cy=\""+this.y+"\" fill=\"red\" />"+
                "</svg>";
    }
}
