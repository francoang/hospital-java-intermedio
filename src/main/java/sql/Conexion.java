package sql;

import java.sql.*;

/**
 *
 * @author franc
 */
public class Conexion {
    
    private static final String URL = "jdbc:mysql://aws.connect.psdb.cloud/hospitaldb?sslMode=VERIFY_IDENTITY";
    private static final String JDBC_USER = "y13ne3r99vf9ro0d75j9";
    private static final String JDBC_PASS = "pscale_pw_hz7GDDz0rT75oS6J1SQs3UaNw27X8z7oBGBWJTWK5gf";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, JDBC_USER, JDBC_PASS);        
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement smtm) throws SQLException{
        smtm.close();
    }
    
    public static void close(PreparedStatement psmtm) throws SQLException{
        psmtm.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
