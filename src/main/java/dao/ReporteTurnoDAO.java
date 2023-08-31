
package dao;

import entidades.ReporteTurnoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static sql.Conexion.close;
import static sql.Conexion.getConnection;


public class ReporteTurnoDAO implements IReporteTurnoDAO {
    
    private Connection conexionTransaccional;
    private static final String SQL_INSERT_REPORTE = "INSERT INTO reporte (nombre, fecha) VALUES(?,?)";

    public ReporteTurnoDAO() {
    }

    public ReporteTurnoDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    private Connection verificarConexion() throws SQLException {
        return this.conexionTransaccional != null
                ? this.conexionTransaccional : getConnection();
    }

    @Override
    public int enviarReporteTurno(ReporteTurnoBean reporteTurno) throws SQLException {
     Connection conn = verificarConexion();
        PreparedStatement pstm = conn.prepareStatement(SQL_INSERT_REPORTE);

        pstm.setString(1, reporteTurno.getNombre());
        pstm.setDate(2, reporteTurno.getFecha());
        
        int registro = pstm.executeUpdate();
        close(pstm);
        close(conn);

        return registro;
    }
         
}
