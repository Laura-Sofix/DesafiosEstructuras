/** Desafío: Sistema de Gestión de Redes de Transporte Público
        Problema: Imagina que eres parte de un equipo que debe diseñar un sistema para gestionar y optimizar una red de transporte público en una ciudad. La red está formada por diferentes estaciones de tren, autobuses y paradas (puntos en el grafo) conectadas por rutas (aristas), cada una con un tiempo de viaje (peso). Los usuarios deben poder consultar las rutas más rápidas entre dos puntos de la red de transporte y ver las estaciones intermedias que deben tomar.

        El objetivo es utilizar tablas hash para almacenar la red de transporte y representar la información en un grafo. Luego, debes aplicar algoritmos para resolver distintos problemas como la búsqueda del camino más corto entre dos estaciones, encontrar el camino con menor número de paradas, y obtener un subgrafo de las estaciones más cercanas a una estación específica.

        Requisitos:
        Estaciones: Cada estación o parada de transporte tiene un identificador único (por ejemplo, un nombre o código).
        Rutas: Las conexiones entre estaciones tienen un tiempo de viaje o coste asociado.
        Operaciones: Debes permitir:
        Consultar el camino más corto entre dos estaciones utilizando Dijkstra.
        Encontrar el camino con menor número de paradas (utilizando una estrategia de BFS).
        Obtener las estaciones cercanas a una estación dada.
        Pasos a seguir:
        Representación del grafo:

        Usa una tabla hash (HashMap<String, List<int[]>>) para almacenar las estaciones y sus conexiones. Cada entrada en la tabla tendrá el nombre de la estación como clave, y el valor será una lista de aristas representadas por un array que contiene el peso y el nombre de la estación conectada.
        Algoritmos a implementar:

        Dijkstra para el camino más corto.
        BFS para el camino con el menor número de paradas.
        Funciones auxiliares para obtener las estaciones cercanas.
        Código en Java: */

import java.util.*; // Se importa la libreria util para utilizar las estructuras de datos

class Desafio13 {
    private Map<String, List<int[]>> adjList = new HashMap<>(); // Mapa para la lista de adyacencia

    public void agregarRuta(String origen, String destino, int tiempo) { // Metodo para agregar una ruta entre dos estaciones con el tiempo correspondiente
        adjList.putIfAbsent(origen, new ArrayList<>()); // Si la estacion de origen no existe en el mapa, se inicia una lista vacia
        adjList.get(origen).add(new int[]{tiempo, destino.hashCode()}); // Se agrega el destino junto con el tiempo de viaje
    }

    public void dijkstra(String inicio, String destino) { // Se usa el algoritmo de Dijkstra para hallar el camino mas corto desde una estacion de inicio a la de destino
        Map<String, Integer> distancias = new HashMap<>(); // Mapa que registra la menor distancia encontrada a cada estacion
        Map<String, String> predecesores = new HashMap<>(); // Mapa que guarda el nodo anterior en el camino optimo
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // Cola de prioridad ordenada por la distancia acumulada

        distancias.put(inicio, 0); // Se inicializa la estacion de inicio con distancia 0
        pq.add(new int[]{0, inicio.hashCode()}); // Se añade la estacion inicial a la cola con su hashCode

        while (!pq.isEmpty()) { // Se repite mientras haya estaciones por evaluar
            int[] nodo = pq.poll(); // Se extrae la estacion con menor distancia acumulada
            int distancia = nodo[0]; // Se obtiene la distancia acumulada hasta el nodo actual
            String actual = String.valueOf(nodo[1]); // Se identifica la estacion actual

            if (actual.equals(destino)) { // Si se alcanza la estacion destino, se finaliza el recorrido
                break;
            }

            if (!adjList.containsKey(actual)) continue; // Si no hay rutas desde la estacion actual, se continua
            for (int[] vecino : adjList.get(actual)) { // Se recorren todas las rutas salientes desde la estacion actual
                String vecinoNodo = String.valueOf(vecino[1]); // Se convierte el hashCode del destino a string
                int peso = vecino[0]; // Se extrae el tiempo de viaje hacia la estacion vecina
                int nuevaDistancia = distancia + peso; // Se calcula la nueva distancia acumulada

                if (nuevaDistancia < distancias.getOrDefault(vecinoNodo, Integer.MAX_VALUE)) { // Se actualiza si la nueva distancia es menor
                    distancias.put(vecinoNodo, nuevaDistancia); // Se guarda la nueva distancia mas corta
                    predecesores.put(vecinoNodo, actual); // Se registra la estacion anterior
                    pq.add(new int[]{nuevaDistancia, vecino[1]}); // Se añade la estacion vecina a la cola de prioridad
                }
            }
        }

        if (distancias.containsKey(destino)) { // Si se encontro un camino al destino se imprime la distancia total
            System.out.println("Distancia mas corta desde " + inicio + " a " + destino + ": " + distancias.get(destino));
            System.out.print("Ruta: ");
            imprimirRuta(predecesores, destino); // Se imprime el camino recorrido
        } else {
            System.out.println("No hay ruta disponible."); // Se informa en consola que no se encontro una ruta disponible
        }
    }

    private void imprimirRuta(Map<String, String> predecesores, String destino) { // imprime recursivamente la ruta desde el origen hasta el destino
        if (predecesores.containsKey(destino)) { // Si hay un nodo anterior al destino
            imprimirRuta(predecesores, predecesores.get(destino)); // Se llama recursivamente para retroceder en el camino
        }
        System.out.print(destino + " "); // Se imprime la estacion en orden de recorrido
    }

