
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.mysql.jdbc.Statement;

public class Categoria {
	private int idCategoria;
	private String nomeCategoria;
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setCodCategoria(int codCategoria) {
		this.idCategoria = codCategoria;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	
	public static  ArrayList<ArrayList<String>> selectAll() {
		
		Statement stmt;
		ResultSet resset;
		String sql = "SELECT * FROM tbcategoria";
		try {
			stmt = (Statement) Conexao.con.createStatement();
			resset = stmt.executeQuery(sql);
			
							

			ArrayList<ArrayList<String>> array = new ArrayList<>(); //uma maneira de criar arrays, aqui criamos uma matrix, ja que é um arraylist de um arraylist de string [["String", "String"]]
			
			int numeroColunas = resset.getMetaData().getColumnCount(); //aqui atraves do metodo getMetaData.getColumnCount conseguimos pegar o numero de colunas que temos na tabela
			
			while(resset.next()) {
				int i=1;
				ArrayList<String> row = new ArrayList<>(); // criando uma arraylist de string(vetor de string), que sera adicionado a matriz
				while(i<=numeroColunas){
					row.add(resset.getString(i)); //adicionado ao array list os elementos das linhas retornadas do banco
					i++;// incremento para ir pra proxima coluna
				}
				array.add(row); // adicionando a matriz a arraylist com os elementos da linha
			}
			return array;
			
			
		}catch(SQLException erro){
			System.out.println("Nao foi poss�vel realizar a consulta");
			return null;
			
		}
		
	}
	
	public boolean insert(String nomeCategoria) {
		
		Statement stmt;
		String sql = "INSERT INTO tbcategoria VALUES (null, '"+nomeCategoria+"')";
		try {
			stmt = (Statement) Conexao.con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		}catch(SQLException erro){
			System.out.println("Nao foi poss�vel realizar a consulta");
			return false;
		}
	}

	public static boolean update(String nomeCategoria, int idCategoria){
		Statement stmt;
		String sql = "UPDATE tbcategoria SET categoria='"+nomeCategoria+"' WHERE idCategoria = "+idCategoria+" " ;
		try {
			stmt = (Statement) Conexao.con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		}catch(SQLException erro){
			System.out.println("Nao foi poss�vel realizar a consulta");
			return false;
		}
	}

	public static ArrayList<String> selectId(int id){
		Statement stmt;
		ResultSet resset;
		String sql = "SELECT * FROM tbcategoria WHERE idCategoria = "+id+"";
		try{
			stmt = (Statement) Conexao.con.createStatement();
			resset = stmt.executeQuery(sql);
			ArrayList<String> array = new ArrayList<>();
			int cols = resset.getMetaData().getColumnCount();
			int i =1;
			resset.first();
			while(i<=cols){
				array.add(resset.getString(i));
				i++;
			}
			return array;

		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static ArrayList<ArrayList<String>> selectByName(String categoria){
		Statement stmt;
        ResultSet resset;
        String sql = "SELECT * FROM tbcategoria WHERE categoria LIKE '%"+categoria+"%'";
        try{
            stmt = (Statement) Conexao.con.createStatement();
            resset = stmt.executeQuery(sql);

            int numeroColunas = resset.getMetaData().getColumnCount();
            ArrayList<ArrayList<String>> array = new ArrayList<>();
            
            while(resset.next()){
                int i=1;
                ArrayList<String> row = new ArrayList<>();
                while(i<=numeroColunas){
                    row.add(resset.getString(i));
					System.out.println(resset.getString(i) + "AAA");
                    i++;
                }
                array.add(row);
				System.out.println(resset.getString("categoria"));
            }
            return array;
        }catch(SQLException erro){
            System.out.println(erro.getMessage());
            return null;
        }
	}

	public static boolean delete(int id){
		Producto.deleteByCategoria(id);
		Statement stmt;
		
		String sql = "DELETE FROM tbcategoria WHERE idCategoria = "+id+" ";
		try{
			stmt = (Statement) Conexao.con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
}
