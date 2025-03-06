import java.util.Arrays;

public class Polygon {
    private Point[] point_arr;

    public Polygon(Point[] point_arr){
        this.point_arr = new Point[point_arr.length];
        int counter = 0;
        for(Point i : point_arr){
            this.point_arr[counter] = new Point(i.getX(),i.getY());
            counter++;
        }
    }
    public Polygon(Polygon polygon){
        this.point_arr = new Point[polygon.point_arr.length];
        int counter = 0;
        for(Point i : polygon.point_arr){
            this.point_arr[counter] = new Point(i.getX(),i.getY());
            counter++;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for(Point i: point_arr){
            result.append(i).append(", ");
        }
        result.setLength(result.length()-2);
        return result.toString();
    }

    public String toSvg(){
        StringBuilder result = new StringBuilder();
        result.append("<polygon points=\"");
        for(Point i: point_arr){
            result.append(i).append(", ");
        }
        result.setLength(result.length()-2);
        result.append(" style=\"fill:lime;stroke:purple;stroke-width:3\" />");
        return result.toString();
    }
}
