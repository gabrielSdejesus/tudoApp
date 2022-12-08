package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controller.ProjectController;
import model.Project;

@SuppressWarnings("serial")
public class DialogScreen extends JDialog {
	private JTextField textField;
	private ProjectController controller;


	public static void main(String[] args) {
		try {
			DialogScreen dialog = new DialogScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public DialogScreen() {
		getContentPane().setBackground(new Color(215, 215, 215));
		setResizable(false);
		setBounds(100, 100, 450, 500);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 102));

		JLabel labelNome = new JLabel("Nome");
		labelNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		textField = new JTextField();
		textField.setBorder(UIManager.getBorder("ComboBox.border"));
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setColumns(10);

		JLabel labelDescricao = new JLabel("Descrição");
		labelDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JTextArea textArea = new JTextArea();
		textArea.setBorder(UIManager.getBorder("ComboBox.border"));
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(labelNome, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE).addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE).addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(labelDescricao, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(labelNome).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(23).addComponent(labelDescricao).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE).addContainerGap()));

		JLabel projetoTitulo = new JLabel("Projeto");
		projetoTitulo.setForeground(new Color(255, 255, 255));
		projetoTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

		JLabel iconConfirmed = new JLabel("");
		iconConfirmed.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				WindowEvent we = new WindowEvent(getOwner(), 0);

				try {
					controller = new ProjectController();
					Project project = new Project();

					if (!textField.getText().isEmpty()) {
						project.setName(textField.getText());
						project.setDescription(textField.getText());
						controller.save(project);

						JOptionPane.showMessageDialog(rootPane, "Projeto salvo com sucesso");
					} else {
						JOptionPane.showMessageDialog(rootPane, "É necessário passar um nome para o projeto");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(rootPane, "Ocorreu algum erro ao salvar!");
					e2.printStackTrace();
				}
				we.getWindow().dispose();
			}
		});

		iconConfirmed.setIcon(new ImageIcon(getClass().getResource("/check.png")));
		iconConfirmed.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(projetoTitulo, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
						.addComponent(iconConfirmed, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addGap(18)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(25)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(iconConfirmed, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(projetoTitulo))
								.addContainerGap(22, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}

}
