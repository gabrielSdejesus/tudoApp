package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import controller.TaskController;
import model.Project;
import model.Task;

public class TaskDialogScreen extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTextField textNome;
	private TaskController controller;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static Integer id;
	Project project;

	public static void main(String[] args) {
		try {
			TaskDialogScreen dialog = new TaskDialogScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TaskDialogScreen() throws ParseException {
		setName("dialog16");
		setBackground(new Color(0, 0, 0));
		getContentPane().setBackground(new Color(215, 215, 215));
		setResizable(false);
		setBounds(100, 100, 450, 625);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 102));

		JLabel nomeTitulo = new JLabel("Nome");
		nomeTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		textNome = new JTextField();
		textNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textNome.setColumns(10);
		textNome.setBorder(UIManager.getBorder("ComboBox.border"));

		JLabel descricaoTitulo = new JLabel("Descrição");
		descricaoTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		final JTextArea textAreaDescricao = new JTextArea();
		textAreaDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textAreaDescricao.setBorder(UIManager.getBorder("ComboBox.border"));

		final JLabel prazoTitulo = new JLabel("Prazo");
		prazoTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JLabel notasTitulo = new JLabel("Notas");
		notasTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		final JTextArea textAreaNotas = new JTextArea();
		textAreaNotas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textAreaNotas.setBorder(UIManager.getBorder("ComboBox.border"));

		final JFormattedTextField prazoTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		prazoTextField.setPreferredSize(new Dimension(7, 25));
		prazoTextField.setSize(new Dimension(7, 20));
		prazoTextField.setBorder(UIManager.getBorder("ComboBox.border"));
		prazoTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addComponent(textAreaDescricao, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
						.addComponent(textNome, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
						.addComponent(nomeTitulo, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(descricaoTitulo, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
						.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(prazoTitulo, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
								.addComponent(textAreaNotas, GroupLayout.PREFERRED_SIZE, 414,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(notasTitulo, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(prazoTextField, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addComponent(panel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(nomeTitulo).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(textNome, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE).addGap(11)
				.addComponent(descricaoTitulo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(textAreaDescricao, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(prazoTitulo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(prazoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(17).addComponent(notasTitulo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(textAreaNotas, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(259, Short.MAX_VALUE)));

		JLabel TarefaTitulo = new JLabel(" Tarefa");
		TarefaTitulo.setForeground(new Color(255, 255, 255));
		TarefaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

		JLabel iconConfirmed = new JLabel("");
		iconConfirmed.setHorizontalAlignment(SwingConstants.CENTER);
		iconConfirmed.setIcon(new ImageIcon(getClass().getResource("/check.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(TarefaTitulo, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(iconConfirmed, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(TarefaTitulo, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(iconConfirmed, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

		iconConfirmed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WindowEvent we = new WindowEvent(getOwner(), WIDTH);
				// System.out.println(id);
				try {
					controller = new TaskController();
					Task task = new Task();

					if (!textNome.getText().isEmpty() 
							&& validateJavaDate(prazoTextField.getText())) {

						task.setIdProject(id);
						task.setName(textNome.getText());
						task.setDescription(textAreaDescricao.getText());
						task.setNotes(textAreaNotas.getText());
						task.setComplete(false);
						task.setDeadLine(sdf.parse(prazoTextField.getText()));
						task.setCreatedAt(new Date());
						task.setUpdateAt(new Date());
						
						controller.save(task);

						//Mensagem de sucesso
						JOptionPane.showMessageDialog(rootPane, "Tarefa salva com sucesso!");
					} else {
						//Mensagem de erro
						JOptionPane.showMessageDialog(rootPane, "É necessário ter nome e prazo!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(rootPane, "Ocorreu algum erro ao salvar!");
					e1.printStackTrace();
				}
				we.getWindow().dispose();
			}
		});
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public boolean validateJavaDate(String strDate) {

		/* Verificando se a data passado é "null" */
		if (strDate.trim().equals("")) {
			return true;
		}
		/* Data não é "null" */
		else {

			SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
			sdfrmt.setLenient(false);

			/*
			 * Criar um objeto do tipo Date e validar seu tamanho
			 */
			try {
				Date javaDate = sdfrmt.parse(strDate);
				return (javaDate.toString().length() == 28) ? true : false;		
			}
			/* Formato da data invalido */
			catch (ParseException e) {
				return false;
			}
		}

	}
}
