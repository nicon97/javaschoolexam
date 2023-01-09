package com.tsystems.javaschool.tasks.currencyconverter;

public class CurrencyConverter {

    /**
     * Currency converter which converts the input statement and returns the result value.
     *
     * @param dollarToEuroRate 1 Dollar equals XX Euro
     * @param statement input statement to convert
     * @return converted value
     */
    public String convert(double dollarToEuroRate, String statement) {
        // TODO: Implement the logic here
        return "";
    }
}package com.tsystems.javaschool.tasks.currencyconverter;

import javax.lang.model.util.ElementScanner14;

public class CurrencyConverter {

    /**
     * Currency converter which converts the input statement and returns the result
     * value.
     *
     * @param dollarToEuroRate 1 Dollar equals XX Euro
     * @param statement        input statement to convert
     * @return converted value
     */
    public String convert(double dollarToEuroRate, String statement) {
        // TODO: Implement the logic here

        String statement_fun = statement.concat(" ");
        boolean fin_euro;
        boolean cancelacion_cambio = false;
        double operaciones[] = new double[statement.length() * 2];
        int cont_oper = 0;
        double conversion = -1;
        int cont_conv = 0;

        String lectura = "";

        for (int i = 0; i < statement.length(); i++) {

            if ("c".contains(Character.toString(statement_fun.charAt(i)))) {
                if (cont_conv == 0) {
                    if ("convertToDollar(".contains(statement_fun.subSequence(i, i + "convertToDollar(".length()))) {
                        conversion = 0;
                        cont_conv++;
                        i = i + "convertToDollar(".length() - 1;
                    } else if ("convertToEuro(".contains(statement_fun.subSequence(i, i + "convertToEuro(".length()))) {
                        conversion = 1;
                        cont_conv++;
                        i = i + "convertToEuro(".length() - 1;
                    } else
                        throw new CannotConvertCurrencyException();
                }

                else if ("convertToDollar(".contains(statement_fun.subSequence(i, i + "convertToDollar(".length()))) {
                    if (conversion == 0)
                        throw new CannotConvertCurrencyException();

                    if (cont_conv % 2 == 0) {
                        cancelacion_cambio = false;
                    } else
                        cancelacion_cambio = true;

                    cont_conv++;
                    i = i + "convertToDollar(".length() - 1;
                }

                else if ("convertToEuro(".contains(statement_fun.subSequence(i, i + "convertToEuro(".length()))) {
                    if (conversion == 1)
                        throw new CannotConvertCurrencyException();

                    if (cont_conv % 2 == 1) {
                        cancelacion_cambio = true;
                    } else
                        cancelacion_cambio = false;

                    cont_conv++;
                    i = i + "convertToEuro(".length() - 1;
                }

                else
                    throw new CannotConvertCurrencyException();
            }

            else if (")".contains(Character.toString(statement_fun.charAt(i)))) {
                cancelacion_cambio = !cancelacion_cambio;
                cont_conv--;
            }

            else if ("$".contains(Character.toString(statement_fun.charAt(i)))) {
                i++;
                while ("0123456789.".contains(Character.toString(statement_fun.charAt(i)))
                        && (i < statement.length())) {
                    lectura = lectura.concat(Character.toString(statement_fun.charAt(i)));
                    i++;
                }
                try {
                    operaciones[cont_oper + 2] = Double.parseDouble(lectura);
                } catch (Exception e) {
                    throw new CannotConvertCurrencyException();
                }
                operaciones[cont_oper + 1] = 0;
                // operaciones[cont_oper] = -1;
                if (cancelacion_cambio)
                    operaciones[cont_oper] = -1;
                else
                    operaciones[cont_oper] = conversion;

                cont_oper = cont_oper + 3;
                i--;
                lectura = "";
            }

            else if ("0123456789.".contains(Character.toString(statement_fun.charAt(i)))) {
                while ("0123456789.".contains(Character.toString(statement_fun.charAt(i)))
                        && (i < statement.length())) {
                    lectura = lectura.concat(Character.toString(statement_fun.charAt(i)));
                    i++;
                }
                try {
                    fin_euro = !"euro".contains(statement_fun.subSequence(i, i + "euro".length()));
                } catch (Exception e) {
                    throw new CannotConvertCurrencyException();
                }

                if (fin_euro)
                    throw new CannotConvertCurrencyException();

                i = i + "euro".length();

                try {
                    operaciones[cont_oper + 2] = Double.parseDouble(lectura);
                } catch (Exception e) {
                    throw new CannotConvertCurrencyException();
                }
                operaciones[cont_oper + 1] = 1;

                if (cancelacion_cambio)
                    operaciones[cont_oper] = -1;
                else
                    operaciones[cont_oper] = conversion;

                cont_oper = cont_oper + 3;
                i--;
                lectura = "";
            }

            else if ("+".contains(Character.toString(statement_fun.charAt(i)))) {
                operaciones[cont_oper] = 1;
                cont_oper++;
            }

            else if ("-".contains(Character.toString(statement_fun.charAt(i)))) {
                operaciones[cont_oper] = -1;
                cont_oper++;
            } else
                throw new CannotConvertCurrencyException();

        }

        for (int i = 0; i < cont_oper; i = i + 4) {
            if (operaciones[i] == operaciones[i + 1])
                throw new CannotConvertCurrencyException();
        }

        double result = 0;
        double divisa = 0;

        for (int i = 0; i < cont_oper; i++) {
            if (i % 4 == 0) {
                switch ((int) operaciones[i]) {
                    case 0:
                        operaciones[i] = -1;
                        operaciones[i + 1] = 0;
                        operaciones[i + 2] = operaciones[i + 2] / dollarToEuroRate;
                        break;

                    case 1:
                        operaciones[i] = -1;
                        operaciones[i + 1] = 1;
                        operaciones[i + 2] = operaciones[i + 2] * dollarToEuroRate;
                        break;
                }
            }
        }

        if (cont_oper > 3) {
            for (int i = 3; i < cont_oper; i = i + 4) {
                if (operaciones[i - 2] != operaciones[i + 2])
                    throw new CannotConvertCurrencyException();
            }
        }

        divisa = operaciones[1];

        if (cont_oper <= 3) {
            result = operaciones[2];
        } else {
            result = operaciones[2];
            for (int i = 3; i < cont_oper; i = i + 4) {
                switch ((int) operaciones[i]) {
                    case 1:
                        result = result + operaciones[i + 3];
                        break;

                    case -1:
                        result = result - operaciones[i + 3];
                        break;
                }
            }
        }

        String solucion_preliminar = String.format("%.2f", result);
        String solucion;
        int longitud = solucion_preliminar.length();
        int i = longitud - 1;
        int cont_ceros = 0;

        while ("0".contains(Character.toString(solucion_preliminar.charAt(i)))) {
            cont_ceros++;
            i--;
        }

        if (cont_ceros == 2)
            solucion = solucion_preliminar.substring(0, i);
        else
            solucion = solucion_preliminar.substring(0, i + 1);

        if ((int) divisa == 0)
            return "$".concat(solucion);
        else
            return solucion.concat("euro");

    }
}
