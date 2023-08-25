package negocio;

import dto.CambiarPersonaDTO;
import entidades.Doctor;
import entidades.OpinionBean;
import entidades.Paciente;
import entidades.Persona;
import java.util.List;

/**
 *
 * @author franc
 */
public interface IHospitalControlador {
    
    String agregarPersona(Persona persona);
    
    String modificarPersona(Persona per);
    
    String borrarPersona(Persona per);
    
    String cambiarPersona(CambiarPersonaDTO per);
    
    Persona buscarPorDNI(Persona per);
    
    String obtenerTodasPersonas();
    
    String obtenerPacientes();
    
    String obtenerDoctores();
    
    List<Doctor> obtenerListaDoctores();
    
    String guardarOpinion(OpinionBean opinion);

    public List<Paciente> obtenerListaPacientes();
}
