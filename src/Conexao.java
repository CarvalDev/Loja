import java.sql.DriverManager;
import java.sql.SQLException; 
import java.sql.Connection;
public class Conexao {
	static Connection con;
	
	public void conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/dbloja", "root", "");
			System.out.println("ook");
			
		}catch(ClassNotFoundException ex){
	        System.out.println("Driver JDBC-ODBC n�o encontrado"); 
	    }	         
         catch(SQLException ex){
           System.out.println("Problemas na conex�o com o banco de dados." + ex.getMessage()); 
        }         
	}
	
	public void desconectar() {
		try {
			con.close();
		}catch(SQLException ex) {
			System.out.println("Problemas ao desconectar");
		}
	}
}
