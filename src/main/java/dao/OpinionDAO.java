
package dao;

import entidades.OpinionBean;
import entidades.Reporte;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public int guardar(OpinionBean opinion, Boolean guardarLog) throws SQLException{        
        Connection conn = verificarConexion();
        PreparedStatement pstm = conn.prepareStatement(SQL_INSERT_OPINION);        
        pstm.setInt(1, opinion.getIdDoctor());
        pstm.setInt(2, opinion.getIdPaciente());
        pstm.setInt(3, opinion.getCalificacion());
        pstm.setString(4, opinion.getMensaje());
        
        if (guardarLog) {
            guardarRegistroLog(opinion);
        }
        
        int registros = pstm.executeUpdate();
        
        close(pstm);
        close(conn);   
        
        return registros;
    }
    
    private Connection verificarConexion() throws SQLException {
        return this.conexionTransaccional != null
                ? this.conexionTransaccional : getConnection();
    }
    
    private void guardarRegistroLog(OpinionBean op){
        String nombreArchivo = "reporte-log.txt";
        
        try (FileWriter fw = new FileWriter(nombreArchivo);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            
            LocalDate fecha = LocalDate.now();
            StringBuilder sb = new StringBuilder();
            sb.append("Log de registro de opiniones: ");
            sb.append("----------------------------------------" + "\n");
            sb.append("ID de doctor: " + op.getIdDoctor()+ "\n");
            sb.append("ID de paciente: " + op.getIdPaciente().toString() + "\n");
            sb.append("Mensaje de opinion: " + op.getMensaje() + "\n");
            sb.append("Realizado el : " + fecha.toString() + "\n");
            sb.append("----------------------------------------");
            bw.write(sb.toString());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
