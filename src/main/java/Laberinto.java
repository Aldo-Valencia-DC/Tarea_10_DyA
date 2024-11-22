import java.util.*;

public class Laberinto {
    static int filas = 14, columnas = 8;
    static char[][] laberinto = {
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {'p', 'p', 'p', 'p', 'c', 'p', 'p', 'p'},
            {'p', 'c', 'c', 'c', 'c', 'c', 'c', 'p'},
            {'p', 'c', 'p', 'c', 'p', 'p', 'c', 'p'},
            {'p', 'c', 'p', 'c', 'p', 'p', 'c', 'p'},
            {'p', 'c', 'p', 'c', 'c', 'c', 'c', 'p'},
            {'p', 'c', 'p', 'c', 'p', 'p', 'c', 'p'},
            {'p', 'c', 'c', 'c', 'c', 'p', 'c', 'p'},
            {'p', 'p', 'p', 'p', 'c', 'p', 'c', 's'},
            {'p', 'p', 'c', 'p', 'c', 'p', 'p', 'p'},
            {'p', 'c', 'c', 'c', 'c', 'c', 'p', 'p'},
            {'p', 'p', 'c', 'p', 'p', 'c', 'p', 'p'},
            {'p', 'p', 'E', 'p', 'p', 'p', 'p', 'p'}

    };

    static int[] inicio = {13, 2};
    static int[] salida = {9, 7};

    static class Nodo implements Comparable<Nodo> {
        int x, y, pasos, estimacion;

        Nodo(int x, int y, int pasos, int estimacion) {
            this.x = x;
            this.y = y;
            this.pasos = pasos;
            this.estimacion = estimacion;
        }

        @Override
        public int compareTo(Nodo otro) {
            return Integer.compare(this.pasos + this.estimacion, otro.pasos + otro.estimacion);
        }
    }

    static int distanciaManhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static boolean esValido(int x, int y) {
        return x >= 0 && x < filas && y >= 0 && y < columnas && laberinto[x][y] != 'p';
    }

    static void encontrarCamino() {
        PriorityQueue<Nodo> cola = new PriorityQueue<>();
        boolean[][] visitado = new boolean[filas][columnas];

        cola.add(new Nodo(inicio[0], inicio[1], 0, distanciaManhattan(inicio[0], inicio[1], salida[0], salida[1])));

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();

            if (visitado[actual.x][actual.y]) continue;
            visitado[actual.x][actual.y] = true;

            if (actual.x == salida[0] && actual.y == salida[1]) {
                System.out.println("Camino encontrado con " + actual.pasos + " pasos.");
                return;
            }

            int[][] direcciones = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] d : direcciones) {
                int nuevoX = actual.x + d[0];
                int nuevoY = actual.y + d[1];

                if (esValido(nuevoX, nuevoY) && !visitado[nuevoX][nuevoY]) {
                    int estimacion = distanciaManhattan(nuevoX, nuevoY, salida[0], salida[1]);
                    cola.add(new Nodo(nuevoX, nuevoY, actual.pasos + 1, estimacion));
                }
            }
        }

        System.out.println("No se encontró un camino.");
    }

    public static void main(String[] args) {
        encontrarCamino();
    }
}

