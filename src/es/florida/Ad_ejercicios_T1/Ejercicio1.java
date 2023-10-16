package es.florida.Ad_ejercicios_T1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Aplicación de Gestión de Archivos");
        System.out.println("1. Buscar y Contar Coincidencias en Ficheros");
        System.out.println("2. Fusionar Ficheros");

        System.out.print("Selecciona una opción (1/2): ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        if (opcion == 1) {
            buscarYContarCoincidencias(scanner);
        } else if (opcion == 2) {
            fusionarFicheros(scanner);
        } else {
            System.out.println("Opción no válida.");
        }

        scanner.close();
    }

    private static void buscarYContarCoincidencias(Scanner scanner) {
        System.out.print("Ingresa el directorio de búsqueda: ");
        String directorio = scanner.nextLine();

        System.out.print("Ingresa la extensión de archivo a buscar (por ejemplo, 'txt'): ");
        String extension = scanner.nextLine();

        System.out.print("Ingresa el texto a buscar: ");
        String textoABuscar = scanner.nextLine();

        File file = new File(directorio);

        if (file.isDirectory()) {
            File[] archivos = file.listFiles(new FiltroExtension(extension));

            if (archivos != null) {
                for (File archivo : archivos) {
                    int coincidencias = contarCoincidencias(archivo, textoABuscar);
                    System.out.println("Nombre del fichero: " + archivo.getName());
                    System.out.println("Extensión: " + extension);
                    System.out.println("Tamaño: " + archivo.length() + " bytes");
                    System.out.println("Última fecha de modificación: " + archivo.lastModified());
                    System.out.println("Coincidencias: " + coincidencias);
                    System.out.println();
                }
            } else {
                System.out.println("No se encontraron archivos con la extensión especificada en el directorio.");
            }
        } else {
            System.out.println("El directorio especificado no es válido.");
        }
    }

    private static void fusionarFicheros(Scanner scanner) {
        List<String> nombresFicheros = new ArrayList<>(); // Initialize an empty list to store input file names

        // Prompt the user to enter the names of the input files
        System.out.println("Fusión de ficheros");
        while (true) {
            System.out.print("Ingresa el nombre de un fichero para fusionar (o 'fin' para finalizar): ");
            String nombreFichero = scanner.nextLine();

            if (nombreFichero.equalsIgnoreCase("fin")) {
                if (nombresFicheros.size() < 2) {
                    System.out.println("Debes especificar al menos dos archivos para fusionar.");
                } else {
                    break;
                }
            } else {
                nombresFicheros.add(nombreFichero);
            }
        }

        System.out.print("Ingresa el nombre del nuevo fichero: ");
        String nuevoFichero = scanner.nextLine();
        System.out.print("Ingresa el directorio donde deseas guardar el nuevo fichero: ");
        String directorio = scanner.nextLine();

        File nuevoFicheroCompleto = new File(directorio, nuevoFichero);

        if (nuevoFicheroCompleto.exists()) {
            System.out.print("El fichero ya existe. ¿Deseas sobrescribirlo? (s/n): ");
            String confirmacion = scanner.nextLine();
            if (!"s".equalsIgnoreCase(confirmacion)) {
                System.out.println("Operación cancelada.");
                return;
            }
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nuevoFicheroCompleto))) {
            for (String nombreFichero : nombresFicheros) {
                File fichero = new File(nombreFichero);
                if (fichero.exists() && fichero.isFile()) {
                    try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {
                        String linea;
                        while ((linea = lector.readLine()) != null) {
                            escritor.write(linea);
                            escritor.newLine();
                        }
                    }
                } else {
                    System.out.println("El fichero '" + nombreFichero + "' no existe o no es válido.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Ficheros fusionados con éxito.");
    }

    private static int contarCoincidencias(File archivo, String textoABuscar) {
        int coincidencias = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                coincidencias += contarCoincidenciasEnLinea(linea, textoABuscar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coincidencias;
    }

    private static int contarCoincidenciasEnLinea(String linea, String textoABuscar) {
        int coincidencias = 0;
        int posicion = linea.indexOf(textoABuscar);

        while (posicion != -1) {
            coincidencias++;
            posicion = linea.indexOf(textoABuscar, posicion + textoABuscar.length());
        }

        return coincidencias;
    }
}