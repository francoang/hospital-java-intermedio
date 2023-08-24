package negocio;

import dto.CambiarPersonaDTO;
import entidades.Doctor;
import entidades.Opinion;
import entidades.Paciente;
import entidades.Persona;
import entidades.Turno;

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
    
    String guardarOpinion(Opinion opinion);
    
    String guardarTurno(Turno turno);
    
    Doctor buscarDoctorPorId(Persona per);
   
    Paciente buscarPacientePorId(Persona per);
}
