import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LeerArchivosPDF {

    public static List<String> obtenerNombresArchivosPDF(String rutaCarpeta) {
        List<String> nombresArchivos = new ArrayList<>();

        File carpeta = new File(rutaCarpeta);

        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile() && archivo.getName().endsWith(".pdf")) {
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
 }