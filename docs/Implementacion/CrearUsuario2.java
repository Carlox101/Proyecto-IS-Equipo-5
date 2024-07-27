import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Usuario {
    String nombre;
    String apellido;
    String email;
    String contrasena;
    long cedula;
    String tipo; // Nuevo atributo para el tipo de cédula

    // Constructor para inicializar los atributos
    public Usuario(String nombre, String apellido, String email, String contrasena, long cedula, String tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.cedula = cedula;
        this.tipo = tipo;
    }
}

public class CrearUsuario2{

    public static boolean validarContrasena(String contrasena) {
        if (contrasena.length() < 8 ||
        !contrasena.matches(".*[A-Z].*") ||
        !contrasena.matches(".*[a-z].*") ||
        !contrasena.matches(".*\\d.*")) {
    return false;
}
return true;
    }

    public static boolean repetirContrasena(String password) {
        Scanner scan = new Scanner(System.in);
        String repass;
        
        do {
            System.out.println("Ingrese nuevamente su Contraseña para validar por favor");
            repass = scan.nextLine();
        } while (!repass.equals(password));
        
        System.out.println("Las contraseñas coinciden.");
        return true;
    
    }

    public static String tipoCedula() {
        String tipo;
        Scanner leer = new Scanner(System.in);

        do {
            System.out.println("Indique tipo de persona: V (Venezolana), E (Extranjera), J (Jurídica)");
            tipo = leer.nextLine().toUpperCase();
            

        } while (!tipo.equals("V") && !tipo.equals("E") && !tipo.equals("J"));

        return tipo;
    }

    public static long validarCedula(String tipo) {
        Scanner leer = new Scanner(System.in);
        long cedula = 0;

            switch (tipo) {
                case "V":
                    do {
                    System.out.print("Ingrese su cédula (entre 6 y 8 dígitos): ");
                     cedula = leer.nextLong();
                     } while (cedula < 100000 || cedula > 33000000);
                    break;
                case "E":
                do {
                    System.out.print("Ingrese su cédula Extranjera (entre 6 y 8 dígitos): ");
                     cedula = leer.nextLong();
                     } while (cedula < 100000 || cedula > 99999999);
                    break;
                case "J":
                do {
                    System.out.print("Ingrese el rif de su empresa (entre 8 y 9 dígitos): ");
                     cedula = leer.nextLong();
                     } while (cedula < 1000000 || cedula > 999999999);
                    break;
                default:
                    System.err.println("Tipo de cédula inválido. Por favor, ingrese V, E o J.");
            }


        return cedula;
    }

    public static Usuario crearUsuario() {
        Scanner leer = new Scanner(System.in);
        String username, lastname, email, password, tipoced;
        boolean repass;
        long cedula;

        System.out.print("Ingrese su nombre: ");
        username = leer.nextLine();
        System.out.print("Ingrese su apellido: ");
        lastname = leer.nextLine();
        System.out.print("Ingrese su email: ");
        email = leer.nextLine();

        // Validación de contraseña
        do {
            System.out.println("Cree una contraseña (mínimo 8 caracteres, una mayúscula, una minúscula y un número):");
            password = leer.nextLine();
        } while (!validarContrasena(password));
        repass= repetirContrasena(password);

        tipoced= tipoCedula();
        cedula = validarCedula(tipoced);
        // ... (resto de la lógica para crear el usuario)
        System.out.println("Usuario creado exitosamente!");
        Usuario usuario = new Usuario(username, lastname, email, password,cedula,tipoced);
            // Crear el archivo .txt
    try (FileWriter writer = new FileWriter(cedula + ".txt")) {
        writer.write(tipoced + cedula + "\n");
        writer.write(username + "\n");
        writer.write(lastname + "\n");
        writer.write(email + "\n");
        writer.write(password);
    } catch (IOException e) {
        System.err.println("Error al crear el archivo: " + e.getMessage());
    }


        return usuario;
    }

    public static void main(String[] args) {
        Usuario nuevoUsuario = crearUsuario();
        System.out.println("Nombre de Usuario: "+nuevoUsuario.nombre);
        System.out.println("Apellido de Usuario: "+nuevoUsuario.apellido);
        System.out.println("Correo de Usuario: "+nuevoUsuario.email);
        System.out.println("Cedula de Usuario: "+nuevoUsuario.tipo+nuevoUsuario.cedula);
        System.out.println("Contraseña de Usuario: "+nuevoUsuario.contrasena);
    }
}
