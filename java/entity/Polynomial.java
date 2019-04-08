package entity;

import comparators.MonomeComparator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;

/**
 * This class provides the main functionality the application has to offer.
 * It only stores the elements ( Monomials) in a LinkedList and provides several operations.
 */

public class Polynomial {

    private LinkedList<Monomial> Elements;

    /**
     * This constructor is used for initializing a new Polynomial. Monomials can be added with the addElement method
     */
    public Polynomial() {
        this.Elements = new LinkedList<>();
    }

    /**
     * This setter adds a new Monomial to the Elements list of the current Polynomial
     */
    public void addElement(Monomial mon1) {
        this.Elements.add(mon1);
    }

    /**
     * This method adds two Polynomials by taking all the elements and then uses the sortElements method that isn't used only for sorting,
     * but also for making sure that the resulting Polynomial meets the criteria of a Polynomial.
     * Note: Since it's addition, the order of the Polynomials does not matter.
     * @param pol1 is the first Polynomial
     * @param pol2 is the second Polynomial
     * @return a new Polynomial that is the addition of the two given Polynomials
     */
    public static Polynomial staticAdd(Polynomial pol1, Polynomial pol2) {
        Polynomial result = new Polynomial();

        result.Elements.addAll(pol1.Elements);
        result.Elements.addAll(pol2.Elements);

        result.sortElements();
        return result;
    }

    /**
     * This method subtracts two Polynomials by taking all the Elements from pol1 and all the negated Elements from pol2.
     * Just like @see staticAdd, it uses sortElements for the resulting Polynomial.
     * Note: Since it's subtraction, the order of the Polynomials matters.
     *
     * @param pol1 is the first Polynomial, the one we subtract from
     * @param pol2 is the second Polynomial, the one that is subtracted
     * @return a new Polynomial that is the differentiation of the two given Polynomials
     */
    public static Polynomial staticSubtract(Polynomial pol1, Polynomial pol2) {
        Polynomial result = new Polynomial();

        result.Elements.addAll(pol1.Elements);
        for (Monomial a : pol2.Elements)
            result.Elements.add(a.negated());

        for (Monomial a : result.Elements)
            if (a.getCoefficient() == 0) {
                result.Elements.remove(a.getClass());
            }

        result.sortElements();
        return result;
    }

    /**
     * This method multiplies two Polynomials by multiplying each element from pol1 with each element from pol2.
     * Just like @see staticAdd, it uses sortElements for the resulting Polynomial.
     * Note: Since it's multiplication, the order of the Polynomials does not matter.
     * @param pol1 is the first Polynomial
     * @param pol2 is the second Polynomial
     * @return a new Polynomial that is the multiplication of the two given Polynomials
     */
    public static Polynomial staticMultiply(Polynomial pol1, Polynomial pol2) {
        Polynomial result = new Polynomial();

        for (Monomial iterator1 : pol1.Elements) {
            Polynomial aux = new Polynomial();

            for (Monomial iterator2 : pol2.Elements) {
                aux.Elements.add(Monomial.staticMultiply(iterator1, iterator2));
            }

            result = Polynomial.staticAdd(result, aux);
        }

        result.sortElements();
        return result;
    }

    /**
     * This method adds two Polynomials by taking all the Elements from pol1 and all the negated Elements from pol2.
     * Just like @see staticAdd, it uses sortElements for the resulting Polynomial.
     * Note: Since it's subtraction, the order of the Polynomials matters.
     * @param pol1 is the first Polynomial, the one we subtract from
     * @param pol2 is the second Polynomial, the one that is subtracted
     * @return a String that is quotient + (residue)/(pol2). It represents the long division of pol1 and pol2
     */
    public static String staticDivide(Polynomial pol1, Polynomial pol2) {
        Polynomial nullPolynomial = new Polynomial();
        Polynomial quotient = new Polynomial();
        Polynomial residue = Polynomial.staticAdd(pol1, quotient);

        pol1.sortElements(); // we make sure both Polynomials have no Monomial with coefficient 0 and that the last Monomial has the biggest exponent
        pol2.sortElements();
        if (pol2.Elements.size() == 0)
            gui.UtilsGUI.alert("DIVISION ERROR", "The second polynomial may have not been initialized or it is 0.", "OK");


        while (!residue.Elements.isEmpty() && (residue.degree() >= pol2.degree())) {
            Polynomial mPolynomial = new Polynomial();                  // the Polynomial that always has only one Monomial.
            mPolynomial.addElement(residue.Elements.peekLast());        // the Monomial is the one that will be divided and added to quotient. The last Element of residue.

            quotient.addElement(Monomial.staticDivide(mPolynomial.Elements.peekLast(), pol2.Elements.peekLast()));      // We add the biggest element of residue / the biggest element of pol2 so that when we subtract the multiplication from residue, we get rid of the current biggest element of residue
            Polynomial aQuotient = new Polynomial();
            aQuotient.addElement(quotient.Elements.peekLast()); // We keep here the element we added 2 rows above (biggest element of residue / biggest element of pol2)
            Polynomial mediator = Polynomial.staticMultiply(aQuotient, pol2); // aQuotient is multiplied with pol2 to be subtracted from residue to reduce the degree of residue.
            residue = Polynomial.staticSubtract(residue, mediator);
            residue.sortElements();
        }
        if (residue.exponentGCD() == pol2.exponentGCD() && residue.exponentGCD() != 1) // here we simplify the residue equation. It's only for exponents.
            Polynomial.simplifyExp(residue, pol2, residue.exponentGCD());

        quotient.sortElements(); // get the polynomials ready to be returned
        residue.sortElements();

        String a = quotient.returnString();
        if (residue.Elements.size() != 0) a = a + " + (" + residue.returnString() + ")/(" + pol2.returnString() + ")";
        return a;
    }

