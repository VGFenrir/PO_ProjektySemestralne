public class SvgScene {
    private Polygon[] polygon_arr;
    private int n = 0;

    public SvgScene(Polygon[] polygon_arr){
        this.polygon_arr = new Polygon[3];
        int counter = 0;
        for(Polygon i: polygon_arr){
            this.polygon_arr[counter] = i;
            counter ++;
        }
    }

    public void addPolygon(Polygon polygon){
        polygon_arr[n%3] = polygon;
        n ++;
    }

    public String toSvg(){
        StringBuilder result = new StringBuilder("<svg height=\"220\" width=\"500\" xmlns=\"http://www.w3.org/2000/svg\">");
        for(Polygon i : polygon_arr){
            if(i != null) {
                result.append(i.toSvg());
            }
        }
        result.append("</svg>");
        return(result.toString());
    }
}
