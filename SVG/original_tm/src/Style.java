public class Style {
    private final String fillColor;
    private final String strokeColor;
    private final double strokeWidth;

    public Style(String fillColor, String strokeColor, double strokeWidth){
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public String toSVG(){
        return "style=\"fill:"+this.fillColor+";stroke:"+this.strokeColor+";stroke-width:"+this.strokeWidth+"\"";
    }

}
