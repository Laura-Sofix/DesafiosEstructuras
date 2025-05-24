/** Desafio 12: Tablas Hash - Hashing Universal
 Pregunta: ¿Que es el hashing universal y como utiliza funciones hash aleatorias para mejorar el rendimiento? ¿Por que es importante en la resolucion de colisiones?
 Caso de Uso: Implementa un sistema para almacenar las calificaciones de los estudiantes en un curso. Utiliza hashing universal para evitar colisiones frecuentes y mejorar la eficiencia de las busquedas.
 Codigo en Java (Hashing Universal):*/

import java.util.*; // Se importa la libreria util

class HashTableUniversal {
    private String[] table; // Arreglo que representa la tabla hash donde se almacenan las claves
    private Random rand = new Random(); // Se crea un objeto Random para generar coeficientes al azar
    private int a, b; // Coeficientes usados en el hash universal

    public HashTableUniversal(int size) {
        this.table = new String[size]; // Se inicializa la tabla hash con un tamaño
        this.a = rand.nextInt(size - 1) + 1; // Se genera un numero aleatorio entre 1 y -1 para a
        this.b = rand.nextInt(size); // Se genera un numero aleatorio entre 0 y -1 para b
    }

    public int hash(String key) { // Metodo que calcula el indice hash
        int hash = 0;
        for (int i = 0; i < key.length(); i++) { // Se recorre cada caracter de la clave
            hash = (a * hash + key.charAt(i)) + b; // Se aplica la formula de hashing universal
        }
        return Math.abs(hash) % table.length; // Se asegura que el resultado sea positivo y dentro del rango
    }

    public void insert(String key) { // Metodo para insertar una clave en la tabla
        int index = hash(key); // Se calcula la posicion usando la funcion hash
        if (table[index] != null) { // Se verifica si ya existe un elemento en esa posicion
            System.out.println("Colision detectada."); // Si hay colision se imprime un mensaje de que se detecto
        } else {
            table[index] = key; // Si no hay colision se almacena la clave
        }
    }

    public String search(String key) { // Metodo para buscar una clave en la tabla
        int index = hash(key); // Se calcula el indice usando la funcion hash
        return table[index]; // Se retorna la clave almacenada en la tabla
    }

    public static void main(String[] args) {
        HashTableUniversal ht = new HashTableUniversal(10); // Se crea una tabla hash con 10 posiciones
        ht.insert("Estudiante1"); // Se inserta una clave 'Estudiante1'
        ht.insert("Estudiante2"); // Se inserta otra clave 'Estudiante2'
        System.out.println(ht.search("Estudiante1")); // Se busca e imprime 'Estudiante1' en la consola
    }
}
