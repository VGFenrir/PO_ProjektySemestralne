public class Polygon {
    private final Point[] point_arr;

    public Polygon(Point[] point_arr){
        int n = point_arr.length;
        this.point_arr = new Point[n];
        for(int i = 0; i < n; i++){
            this.point_arr[i] = new Point(point_arr[i].getX(),point_arr[i].getY());
        }
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("(");
        for(Point i : point_arr){
            result.append(i.getX()).append(",").append(i.getY()).append(",");
        }
        result.append(")");
        return result.toString();
    }

    public String toSvg(){
        StringBuilder result = new StringBuilder();
        result.append("<polygon points=\"");
        for(int i = 0; i < point_arr.length; i++){
            result.append(point_arr[i].getX()).append(",").append(point_arr[i].getY());
            if(i < point_arr.length - 1) result.append(" ");
        }
        result.append("\" style=\"fill:lime;stroke:purple;stroke-width:3\" />\n");
        return result.toString();
    }

    public BoundingBox boundingBox(){
        float x = 9999;
        float y = 0;
        float width = 0;
        float height = 9999;

        for(Point i : point_arr){
            if(i.getX() < x) x = i.getX();
            if(i.getX() > width) width = i.getX();
            if(i.getY() > y) y = i.getY();
            if(i.getY() < height) height = i.getY();
        }
        return new BoundingBox(x,y,width,height);
    }
}
