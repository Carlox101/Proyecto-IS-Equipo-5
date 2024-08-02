 package persistencia;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class Usuario {
    String nombre;
    String apellido;
    String email;
    String contrasena;
    int cedula;
    String tipo;
    boolean verificacion;

    public Usuario(String nombre, String apellido, String email, String contrasena, int cedula, String tipo, boolean verificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.cedula = cedula;
        this.tipo = tipo;
        this.verificacion = verificacion;
    }
}

public class CrearUsuario2 {

    public static boolean validarContrasena(String contrasena) {
        return contrasena.length() >= 8
                && contrasena.matches(".*[A-Z].*")
                && contrasena.matches(".*[a-z].*")
                && contrasena.matches(".*\\d.*");
    }

    public static boolean repetirContrasena(String password, String repass) {
        return password.equals(repass);
    }

    @SuppressWarnings("empty-statement")
    public static Usuario crearUsuario(String username, String lastname, String email, String password, int cedula, String nacionalidad, boolean verificacion) {

        System.out.println("Usuario creado exitosamente!");
        Usuario usuario = new Usuario(username, lastname, email, password, cedula, nacionalidad, verificacion);
    
     // Obtener la ruta del directorio actual
        Path currentDir = Paths.get("");
        String carpetaUsuario = currentDir.toAbsolutePath().toString() + "\\" + cedula;

        // Crear la carpeta si no existe
        try {
            Files.createDirectories(Paths.get(carpetaUsuario));
        } catch (IOException e) {
            System.err.println("Error al crear la carpeta: " + e.getMessage());
        }

        // Ruta completa del archivo dentro de la carpeta
        String rutaArchivo = carpetaUsuario + "\\" + cedula + ".txt";
        Path propuestasFolder = Paths.get(carpetaUsuario, "propuestas"); 
        try {
        Files.createDirectories(propuestasFolder);
        } catch (IOException e) {
        System.err.println("Error al crear la carpeta de propuestas: " + e.getMessage());
        }
 
        // Crear el archivo y escribir los datos
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write(nacionalidad + " " + cedula + " " + password + " " + username + " " + lastname + " " + email + " " + verificacion + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
        String usuarioClave = nacionalidad+ cedula + " " + password + System.lineSeparator();
        try {
            Files.write(Paths.get("usuariosClave.txt"), usuarioClave.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al escribir en usuariosClave.txt: " + e.getMessage());
        }
        return usuario;
    }
    
    
  public static void encontrarUsuario(String cedula) {
        String rutaArchivo = "usuarios.txt"; // Ruta del archivo donde están guardados los datos

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(" ");
                if (partes.length >= 7) {
                    String cedulaGuardada = partes[1];
                    if (cedulaGuardada.equals(cedula)) {
                        String nacionalidad = partes[0];
                        String password = partes[2];
                        String username = partes[3];
                        String lastname = partes[4];
                        String email = partes[5];
                        boolean verificacion = Boolean.parseBoolean(partes[6]);

                        return; // Salimos del bucle al encontrar el usuario
                    }
                }
            }
            System.out.println("Usuario con cédula " + cedula + " no encontrado.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

}