    /**
     * This is n auxiliary method used for staticDivide.
     * It simplifies the equation by reducing the degree of the polynomials by the number given through a.
     * @param  residue is the first Polynomial.
     * @param pol2 is the second Polynomial
     * @param a is the number which will be extracted from each Monomial residue and pol2 have
     * */
    private static void simplifyExp(Polynomial residue, Polynomial pol2, int a) {
        for (Monomial m1 : residue.Elements) {
            m1.setExponent(m1.getExponent() - a);
        }
        for (Monomial m2 : pol2.Elements) {
            m2.setExponent(m2.getExponent() - a);
        }
    }

    /**
     * This method integrates each Monomial the given Polynomial has, using a function that the class Monomial provides.
     * This function simply integrates each element and adds it to the result Polynomial.
     * @param pol is the given Polynomial that will be integrated
     * @return the given Polynomial integrated
     */
    public static Polynomial staticIntegrate(Polynomial pol) {
        Polynomial result = new Polynomial();

        for (Monomial a : pol.Elements) {
            result.addElement(Monomial.staticIntegrate(a));
        }

        return result;
    }

    /**
     * This method differentiates each Monomial the given Polynomial has, using a function that the class Monomial provides.
     * This function simply differentiates each element and adds it to the result Polynomial.
     * @param pol is the given Polynomial that will be differentiated.
     * @return the given Polynomial differentiated
     */
    public static Polynomial staticDifferentiate(Polynomial pol) {
        Polynomial result = new Polynomial();

        for (Monomial a : pol.Elements) {
            result.addElement(Monomial.staticDifferentiate(a));
        }
        return result;
    }

    /**
     * This method is used for calculating the greatest common divisor a Polynomial has. It only calculates it for the exponents.
     * @return the greatest common divisor of a Polynomial's exponents
     */
    private int exponentGCD() {
        int minGCD = 1;

        if (this.Elements.size() == 1)
            return this.Elements.getFirst().getExponent();
        for (int a = 0; a <= this.Elements.size() - 2; a++) {
            minGCD = Polynomial.intGCD(this.Elements.get(a).getExponent(), this.Elements.get(a + 1).getExponent());
            if (minGCD == 1) return minGCD;
        }
        return minGCD;
    }

    /**
     * This method is used for calculating the greatest common divisor of two integers.
     * These two integers are mainly used for calculating the exponent.
     * @param a is the first integer
     * @param b is the second integer
     * @return is the greatest common divisor of the given two numbers
     */
    private static int intGCD(int a, int b) {
        if (b == 0) return a;
        return intGCD(b, a % b);
    }

    /**
     * This method evaluates a Polynomial using the value given by x
     * @param x is the value we give to the Polynomial to be evaluated
     * @return the value of the evaluated Polynomial
     */
    public double evaluate(double x) {
        double result = 0;

        for (Monomial m1 : this.Elements) {
            result += m1.getCoefficient() * Math.pow(x, m1.getExponent());
        }
        return result;
    }

    /**
     * This method negates the Polynomial by negating all the Monomials this Polynomial has.
     * @return the negated Polynomial.
     */
    Polynomial negated() {
        Polynomial newPol = new Polynomial();
        for (Monomial a : this.Elements) {
            newPol.addElement(a.negated());
        }
        return newPol;
    }

    /**
     * This method is used to translate a Polynomial to text so it can be shown as a result.
     * @return a String representing the polynomial
     */
    public String returnString() {
        int sgn = 0; // used to see where to remove an additional "+" symbol. It remains 0 if there is no element
        NumberFormat formatter = new DecimalFormat("#0.0");
        StringBuilder result = new StringBuilder();
        for (Monomial rec : this.Elements) {
            if (rec.getCoefficient() == 0) {
            } else if (rec.getExponent() == 0) {
                result.append(" ").append(String.valueOf(formatter.format(rec.getCoefficient()))).append(" +");
                sgn = 1;
            } else if (rec.getExponent() == 1) {
                result.append(" ").append(String.valueOf(formatter.format(rec.getCoefficient()))).append("x +");
                sgn = 1;
            } else {
                result.append(" ").append(String.valueOf(formatter.format(rec.getCoefficient()))).append("x^").append(String.valueOf(rec.getExponent())).append(" +");
                sgn = 1;
            }
        }
        return result.substring(0, result.length() - sgn);
    }

    /**
     * This method is used to get the exponent of the greatest Element a Polynomial has.
     * @return the value of the exponent of the highest Element of Polynomial.
     */
    private int degree() { return this.Elements.getLast().getExponent(); }

    /**
     * Sorts all the elements of a Polynomial and makes it functional and presentable
     * by adding each Monomial with the same exponent and removing Monomials with coefficient 0
     */
    void sortElements() {

        LinkedList<Monomial> sortedMonomials = new LinkedList<>(this.Elements);

        sortedMonomials.sort(new MonomeComparator()); // Used to sort all the elements
        for (int i = 0; i < sortedMonomials.size() - 1; i++) { // Used to add elements with the same exponent
            if (sortedMonomials.get(i).getExponent() == sortedMonomials.get(i + 1).getExponent()) {
                sortedMonomials.add(i, Monomial.staticAdd(sortedMonomials.get(i), sortedMonomials.get(i + 1)));
                sortedMonomials.remove(i + 1);
                sortedMonomials.remove(i + 1);
                i--;
            }
        }

        for (int i = 0; i < sortedMonomials.size(); i++) { // used to remove Monomials with coefficient 0
            if (sortedMonomials.get(i).getCoefficient() == 0) {
                sortedMonomials.remove(i);
                i--;
            }
        }
        this.Elements = sortedMonomials;
    }

}