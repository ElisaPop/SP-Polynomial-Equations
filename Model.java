package gui;

import entity.Monomial;
import javafx.scene.Scene;
import javafx.stage.Stage;

import entity.Polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {

    /**
     * This method displays a verification box to see if the user truly wants to close the program or not.
     * This is mainly used if we have something to save before the application is closed, but here it isn't the case.
     *
     * It's a good function to avoid accidental closing
     *
    */

    static private void closeProgram(Stage window) {
        boolean answer = UtilsGUI.exitAlert("WARNING", "Are you sure you want to terminate the program?");
        if (answer)
            window.close();
    }

    /**
     * Sets the main scene for the stage that will be called in the main function.
     * It also sets the dimensions of the scene.
    */
    public static Scene mainInterface() {
        return new Scene(View.mainInterfaceUI(), 800, 600);
    }

    public static void alertOnClose(Stage primaryStage) {
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(primaryStage);
        });
    }

    /**
     * This method parses the input to transform it into an operable Polynomial
     * @param text String from the text box
     * @return a fully operable Polynomial
     */

    public static Polynomial getPolynome(String text) {
        text = text.replace(" ", "");
        text = text.replace("-", "+-");
        Pattern r = Pattern.compile("\\G[+]?(?:(?:(-?[0-9]+([.][0-9]*)?)?+[*]?+x?\\^?([0-9]+)?))");
        Polynomial pol = new Polynomial();
        Matcher m = r.matcher(text);
        int lastMatchPosition = 0;

        while (m.find()) {
            Monomial aux = new Monomial(0, 0);

            if (m.group(1) != null && m.group(3) != null) { // if both coefficient and exponent exists
                aux.setCoefficient(Double.parseDouble(m.group(1)));
                aux.setExponent(Integer.parseInt(m.group(3)));
            } else if (m.group(1) != null) aux.setCoefficient(Double.parseDouble(m.group(1))); // if only coefficient exists
            else if (m.group(3) != null) aux.setExponent(Integer.parseInt(m.group(3))); // if only the exponent exists

            Polynomial auxiliary = new Polynomial();
            auxiliary.addElement(aux);
            pol = Polynomial.staticAdd(pol, auxiliary);

            lastMatchPosition = m.end();
        }

        if (lastMatchPosition != text.length())
            gui.UtilsGUI.alert("ERROR", "You've entered an invalid input. Please try again.", "OK");

        return pol;
    }

    static String viewAdd(String pol1, String pol2)
    {
        Polynomial polyn1 = Model.getPolynome(pol1);
        Polynomial polyn2 = Model.getPolynome(pol2);

        Polynomial polyn3 = entity.Polynomial.staticAdd(polyn1, polyn2);
        String result = polyn3.returnString();
        return result;
    }

    static String viewSubtract(String pol1, String pol2)
    {
        Polynomial polyn1 = Model.getPolynome(pol1);
        Polynomial polyn2 = Model.getPolynome(pol2);

        Polynomial polyn3 = entity.Polynomial.staticSubtract(polyn1, polyn2);
        String result = polyn3.returnString();
        return result;
    }
    static String viewMultiply(String pol1, String pol2)
    {
        Polynomial polyn1 = Model.getPolynome(pol1);
        Polynomial polyn2 = Model.getPolynome(pol2);

        Polynomial polyn3 = entity.Polynomial.staticMultiply(polyn1, polyn2);
        String result = polyn3.returnString();
        return result;
    }
    static String viewDivide(String pol1, String pol2)
    {
        Polynomial polyn1 = Model.getPolynome(pol1);
        Polynomial polyn2 = Model.getPolynome(pol2);

        return entity.Polynomial.staticDivide(polyn1, polyn2);
    }
    static String viewDifferentiate(String pol1)
    {
        Polynomial polyn1 = Model.getPolynome(pol1);

        Polynomial polyn3 = entity.Polynomial.staticDifferentiate(polyn1);
        String result = polyn3.returnString();
        return result;
    }
    static String viewIntegrate(String pol1)
    {
        Polynomial polyn1 = Model.getPolynome(pol1);

        Polynomial polyn3 = entity.Polynomial.staticIntegrate(polyn1);
        String result = polyn3.returnString();
        return result;
    }

    static String viewEvaluation(String pol1, String x)
    {
        Polynomial polyn1 = Model.getPolynome(pol1);
        double result = polyn1.evaluate(Double.parseDouble(x));

        return String.valueOf(result);
    }
}


