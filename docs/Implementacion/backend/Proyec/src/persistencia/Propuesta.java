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

    public static void crearPropuesta(int cedulaProponente, char estadoCoord, char estadoDEU) {
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
    public static String validarPropuesta(int cedula, String propuesta) {
        try {
            // Construir la ruta al archivo
            String rutaBase = new File("").getAbsolutePath();
            String rutaPropuestas = rutaBase + "\\" + cedula + "\\propuestas";
            String rutaArchivo = rutaPropuestas + "\\" + propuesta + ".txt";

            // Crear un objeto File y verificar si existe
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                System.out.println("El archivo de propuesta no existe.");
                return "Propuesta no Existe";
            }

            // Leer el contenido del archivo
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea = br.readLine();
            br.close();

            // Dividir la línea en los dos caracteres
            String[] caracteres = linea.split(" ");
            if (caracteres.length != 2) {
                System.out.println("Formato de archivo inválido.");
                return "Error al formato de archivo";
            }
            char primerCaracter = caracteres[0].charAt(0);
            char segundoCaracter = caracteres[1].charAt(1);

            if (primerCaracter == 'A' && segundoCaracter == 'A'){
                return "La Propuesta fue Aceptada!!!";
            }else if(primerCaracter == 'E' || segundoCaracter == 'E'){
                return "La Propuesta sigue en Revision";
            }else if(primerCaracter == 'R' || segundoCaracter == 'R'){
            return "La Propuesta fue Rechazada";
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return "Error al abrir la propuesta";
        }
        return "Error al validar";
    }
}

