package negocio;

import dto.CambiarPersonaDTO;
import entidades.Persona;

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
}
