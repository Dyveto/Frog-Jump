import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        Solution solucion = new Solution();

        int[] piedrasEjemplo1 = {0, 1, 3, 5, 6, 8, 12, 17};
        int[] piedrasEjemplo2 = {0, 1, 2, 3, 4, 8, 9, 11};

        System.out.println("Ejemplo 1 (Deberia ser true): " + solucion.canCross(piedrasEjemplo1));
        System.out.println("Ejemplo 2 (Deberia ser false): " + solucion.canCross(piedrasEjemplo2));
    }
}

class Solution {
    private int[] piedras;
    private Boolean[][] memo;
    private HashMap<Integer, Integer> mapaPiedras;

    public boolean canCross(int[] stones) {
        this.piedras = stones;
        int n = stones.length;

        if( n<= 2 || n>= 2000) {
            System.out.println("No puedes tener 2 piedras o menos ni 2000 piedras o mas");
            return false;
        }

        mapaPiedras = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mapaPiedras.put(stones[i], i);
        }

        memo = new Boolean[n][n+1];

        return saltoRecursivo(0, 1); // primer salto es 1
    }

    private boolean saltoRecursivo(int indiceActual, int saltoAnteriorK) {
        if (indiceActual == piedras.length - 1)
            return true; // llega al final la rana

        if (memo[indiceActual][saltoAnteriorK] != null)
            return memo[indiceActual][saltoAnteriorK]; // ruta ya recorrida

        int[] posiblesSaltos = {saltoAnteriorK - 1, saltoAnteriorK, saltoAnteriorK + 1};

        for(int siguienteSalto : posiblesSaltos) {
            if(siguienteSalto > 0) {

                int posicionDestino = piedras[indiceActual] + siguienteSalto;

                if(mapaPiedras.containsKey(posicionDestino)) {
                    int indiceSiguientePiedra = mapaPiedras.get(posicionDestino);

                    if(saltoRecursivo(indiceSiguientePiedra, siguienteSalto)) {
                        memo[indiceActual][saltoAnteriorK] = true;
                        return true;
                    }
                }
            }
        }
        memo[indiceActual][saltoAnteriorK] = false;
        return false;
    }
}