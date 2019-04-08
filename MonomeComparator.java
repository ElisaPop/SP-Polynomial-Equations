package comparators;

import entity.Monomial;

import java.util.Comparator;

/**
 * A class containing a method used for sorting Polynomils.
 * It sorts each Monomial by exponent.
 */

public class MonomeComparator implements Comparator<Monomial> {

    @Override
    public int compare(Monomial d1, Monomial d2) {
        return Integer.compare(d1.getExponent(), d2.getExponent());
    }

}
