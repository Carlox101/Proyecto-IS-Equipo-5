package persistencia;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class seleccionarPDF {
    public static void seleccionarYCopiarPDF() {
        // Crear un selector de archivos
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione el archivo PDF");

        // Mostrar el diálogo y obtener el archivo seleccionado
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            Path sourcePath = fileChooser.getSelectedFile().toPath();

            // Obtener la ruta base (directorio del proyecto)
            Path rutaBase = Paths.get(System.getProperty("user.dir"));

            // Construir la ruta de destino completa
            Path destinationPath = rutaBase.resolve("28309031").resolve("propuestas").resolve(sourcePath.getFileName());

            // Crear los directorios de destino si no existen
            try {
                Files.createDirectories(destinationPath.getParent());
            } catch (IOException e) {
                System.err.println("Error al crear los directorios: " + e.getMessage());
                return;
            }

            // Copiar el archivo a la ruta de destino
            try {
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Archivo copiado exitosamente a: " + destinationPath);
            } catch (IOException e) {
                System.err.println("Error al copiar el archivo: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}