import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LeerArchivosTXT {

    public static List<String> obtenerNombresArchivosTXT(String rutaCarpeta) {
        List<String> nombresArchivos = new ArrayList<>();

        File carpeta = new File(rutaCarpeta);

        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null){
                for (File archivo : archivos) {
                    if (archivo.isFile()
 && archivo.getName().endsWith(".txt")) {
                        // Aquí eliminamos la extensión usando substring
                        String nombreSinExt = archivo.getName().substring(0, archivo.getName().lastIndexOf("."));
                        nombresArchivos.add(nombreSinExt);
                    }
                }
            }
        } else {
            System.out.println("La carpeta especificada no existe o no es una carpeta.");
        }

        return nombresArchivos;
    }

    public static void main(String[] args) {
        // Reemplaza "C:/ruta/a/tu/carpeta" con la ruta real de tu carpeta
        String rutaCarpeta = "C:\\test";

        List<String> nombresArchivos = obtenerNombresArchivosTXT(rutaCarpeta);

        if (!nombresArchivos.isEmpty()) {
            System.out.println("Lista de Proponentes: ");
            for (String nombreArchivo : nombresArchivos) {
                System.out.println(nombreArchivo);
            }
        } else {
            System.out.println("No se encontraron archivos .txt en la carpeta especificada.");
        }
    }
}