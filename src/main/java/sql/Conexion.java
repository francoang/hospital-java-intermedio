package sql;

import java.sql.*;

/**
 *
 * @author franc
 */
public class Conexion {
    
    private static final String URL = "jdbc:mysql://aws.connect.psdb.cloud/hospitaldb?sslMode=VERIFY_IDENTITY";
    private static final String JDBC_USER = "n6osnx2jgqst2ozt1rh7";
    private static final String JDBC_PASS = "pscale_pw_gXXqg5cBKKULLcrdSAXHdbWHrJZlqe0pvdV2V0SRvM3";
    
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
