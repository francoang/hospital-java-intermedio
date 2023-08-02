package negocio;

import entidades.Persona;

/**
 *
 * @author franc
 */
public interface IHospitalControlador {
    
    String agregarPersona(Persona persona);
    
    String modificarPersona(Persona per);
    
    String borrarPersona(Persona per);
    
    String cambiarPersona(Persona per);
    
    String buscarPorDNI(int documento);
    
    String obtenerTodasPersonas();
}
