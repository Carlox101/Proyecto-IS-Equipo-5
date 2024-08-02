package persistencia;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;

public class seleccionarPDF {
    public static void seleccionarYCopiarPDF() {
        // Aquí creamos FileChooser para seleccionar el archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione el archivo PDF");

        // Mostrar el diálogo de selección de archivo
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Asignar el archivo seleccionado a la variable sourcePath
            Path sourcePath = fileChooser.getSelectedFile().toPath();

            // Conseguir el directorio donde se encuentra el archivo jar
            Path jarDir = Paths.get(System.getProperty("user.dir"));

            // Lo metemos en una carpeta llamada "database" o la creamos si no existe
            Path databaseDir = jarDir.resolve("database");
            if (!Files.exists(databaseDir)) {
                try {
                    Files.createDirectory(databaseDir);
                } catch (IOException e) {
                    System.err.println("Error al crear la carpeta 'database': " + e.getMessage());
                    return;
                }
            }

            // Conseguimos el nombre del archivo seleccionado
            String fileName = sourcePath.getFileName().toString();

            // Definimos la ruta de destino
            Path destinationPath = jarDir.resolve(fileName);

            // Copiar el archivo seleccionado a la ruta de destino
            try {
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Archivo copiado exitosamente.");
            } catch (IOException e) {
                System.err.println("Error al copiar el archivo: " + e.getMessage());
            }
        }
    }

}
