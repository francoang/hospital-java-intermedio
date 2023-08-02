package dao;

import entidades.Doctor;
import entidades.Paciente;
import java.util.List;
import entidades.Persona;
import java.sql.*;
import java.util.ArrayList;
import static sql.Conexion.*;

/**
 * Gestion de personas en base de datos
 * @author Angonoa Franco
 */
public class PersonaDAO implements IPersonaDAO{
    
    private Connection conexionTransaccional;
    private static final String SQL_SELECT_DOCTORES = "SELECT * FROM doctor";    
    private static final String SQL_SELECT_PACIENTES = "SELECT * FROM paciente";
    private static final String SQL_INSERT_DOCTOR = "INSERT INTO doctor(documento, nombreApellido, matricula) VALUES(?,?,?)";
    private static final String SQL_INSERT_PACIENTE = "INSERT INTO paciente(documento, nombreApellido, obraSocial) VALUES(?,?,?)";
    private static final String SQL_UPDATE_DOCTOR = "UPDATE doctor SET documento = ?, nombreApellido = ?, matricula = ? WHERE idDoctor = ?";
    private static final String SQL_UPDATE_PACIENTE = "UPDATE paciente SET documento = ?, nombreApellido = ?, obraSocial = ? WHERE idPaciente = ?";
    private static final String SQL_DELETE_DOCTOR = "DELETE FROM doctor WHERE idDoctor = ?";
    private static final String SQL_DELETE_PACIENTE = "DELETE FROM paciente WHERE idPaciente = ?";
    private static final String SQL_SEL_WHERE_DOCTOR = "SELECT * FROM doctor WHERE idDoctor = ?";
    private static final String SQL_SEL_WHERE_PACIENTE = "SELECT * FROM paciente WHERE idPaciente = ?";
    private static final String SQL_CAMBIAR_PACIENTE = "SELECT * FROM paciente WHERE documento = ?";
    
    public PersonaDAO(){
        
    }
    
    public PersonaDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public int agregar(Persona per) throws SQLException{
        Connection conn = verificarConexion();
        PreparedStatement pstm = conn.prepareStatement(SQL_INSERT_DOCTOR);
        Doctor doc = (Doctor) per;
        pstm.setInt(1, doc.getDocumento());
        pstm.setString(2, doc.getNombreApellido());
        pstm.setInt(3, doc.getMatricula());
        int registros = pstm.executeUpdate();
        
        close(pstm);
        
        if(this.conexionTransaccional == null){
            close(conn);            
        }
        
        return registros;
    }

    @Override
    public int modificar(Persona per) throws SQLException{
        Connection conn = verificarConexion();
        PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE_DOCTOR);
        Doctor doc = (Doctor) per;
        pstm.setInt(1, doc.getDocumento());
        pstm.setString(2, doc.getNombreApellido());
        pstm.setInt(3, doc.getMatricula());
        pstm.setInt(4, doc.getIdDoctor());
        int registros = pstm.executeUpdate();
        
        close(pstm);
        if(this.conexionTransaccional == null){
            close(conn);            
        }
        
        return registros;
    }

    @Override
    public int borrar(Persona per) throws SQLException {
        Connection conn = verificarConexion();
        PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_DOCTOR);
        Doctor doc = (Doctor) per;
        pstm.setInt(1, doc.getIdDoctor());
        int registros = pstm.executeUpdate();
        
        close(pstm);
        if(this.conexionTransaccional == null){
            close(conn);            
        }
        
        return registros;
    }

    @Override
    public Persona buscarPorDNI(Persona per) throws SQLException {
        return null;
    }

    @Override
    public List<Persona> obtenerTodasPersonas() throws SQLException{              
        Connection conn = verificarConexion();        
        List<Persona> personas = new ArrayList<>();
        
        personas.addAll(obtenerDoctores(conn));
        personas.addAll(obtenerPacientes(conn));                
                
        if(this.conexionTransaccional == null){
            close(conn);            
        }
        
        return personas;        
    }      
    
    private List<Persona> obtenerDoctores(Connection conn) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(SQL_SELECT_DOCTORES);
        ResultSet rs = pstm.executeQuery();
        List<Persona> doctores = new ArrayList<>();
        
        while( rs.next() ){
            int idDoctor = rs.getInt("idDoctor");
            int documento = rs.getInt("documento");
            String nombreApe = rs.getString("nombreApellido");
            int matricula = rs.getInt("matricula");
            
            Persona persona = 
                    new Doctor(matricula, idDoctor, documento, nombreApe);
            doctores.add(persona);
        }
        
        close(rs);
        close(pstm);
        
        return doctores;
    }    
    
    private List<Persona> obtenerPacientes(Connection conn) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(SQL_SELECT_PACIENTES);
        ResultSet rs = pstm.executeQuery();
        List<Persona> pacientes = new ArrayList<>();
        
        while( rs.next() ){
            int idPaciente = rs.getInt("idPaciente");
            int documento = rs.getInt("documento");
            String nombreApe = rs.getString("nombreApellido");
            String obraSocial = rs.getString("obraSocial");
            
            Boolean obSoc = obraSocial.equalsIgnoreCase("S");
            
            Persona persona = new Paciente(obSoc, idPaciente, documento, nombreApe);
            pacientes.add(persona);
        }
        
        close(rs);
        close(pstm);
        
        return pacientes;
    }
    
    @Override
    public int cambiar(Persona per) throws SQLException {
        int cambios = 0;
        Connection conn = verificarConexion();
        PreparedStatement pstm;
        ResultSet rs;

        pstm = conn.prepareStatement(SQL_CAMBIAR_PACIENTE);
        pstm.setInt(1, per.getDocumento());
        rs= pstm.executeQuery();        
        boolean resultado = rs.next();
        
        if(resultado){
            int idPaciente = rs.getInt("idPaciente");
            int documento = rs.getInt("documento");
            String nombreApe = rs.getString("nombreApellido");
            String obraSocial = rs.getString("obraSocial");            
            Boolean obSoc = obraSocial.equalsIgnoreCase("S");
            
            Paciente paciente = new Paciente(obSoc, idPaciente, documento, nombreApe);
            
            pstm = conn.prepareStatement(SQL_DELETE_PACIENTE);
            pstm.setInt(1, paciente.getIdPaciente());
            cambios += pstm.executeUpdate();
            
            pstm = conn.prepareStatement(SQL_INSERT_DOCTOR);            
            pstm.setInt(1, paciente.getDocumento());
            pstm.setString(2, paciente.getNombreApellido());
            pstm.setInt(3, (int) (Math.random()*100000)); 
            cambios += pstm.executeUpdate();
        }
        
        close(rs);
        close(pstm);      
        
        return cambios;
    }
    
    private Connection verificarConexion() throws SQLException{
        return this.conexionTransaccional != null ? 
                this.conexionTransaccional : getConnection();
    }

}
