/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author ANDRE
 */
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DEU {

    public static void procesarPropuestaDEU(String cedula, String propuesta, boolean acept) {
        try {
            // Obtener la ruta base de ejecución del programa
            String rutaBase = new File("").getAbsolutePath();

            // Construir la ruta completa a la carpeta de propuestas
            String rutaPropuestas = rutaBase + File.separator + cedula + File.separator + "propuestas";

            // Construir la ruta completa al archivo .txt
            String rutaArchivo = rutaPropuestas + File.separator + propuesta + ".txt";

            // Crear un objeto File para representar el archivo
            File archivo = new File(rutaArchivo);

            // Verificar si el archivo existe
            if (archivo.exists()) {
                // Leer el contenido del archivo
                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                String linea = reader.readLine();
                reader.close();

                // Modificar el primer caracter según el valor de acept
                char[] caracteres = linea.toCharArray();
                caracteres[0] = acept ? 'A' : 'R';

                // Escribir el nuevo contenido en el archivo
                BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
                writer.write(caracteres);
                writer.close();

                System.out.println("Archivo modificado correctamente.");
            } else {
                System.out.println("El archivo no existe.");
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
    public class CartaRechazo {

    public static void crearCartaRechazo(String cedula, String propuesta, String carta) 
            throws IOException {
        // Obtener la ruta base
        Path rutaBase = Paths.get(System.getProperty("user.dir"));

        // Construir la ruta de destino completa
        Path destinationPath = rutaBase.resolve(cedula).resolve("propuestas").resolve(propuesta + "_CartaRechazo.txt");

        // Crear los directorios si no existen
        File directorio = destinationPath.toFile().getParentFile();
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                throw new IOException("No se pudo crear el directorio: " + directorio.getAbsolutePath());
            }
        }

        // Crear el archivo y escribir la carta
        try (FileWriter writer = new FileWriter(destinationPath.toFile())) {
            writer.write(carta);
        }
    }
}
}

