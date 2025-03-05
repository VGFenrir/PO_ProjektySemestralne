public class Segment {
    public Point p1,p2;

//    public Segment(Point p1_, Point p2_){
//        this.p1 = p1_;
//        this.p2 = p2_;
//    }
    
    public Segment(Point p1_, Point p2_){
        this.p1 = new Point (p1_.x, p2_.y);
        this.p2 = new Point (p2_.x, p2_.y);
    }

    public double length(){
        return Math.sqrt(Math.pow(p2.x - p1.x,2)+Math.pow(p2.y-p1.y,2));
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
