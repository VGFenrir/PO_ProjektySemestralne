import java.io.FileWriter;
import java.io.IOException;

public class SvgScene {
    private Shape[] shape_arr;
    private int counter;

    public SvgScene(){
        this.shape_arr = new Shape[3];
        this.counter = 0;
    }

    public void addShape(Shape shape){
        this.shape_arr[this.counter%3] = shape;
        this.counter ++;
    }

    public String toSVG(){
        BoundingBox boundingBox = generateBoundingBox();
        StringBuilder tmp_string = new StringBuilder();
        tmp_string.append("<svg height=\""+boundingBox.height()+"\" width=\""+boundingBox.width()+"\" xmlns=\"http://www.w3.org/2000/svg\">\n");
        for(int i = 0; i < this.shape_arr.length; i++){
            if(this.shape_arr[i] != null) {
                tmp_string.append(this.shape_arr[i].toSVG(this.shape_arr[i].));
                tmp_string.append("\n");
            }
        }
        tmp_string.append("</svg>");
        return tmp_string.toString();
    }

    private static double getLength(Vec2 alpha, Vec2 beta){
        return Math.sqrt((alpha.getX() - beta.getX())*(alpha.getX() - beta.getX()) +
                (alpha.getY() - beta.getY())*(alpha.getY() - beta.getY()));
    }

    private BoundingBox generateBoundingBox(){
        double height, width,
                min_x = Double.MAX_VALUE, min_y = Double.MAX_VALUE,
                max_x = 0, max_y = 0;

        for(Shape element: this.shape_arr){
            if(element != null) {
                if (element.getBoundingBox().x() < min_x) min_x = element.getBoundingBox().x();
                if (element.getMaxX() > max_x) max_x = element.getMaxX();
                if (element.getBoundingBox().y() > max_y) max_y = element.getBoundingBox().y();
                if (element.getMinY() < min_y) min_y = element.getMinY();
            }
        }

        width = getLength(new Vec2(0, max_y), new Vec2(max_x, max_y));
        height = getLength(new Vec2(0, max_y), new Vec2(0, 0));

        return new BoundingBox(min_x, max_y, height, width);
    }

    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter("image.svg");
        fileWriter.write(this.toSVG());
        fileWriter.close();
    }
}
