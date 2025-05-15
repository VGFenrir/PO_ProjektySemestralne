public class Segment {
    private Vec2 a;
    private Vec2 b;
    public Segment(Vec2 a, Vec2 b){
        this.a = new Vec2(a.getX(),a.getY());
        this.b = new Vec2(b.getX(),b.getY());;
    }

    public Vec2 getA() {
        return a;
    }

    public Vec2 getB() {
        return b;
    }

    public double length(){
        return Math.sqrt((this.a.getX() - this.b.getX())*(this.a.getX() - this.b.getX()) +
                (this.a.getY() - this.b.getY())*(this.a.getY() - this.b.getY()));
    }

    public static Segment find_longest_segment(Segment[] segment_arr){
        Segment longest_segment = null;
        for(Segment element: segment_arr){
            if(longest_segment == null){
                longest_segment = element;
            }
            else if(longest_segment.length() < element.length()){
                longest_segment = element;
            }
        }
        return longest_segment;
    }

//    public Segment perpendicular(){
//
//    }
}
