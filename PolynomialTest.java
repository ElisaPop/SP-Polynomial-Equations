package entity;

import gui.Model;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @org.junit.jupiter.api.Test
    void staticAddTest() {

        Polynomial b1Test;
        Polynomial b2Test;

        b1Test = Model.getPolynome("3x^2 + 4*x^2 + 8*x^1");
        b2Test = Model.getPolynome("0.0 + 3x^9 + 4.2x^0 ");
        b1Test.sortElements();
        b2Test.sortElements();

        Polynomial bb = Polynomial.staticAdd(b1Test, b2Test);
        bb.sortElements();

        assertEquals(" 4.2 + 8.0x + 7.0x^2 + 3.0x^9 ", bb.returnString());
    }

    @org.junit.jupiter.api.Test
    void staticSubtractTest() {

        Polynomial b1Test;
        Polynomial b2Test;

        b1Test = Model.getPolynome("2.0*x^2 + 4.0x^4 + 6.0x^6+8x^8");
        b2Test = Model.getPolynome("1.0 + 7.0x^9 + 3.0x^3 + 5.0x^6 + 7.0x^9 + 9.0x^12");
        b1Test.sortElements();
        b2Test.sortElements();

        Polynomial bb = Polynomial.staticSubtract(b1Test, b2Test);
        bb.sortElements();

        assertEquals(" -1.0 + 2.0x^2 + -3.0x^3 + 4.0x^4 + 1.0x^6 + 8.0x^8 + -14.0x^9 + -9.0x^12 ", bb.returnString());
    }

    @org.junit.jupiter.api.Test
    void staticMultiplyTest() {
        Polynomial b1Test;
        Polynomial b2Test;

        b1Test = Model.getPolynome("+ 8.0*x^8 +2x^2 + 4.0x^4 + 6.0x^6");
        b2Test = Model.getPolynome("-1.0 + 7.0x^9 + 3.0x^3 + 5.0x^6 + 7.0x^9 + 9.0x^12");
        b1Test.sortElements();
        b2Test.sortElements();

        Polynomial bb = Polynomial.staticMultiply(b1Test, b2Test);
        bb.sortElements();

        assertEquals(" -2.0x^2 + -4.0x^4 + 6.0x^5 + -6.0x^6 + 12.0x^7 + 2.0x^8 + 18.0x^9 + 20.0x^10 + 52.0x^11 + 30.0x^12 + 56.0x^13 + 58.0x^14 + 84.0x^15 + 36.0x^16 + 112.0x^17 + 54.0x^18 + 72.0x^20 ", bb.returnString());
    }

    @org.junit.jupiter.api.Test
    void staticDivideTest() {
        Polynomial b1Test;
        Polynomial b2Test;

        b1Test = Model.getPolynome("3x^4 + 6x^6 + 8x^8");
        b2Test = Model.getPolynome("2x^2 + 4x^4");
        b1Test.sortElements();
        b2Test.sortElements();

        String bb = Polynomial.staticDivide(b1Test, b2Test);

        assertEquals(" 0.5 + 0.5x^2 + 2.0x^4  + ( -1.0 )/( 2.0 + 4.0x^2 )", bb);

    }

    @org.junit.jupiter.api.Test
    void staticIntegrateTest() {

        Polynomial b1Test;
        b1Test = Model.getPolynome(" 2.0x^2 +      4.0x^4 + 6.0x^6 + 8.0x^8 ");


        Polynomial bb = Polynomial.staticIntegrate(b1Test);
        bb.sortElements();
        assertEquals(" 0.7x^3 + 0.8x^5 + 0.9x^7 + 0.9x^9 ", bb.returnString());

    }

    @org.junit.jupiter.api.Test
    void staticDifferentiateTest() {
        Polynomial b1Test;
        b1Test = Model.getPolynome(" 2.0x^2 + 4.0x^4 + 6.0x^6 + 8.0x^8 ");


        Polynomial bb = Polynomial.staticDifferentiate(b1Test);
        bb.sortElements();
        assertEquals(" 4.0x + 16.0x^3 + 36.0x^5 + 64.0x^7 ", bb.returnString());
    }

    @org.junit.jupiter.api.Test
    void evaluateTest() {
        Polynomial b1Test;
        b1Test = Model.getPolynome("3x^2 - 4*x^2 + 8*x^1");
        assertEquals(7, b1Test.evaluate(1));
    }

    @org.junit.jupiter.api.Test
    void negatedTest() {
        Polynomial b1Test;


        b1Test = Model.getPolynome("3x^2 - 4*x^2 + 8*x^1");

        b1Test = b1Test.negated();
        b1Test.sortElements();


        assertEquals(" -8.0x + 1.0x^2 ", b1Test.returnString());

    }

    @org.junit.jupiter.api.Test
    void sortElementsTest() {
        Polynomial b1Test;
        Polynomial b2Test;

        b1Test = Model.getPolynome("3x^2 + 4*x^2 + 8*x^1");
        b2Test = Model.getPolynome("0.0 + 3x^9 + 4.2x^0 ");

        b1Test.sortElements();
        b2Test.sortElements();

        assertEquals(" 8.0x + 7.0x^2 ", b1Test.returnString());
        assertEquals(" 4.2 + 3.0x^9 ", b2Test.returnString());
    }
}