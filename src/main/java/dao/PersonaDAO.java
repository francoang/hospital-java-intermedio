package dao;

import dto.CambiarPersonaDTO;
import entidades.Doctor;
import entidades.Paciente;
import java.util.List;
import entidades.Persona;
import java.sql.*;
import java.util.ArrayList;
import static sql.Conexion.*;

/**
 * Gestion de personas en base de datos
 *
 * @author Angonoa Franco
 */
public class PersonaDAO implements IPersonaDAO {

    private Connection conexionTransaccional;
    private static final String SQL_SELECT_DOCTORES = "SELECT * FROM doctor";
    private static final String SQL_SELECT_PACIENTES = "SELECT * FROM paciente";
    private static final String SQL_INSERT_DOCTOR = "INSERT INTO doctor(documento, nombreApellido, matricula) VALUES(?,?,?)";
    private static final String SQL_INSERT_PACIENTE = "INSERT INTO paciente(documento, nombreApellido, obraSocial) VALUES(?,?,?)";
    private static final String SQL_UPDATE_DOCTOR = "UPDATE doctor SET nombreApellido = ?, matricula = ? WHERE documento = ?";
    private static final String SQL_UPDATE_PACIENTE = "UPDATE paciente SET nombreApellido = ?, obraSocial = ? WHERE documento = ?";
    private static final String SQL_DELETE_DOCTOR = "DELETE FROM doctor WHERE idDoctor = ?";
    private static final String SQL_DELETE_PACIENTE = "DELETE FROM paciente WHERE idPaciente = ?";
    private static final String SQL_SEL_WHERE_DOCTOR = "SELECT * FROM doctor WHERE documento = ?";
    private static final String SQL_SEL_WHERE_PACIENTE = "SELECT * FROM paciente WHERE documento = ?";
    private static final String SQL_CAMBIAR_PACIENTE = "SELECT * FROM paciente WHERE documento = ?";
    private static final String SQL_SEL_WHERE_DOCTOR_ID = "SELECT * FROM doctor WHERE idDoctor = ?";
    private static final String SQL_SEL_WHERE_PACIENTE_ID = "SELECT * FROM paciente WHERE idPaciente = ?";

    public PersonaDAO() {

    }

    public PersonaDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public int agregar(Persona per) throws SQLException {
        Connection conn = verificarConexion();
        int registros = 0;

        if (per instanceof Doctor) {
            registros = cargarDoctor(conn, per);
        } else {
            registros = cargarPaciente(conn, per);
        }

        if (this.conexionTransaccional == null) {
            close(conn);
        }

        return registros;
    }

