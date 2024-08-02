import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VerificadorCredenciales {

    public static boolean verificarCredenciales(String usuario, String clave) {
        try (BufferedReader br = new BufferedReader(new FileReader("usuariosClave.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] credenciales = linea.split(" ");
                if (credenciales.length == 2 &&
                        credenciales[0].equals(usuario) &&
                        credenciales[1].equals(clave)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String usuarioIngresado = "29665267";
        String claveIngresada = "29665267Ch";

        if (verificarCredenciales(usuarioIngresado, claveIngresada)) {
            System.out.println("Credenciales válidas");
        } else {
            System.out.println("Credenciales inválidas");
        }
    }
}
