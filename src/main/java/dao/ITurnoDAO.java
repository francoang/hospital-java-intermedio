
package dao;

import entidades.Persona;
import entidades.TurnoBean;
import java.sql.SQLException;

public interface ITurnoDAO {
    
   int guardarTurno (TurnoBean turno)throws SQLException; 
    
}