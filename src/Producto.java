import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
public class Producto {
    private int idProduto;
    private String produto;
    private int idCategoria;
    private double valor;
    private String descricao;
    private int quantidade;
    
    public int getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    public String getProduto() {
        return produto;
    }
    public void setProduto(String produto) {
        this.produto = produto;
    }
    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public static boolean insert(Producto p){
        Statement stmt;
        String sql = "INSERT INTO tbproduto VALUES(NULL, '"+p.getProduto()+"', '"+p.getIdCategoria()+"', '"+p.getValor()+"', '"+p.getDescricao()+"', '"+p.getQuantidade()+"' )";
        try {
			stmt = (Statement) Conexao.con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		}catch(SQLException erro){
			System.out.println("Nao foi poss vel realizar a consulta");
			return false;
		}
    }

    public static ArrayList<ArrayList<String>> selectAll(){
        Statement stmt;
        ResultSet resset;
        String sql = "SELECT idProduto, produto, categoria, valor, descricao, quantidade FROM tbproduto as p INNER JOIN tbcategoria c ON p.idCategoria = c.idCategoria";
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
                    i++;
                }
                array.add(row);
            }
            return array;
        }catch(SQLException erro){
            System.out.println(erro.getMessage());
            return null;
        }
    }

    public static boolean delete(int id){
		Statement stmt;
		String sql = "DELETE FROM tbproduto WHERE idProduto = "+id+" ";
		try{
			stmt = (Statement) Conexao.con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}

    public static boolean deleteByCategoria(int idCategoria){
		Statement stmt;
		
		String sql = "DELETE FROM tbproduto WHERE idCategoria = "+idCategoria+" ";
		try{
			stmt = (Statement) Conexao.con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}

    public static ArrayList<String> selectId(int id){
		Statement stmt;
		ResultSet resset;
		String sql = "SELECT idProduto, produto, p.idCategoria ,  categoria, valor, descricao, quantidade FROM tbproduto as p INNER JOIN tbcategoria c ON p.idCategoria = c.idCategoria WHERE idProduto = '"+id+"'";
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

    public static ArrayList<ArrayList<String>> selectByName(String produto){
		Statement stmt;
        ResultSet resset;
        String sql = "SELECT idProduto, produto, categoria, valor, descricao, quantidade FROM tbproduto as p INNER JOIN tbcategoria c ON p.idCategoria = c.idCategoria WHERE produto LIKE '%"+produto+"%'";
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
					
                    i++;
                }
                array.add(row);
				
            }
            return array;
        }catch(SQLException erro){
            System.out.println(erro.getMessage());
            return null;
        }
	}

    public static boolean update(Producto p, int idProduto){
		Statement stmt;
		String sql = "UPDATE tbproduto SET produto='"+p.getProduto()+"', idCategoria = '"+p.getIdCategoria()+"', valor = '"+p.getValor()+"', descricao = '"+p.getDescricao()+"', quantidade = '"+p.getQuantidade()+"' WHERE idProduto = "+idProduto+" " ;
		try {
			stmt = (Statement) Conexao.con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		}catch(SQLException erro){
			System.out.println("Nao foi poss�vel realizar a consulta");
			return false;
		}
	}
    
}
