package com.tsystems.javaschool.tasks.spreadsheet;

import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {

    /**
     * Process table cells
     *
     * @param inputData unprocessed table cells
     */
    public List<String> process(List<String> inputData) {
        // TODO : Implement your solutiopackage com.tsystems.javaschool.tasks.spreadsheet;

import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {

    /**
     * Process table cells
     *
     * @param inputData unprocessed table cells
     */
    public List<String> process(List<String> inputData) {
        // TODO : Implement your solution here

        // A B C D Input:
        // 1 f1 f2 f3 f4 {"f1 f1 f1 f1", "f2 f2 f2 f2", "f3 f3 f3 f3",
        // 2 f1 f2 f3 f4 "f4 f4 f4 f4"}
        // 3 f1 f2 f3 f4
        // 4 f1 f2 f3 f4 separacion de elementos con espacios

        if (inputData.size() == 1 && inputData.get(0).length() == 0)
            return inputData;

        String[][] operaciones = new String[inputData.size()][inputData.get(0).split(" ").length];

        for (int i = 0; i < inputData.size(); i++) {
            operaciones[i] = inputData.get(i).split(" ");
        }

        int valor = 0;
        int repetir = 1;
        int operacion = -1;

        while (repetir != 0) {
            repetir = 0;
            for (int i = 0; i < inputData.size(); i++) {
                for (int j = 0; j < inputData.get(0).split(" ").length; j++) {
                    if ("=".contains(Character.toString(operaciones[i][j].charAt(0)))) {

                        if (operaciones[i][j].indexOf("+") != -1) {
                            operacion = operaciones[i][j].indexOf("+");
                            try {
                                valor = Integer.parseInt(operaciones[i][j].substring(1, operacion));
                            } catch (Exception e) {
                                try {
                                    int i1 = operaciones[i][j].charAt(1) - 'A';
                                    int i2 = Integer.parseInt(operaciones[i][j].substring(2, operacion)) - 1;
                                    valor = Integer.parseInt(operaciones[i1][i2]);
                                } catch (Exception f) {
                                    repetir++;
                                    continue;
                                }
                            }
                            try {
                                valor += Integer.parseInt(operaciones[i][j].substring(operacion + 1));
                            } catch (Exception e) {
                                try {
                                    int i1 = operaciones[i][j].charAt(operacion + 1) - 'A';
                                    int i2 = Integer.parseInt(operaciones[i][j].substring(operacion + 2)) - 1;
                                    valor += Integer.parseInt(operaciones[i1][i2]);
                                } catch (Exception f) {
                                    repetir++;
                                    continue;
                                }
                            }

                            operaciones[i][j] = Integer.toString(valor);
                        } else if (operaciones[i][j].indexOf("-") != -1) {
                            operacion = operaciones[i][j].indexOf("-");
                            try {
                                valor = Integer.parseInt(operaciones[i][j].substring(1, operacion));
                            } catch (Exception e) {
                                try {
                                    int i1 = operaciones[i][j].charAt(1) - 'A';
                                    int i2 = Integer.parseInt(operaciones[i][j].substring(2, operacion)) - 1;
                                    valor = Integer.parseInt(operaciones[i1][i2]);
                                } catch (Exception f) {
                                    repetir++;
                                    continue;
                                }
                            }
                            try {
                                valor -= Integer.parseInt(operaciones[i][j].substring(operacion + 1));
                            } catch (Exception e) {
                                try {
                                    int i1 = operaciones[i][j].charAt(operacion + 1) - 'A';
                                    int i2 = Integer.parseInt(operaciones[i][j].substring(operacion + 2)) - 1;
                                    valor -= Integer.parseInt(operaciones[i1][i2]);
                                } catch (Exception f) {
                                    repetir++;
                                    continue;
                                }
                            }

                            operaciones[i][j] = Integer.toString(valor);
                        } else if (operaciones[i][j].indexOf("*") != -1) {
                            operacion = operaciones[i][j].indexOf("*");
                            try {
                                valor = Integer.parseInt(operaciones[i][j].substring(1, operacion));
                            } catch (Exception e) {
                                try {
                                    int i1 = operaciones[i][j].charAt(1) - 'A';
                                    int i2 = Integer.parseInt(operaciones[i][j].substring(2, operacion)) - 1;
                                    valor = Integer.parseInt(operaciones[i1][i2]);
                                } catch (Exception f) {
                                    repetir++;
                                    continue;
                                }
                            }
                            try {
                                valor *= Integer.parseInt(operaciones[i][j].substring(operacion + 1));
                            } catch (Exception e) {
                                try {
                                    int i1 = operaciones[i][j].charAt(operacion + 1) - 'A';
                                    int i2 = Integer.parseInt(operaciones[i][j].substring(operacion + 2)) - 1;
                                    valor *= Integer.parseInt(operaciones[i1][i2]);
                                } catch (Exception f) {
                                    repetir++;
                                    continue;
                                }
                            }

                            operaciones[i][j] = Integer.toString(valor);
                        } else if (operaciones[i][j].indexOf("/") != -1) {
                            operacion = operaciones[i][j].indexOf("/");
                            try {
                                valor = Integer.parseInt(operaciones[i][j].substring(1, operacion));
                            } catch (Exception e) {
                                try {
                                    int i1 = operaciones[i][j].charAt(1) - 'A';
                                    int i2 = Integer.parseInt(operaciones[i][j].substring(2, operacion)) - 1;
                                    valor = Integer.parseInt(operaciones[i1][i2]);
                                } catch (Exception f) {
                                    repetir++;
                                    continue;
                                }
                            }
                            try {
                                valor /= Integer.parseInt(operaciones[i][j].substring(operacion + 1));
                            } catch (Exception e) {
                                try {
                                    int i1 = operaciones[i][j].charAt(operacion + 1) - 'A';
                                    int i2 = Integer.parseInt(operaciones[i][j].substring(operacion + 2)) - 1;
                                    valor /= Integer.parseInt(operaciones[i1][i2]);
                                } catch (Exception f) {
                                    repetir++;
                                    continue;
                                }
                            }

                            operaciones[i][j] = Integer.toString(valor);
                        } else {
                            try {
                                valor = Integer.parseInt(operaciones[i][j].substring(1));
                            } catch (Exception e) {
                                try {
                                    int i1 = operaciones[i][j].charAt(1) - 'A';
                                    int i2 = Integer.parseInt(operaciones[i][j].substring(2)) - 1;
                                    valor = Integer.parseInt(operaciones[i1][i2]);
                                } catch (Exception f) {
                                    repetir++;
                                    continue;
                                }
                            }
                            operaciones[i][j] = Integer.toString(valor);
                        }

                    } else if ("0123456789".contains(Character.toString(operaciones[i][j].charAt(0)))) {
                        continue;
                    } else if ("'".contains(Character.toString(operaciones[i][j].charAt(0)))) {
                        operaciones[i][j] = operaciones[i][j].substring(1);
                    }

                }
            }
        }

        ArrayList<String> solucion = new ArrayList<String>();

        String lectura = "";
        for (int i = 0; i < inputData.size(); i++) {
            for (int j = 0; j < inputData.get(0).split(" ").length; j++) {
                lectura = lectura + operaciones[i][j] + " ";
            }
            solucion.add(lectura.substring(0, lectura.length() - 1));
            lectura = "";
        }

        return solucion;
    }
}
 here
        return new ArrayList<>();
    }
}
