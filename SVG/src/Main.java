public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Point p1 = new Point(10,11);
        System.out.println(p1.toSvg());
        System.out.println(p1.translated(10,11));

//        Segment[] segment_arr = new Segment[]{};
//        Segment.find_segment(segment_arr);

        Point p2 = new Point(10,11);
        Point p3 = new Point(20,1);
        Point p4 = new Point(1,11);
        Point p5 = new Point(5,8);
        Point p6 = new Point(6,7);
        Point p7 = new Point(3,6);

        Polygon pol1 = new Polygon(new Point[]{p1,p3,p6});
        Polygon pol2 = new Polygon(new Point[]{p7,p4,p2});
        Polygon pol3 = new Polygon(new Point[]{p5,p1,p4});

        SvgScene scene = new SvgScene();
        scene.addPolygon(pol1);
        scene.addPolygon(pol2);
        scene.addPolygon(pol3);

        scene.save("/scene.svg");
    }
}