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

public class Produtos extends JDialog {

     JButton[] btnAlterar;
     JButton[] btnExcluir;
     JLabel lbCod[];
     JButton btnTodos = new JButton();
    

    public Produtos(String pesquisa){

        this.setTitle("Visualizar Produtos");
		this.setSize(700, 550);
		setModal(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
        
        JLabel lbTitle = new JLabel();
        lbTitle.setText("Visualizar Produtos");
        lbTitle.setBounds(200, 20, 300, 35);
        lbTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        add(lbTitle);
        
        JLabel lbPesquisa = new JLabel();
        lbPesquisa.setBounds(160, 80, 150, 25);
        lbPesquisa.setText("Pesquisar um produto:");
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
            array =  Producto.selectByName(pesquisa);
           System.out.println(pesquisa);
        }else{
            array = Producto.selectAll();
        }


        int height = 150;
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
    
        jp.setBounds(38,y,600,150);
        jp.setLayout(null);
    
        lbCod[i] = new JLabel();
        lbCod[i].setText(array.get(i).get(0));
        
         
        JLabel lbProduto = new JLabel();
        lbProduto.setBounds(40, 26, 250 ,20);
        lbProduto.setText("Nome do Produto: " + array.get(i).get(1));
        
        
        JLabel lbValor = new JLabel();
        lbValor.setBounds(40, 46, 250 ,20);
        lbValor.setText("Valor: " + array.get(i).get(3) + "R$");

        JLabel lbQuantidade = new JLabel();
        lbQuantidade.setBounds(40, 66, 250 ,20);
        lbQuantidade.setText("Quantidade: " + array.get(i).get(5));

        JLabel lbDesc = new JLabel();
        lbDesc.setBounds(40, 86, 250 ,20);
        lbDesc.setText("Descrição:");

        JLabel lbCategoria = new JLabel();
        lbCategoria.setBounds(400, 86, 250 ,20);
        lbCategoria.setText("Categoria: " + array.get(i).get(2));

        JLabel lbDescricao = new JLabel();
        lbDescricao.setBounds(40, 106, 450 ,40);
        lbDescricao.setText(array.get(i).get(4));

        btnAlterar[i] = new JButton();
        btnAlterar[i].setText("ALTERAR");
        btnAlterar[i].setBounds(370, 36, 100, 25);

        btnExcluir[i] = new JButton();
        btnExcluir[i].setText("EXCLUIR");
        btnExcluir[i].setBounds(475, 36, 100, 25);
            
        jp.add(lbProduto);
        jp.add(lbValor);   
        jp.add(lbQuantidade);
        jp.add(lbDesc);
        jp.add(lbCategoria);
        jp.add(lbDescricao);
        jp.add(btnAlterar[i]);
        jp.add(btnExcluir[i]);
        jp.setBorder(BorderFactory.createLineBorder(Color.black));
    
        panel.add(jp);

        height = height+155;
        y = y + 152;
        
        
    }
    
    
    
        
        
        
        
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
                String codProduto = lbCod[indiceDaLabel].getText();
                ArrayList<String> array = Producto.selectId(Integer.parseInt(codProduto));
                new GerenciarProduto(array);
                dispose();
                new Produtos(null);

            }
        });
        }

        for(i=0;i<btnExcluir.length;i++){
            int indiceDaLabel = i;
             btnExcluir[i].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String codProduto = lbCod[indiceDaLabel].getText();
                int option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir esse item?", null, JOptionPane.YES_NO_OPTION );
                
                //sim 0, nao 1

                if(option==0){
                    if(Producto.delete(Integer.parseInt(codProduto))){
                        
                        JOptionPane.showMessageDialog(null, "Produto excluido");
                        dispose();
                        new Produtos(null);
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao excluir produto");
                    }
                }

            }
        });
        }

        btnPesquisa.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
                dispose();
                new Produtos(tfPesquisa.getText());
			}	
        });
        btnTodos.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
                dispose();
                new Produtos(null);
			}	
        });

        
        this.setVisible(true);
    }    
}
