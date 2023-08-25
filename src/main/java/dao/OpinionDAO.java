
package dao;

import entidades.OpinionBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static sql.Conexion.*;

public class OpinionDAO implements IOpinionDAO{
       
    private static final String SQL_SELECT_OPINION = "SELECT * FROM opinion";        
    private static final String SQL_INSERT_OPINION = "INSERT INTO opinion(idDoctor, idPaciente, calificacion, mensaje) VALUES(?,?,?,?)";
    
    
    public OpinionDAO(){
        
    }
    
    @Override
    public int guardar(OpinionBean opinion) throws SQLException{
        
        Connection conn = getConnection();
        PreparedStatement pstm = conn.prepareStatement(SQL_INSERT_OPINION);        
        pstm.setInt(1, opinion.getIdDoctor());
        pstm.setInt(2, opinion.getIdPaciente());
        pstm.setInt(3, opinion.getCalificacion());
        pstm.setString(4, opinion.getMensaje());
        
        int registros = pstm.executeUpdate();
        close(pstm);
        close(conn);
        return registros;
    }
        
}
