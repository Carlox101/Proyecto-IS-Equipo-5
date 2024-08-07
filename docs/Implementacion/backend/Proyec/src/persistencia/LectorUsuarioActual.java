package persistencia;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlo
 */
public class LectorUsuarioActual {
    public static String obtenerCedula() {
        String cedula = "";
        try (BufferedReader br = new BufferedReader(new FileReader("UsuarioActual.txt"))) {
            cedula = br.readLine();
            if (cedula != null && cedula.length() > 0) {
                // Elimina el primer carácter
                cedula = cedula.substring(1);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return cedula;
    }
}
