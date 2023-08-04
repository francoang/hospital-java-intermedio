package principal;

import dto.CambiarPersonaDTO;
import entidades.Persona;
import gui.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import negocio.HospitalControlador;
import negocio.IHospitalControlador;

/**
 * Clase Principal.
 *
 * @author Angonoa Franco
 */
public class Principal {
    
    private static IHospitalControlador hospitalCtrl;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        hospitalCtrl = new HospitalControlador(false);
        new Menu().setVisible(true); 
    }    

    public static void cargarPersona(Persona per, JFrame car) {
        String msjeAgregar = hospitalCtrl.agregarPersona(per);
        JOptionPane.showMessageDialog(null, msjeAgregar, "Resultado", JOptionPane.INFORMATION_MESSAGE);        
        car.setVisible(false);
    } //Fin de cargarPersona()
    
    public static String listarTodos(){
        return hospitalCtrl.obtenerTodasPersonas();
    }
    
    public static void modificarPersona(Persona per, JFrame car){
        String msjeModificar = hospitalCtrl.modificarPersona(per);
        JOptionPane.showMessageDialog(null, msjeModificar, "Resultado", JOptionPane.INFORMATION_MESSAGE);        
        car.setVisible(false);
    }
    
    public static void borrarPersona(Persona per, JFrame car){
        String msjeBorrar = hospitalCtrl.borrarPersona(per);
        JOptionPane.showMessageDialog(null, msjeBorrar, "Resultado", JOptionPane.INFORMATION_MESSAGE);        
        car.setVisible(false);
    }
    
    public static void cambiarPersona(CambiarPersonaDTO per, JFrame car){
        IHospitalControlador hospitalCtrl = new HospitalControlador(true);
        String msjeCambiar = hospitalCtrl.cambiarPersona(per);
        JOptionPane.showMessageDialog(null, msjeCambiar, "Resultado", JOptionPane.INFORMATION_MESSAGE);        
        car.setVisible(false);
    }
    
    public static Persona buscarPersona(Persona per, JFrame car){
        return hospitalCtrl.buscarPorDNI(per);
    }
    
} //Fin de la clase
