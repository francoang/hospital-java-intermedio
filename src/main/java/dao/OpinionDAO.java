
package dao;

import entidades.OpinionBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static sql.Conexion.*;

public class OpinionDAO implements IOpinionDAO{
       
    private Connection conexionTransaccional;
    private static final String SQL_SELECT_OPINION = "SELECT * FROM opinion";        
    private static final String SQL_INSERT_OPINION = "INSERT INTO opinion(idDoctor, idPaciente, calificacion, mensaje) VALUES(?,?,?,?)";
    
    
    public OpinionDAO(){
        
    }
    
    public OpinionDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    public int guardar(OpinionBean opinion) throws SQLException{        
        Connection conn = verificarConexion();
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
    
    private Connection verificarConexion() throws SQLException {
        return this.conexionTransaccional != null
                ? this.conexionTransaccional : getConnection();
    }
        
}
