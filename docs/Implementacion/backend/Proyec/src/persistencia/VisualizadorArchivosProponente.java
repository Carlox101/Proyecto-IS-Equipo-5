/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ANDRE
 */
public class VisualizadorArchivosProponente {

    public static void mostrarArchivos(String cedulaProponente) {
        // Obtener la ruta base de ejecución del programa
        String rutaBase = new File("").getAbsolutePath();

        // Construir la ruta completa a la carpeta de propuestas
        String rutaPropuestas = rutaBase + File.separator + cedulaProponente + File.separator + "propuestas";

        // Crear la ventana principal
        JFrame frame = new JFrame("Archivos del Proponente");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Obtener la lista de archivos PDF
        List<String> nombresArchivos = obtenerNombresArchivosPDF(rutaPropuestas);

        // Crear la lista de archivos y el scroll pane
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(nombresArchivos);

        JList<String> listaArchivos = new JList<>(listModel);
        listaArchivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaArchivos);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Agregar el panel a la ventana y mostrarla
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Obtiene una lista de los nombres de los archivos PDF en una carpeta.
     *
     * @param rutaCarpeta La ruta de la carpeta.
     * @return Una lista con los nombres de los archivos PDF.
     */
    public static List<String> obtenerNombresArchivosPDF(String rutaCarpeta) {
        List<String> nombresArchivos = new ArrayList<>();

        File carpeta = new File(rutaCarpeta);

        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile() && archivo.getName().endsWith(".pdf")) {
                        nombresArchivos.add(archivo.getName());
                    }
                }
            } else {
                System.out.println("La carpeta especificada no contiene archivos.");
            }
        } else {
            System.out.println("La carpeta especificada no existe o no es una carpeta.");
        }

        return nombresArchivos;
    }
}
