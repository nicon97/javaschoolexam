package com.tsystems.javaschool.tasks.pyramid;

import java.util.*;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and
     * maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build
     *                with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // Los niveles se obtienen a traves de los numeros triangulares:
        // inputNumbers.size() = Nivel*(Nivel+1)/2
        // Nivel (entero) = (-1 + SQRT(1 + 8 * inputNumbers.size()))/2
        // El tiene que ser entero. Si sale con decimal, no se puede hacer la piramide

        double nivel_db = (-1 + Math.sqrt(1 + 8 * inputNumbers.size())) / 2;

        if (nivel_db % 1 != 0)
            throw new CannotBuildPyramidException();

        if (inputNumbers.contains(null))
            throw new CannotBuildPyramidException();

        Collections.sort(inputNumbers);

        int nivel = (int) nivel_db;

        int matriz[][] = new int[nivel][2 * nivel - 1];

        for (int i = 0; i < nivel; i++) {
            for (int j = 0; j < 2 * nivel - 1; j++) {
                matriz[i][j] = 0;
            }
        }

        int cont = 0;
        int k = inputNumbers.size() - 1;

        for (int i = nivel - 1; i >= 0; i--) {
            for (int j = (2 * nivel - 2) - cont; j >= cont; j--) {
                if (cont % 2 == 0) {
                    matriz[i][j] = ((j + 1) % 2) * inputNumbers.get(k);
                    k = k - (j + 1) % 2;

                } else {
                    matriz[i][j] = (j % 2) * inputNumbers.get(k);
                    k = k - j % 2;

                }
            }
            cont++;

        }

        return matriz;
    }

}

