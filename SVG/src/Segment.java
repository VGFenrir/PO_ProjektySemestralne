public class Segment {
    private Point p1;
    private Point p2;
    public Segment(Point p1, Point p2){
        this.p1 = new Point(p1.getX(), p1.getY());
        this.p2 = new Point(p2.getX(), p2.getY());
    }
     public double length(){
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(),2)+Math.pow(p2.getY() - p1.getY(),2));
     }

    @Override
    public String toString() {
        return "Segment{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }

    public static Segment find_segment(Segment[] segment_arr){
        double max_l = 0;
        Segment max = null;
        for(Segment i : segment_arr){
            if(i.length() > max_l){
                max_l = i.length();
                max = i;
            }
        }
        return max;
    }
}
