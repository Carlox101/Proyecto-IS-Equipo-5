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

        // Crear el archivo y escribir los datos
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write(nacionalidad + cedula + " " + password + " " + username + " " + lastname + " " + email + " " + verificacion);
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

    public static void main(String[] args) {
        // Datos de prueba para un nuevo usuario
        String nombre = "Juan";
        String apellido = "Pérez";
        String email = "juanperez@example.com";
        String password = "contraseña123";
        int cedula = 29665267;
        String nacionalidad = "V";
        boolean verificacion = false;

        // Crear el usuario
        Usuario nuevoUsuario = CrearUsuario2.crearUsuario(nombre, apellido, email, password, cedula, nacionalidad, verificacion);

        // Mensaje de confirmación
        System.out.println("Usuario creado exitosamente: " + nuevoUsuario.nombre + " " + nuevoUsuario.apellido);
    }
}
