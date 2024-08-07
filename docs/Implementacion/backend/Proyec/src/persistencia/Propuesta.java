package persistencia;
import java.io.*;
import java.nio.file.*;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author ANDRE
 */
public class Propuesta {
        private char estadoCoord;
        private char estadoDEU;

    // Constructor con valores por defecto 'E'
    public Propuesta() {
        this.estadoCoord = 'E';
        this.estadoDEU = 'E';
    }

    public static void crearPropuesta(String cedulaProponente, char estadoCoord, char estadoDEU) {
        // Obtener la ruta de la carpeta actual
        String rutaActual = System.getProperty("user.dir");

        // Construir la ruta completa para la carpeta del proponente
        String rutaProponente = rutaActual + File.separator + cedulaProponente + File.separator + "propuestas";

        // Crear los directorios si no existen
        new File(rutaProponente).mkdirs();

        // Generar un código aleatorio de 4 dígitos
        Random random = new Random();
        int codigoUnico = random.nextInt(9000) + 1000;

        // Construir el nombre del archivo .txt con el código único
        String nombreArchivoTxt = cedulaProponente + "_" + codigoUnico + ".txt";

        // Crear el contenido del archivo .txt
        String contenido = estadoCoord + " " + estadoDEU;

        try {
            // Escribir el contenido en el archivo
            FileWriter writer = new FileWriter(rutaProponente + File.separator + nombreArchivoTxt);
            writer.write(contenido);
            writer.close();
            System.out.println("Propuesta creada exitosamente. Archivo .txt generado: " + nombreArchivoTxt);
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }
 public static String validarPropuesta(String cedula, String propuesta) {
    try (BufferedReader br = new BufferedReader(new FileReader(getRutaArchivo(cedula, propuesta)))) {
        String linea = br.readLine();
        // Eliminar espacios en blanco al inicio y final de la línea
        linea = linea.trim();

        // Dividir la línea por espacios y tomar los dos primeros elementos
        String[] partes = linea.split("\\s+"); // Divide por uno o más espacios
        if (partes.length != 2) {
            return "Formato de archivo inválido.";
        }

        // Convertir a mayúsculas para una comparación insensible a mayúsculas/minúsculas
        String primerCaracter = partes[0].toUpperCase();
        String segundoCaracter = partes[1].toUpperCase();

        if (primerCaracter.equals("A") && segundoCaracter.equals("A")) {
            return "La Propuesta fue Aceptada!!!";
        } else if (primerCaracter.equals("R") || segundoCaracter.equals("R")) {
            return "La Propuesta fue Rechazada";
        }else if (primerCaracter.equals("E") || segundoCaracter.equals("E")) {
            return "La Propuesta sigue en Revision";
        }  else {
            return "Estado de la propuesta desconocido.";
        }
    } catch (FileNotFoundException e) {
        return "Archivo no encontrado: " + e.getMessage();
    } catch (IOException e) {
        return "Error al leer el archivo: " + e.getMessage();
    }
}
    private static String getRutaArchivo(String cedula, String propuesta) {
        String rutaBase = new File("").getAbsolutePath();
        String rutaPropuestas = rutaBase + "\\" + cedula + "\\propuestas";
        return rutaPropuestas + "\\" + propuesta + ".txt";
    }
}