    private int cargarDoctor(Connection conn, Persona per) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SQL_INSERT_DOCTOR);
        Doctor doc = (Doctor) per;
        pstm.setInt(1, doc.getDocumento());
        pstm.setString(2, doc.getNombreApellido());
        pstm.setInt(3, doc.getMatricula());
        int registros = pstm.executeUpdate();
        close(pstm);

        return registros;
    }

    private int cargarPaciente(Connection conn, Persona per) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SQL_INSERT_PACIENTE);
        Paciente pac = (Paciente) per;
        pstm.setInt(1, pac.getDocumento());
        pstm.setString(2, pac.getNombreApellido());
        pstm.setString(3, tieneObraSocial(pac));
        int registros = pstm.executeUpdate();
        close(pstm);

        return registros;
    }

    @Override
    public int modificar(Persona per) throws SQLException {
        Connection conn = verificarConexion();
        int registros = 0;

        if (per instanceof Doctor) {
            registros = modificarDoctor(conn, per);
        } else {
            registros = modificarPaciente(conn, per);
        }

        if (this.conexionTransaccional == null) {
            close(conn);
        }

        return registros;
    }

    private int modificarDoctor(Connection conn, Persona per) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE_DOCTOR);
        Doctor doc = (Doctor) per;
        pstm.setString(1, doc.getNombreApellido());
        pstm.setInt(2, doc.getMatricula());
        pstm.setInt(3, doc.getDocumento());
        int registros = pstm.executeUpdate();
        close(pstm);
        return registros;
    }

    private int modificarPaciente(Connection conn, Persona per) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE_PACIENTE);
        Paciente pac = (Paciente) per;
        pstm.setString(1, pac.getNombreApellido());
        pstm.setString(2, tieneObraSocial(pac));
        pstm.setInt(3, pac.getDocumento());
        int registros = pstm.executeUpdate();
        close(pstm);

        return registros;
    }

    private String tieneObraSocial(Paciente pac) {
        return pac.tieneObraSocial() ? "S" : "N";
    }

    @Override
    public int borrar(Persona per) throws SQLException {
        Connection conn = verificarConexion();
        PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_DOCTOR);
        Doctor doc = (Doctor) per;
        pstm.setInt(1, doc.getIdDoctor());
        int registros = pstm.executeUpdate();

        close(pstm);
        if (this.conexionTransaccional == null) {
            close(conn);
        }

        return registros;
    }

    @Override
    public Persona buscarPorDNI(Persona per) throws SQLException {
        Connection conn = verificarConexion();

        Doctor doc = buscarDoctores(conn, per);
        return doc != null ? doc : buscarPacientes(conn, per);
    }

    private Doctor buscarDoctores(Connection conn, Persona per) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SQL_SEL_WHERE_DOCTOR);
        pstm.setInt(1, per.getDocumento());
        ResultSet rs = pstm.executeQuery();

        return rs.next() ? recorrerDoctores(rs) : null;
    }

    private Paciente buscarPacientes(Connection conn, Persona per) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SQL_SEL_WHERE_PACIENTE);
        pstm.setInt(1, per.getDocumento());
        ResultSet rs = pstm.executeQuery();

        return rs.next() ? recorrerPacientes(rs) : null;
    }

    @Override
    public List<Persona> obtenerTodasPersonas() throws SQLException {
        Connection conn = verificarConexion();
        List<Persona> personas = new ArrayList<>();

        personas.addAll(obtenerDoctores(conn));
        personas.addAll(obtenerPacientes(conn));

        if (this.conexionTransaccional == null) {
            close(conn);
        }

        return personas;
    }

    private List<Persona> obtenerDoctores(Connection conn) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SQL_SELECT_DOCTORES);
        ResultSet rs = pstm.executeQuery();
        List<Persona> doctores = new ArrayList<>();

        while (rs.next()) {
            Persona persona = recorrerDoctores(rs);
            doctores.add(persona);
        }

        close(rs);
        close(pstm);

        return doctores;
    }

    private Doctor recorrerDoctores(ResultSet rs) throws SQLException {
        int idDoctor = rs.getInt("idDoctor");
        int documento = rs.getInt("documento");
        String nombreApe = rs.getString("nombreApellido");
        int matricula = rs.getInt("matricula");

        return new Doctor(matricula, idDoctor, documento, nombreApe);
    }

    private List<Persona> obtenerPacientes(Connection conn) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SQL_SELECT_PACIENTES);
        ResultSet rs = pstm.executeQuery();

        List<Persona> pacientes = new ArrayList<>();

        while (rs.next()) {
            Persona persona = recorrerPacientes(rs);
            pacientes.add(persona);
        }

        close(rs);
        close(pstm);

        return pacientes;
    }

    private Paciente recorrerPacientes(ResultSet rs) throws SQLException {
        int idPaciente = rs.getInt("idPaciente");
        int documento = rs.getInt("documento");
        String nombreApe = rs.getString("nombreApellido");
        String obraSocial = rs.getString("obraSocial");

        Boolean obSoc = obraSocial.equalsIgnoreCase("S");

        return new Paciente(obSoc, idPaciente, documento, nombreApe);
    }

    @Override
    public int cambiar(CambiarPersonaDTO per) throws SQLException {
        int cambios = 0;
        Connection conn = verificarConexion();
        PreparedStatement pstm;
        ResultSet rs;

        //Consulta si existe el paciente
        pstm = conn.prepareStatement(SQL_CAMBIAR_PACIENTE);
        pstm.setInt(1, per.getDocumento());
        rs = pstm.executeQuery();

        if (rs.next()) {
            int idPaciente = rs.getInt("idPaciente");
            String nombreApe = rs.getString("nombreApellido");

            //Elimino al paciente de la tabla Paciente
            pstm = conn.prepareStatement(SQL_DELETE_PACIENTE);
            pstm.setInt(1, idPaciente);
            cambios += pstm.executeUpdate();

            //Agregar el paciente a la tabla Doctor
            pstm = conn.prepareStatement(SQL_INSERT_DOCTOR);
            pstm.setInt(1, per.getDocumento());
            pstm.setString(2, nombreApe);
            pstm.setInt(3, per.getMatricula());
            cambios += pstm.executeUpdate();
        }

        close(rs);
        close(pstm);

        return cambios;
    }

    private Connection verificarConexion() throws SQLException {
        return this.conexionTransaccional != null
                ? this.conexionTransaccional : getConnection();
    }

    @Override
    public List<Paciente> obtenerPacientes() throws SQLException {
        Connection con = verificarConexion();
        List<Persona> personas = obtenerPacientes(con);
        List<Paciente> pacientes = new ArrayList<>();

        for (Persona persona : personas) {
            pacientes.add((Paciente) persona);
        }

        if (this.conexionTransaccional == null) {
            close(con);
        }

        return pacientes;
    }

    @Override
    public List<Doctor> obtenerDoctores() throws SQLException {
        Connection con = verificarConexion();
        List<Persona> personas = obtenerDoctores(con);
        List<Doctor> doctores = new ArrayList<>();

        for (Persona persona : personas) {
            doctores.add((Doctor) persona);
        }

        if (this.conexionTransaccional == null) {
            close(con);
        }

        return doctores;
    }

    @Override
    public Doctor buscarDoctorPorId(Doctor doc) throws SQLException {        
        Connection conn = verificarConexion();
        PreparedStatement pstm = conn.prepareStatement(SQL_SEL_WHERE_DOCTOR_ID);
        pstm.setInt(1, doc.getIdDoctor());
        ResultSet rs = pstm.executeQuery();

        doc = buscarDoctoresId(rs);

        close(rs);
        close(pstm);
        close(conn);

        return doc;
    }

    private Doctor buscarDoctoresId(ResultSet rs) throws SQLException {        
        return rs.next() ? recorrerDoctores(rs) : null;
    }

    @Override
    public Paciente buscarPacientePorId(Paciente pac) throws SQLException {       
        Connection conn = verificarConexion();
        PreparedStatement pstm = conn.prepareStatement(SQL_SEL_WHERE_PACIENTE_ID);
        pstm.setInt(1, pac.getIdPaciente());
        ResultSet rs = pstm.executeQuery();

        pac = buscarPacientesId(rs);
        
        close(rs);
        close(pstm);
        close(conn);
        
        return pac;
    }

    private Paciente buscarPacientesId(ResultSet rs) throws SQLException {    
        return rs.next() ? recorrerPacientes(rs) : null;
    }

}