    public void bfs(String inicio, String destino) { // se utiliza el algoritmo bfs para encontrar el camino con menor numero de paradas
        Map<String, Integer> distancias = new HashMap<>(); // Se registra la cantidad de paradas desde la estacion inicial
        Queue<String> queue = new LinkedList<>(); // Se crea una cola para recorrer las estaciones por nivel
        Map<String, String> predecesores = new HashMap<>(); // Se guarda la estacion anterior para reconstruir el camino

        distancias.put(inicio, 0); // Se inicia con cero paradas desde la estacion inicial
        queue.add(inicio); // Se añade la estacion inicial a la cola

        while (!queue.isEmpty()) { // Mientras haya estaciones en la cola
            String nodo = queue.poll(); // Se obtiene la siguiente estacion en la cola

            if (!adjList.containsKey(nodo)) continue; // Si no hay conexiones desde esa estacion, se continua
            for (int[] vecino : adjList.get(nodo)) { // Se recorren las estaciones vecinas conectadas directamente
                String vecinoNodo = String.valueOf(vecino[1]); // Se obtiene la estacion vecina como 'String'
                if (!distancias.containsKey(vecinoNodo)) { // Si aun no se ha visitado esa estacion
                    distancias.put(vecinoNodo, distancias.get(nodo) + 1); // Se registra la cantidad de paradas
                    predecesores.put(vecinoNodo, nodo); // Se guarda el nodo anterior
                    queue.add(vecinoNodo); // Se añade la estacion vecina a la cola
                    if (vecinoNodo.equals(destino)) { // Si se alcanza el destino, se detiene la busqueda
                        break;
                    }
                }
            }
        }

        if (distancias.containsKey(destino)) { // Si se encontro una ruta al destino, se imprime el numero total de paradas
            System.out.println("Numero de paradas desde " + inicio + " a " + destino + ": " + distancias.get(destino));
            System.out.print("Ruta: ");
            imprimirRuta(predecesores, destino); // Se imprime la ruta desde el inicio hasta el destino
        } else {
            System.out.println("No hay ruta disponible."); // Se muestra en consola que esa ruta no esta disponible
        }
    }

    public void estacionesCercanas(String estacion) { // Metodo que muestra todas las estaciones conectadas a una estacion ingresada
        if (adjList.containsKey(estacion)) { // Si la estacion existe en el mapa se recorren todas las estaciones
            System.out.println("Estaciones cercanas a " + estacion + ": ");
            for (int[] vecino : adjList.get(estacion)) {
                System.out.println(vecino[1]); // Se imprime el identificador de la estacion vecina
            }
        } else {
            System.out.println("La estacion no existe."); // Se muestra en consola que la estacion no existe
        }
    }

    public static void main(String[] args) {
        Desafio13 grafo = new Desafio13(); // Se crea el grafo

        // Se agregan rutas entre estaciones con su tiempo de viaje
        grafo.agregarRuta("Estacion A", "Estacion B", 5);
        grafo.agregarRuta("Estacion A", "Estacion C", 8);
        grafo.agregarRuta("Estacion B", "Estacion D", 3);
        grafo.agregarRuta("Estacion C", "Estacion D", 2);
        grafo.agregarRuta("Estacion D", "Estacion E", 4);

        grafo.dijkstra("Estacion A", "Estacion E"); // Se calcula e imprime en consola la ruta mas corta segun el tiempo
        grafo.bfs("Estacion A", "Estacion E"); // Se calcula e imprime la ruta con menor numero de paradas
        grafo.estacionesCercanas("Estacion A"); // Se muestran las estaciones conectadas a 'Estacion A'
    }
}


/** Explicación del código:

Representación del Grafo:

Utilizamos un HashMap para almacenar la red de transporte público. Las claves del mapa son los nombres de las estaciones, y los valores son listas de aristas, donde cada arista es un array de dos elementos: el peso (tiempo de viaje) y el nombre de la estación de destino.
Método agregarRuta:

Este método agrega rutas (aristas) entre estaciones con el tiempo de viaje como peso.
Método dijkstra:

Implementa el algoritmo de Dijkstra para encontrar el camino más corto entre dos estaciones. Usa una PriorityQueue para asegurar que siempre procesamos el nodo con la distancia más corta.
El mapa predecesores se utiliza para reconstruir la ruta una vez que se ha encontrado el camino más corto.
Método bfs:

Implementa el algoritmo BFS para encontrar el camino con el menor número de paradas entre dos estaciones. Aquí, no nos importa el peso de las aristas, solo contar cuántas paradas se deben hacer.
Método estacionesCercanas:

Este método recibe el nombre de una estación y muestra las estaciones que están directamente conectadas a ella.
main:

En la función main, se crean algunas estaciones y se agregan rutas entre ellas. Luego, se llaman a las funciones de Dijkstra, BFS y estaciones cercanas para probar el sistema.

Caso de Uso:
Este sistema puede ser utilizado por los usuarios del transporte público para:

Encontrar la ruta más rápida entre dos estaciones (con el algoritmo de Dijkstra).
Encontrar la ruta con el menor número de paradas (utilizando BFS).
Ver qué estaciones están cerca de una estación dada (por ejemplo, estaciones cercanas a una parada de metro).

Conclusión:
Este desafío combina el uso de tablas hash para representar un grafo y las operaciones más comunes en grafos, como encontrar caminos más cortos y caminos con menor número de paradas, todo mientras se gestionan redes de transporte público de manera eficiente. */