import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;

public class Categorias extends JDialog {

     JButton[] btnAlterar;
     JButton[] btnExcluir;
     JLabel lbCod[];
     JButton btnTodos = new JButton();
    

    public Categorias(String pesquisa){

        this.setTitle("Visualizar Categorias");
		this.setSize(700, 550);
		setModal(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
        
        JLabel lbTitle = new JLabel();
        lbTitle.setText("Visualizar Categorias");
        lbTitle.setBounds(200, 20, 300, 35);
        lbTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        add(lbTitle);
        
        JLabel lbPesquisa = new JLabel();
        lbPesquisa.setBounds(160, 80, 150, 25);
        lbPesquisa.setText("Pesquisar uma categoria:");
        add(lbPesquisa);

        JTextField tfPesquisa = new JTextField();
        tfPesquisa.setBounds(315, 80, 140, 25);
        add(tfPesquisa);

        JButton btnPesquisa = new JButton();
        btnPesquisa.setText("OK");
        btnPesquisa.setBounds(460, 80, 55, 25);
        add(btnPesquisa);

        if(pesquisa!=null){
            tfPesquisa.setText(pesquisa);
            btnTodos.setText("VER TODOS");
            btnTodos.setBounds(520, 80, 120, 25);
            add(btnTodos);
        }

        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        ArrayList<ArrayList<String>> array;
        if(pesquisa!=null){
            array =  Categoria.selectByName(pesquisa);
           System.out.println(pesquisa);
        }else{
            array = Categoria.selectAll();
        }


        int height = 105;
        if(pesquisa!=null){
            height = 1000;
        }
        int y=2;
        btnAlterar = new JButton[array.size()];
        btnExcluir = new JButton[array.size()];
        lbCod = new JLabel[array.size()];

        for(int i=0;i<array.size();i++){
        
        panel.setPreferredSize(new Dimension(100, height));
        panel.setLocation(0, 0);
        // panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
        JPanel jp = new JPanel();
    
        jp.setBounds(80,y,500,100);
        jp.setLayout(null);
    
        lbCod[i] = new JLabel();
        lbCod[i].setText("Codigo da Categoria: " + array.get(i).get(0));
        lbCod[i].setBounds(40, 26, 250, 20);
         
        JLabel lbcat = new JLabel();
        lbcat.setBounds(40, 46, 250 ,20);
        lbcat.setText("Nome da Categoria: " + array.get(i).get(1));

        btnAlterar[i] = new JButton();
        btnAlterar[i].setText("ALTERAR");
        btnAlterar[i].setBounds(270, 36, 100, 25);

        btnExcluir[i] = new JButton();
        btnExcluir[i].setText("EXCLUIR");
        btnExcluir[i].setBounds(373, 36, 100, 25);
            
        jp.add(lbCod[i]);
        jp.add(lbcat);   
        jp.add(btnAlterar[i]);
        jp.add(btnExcluir[i]);
        jp.setBorder(BorderFactory.createLineBorder(Color.black));
    
        panel.add(jp);

        height = height+105;
        y = y + 105;
        
        
    }
    
    
    System.out.println(y);
    
        
        
        
        
        
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBounds(0, 120, 680, 390);
        scroll.setAutoscrolls(true);
        add(scroll);
        
        
       // add(scroll);
        int i;
        for(i=0;i<btnAlterar.length;i++){
            int indiceDaLabel = i;
             btnAlterar[i].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String codCategoria = lbCod[indiceDaLabel].getText();
                String[] codCategoriaArray = codCategoria.split(":");
                codCategoria = codCategoriaArray[1].replaceAll(" ", "");
                ArrayList<String> array = Categoria.selectId(Integer.parseInt(codCategoria));
                new GerenciarCategoria(array);
                dispose();
                new Categorias(null);

            }
        });
        }

        for(i=0;i<btnExcluir.length;i++){
            int indiceDaLabel = i;
             btnExcluir[i].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String codCategoria = lbCod[indiceDaLabel].getText();
                String[] codCategoriaArray = codCategoria.split(":");
                codCategoria = codCategoriaArray[1].replaceAll(" ", "");
                int option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir esse item?\n OBS: vc excluira todos os produtos desta categoria", null, JOptionPane.YES_NO_OPTION );
                System.out.println(option);
                //sim 0, nao 1

                if(option==0){
                    if(Categoria.delete(Integer.parseInt(codCategoria))){
                        Categoria.delete(Integer.parseInt(codCategoria));
                        JOptionPane.showMessageDialog(null, "Categoria e itens excluidos");
                        dispose();
                        new Categorias(null);
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao excluir categoria");
                    }
                }

            }
        });
        }

        btnPesquisa.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
                dispose();
                new Categorias(tfPesquisa.getText());
			}	
        });
        btnTodos.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
                dispose();
                new Categorias(null);
			}	
        });

        
        this.setVisible(true);
    }    
}
