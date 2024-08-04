
package persistencia;
import java.io.*;

public class CoordinacionExt {
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
}
