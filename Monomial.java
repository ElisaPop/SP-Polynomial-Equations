package entity;

/**
 * This class provides the capacity to store
 * the characteristics of a monomial
 * (coefficient and exponent) and
 * allows us to use them in
 * several operations
 *
 * It's used by the Polynomial class
 * in a LinkedList
 *
 * @author  Pop Elisa Teea
 */

public class Monomial {

    private double coefficient;
    private int exponent;

    /**
     * This constructor initializes an empty Monomial.
     * Its characteristics (coefficient, exponent) are
     * both set to 0.
     * */

    public Monomial() {
        this.coefficient = 0;
        this.exponent = 0;
    }

    /**
     * This constructor initializes a well defined Monomial.
     * Its characteristics (coefficient, exponent) are
     * both set to the parameters given.
     *
     * @param coefficient is a value of type double. If it's 0
     *                    it will likely be ignored by many
     *                    functions of the Polynomial class
     * @param exponent is an integer that can't be negative
     *                 and if it is, it will be set to 0
     * */

    public Monomial(double coefficient, int exponent) {
        this.coefficient = coefficient;
        if(exponent < 0)
            this.exponent = 0;
        else
            this.exponent = exponent;
    }

    /**
     * A getter that returns the coefficient of a Monomial
     * */

    double getCoefficient() {
        return this.coefficient;
    }

    /**
     * A getter that returns the exponent of a Monomial
     * */

    public int getExponent() {
        return this.exponent;
    }

    /**
     * A setter that gives a new coefficient to the already existing Monomial
     * @param coefficient is the new <double> the Monomial's coefficient will get
     * */

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * A setter that gives a new exponent to the already existing Monomial
     * @param exponent  is the new integer the Monomial's exponent will get.
     *                  If this parameter is negative, the new value will
     *                  actually be 0.
     * */

    public void setExponent(int exponent) {
        if(exponent < 0)
            this.exponent = 0;
        else
            this.exponent = exponent;
    }

    /**
     * A method that add two monomials if their exponent is equal.
     * It's usually only used in methods where we know beforehand
     * that the exponents are equal.
     *
     * @param mon1  is the first Monomial that will be added
     * @param mon2 is the second Monomial that will be added
     * */

    static Monomial staticAdd(Monomial mon1, Monomial mon2) {
        Monomial result = new Monomial();

        if (mon1.exponent == mon2.exponent) {
            result.exponent = mon1.exponent;
            result.coefficient = mon1.coefficient + mon2.coefficient;
        }

        return result;
    }

    /**
     * A method that subtracts two monomials if their exponent is equal.
     *
     * Note: I didn't use it because when a subtraction had to be done,
     * I've just negated the whole Polynomial to be subtracted.
     *
     * @param mon1  is the first Monomial, the one that we subtract mon2 from
     * @param mon2 is the second Monomial that will be subtracted from mon1
     * */

    static Monomial staticSubtract(Monomial mon1, Monomial mon2) {
        Monomial result = new Monomial();

        if (mon1.exponent == mon2.exponent) {
            result.exponent = mon1.exponent;
            result.coefficient = mon1.coefficient - mon2.coefficient;
        }

        return result;
    }

    /**
     * A method that multiplies two monomials.
     * The order of the parameters doesn't matter.
     *
     * @param mon1 is the first Monomial
     * @param mon2 is the second Monomial
     * */

    static Monomial staticMultiply(Monomial mon1, Monomial mon2) {
        Monomial result = new Monomial();

        result.exponent = mon1.exponent + mon2.exponent;
        result.coefficient = mon1.coefficient * mon2.coefficient;

        return result;
    }

    /**
     * A method that divides two monomials.
     * The order of the parameters play a huge role.
     *
     * @param mon1 is the numerator Monomial
     * @param mon2 is the denominator Monomial
     * */

    static Monomial staticDivide(Monomial mon1, Monomial mon2) {
        Monomial result = new Monomial();
        if (mon1.getExponent() > mon2.getExponent())
            result.exponent = mon1.exponent - mon2.exponent;
        result.coefficient = mon1.coefficient / mon2.coefficient;

        return result;
    }

    /**
     * A method that integrated a Monomial.
     * It doesn't integrate Monomials with negative exponents.
     * Since this class naturally can't have a negative exponent,
     * there is no double-checking.
     *
     * @param mon1 is the element to be integrated

     * */

    static Monomial staticIntegrate(Monomial mon1) {
        Monomial result = new Monomial();

        if (mon1.getExponent() == 0)
            return result;
        int newExponent = mon1.exponent + 1;

        result.exponent = newExponent;
        result.coefficient = mon1.coefficient / newExponent;

        return result;
    }

    /**
     * A method that differentiates a Monomial.
     *
     * @param mon1 is the element to be differentiated
     * */

    static Monomial staticDifferentiate(Monomial mon1) {
        Monomial result = new Monomial();
        int aux = mon1.exponent;

        result.exponent = aux - 1;
        result.coefficient = mon1.coefficient * aux;

        return result;
    }

    /**
     * A method that negates the Monomial.
     * Only the coefficient is negated.
     * */

    Monomial negated() {
        return new Monomial(this.getCoefficient() * (-1), this.getExponent());
    }

}

