public class Main {

    public static void main(String[] args) {
        Point point = new Point(2,3);
        System.out.println(point.toString());
        System.out.println(point.toSVG());
        point.translate(2,3);
        System.out.println(point);
        point.translate(2,3);
        Point point2 = new Point(1,1);
        Point point3 = new Point(2,1);
        Point point4 = new Point(4,1);
        Point point5 = new Point(6,1);
        Point point6 = new Point(10,1);

        System.out.println(point.translated(2,3));
        Segment segment1 = new Segment(point, point2);
        System.out.println(segment1.length());

        Segment segment2 = new Segment(point,point3);
        Segment segment3 = new Segment(point,point4);
        Segment segment4 = new Segment(point,point5);
        Segment segment5 = new Segment(point,point6);

        int n = 5;
        Segment[] seg_arr = {segment1,segment2,segment3,segment4,segment5};

        double max_in_arr = Segment.find(seg_arr,n);
        System.out.println(max_in_arr);
    }
}