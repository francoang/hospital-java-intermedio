
package dao;

import entidades.ReporteTurnoBean;
import java.sql.SQLException;


public interface IReporteTurnoDAO {
    
    int enviarReporteTurno (ReporteTurnoBean reporteTurno)throws SQLException; 
    
}
