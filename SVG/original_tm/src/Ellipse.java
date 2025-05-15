public class Ellipse implements Shape{
    private Vec2 centerVec2;
    private double radius_x;
    private double radius_y;
    private BoundingBox boundingBox;

    public Ellipse(Vec2 centerVec2, double radius_x, double radius_y){
        this.centerVec2 = centerVec2;
        this.radius_x = radius_x;
        this.radius_y = radius_y;
        this.boundingBox = this.generateBoundingBox();
    }

    public String toSVG(String param){
        return "<ellipse rx=\""+this.radius_x+"\" ry=\""+this.radius_y+"\" cx=\"" +
                centerVec2.getX()+"\" cy=\""+ centerVec2.getY()+"\" /*INSERT STYLE HERE*/  />\"";
    }


    public BoundingBox generateBoundingBox(){
        double min_x, max_y, width, height;

        min_x = this.centerVec2.getX() - this.radius_x;
        max_y = this.centerVec2.getY() + this.radius_y;
        height = 2*this.radius_y;
        width = 2*this.radius_x;

        return new BoundingBox(min_x, max_y, height, width);
    }

    public BoundingBox getBoundingBox() { return this.boundingBox; }

    public double getMaxX(){
        return this.centerVec2.getY()-this.radius_y;
    }
    public double getMinY(){
        return this.centerVec2.getX()+this.radius_x;
    }
}
