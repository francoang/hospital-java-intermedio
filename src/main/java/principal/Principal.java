package principal;

import dto.CambiarPersonaDTO;
import entidades.*;
import gui.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        //Solo se crea este objeto para realizar la transaccion
        IHospitalControlador hospitalCtrl = new HospitalControlador(true);
        String msjeCambiar = hospitalCtrl.cambiarPersona(per);
        JOptionPane.showMessageDialog(null, msjeCambiar, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        car.setVisible(false);
    }

    public static Persona buscarPersona(Persona per, JFrame car){
        return hospitalCtrl.buscarPorDNI(per);
    }

    public static String listarDoctores(){
        return hospitalCtrl.obtenerDoctores();
    }
    
    public static List<Doctor> listadoDoctores(){
        return hospitalCtrl.obtenerListaDoctores();
    }
    
    public static String listarPacientes(){
        return hospitalCtrl.obtenerPacientes();
    }
    
    public static List<Paciente> listadoPacientes(){
        return hospitalCtrl.obtenerListaPacientes();
    }
    
    public static void guardarOpinion(OpinionBean op, JFrame car){
        String mensaje = hospitalCtrl.guardarOpinion(op);   
        Reporte rep = new Reporte("reporte-log.txt", LocalDate.now());        
        String mensajeRep = hospitalCtrl.guardarReporte(rep);
        JOptionPane.showMessageDialog(null, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        car.setVisible(false);
    }

    public static void guardarTurno(TurnoBean tu, JFrame car) {
        String mensaje = hospitalCtrl.guardarTurno(tu);
        JOptionPane.showMessageDialog(null, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        car.setVisible(false);
    }

    public static Doctor buscarDoctorPorId(Doctor doc, JFrame car) {
        return hospitalCtrl.buscarDoctorPorId(doc);
    }

    public static Paciente buscarPacientePorId(Paciente pac, JFrame car) {
        return hospitalCtrl.buscarPacientePorId(pac);
    }

    public static String fechaActual() {
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String resultado = formatoFecha.format(fechaHoy);
        
        return resultado;
    }
    
    public static void enviarReporteTurno(ReporteTurnoBean rt, JFrame car){
        String mensaje = hospitalCtrl.enviarReporteTurno(rt);
        JOptionPane.showMessageDialog(null, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        car.setVisible(false);
    }
    
        
} //Fin de la clase
