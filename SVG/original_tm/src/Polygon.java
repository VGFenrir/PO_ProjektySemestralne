public class Polygon implements Shape{
    private Vec2[] vec2_arr;
    private BoundingBox boundingBox;
    //private Style style;

    public Polygon(Vec2[] vec2_arr){
        this.vec2_arr = new Vec2[vec2_arr.length];
        for(int i = 0; i < vec2_arr.length; i++){
            this.vec2_arr[i] = new Vec2(vec2_arr[i].getX(), vec2_arr[i].getY());
        }
        this.boundingBox = generateBoundingBox();
        //this.style = new Style("#00000000","#000000",1);
    }

    public Polygon(Vec2[] vec2_arr, String fillColor, String strokeColor, double strokeWidth){
        this.vec2_arr = new Vec2[vec2_arr.length];
        for(int i = 0; i < vec2_arr.length; i++){
            this.vec2_arr[i] = new Vec2(vec2_arr[i].getX(), vec2_arr[i].getY());
        }
        this.boundingBox = generateBoundingBox();
        //this.style = new Style(fillColor,strokeColor,strokeWidth);
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public String toString(){
        StringBuilder tmp_string = new StringBuilder();
        int counter = 0;
        for(Vec2 element : this.vec2_arr){
            counter++;
            tmp_string.append(element.toString());
            if(counter != this.vec2_arr.length) tmp_string.append(" ");
        }
        return tmp_string.toString();
    }

    public String toSVG(String points){
        return "<polygon points=\""+ points +"\" /*INSERT STYLE HERE*/ />";
    }

    private static double getLength(Vec2 alpha, Vec2 beta){
        return Math.sqrt((alpha.getX() - beta.getX())*(alpha.getX() - beta.getX()) +
                (alpha.getY() - beta.getY())*(alpha.getY() - beta.getY()));
    }

    public double getMaxX(){
        double max_x = 0;
        for(Vec2 element: this.vec2_arr){
            if(element.getX() > max_x) max_x = element.getX();
        }
        return max_x;
    }
    public double getMinY(){
        double min_y = Double.MAX_VALUE;
        for(Vec2 element: this.vec2_arr){
            if(element.getY() < min_y) min_y = element.getY();
        }
        return min_y;
    }

    public BoundingBox generateBoundingBox(){
        double min_x = Double.MAX_VALUE, max_y = 0,
                max_x = 0, min_y = Double.MAX_VALUE,
                width = 0, height = 0;

        for(Vec2 element: this.vec2_arr){
            if(element.getX() < min_x) min_x = element.getX();
            if(element.getX() > max_x) max_x = element.getX();
            if(element.getY() > max_y) max_y = element.getY();
            if(element.getY() < min_y) min_y = element.getY();
        }
        width = getLength(new Vec2(min_x, max_y), new Vec2(max_x, max_y));
        height = getLength(new Vec2(min_x, max_y), new Vec2(min_x, min_y));

        return new BoundingBox(min_x, max_y, height, width);
    }
}
