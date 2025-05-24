/** Desafío 1: Tablas Hash - Resolución de Colisiones (Encadenamiento)
Pregunta: ¿Cómo manejarías colisiones en una tabla hash utilizando el método de encadenamiento? ¿Qué ventajas y desventajas tiene este enfoque en comparación con otros métodos?
 Caso de Uso: Imagina que estás creando una aplicación que almacena productos en un inventario. Cada producto tiene un ID único, y debes buscar rápidamente si un producto está en el inventario. Si dos productos tienen el mismo ID (colisión), ¿cómo lo manejarías?
 Código en Java (Encadenamiento): */

import java.util.LinkedList; // Se importa la linkedList para usar listas enlazadas

// Se crea la clase 'HashTable'
class Desafio1 {

    static class Node1 { // Es la clase para representar cada nodo dentro de la tabla hash
        int key; // Es la clave que identifica al producto
        String value; // Valor que se asocia a la clave del producto

        public Node1(int key, String value) { // Constructor que inicializa el nodo con la clave y el valor
            this.key = key; // Asigna la clave
            this.value = value; // Asigna el valor
        }
    }

    private LinkedList<Node1>[] table; // Se crea un arreglo de listas enlazadas con los nodos
    private int size; // Tamaño de la tabla hash

    public Desafio1(int size) { // Metodo constructor para la tabla hash
        this.size = size; // Se le asigna el tamaño
        table = new LinkedList[size]; // Crea el arreglo de listas enlazadas del tamaño recibido
        for (int i = 0; i < size; i++) {  // Se crea un bucle for que recorre cada posicion
            table[i] = new LinkedList<>(); // Inicializa cada posicion del arreglo con una nueva lista vacia
        }
    }

    private int hash(int key) { // determina la posicion en el arreglo a partir de la clave
        return key % size; // Usa el modulo para obtener el indice dentro del rango del tamaño
    }

    public void insert(int key, String value) { // Inserta un nuevo nodo en la tabla hash
        int index = hash(key); // Calcula el indice para poner el valor a partir de la clave
        table[index].add(new Node1(key, value)); // Agrega un nuevo nodo con la clave y valor en la posicion de la lista
    }

    public String search(int key) { // Metodo para buscar un nodo en la tabla
        int index = hash(key); // Calcula el indice para poner el valor a partir de la clave
        for (Node1 node : table[index]) { // Recorre la lista en la posicion correspondiente
            if (node.key == key) {  // Si encuentra el nodo con la clase se devuelve el valor de este
                return node.value;
            }
        }
        return null;  // Si no encuentra el nodo se devuelve null
    }

    public static void main(String[] args) {
        Desafio1 hashTable = new Desafio1(10); // Se crea en el main una tabla hash con 10 posiciones
        hashTable.insert(101, "Producto A"); // Inserta un producto con la key de 101
        hashTable.insert(102, "Producto B"); // Inserta un producto con la key de 102
        System.out.println(hashTable.search(101)); // Busca e imprime el valor que se especifica, en este caso imprime en consola "Producto A"
    }
}
