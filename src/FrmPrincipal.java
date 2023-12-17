
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.event.*;


public class FrmPrincipal extends JFrame {
    JMenu[] menus;
    String[] namesMenu = {"Arquivo", "Cadastrar", "Visualizar", "Ajuda"};
    JMenuItem sair;
    JMenuItem gerCategoria;
    JMenuItem gerProduto;
    JMenuItem categoria;
    JMenuItem produtos;
    JMenuItem sobre;

    public FrmPrincipal(){
        setExtendedState(MAXIMIZED_BOTH);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
        setLayout(null);
        addMenu(namesMenu);
        eventListeners();
        ImageIcon img = new ImageIcon(getClass().getResource("alan.PNG"));
		img.setImage(img.getImage().getScaledInstance(400, 500, 100));
		JLabel lbImg = new JLabel(img);
		lbImg.setBounds(630, 60, 600, 700);
        JLabel lbLoja = new JLabel();
        lbLoja.setBounds(765, 680, 350, 100);
        lbLoja.setText("Loja do Mestre Alan");
        lbLoja.setFont(new Font("Arial", Font.PLAIN, 36));
        add(lbImg);
        add(lbLoja);
        setVisible(true);
    } 



    public void addMenu(String[] namesMenu){
        menus = new JMenu[namesMenu.length];
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        for(int i=0;i<namesMenu.length;i++){
            menus[i] = new JMenu(namesMenu[i]);
            addMenuItens(menus[i], namesMenu[i]);
            bar.add(menus[i]);
        }
    }


    public void addMenuItens(JMenu menu, String namesMenu){
        if(namesMenu.equals("Arquivo")){
             sair = new JMenuItem("Sair");
            menu.add(sair);
        }else if(namesMenu.equals("Cadastrar")){
             gerCategoria = new JMenuItem("Categoria");
             gerProduto = new JMenuItem("Produto");
            menu.add(gerCategoria);
            menu.add(gerProduto);
        }
        else if(namesMenu.equals("Visualizar")){
            categoria = new JMenuItem("Categoria");
             produtos = new JMenuItem("Produtos");
            menu.add(categoria);
            menu.add(produtos);
        }else{
             sobre = new JMenuItem("Sobre");
            menu.add(sobre);
        }
    }

    public void eventListeners(){
        sair.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
		}
		});

        gerCategoria.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				  new GerenciarCategoria(null);
		}
		});

        gerProduto.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				  new GerenciarProduto(null);
		}
		});

        sobre.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				  new Sobre();
		}
		});
        categoria.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				  new Categorias(null);
		}
		});

        produtos.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				  new Produtos(null);
		}
		});
    
    }
}
