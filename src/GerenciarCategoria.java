
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GerenciarCategoria extends JDialog {
    public GerenciarCategoria(ArrayList<String> itemUpdate){
        this.setTitle("Gerenciar Categorias");
		this.setSize(500, 500);
		setModal(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);


        JLabel title = new JLabel();
        title.setText("Gerenciar Categorias");
        title.setBounds(185, 155, 150, 20);
        
        JLabel lbCategoria = new JLabel();
        lbCategoria.setText("Categoria:");
        lbCategoria.setBounds(165, 195, 100, 20 );

        JTextField tfCategoria = new JTextField();
        tfCategoria.setBounds(235, 195, 100, 20);

        JButton btn = new JButton();
        btn.setText("Salvar");
        btn.setBounds(175, 245, 150, 20);
        add(btn);
        add(tfCategoria);
        add(lbCategoria);
        add(title);
        System.out.println("oxi");

        if(itemUpdate!=null){
            tfCategoria.setText(itemUpdate.get(1));
        }
        
        btn.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
                Categoria cat = new Categoria();
                if(itemUpdate!=null){
                    if(Categoria.update(tfCategoria.getText(), Integer.parseInt(itemUpdate.get(0)))){
                        dispose();
                        JOptionPane.showMessageDialog(null, "Categoria alterada com sucesso!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao alterar categoria!");
                    }
                }else{
                if(cat.insert(tfCategoria.getText())){
                    
                    JOptionPane.showMessageDialog(null, "Categoria inserida com sucesso!");
                    tfCategoria.setText("");
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Erro ao inserir a categoria!");
                }
                }

			}	
        });
        setVisible(true);
}
    }
    
   
