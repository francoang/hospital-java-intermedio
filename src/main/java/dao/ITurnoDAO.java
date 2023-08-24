
package dao;

import entidades.Persona;
import entidades.Turno;
import java.sql.SQLException;

public interface ITurnoDAO {
    
   int guardarTurno (Turno turno)throws SQLException; 
   
   
    
}