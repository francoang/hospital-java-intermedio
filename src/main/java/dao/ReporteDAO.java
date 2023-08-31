
package dao;

import entidades.Reporte;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static sql.Conexion.close;
import static sql.Conexion.getConnection;

public class ReporteDAO implements IReporteDAO{

    private Connection conexionTransaccional;
    private static final String SQL_INSERT_REPORTE = "INSERT INTO reporte(idReporte, nombre, fecha) VALUES(?,?,?)";

    public ReporteDAO() {
    }
    
    public ReporteDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    
    private Connection verificarConexion() throws SQLException {
        return this.conexionTransaccional != null
                ? this.conexionTransaccional : getConnection();
    }
    
    @Override
    public int guardar(Reporte rep) throws SQLException {
        
        Connection conn = verificarConexion();
        PreparedStatement pstm = conn.prepareStatement(SQL_INSERT_REPORTE);        
        pstm.setInt(1, rep.getIdReporte());
        pstm.setString(2, rep.getNombre());
        pstm.setDate(3, Date.valueOf(rep.getFecha()));
                
        int registros = pstm.executeUpdate();
        
        close(pstm);
        close(conn);
        
        return registros;
    }
    
    private Boolean enviarPorCorreo(Reporte rep){
       Boolean estado= false;
            
       return estado;
    }
}
