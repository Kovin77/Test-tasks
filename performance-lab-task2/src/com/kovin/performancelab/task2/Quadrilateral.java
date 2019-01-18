package com.kovin.performancelab.task2;

public class Quadrilateral {

    MyPoint point1, point2, point3, point4;
    // because we use floats in our calculations lets define some constant,
    // if difference in distance or area less than this constant then we will say point or shapes are equal
    static final float EPSILON = 1e-10f;

    public Quadrilateral(MyPoint point1, MyPoint point2, MyPoint point3, MyPoint point4) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    public boolean onCorner(MyPoint somePoint){ // if point on corner return TRUE
        if ((ppDistance(point1, somePoint) < EPSILON) |
           (ppDistance(point2, somePoint) < EPSILON) |
           (ppDistance(point3, somePoint) < EPSILON) |
           (ppDistance(point4, somePoint) < EPSILON)) {
            return true;
        } else return false;
    }

    private float ppDistance(MyPoint p1, MyPoint p2) { // calculate distance between two points
        return (float) Math.sqrt(Math.pow((p1.x-p2.x),2)+Math.pow((p1.y-p2.y),2));
    }

    public boolean onSide(MyPoint somePoint){ // if point on side return TRUE
        if (
           ( (psDistance(point1, point2, somePoint) < EPSILON) & isBetween(point1, point2, somePoint) ) |
           ( (psDistance(point2, point3, somePoint) < EPSILON) & isBetween(point2, point3, somePoint) ) |
           ( (psDistance(point3, point4, somePoint) < EPSILON) & isBetween(point3, point4, somePoint) ) |
           ( (psDistance(point4, point1, somePoint) < EPSILON) & isBetween(point4, point1, somePoint) )
        ) { return true;
        } else return false;
    }

    public boolean isBetween(MyPoint p1, MyPoint p2, MyPoint sP) { // check if point located between two points
        if (( ( p1.x>=sP.x & sP.x>=p2.x ) | ( p1.x<=sP.x & sP.x<=p2.x ) ) & // is somePoint X coordinate between X1 and X2 and
           ( ( p1.y>=sP.y & sP.y>=p2.y ) | ( p1.y<=sP.y & sP.y<=p2.y ) )) { // somePoint Y coordinate between Y1 and Y2
            return true;
        } else return false;
    }

    public float psDistance(MyPoint p1, MyPoint p2, MyPoint sP) { // distance between point sP and line (p1, p2)
        // P1=(x1,y1)  P2=(x2,y2), distance from (x0,y0) to line:
        // distance ⁡ ( P 1 , P 2 , ( x 0 , y 0 ) ) =
        //
        // | ( y 2 − y 1 ) x 0 − ( x 2 − x 1 ) y 0 + x 2 y 1 − y 2 x 1 |
        // -------------------------------------------------------------
        //              sqrt( ( y 2 − y 1 )^2 + ( x 2 − x 1 )^2 )
        return (float) (Math.abs( (p2.y-p1.y) * sP.x - (p2.x-p1.x) * sP.y + p2.x*p1.y-p2.y*p1.x ) /
                        Math.sqrt( Math.pow((p2.y-p1.y),2)+Math.pow((p2.x-p1.x),2)));
    }


    public boolean isPointInside(MyPoint somePoint){ // if point inside quadrilateral return TRUE
        // we calculate sum of areas for 4 triangles formed by two points of our quadrilateral and one testing point
        // and then compare this sum to area of quadrilateral itself
        float quadrArea, sum;

        // Quadrilateral area as sum of two triangles
        quadrArea = triangleArea(point1, point2, point3) + triangleArea(point3, point4, point1);

        sum = triangleArea(point1, point2, somePoint) + triangleArea(point2, point3, somePoint) +
                triangleArea(point3, point4, somePoint) +triangleArea(point4, point1, somePoint);

        if (sum - quadrArea < EPSILON){
            return true;
        } else return false;
    }

    public float triangleArea(MyPoint p1, MyPoint p2, MyPoint p3){ // calculate area of the triangle
        // S = 1/2 * | (x_2 - x_1 ) (y_3 - y_1 ) - (x_3 - x_1 ) (y_2 - y_1 ) |
        return 0.5f * Math.abs( (p2.x-p1.x)*(p3.y-p1.y) - (p3.x-p1.x)*(p2.y-p1.y) );
    }
}
