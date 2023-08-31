package dao;

import entidades.Doctor;
import entidades.Paciente;
import entidades.Persona;
import entidades.TurnoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import static sql.Conexion.close;
import static sql.Conexion.getConnection;

public class TurnoDAO implements ITurnoDAO {

    private Connection conexionTransaccional;
    private static final String SQL_INSERT_TURNO = "INSERT INTO turno (idPaciente, idDoctor, fecha, motivo) VALUES(?,?,?,?)";

    public TurnoDAO() {
    }

    public TurnoDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    private Connection verificarConexion() throws SQLException {
        return this.conexionTransaccional != null
                ? this.conexionTransaccional : getConnection();
    }

    @Override
    public int guardarTurno(TurnoBean turno) throws SQLException {
        Connection conn = verificarConexion();
        PreparedStatement pstm = conn.prepareStatement(SQL_INSERT_TURNO);

        pstm.setInt(1, turno.getIdPaciente());
        pstm.setInt(2, turno.getIdDoctor());
        pstm.setDate(3, turno.getFecha());
        pstm.setString(4, turno.getMotivo());

        int registro = pstm.executeUpdate();
        close(pstm);
        close(conn);

        return registro;
    }
   
}
