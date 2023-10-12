package com.dominotrainbuilder;
import java.util.Objects;

/**
 * Class represents a domino which has two sides, each containing a number of pips in the range 0-12
 *
 */
public class Domino {
    private int side1;
    private int side2;

    /** Creates a domino  */
    public Domino(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    /** Returns the number of pips on side 1 of the domino */
    public int getSide1() {
        return side1;
    }

    /** Returns the number of pips on side 2 of the domino */
    public int getSide2() {
        return side2;
    }

    /** Flips the domino */
    public void flip() {
        int temp = this.side1;
        this.side1 = this.side2;
        this.side2 = temp;
    }

    /** Returns the number of pips on side 2 of the domino */
    public int getTotal() {
        return side1+side2;
    }

    /** Checks whether the domino is a double **/
    public boolean isDouble(){
        return this.side1 == this.side2;
    }

    @Override
    public String toString() {
        return side1 + "," + side2;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Domino domino = (Domino) o;
        return (this.side1 == domino.side1 && this.side2 == domino.side2) ||
                (this.side1 == domino.side2 && this.side2 == domino.side1);

    }

    @Override
    public int hashCode() {
        return Objects.hash(side1, side2);
    }
}
