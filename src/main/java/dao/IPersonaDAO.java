 
package dao;

import entidades.Persona;
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
    
    int cambiar(Persona per) throws SQLException;
    
    Persona buscarPorDNI(Persona per) throws SQLException;
    
    List<Persona> obtenerTodasPersonas() throws SQLException;
}
