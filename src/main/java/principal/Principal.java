package principal;

import entidades.Persona;
import gui.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import negocio.HospitalControlador;
import negocio.IHospitalControlador;

/**
 * Clase Principal. Encontraremos todo lo
 * que el usuario verá por pantalla.
 *
 * @author fnang
 */
public class Principal {
    
    private static IHospitalControlador hospital;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        hospital = new HospitalControlador();
        new Menu().setVisible(true); 
    }    

    public static void cargarPersona(Persona per, JFrame car) {
        String msjeAgregar = hospital.agregarPersona(per);
        JOptionPane.showMessageDialog(null, msjeAgregar, "Resultado", JOptionPane.INFORMATION_MESSAGE);        
        car.setVisible(false);
    } //Fin de cargarPersona()
    
    public static String listarTodos(){
        return hospital.obtenerTodasPersonas();
    }
    
    public static void modificarPersona(Persona per, JFrame car){
        String msjeModificar = hospital.modificarPersona(per);
        JOptionPane.showMessageDialog(null, msjeModificar, "Resultado", JOptionPane.INFORMATION_MESSAGE);        
        car.setVisible(false);
    }
    
    public static void borrarPersona(Persona per, JFrame car){
        String msjeBorrar = hospital.borrarPersona(per);
        JOptionPane.showMessageDialog(null, msjeBorrar, "Resultado", JOptionPane.INFORMATION_MESSAGE);        
        car.setVisible(false);
    }
    
    public static void cambiarPersona(Persona per, JFrame car){
        String msjeCambiar = hospital.cambiarPersona(per);
        JOptionPane.showMessageDialog(null, msjeCambiar, "Resultado", JOptionPane.INFORMATION_MESSAGE);        
        car.setVisible(false);
    }
    
} //Fin de la clase
