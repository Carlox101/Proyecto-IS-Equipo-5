package persistencia;
import java.io.FileWriter;
import java.io.IOException;

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
         String contenido = cedula + " " + password + " " + username + " " + lastname + " " + nacionalidad + " " + email + " " + verificacion;
    
        
        try {
            // Ruta donde se guardará el archivo
            String rutaArchivo = "C:\\Users\\ANDRE\\Desktop\\Proponentes" + cedula + ".txt";

            // Crear el archivo y escribir los datos
            FileWriter writer = new FileWriter(rutaArchivo);
            writer.write(contenido);
            writer.close();
        }catch (IOException e){
        
        }
        return usuario;
    }
}
