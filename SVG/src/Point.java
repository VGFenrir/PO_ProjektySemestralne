public class Point {
    public float x,y;

    public Point(float x_, float y_){
        this.x = x_;
        this.y = y_;
    }

    public String toString(){
        return "("+x+ " "+y+")";
    }

    public String toSVG(){
        return "<circle r="+45+" cx="+50+" cy="+50+" fill=red />";
    }

    public void translate(float dx, float dy){
        this.x += dx;
        this.y += dy;
    }
    public Point translated(float dx, float dy){
        dx += this.x;
        dy += this.y;
        return new Point(dx, dy);
    }
}
