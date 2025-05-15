public interface Shape {
    BoundingBox generateBoundingBox();
    BoundingBox getBoundingBox();
    String toSVG(String param);
    double getMaxX();
    double getMinY();
}