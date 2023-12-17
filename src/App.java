

public class App {
    
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new FrmPrincipal();
        Conexao con = new Conexao();
        con.conectar();
        System.out.println("helo world");
    }
}
