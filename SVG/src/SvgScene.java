import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SvgScene {
    private final Polygon[] polygon_arr = new Polygon[3];
    private int n = 0;
    private float width = 0;
    private float height = 9999;

    public void addPolygon(Polygon polygon){
        polygon_arr[n%3] = polygon;
        n++;
    }

    public String toSvg(){
        StringBuilder result = new StringBuilder();
        result.append("<svg height=\""+9999+"\" width=\""+width+"\" xmlns=\"http://www.w3.org/2000/svg\">\n");
        for(Polygon i : polygon_arr){
            result.append(i.toSvg());
        }
        result.append("</svg>");
        return result.toString();
    }

    public void save(String path){
        for(Polygon i: polygon_arr){
            if(i.boundingBox().width() > width) width = i.boundingBox().width();
            if(i.boundingBox().height() < height) height = i.boundingBox().height();
        }
        String SVG_image = this.toSvg();
        try {
            File file = new File(path);
            if(file.createNewFile()){
                try {
                    FileWriter writer = new FileWriter(path);
                    writer.write(SVG_image);
                    writer.close();
                } catch (IOException e) {
                    System.out.println("IOException occurred: ");
                    e.printStackTrace();
                }
            }
            else{
                try {
                    FileWriter writer = new FileWriter(path);
                    writer.write(SVG_image);
                    writer.close();
                } catch (IOException e) {
                    System.out.println("IOException occurred: ");
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e){
            System.out.println("IOException occurred: ");
            e.printStackTrace();
        }
    }
}
