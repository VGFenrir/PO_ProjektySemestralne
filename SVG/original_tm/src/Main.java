import java.io.IOException;

public class Main{

    public static void main(String[] args) throws IOException {
        Vec2 alpha = new Vec2(50,100);
        Vec2 beta = new Vec2(130,180);
        Vec2 gamma = new Vec2(50,140);
        Vec2 delta = new Vec2(100,180);
        Vec2 epsilon = new Vec2(80,120);
        Vec2 dzeta = new Vec2(140,50);
        Vec2 eta = new Vec2(20,110);
        Vec2 theta = new Vec2(50,200);
//        Segment alpha_beta = new Segment(alpha,beta);
//        Segment alpha_gamma = new Segment(alpha,gamma);
//        Segment alpha_delta = new Segment(alpha,delta);
//        Segment beta_gamma = new Segment(beta,gamma);
//        Segment beta_delta = new Segment(alpha,delta);
//        Segment gamma_delta = new Segment(gamma,delta);
//        Segment[] segment_arr = new Segment[]{alpha_beta, alpha_gamma, alpha_delta, beta_gamma, beta_delta, gamma_delta};

        Vec2[] vec2_arr1 = new Vec2[]{alpha, gamma,delta ,beta};
        Vec2[] vec2_arr2 = new Vec2[]{epsilon, dzeta, eta, theta};
        Vec2[] vec2_arr3 = new Vec2[]{alpha, epsilon, beta, dzeta};
        Vec2[] vec2_arr4 = new Vec2[]{gamma, eta, delta, theta};
        Vec2[] vec2_arr5 = new Vec2[]{ beta,  epsilon,delta, dzeta, eta,gamma,alpha, theta};

        Polygon polygon1 = new Polygon(vec2_arr1);
        Polygon polygon2 = new Polygon(vec2_arr2);
        Polygon polygon3 = new Polygon(vec2_arr3);
        Polygon polygon4 = new Polygon(vec2_arr4);
        Polygon polygon5 = new Polygon(vec2_arr5);

        Ellipse ellipse1 = new Ellipse(new Vec2(20,20),10,5);

        SvgScene scene = new SvgScene();
        scene.addShape(polygon1);
        scene.addShape(polygon2);
        scene.addShape(polygon3);
        scene.addShape(ellipse1);
//        scene.addShape(polygon4);
//        scene.addShape(polygon5);

        System.out.println(scene.toSVG());
        scene.save();
    }
}