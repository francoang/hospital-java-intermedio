 
package dao;

import dto.CambiarPersonaDTO;
import entidades.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz que respeta el patron DAO.
 * Donde todas aquellas clases que lo implanten deberan cumplir
 * con los metodos y sus respectivas firmas.
 * @author fnang
 */
public interface IPersonaDAO {        
    
    int agregar(Persona per) throws SQLException;
    
    int modificar(Persona per) throws SQLException;
    
    int borrar(Persona per) throws SQLException;
    
    int cambiar(CambiarPersonaDTO per) throws SQLException;
    
    Persona buscarPorDNI(Persona per) throws SQLException;
    
    List<Persona> obtenerTodasPersonas() throws SQLException;
    
    List<Paciente> obtenerPacientes() throws SQLException;
    
    List<Doctor> obtenerDoctores() throws SQLException;
}
