
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GerenciarProduto extends JDialog {

    JTextField[] inputs;
    JLabel[] lbsInputs;
    JComboBox cbCategorias;
    
    public GerenciarProduto( ArrayList<String> itemUpdate){
        inputs = new JTextField[4];
        lbsInputs = new JLabel[5];
        this.setTitle("Gerenciar Produtos");
		this.setSize(500, 500);
		setModal(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
        JLabel lbTitle = new JLabel();
        lbTitle.setText("Gerenciar Produto");
        lbTitle.setBounds(165, 100, 150, 20);
        add(lbTitle);
        fazCampos("Produto:",  60,  140,  0); 
        lbsInputs[1] = new JLabel();
        lbsInputs[1].setBounds(120, 170, 120, 20 );
        lbsInputs[1].setText("Categoria:");
        add(lbsInputs[1]);
        cbCategorias = new JComboBox<>();
        cbCategorias.setBounds(230, 170, 120, 20);
        ArrayList<ArrayList<String>> categorias = Categoria.selectAll();
        for(int i=0;i<categorias.size();i++){
            
             cbCategorias.addItem(categorias.get(i).get(1));
            
        }
        add(cbCategorias);
        

        fazCampos("Valor:", 50, 200, 1);
        fazCampos("Descriçao:", 70, 230, 2);
        fazCampos("Quantidade:", 75, 260, 3);
         
        JButton btn = new JButton();
        btn.setText("Salvar");
        btn.setBounds(205, 300, 70, 20);

        if(itemUpdate!=null){
            inputs[0].setText(itemUpdate.get(1));
            int indexCb =0;
            for(int i =0;i<categorias.size();i++){
                if(categorias.get(i).get(0).equals(itemUpdate.get(2))){
                    indexCb = i;
                }
            }
            cbCategorias.setSelectedIndex(indexCb);
            inputs[1].setText(itemUpdate.get(4));
            inputs[2].setText(itemUpdate.get(5));
            inputs[3].setText(itemUpdate.get(6));
        }

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Producto p = new Producto();
                p.setProduto(inputs[0].getText());

                //SELECIONANDO O ID CORRETO DA CATEGORIA
                int index = cbCategorias.getSelectedIndex();

                p.setIdCategoria(Integer.parseInt(categorias.get(index).get(0))); // o primeiro index é a linha, o segundo a coluna, 0 pq idCategoria é a primeira coluna
                
                p.setValor(Double.parseDouble(inputs[1].getText()));
                System.out.println(p.getValor());
                p.setDescricao(inputs[2].getText());
               
                p.setQuantidade(Integer.parseInt(inputs[3].getText()));

                if(itemUpdate!=null){
                    if(Producto.update(p, Integer.parseInt(itemUpdate.get(0)))){
                        JOptionPane.showMessageDialog(null, "Produto Alterado com sucesso!");
                        dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Aconteceu algum erro ao alterar seu produto!");
                    dispose();
                }
                    }
                else{
                if(Producto.insert(p)){
                    for(int i=0;i<inputs.length;i++){
                        inputs[i].setText("");  
                    }
                    JOptionPane.showMessageDialog(null, "Produto Cadastrado com sucesso!");
                }else{
                    JOptionPane.showMessageDialog(null, "Aconteceu algum erro ao inserir seu produto!");
                }
                }

            }
        });



        add(btn);
        setVisible(true);
    }

    public void fazCampos(String textLabel,  int lgLabel,  int y,  int i){
        lbsInputs[i] =new JLabel();
        lbsInputs[i].setText(textLabel);
        lbsInputs[i].setBounds(120, y, lgLabel, 20);
        add(lbsInputs[i]);
        inputs[i] = new JTextField();
        inputs[i].setBounds(230, y, 120, 20);
        add(inputs[i]);

    }
}
