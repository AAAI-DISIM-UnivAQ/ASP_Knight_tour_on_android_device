package it.univaq.marco.embsasp;

import it.unical.mat.embasp.mapper.Predicate;
import it.unical.mat.embasp.mapper.Term;



@Predicate("move")
public class Move {

    public Move() {
    }

    public Move(String x1, String y1,String x2, String y2 ) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Term(0)
    String x1;

    @Term(1)
    String y1;

    @Term(2)
    String x2;

    @Term(3)
    String y2;

    public void setX1(String x1) {
        this.x1 = x1;
    }

    public void setY1(String y1) {
        this.y1 = y1;
    }

    public void setX2(String x2) {
        this.x2 = x2;
    }

    public void setY2(String y2) {
        this.y2 = y2;
    }

    public String getX1() {
        return x1;
    }

    public String getY1() {
        return y1;
    }

    public String getX2() {
        return x2;
    }

    public String getY2() {
        return y2;
    }



    @Override
    public String toString() {
        return " move{ " +
                "" + x1 +
                ", " + y1 + ", " + x2 + ", " + y2 +
                "} ";
    }
}

/*


*/