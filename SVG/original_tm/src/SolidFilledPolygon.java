public class SolidFilledPolygon extends Polygon{
    private String color;

    SolidFilledPolygon(Vec2[] vec2_arr, String color){
        super(vec2_arr);
        this.color = color;
    }
}
