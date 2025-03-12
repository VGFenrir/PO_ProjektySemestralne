public class Point {
    private float x, y;
    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPoint(float x, float y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "("+ x + ", "+ y + ")";
    }
    public String toSvg(){
        return "<circle r=\""+45+"\" cx=\""+x+"\" cy=\""+y+"\" fill=\"red\" />";
    }
    public void translate(float dx, float dy){
        this.x += dx;
        this.y += dy;
    }
    public Point translated(float dx, float dy){
        return new Point(this.x + dx, this.y + dy);
    }
}
