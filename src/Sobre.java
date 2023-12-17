
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Sobre extends JDialog {
    public Sobre(){
        this.setTitle("Gerenciar Categorias");
		this.setSize(400, 300);
		setModal(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		ImageIcon img = new ImageIcon(getClass().getResource("desenvolvedor.png"));
		img.setImage(img.getImage().getScaledInstance(100, 100, 100));
		JLabel lbImg = new JLabel(img);
		lbImg.setBounds(50, 70, 100, 100);
		JLabel lbTitle = new JLabel();
		lbTitle.setText("Desenvolvedor");
		lbTitle.setBounds(110, 20, 200, 30);
		lbTitle.setFont(new Font("Arial", Font.PLAIN, 26));
		add(lbTitle);
		JLabel lbNome = new JLabel();
		lbNome.setText("Hugo Ferreira de Carvalho");
		lbNome.setBounds(180, 80, 200, 20);
		JLabel lbEmail = new JLabel();
		lbEmail.setText("carvalhohugo425@gmail.com");
		lbEmail.setBounds(180, 110, 200, 20);
		add(lbNome);
		add(lbEmail);
		add(lbImg);
        setVisible(true);
    }
}
