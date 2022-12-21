package com.tsystems.javaschool.tasks.calculator;

import java.util.*;
import java.lang.Math;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as
     *                  decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is
     *         invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here

        if (statement == null || statement == "")
            return null;

        for (int i = 0; i < statement.length(); i++) {
            if (!"0123456789+-*/.()".contains(Character.toString(statement.charAt(i))))
                return null;
        }

        if ("+-*/.".contains(Character.toString(statement.charAt(0))))
            return null;

        for (int i = 0; i < statement.length() - 1; i++) {
            if ("+-*/.".contains(Character.toString(statement.charAt(i)))) {
                if ("+-*/.".contains(Character.toString(statement.charAt(i + 1))))
                    return null;
            }
        }

        if (statement.contains("(") || statement.contains(")")) {
            int p_abierto[] = new int[statement.length()];
            int p_cerrado[] = new int[statement.length()];

            int cont_abierto = 0;
            int cont_cerrado = 0;

            for (int i = 0; i < statement.length(); i++) {
                if ("(".contains(Character.toString(statement.charAt(i)))) {
                    p_abierto[cont_abierto] = i;
                    cont_abierto++;
                } else if (")".contains(Character.toString(statement.charAt(i)))) {
                    p_cerrado[cont_cerrado] = i;
                    cont_cerrado++;
                }
            }

            if (cont_abierto != cont_cerrado)
                return null;

            for (int i = 0; i < cont_abierto - 1; i++) {
                if (!((p_cerrado[i] > p_abierto[i]) && (p_abierto[i + 1] > p_cerrado[i])))
                    return null;
            }
        }

        // * -> 2 , / -> -2, + -> 1, - -> -1 (aumentar de prioridad *2), posiciones
        // impares

        Float base_oper[] = new Float[statement.length()];
        int cont_base = 0;
        String lectura = "";
        int cont_puntos = 0;
        float prioridad = 0; // deteccion de parentesis
        for (int i = 0; i < statement.length(); i++) {
            if ("0123456789.".contains(Character.toString(statement.charAt(i)))) {
                lectura = lectura.concat(Character.toString(statement.charAt(i)));
            } else if ("(".contains(Character.toString(statement.charAt(i)))) {
                prioridad++;
            } else if (")".contains(Character.toString(statement.charAt(i))))
                prioridad--;
            else {
                if (lectura.contains(".")) {
                    for (int j = 0; j < lectura.length(); j++)
                        if (".".contains(Character.toString(lectura.charAt(j))))
                            cont_puntos++;

                    if (cont_puntos > 1)
                        return null;
                    cont_puntos = 0;
                }

                base_oper[cont_base] = Float.parseFloat(lectura);
                cont_base++;
                lectura = "";

                switch (Character.toString(statement.charAt(i))) {
                    case "*":
                        base_oper[cont_base] = 2 * (1 + prioridad * 2);
                        break;
                    case "/":
                        base_oper[cont_base] = -2 * (1 + prioridad * 2);
                        break;
                    case "+":
                        base_oper[cont_base] = 1 * (1 + prioridad * 2);
                        break;
                    case "-":
                        base_oper[cont_base] = -1 * (1 + prioridad * 2);
                        break;
                }
                cont_base++;

            }
        }

        if (lectura.contains(".")) {
            for (int j = 0; j < lectura.length(); j++)
                if (".".contains(Character.toString(lectura.charAt(j))))
                    cont_puntos++;
            if (cont_puntos > 1)
                return null;
            cont_puntos = 0;
        }

        base_oper[cont_base] = Float.parseFloat(lectura);

        List<Float> base_oper_l = new ArrayList<Float>();

        for (int i = 0; i <= cont_base; i++) {
            base_oper_l.add(base_oper[i]);
        }

        float op = base_oper[1];
        int index_op = 1;
        float result = 0;

        // boolean flag = true;
        while (cont_base != 0) {
            for (int i = 0; i <= cont_base; i++) {
                if (i % 2 == 1) {
                    if (Math.abs(base_oper_l.get(i)) > Math.abs(op))
                        if ((index_op < i)) {
                            op = base_oper_l.get(i);
                            index_op = i;
                        }
                }
            }

            if (Math.abs(op) == 3)
                op = op / 3;
            else if (Math.abs(op) == 6)
                op = op / 3;

            switch ((int) op) {
                case 2:
                    result = base_oper_l.get(index_op - 1) * base_oper_l.get(index_op + 1);
                    break;
                case -2:
                    if (base_oper_l.get(index_op + 1) == 0)
                        return null;
                    result = base_oper_l.get(index_op - 1) / base_oper_l.get(index_op + 1);
                    break;
                case 1:
                    result = base_oper_l.get(index_op - 1) + base_oper_l.get(index_op + 1);
                    break;
                case -1:
                    result = base_oper_l.get(index_op - 1) - base_oper_l.get(index_op + 1);
                    break;
            }

            base_oper_l.add(index_op - 1, (Float) result);
            base_oper_l.remove(index_op);
            base_oper_l.remove(index_op);
            base_oper_l.remove(index_op);

            cont_base = cont_base - 2;
            op = 0;
            index_op = 0;

        }

        result = base_oper_l.get(0);

        String solucion_preliminar = String.format("%.4f", result);
        String solucion;
        int longitud = solucion_preliminar.length();
        int i = longitud - 1;
        int cont_ceros = 0;

        while ("0".contains(Character.toString(solucion_preliminar.charAt(i)))) {
            cont_ceros++;
            i--;
        }

        if (cont_ceros == 4)
            solucion = solucion_preliminar.substring(0, i);
        else
            solucion = solucion_preliminar.substring(0, i + 1);

        return solucion;
    }

}

