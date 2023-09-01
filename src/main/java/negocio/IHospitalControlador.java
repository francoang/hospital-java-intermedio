package negocio;

import dto.CambiarPersonaDTO;
import entidades.*;
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
    
    String guardarTurno(Turno turno);
    
    Doctor buscarDoctorPorId(Doctor doc);
   
    Paciente buscarPacientePorId(Paciente pac);
    
    List<Doctor> obtenerListaDoctores();
    
    String guardarOpinion(OpinionBean opinion);
    
    String guardarReporte(Reporte rep);

    public List<Paciente> obtenerListaPacientes();
}
