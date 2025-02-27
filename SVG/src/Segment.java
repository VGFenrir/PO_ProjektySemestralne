public class Segment {
    public Point p1,p2;

    public Segment(Point p1_, Point p2_){
        this.p1 = p1_;
        this.p2 = p2_;
    }

    public double length(){
        return Math.sqrt(Math.pow(p2.x - p1.x,2)+Math.pow(p2.y-p1.y,2));
    }

    public static double find_longest_segment(Segment[] seg_arr, int n){
        double max = 0;
        for(int i = 0; i < n; i++){
            if(max < seg_arr[i].length()){
                max = seg_arr[i].length();
            }
            //System.out.println(seg_arr[i].length());
        }
        return max;
    }
}
