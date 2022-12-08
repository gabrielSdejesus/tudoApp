package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controller.ProjectController;
import controller.TaskController;
import model.Project;
import model.Task;
import util.ButtonColumnCellRenderer;
import util.DeadLineColumnCellRenderer;
import util.TaskTableModel;

public class MainScreen extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable tableTasks;
	private static Integer idProject;

	ProjectController projectController;
	TaskController taskController;
	@SuppressWarnings("rawtypes")
	DefaultListModel projectModel;
	TaskTableModel taskTableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public MainScreen() {
		setMinimumSize(new Dimension(1000, 775));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 775);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(215, 215, 215));
		setContentPane(contentPane);

		JPanel painelTopo = new JPanel();
		painelTopo.setBackground(new Color(0, 128, 102));
		painelTopo.setLayout(null);

		JLabel description = new JLabel("Anote tudo, não esqueça de nada!");
		description.setBounds(7, 89, 964, 25);
		description.setForeground(new Color(255, 255, 255));
		description.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		painelTopo.add(description);

		JLabel TudoApp = new JLabel(" Tudo App");
		TudoApp.setBounds(7, 7, 964, 64);
		TudoApp.setIcon(new ImageIcon(getClass().getResource("/tick.png")));
		TudoApp.setForeground(new Color(255, 255, 255));
		TudoApp.setFont(new Font("Segoe UI", Font.BOLD, 32));
		painelTopo.add(TudoApp);

		JPanel painelTituloProjetos = new JPanel();
		painelTituloProjetos.setBorder(UIManager.getBorder("ComboBox.border"));

		JPanel painelTituloTarefas = new JPanel();
		painelTituloTarefas.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));

		JPanel paineLateralEsquerda = new JPanel();
		paineLateralEsquerda.setMinimumSize(new Dimension(5, 5));
		paineLateralEsquerda.setAlignmentX(0.0f);
		paineLateralEsquerda.setAlignmentY(0.0f);
		paineLateralEsquerda.setBackground(new Color(255, 255, 255));
		paineLateralEsquerda.setBorder(UIManager.getBorder("ComboBox.border"));

		JPanel painelFundoLateralDireita = new JPanel();
		painelFundoLateralDireita.setBorder(UIManager.getBorder("ComboBox.border"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(painelTopo, GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(painelTituloProjetos, GroupLayout.PREFERRED_SIZE, 200, 200)
								.addComponent(paineLateralEsquerda, GroupLayout.DEFAULT_SIZE, 200, 200))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(painelFundoLateralDireita, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
								.addComponent(painelTituloTarefas, GroupLayout.PREFERRED_SIZE, 754, Short.MAX_VALUE))
						.addGap(12)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(painelTopo, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(painelTituloTarefas, 0, 0, Short.MAX_VALUE)
								.addComponent(painelTituloProjetos, GroupLayout.PREFERRED_SIZE, 62, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(paineLateralEsquerda, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
								.addComponent(painelFundoLateralDireita, GroupLayout.PREFERRED_SIZE, 518,
										Short.MAX_VALUE))
						.addContainerGap()));
		GroupLayout gl_painelFundoLateralDireita = new GroupLayout(painelFundoLateralDireita);
		gl_painelFundoLateralDireita.setHorizontalGroup(
				gl_painelFundoLateralDireita.createParallelGroup(Alignment.LEADING).addGap(0, 752, Short.MAX_VALUE));
		gl_painelFundoLateralDireita.setVerticalGroup(
				gl_painelFundoLateralDireita.createParallelGroup(Alignment.LEADING).addGap(0, 516, Short.MAX_VALUE));
		painelFundoLateralDireita.setLayout(gl_painelFundoLateralDireita);
		GroupLayout gl_painelFundoLateralDireita1 = new GroupLayout(painelFundoLateralDireita);
		gl_painelFundoLateralDireita1.setHorizontalGroup(
				gl_painelFundoLateralDireita1.createParallelGroup(Alignment.LEADING).addGap(0, 753, Short.MAX_VALUE));
		gl_painelFundoLateralDireita1.setVerticalGroup(
				gl_painelFundoLateralDireita1.createParallelGroup(Alignment.LEADING).addGap(0, 517, Short.MAX_VALUE));
		painelFundoLateralDireita.setLayout(gl_painelFundoLateralDireita1);

		tableTasks = new JTable();
		tableTasks.setShowGrid(false);
		tableTasks.setBackground(new Color(255, 255, 255));
		tableTasks.setBorder(UIManager.getBorder("Button.border"));
		tableTasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTasks.setSelectionForeground(new Color(0, 0, 0));
		tableTasks.setForeground(new Color(0, 0, 0));
		tableTasks.setFocusable(false);
		tableTasks.setShowVerticalLines(false);
		tableTasks.setSelectionBackground(new Color(204, 255, 204));
		tableTasks.setRowHeight(50);
		tableTasks.setModel(new DefaultTableModel(
				new Object[][] { { "", null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null }, },
				new String[] { "Nome", "Descri\u00E7\u00E3o", "Prazo", "Tarefa Conclu\u00EDda", "Editar", "Excluir" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Boolean.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		tableTasks.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableTasks.setAutoCreateRowSorter(true);

		// Table Header
		tableTasks.getTableHeader().setBackground(new Color(0, 152, 102));
		tableTasks.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

		final JPanel tituloColunasTabela = new JPanel();
		tituloColunasTabela.setBorder(UIManager.getBorder("TextPane.border"));
		tituloColunasTabela.setMaximumSize(new Dimension(100, 100));
		tituloColunasTabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tituloColunasTabela.setAlignmentY(Component.TOP_ALIGNMENT);
		tituloColunasTabela.setAlignmentX(Component.LEFT_ALIGNMENT);
		GroupLayout gl_painelFundoLateralDireita2 = new GroupLayout(painelFundoLateralDireita);
		gl_painelFundoLateralDireita2
				.setHorizontalGroup(gl_painelFundoLateralDireita2.createParallelGroup(Alignment.LEADING)
						.addComponent(tituloColunasTabela, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 752,
								Short.MAX_VALUE)
						.addComponent(tableTasks, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE));
		gl_painelFundoLateralDireita2.setVerticalGroup(gl_painelFundoLateralDireita2
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelFundoLateralDireita2.createSequentialGroup()
						.addComponent(tituloColunasTabela, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tableTasks, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)));
		tituloColunasTabela.add(tableTasks.getTableHeader());
		tituloColunasTabela.setLayout(new BoxLayout(tituloColunasTabela, BoxLayout.X_AXIS));
		painelFundoLateralDireita.setLayout(gl_painelFundoLateralDireita2);

		visibleOff(tituloColunasTabela);

		final JList menuItem = new JList();
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int projectIndex = menuItem.getSelectedIndex();

				if (menuItem.getSelectedIndex() > -1) {

					Project project = (Project) projectModel.getElementAt(projectIndex);
					setIdProject(project.getId());
					TaskDialogScreen.id = project.getId();
					visibleOff(tituloColunasTabela);

					if (project.getId() > 0 && countTasks()) {
						loadTasks();
						reloadTasks(tituloColunasTabela);
					}
				}

			}
		});

		menuItem.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		menuItem.setFixedCellHeight(35);
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.setSelectionBackground(new Color(0, 153, 102));
		menuItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menuItem.setModel(new AbstractListModel() {
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		menuItem.setFont(new Font("Segoe UI", Font.BOLD, 16));
		GroupLayout gl_paineLateralEsquerda = new GroupLayout(paineLateralEsquerda);
		gl_paineLateralEsquerda.setHorizontalGroup(gl_paineLateralEsquerda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paineLateralEsquerda.createSequentialGroup().addContainerGap()
						.addComponent(menuItem, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE).addContainerGap()));
		gl_paineLateralEsquerda.setVerticalGroup(gl_paineLateralEsquerda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paineLateralEsquerda.createSequentialGroup().addContainerGap()
						.addComponent(menuItem, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE).addContainerGap()));
		paineLateralEsquerda.setLayout(gl_paineLateralEsquerda);

		JLabel Tarefas = new JLabel("Tarefas");
		Tarefas.setForeground(new Color(0, 128, 102));
		Tarefas.setFont(new Font("Segoe UI", Font.BOLD, 22));

		JLabel IconTarefas = new JLabel("");
		IconTarefas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					TaskDialogScreen taskDialogScreen = new TaskDialogScreen();
					taskDialogScreen.setVisible(true);
					taskDialogScreen.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							visibleOff(tituloColunasTabela);
							loadTasks();
							reloadTasks(tituloColunasTabela);

						}
					});
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(rootPane, e1.getMessage());
				}
			}
		});
		IconTarefas.setHorizontalAlignment(SwingConstants.RIGHT);
		IconTarefas.setIcon(new ImageIcon(getClass().getResource("/add.png")));
		GroupLayout gl_painelTituloTarefas = new GroupLayout(painelTituloTarefas);
		gl_painelTituloTarefas.setHorizontalGroup(gl_painelTituloTarefas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTituloTarefas.createSequentialGroup().addGap(10)
						.addComponent(Tarefas, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 614, Short.MAX_VALUE).addComponent(IconTarefas)
						.addContainerGap()));
		gl_painelTituloTarefas.setVerticalGroup(gl_painelTituloTarefas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTituloTarefas.createSequentialGroup()
						.addGroup(gl_painelTituloTarefas.createParallelGroup(Alignment.TRAILING)
								.addComponent(IconTarefas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 62,
										Short.MAX_VALUE)
								.addComponent(Tarefas, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 62,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		painelTituloTarefas.setLayout(gl_painelTituloTarefas);

		JLabel Projetos = new JLabel("Projetos");
		Projetos.setHorizontalAlignment(SwingConstants.LEFT);
		Projetos.setMaximumSize(new Dimension(93, 62));
		Projetos.setFont(new Font("Segoe UI", Font.BOLD, 22));
		Projetos.setForeground(new Color(0, 128, 102));

		JLabel IconProjetos = new JLabel("");
		IconProjetos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DialogScreen dialogScreen = new DialogScreen();
					dialogScreen.setVisible(true);
					dialogScreen.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							loadProjects(menuItem);
						}
					});
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(rootPane, e1.getMessage());
				}
			}
		});
		IconProjetos.setHorizontalAlignment(SwingConstants.RIGHT);
		IconProjetos.setIcon(new ImageIcon(getClass().getResource("/add.png")));
		GroupLayout gl_painelTituloProjetos = new GroupLayout(painelTituloProjetos);
		gl_painelTituloProjetos.setHorizontalGroup(gl_painelTituloProjetos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTituloProjetos.createSequentialGroup().addGap(10).addComponent(Projetos,
						GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_painelTituloProjetos.createSequentialGroup()
						.addContainerGap(158, Short.MAX_VALUE).addComponent(IconProjetos).addGap(8)));
		gl_painelTituloProjetos.setVerticalGroup(gl_painelTituloProjetos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTituloProjetos.createSequentialGroup()
						.addGroup(gl_painelTituloProjetos.createParallelGroup(Alignment.LEADING)
								.addComponent(Projetos, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addComponent(IconProjetos, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		painelTituloProjetos.setLayout(gl_painelTituloProjetos);
		contentPane.setLayout(gl_contentPane);

		tableTasks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int rowIndex = tableTasks.rowAtPoint(e.getPoint());
				int columnIndex = tableTasks.columnAtPoint(e.getPoint());
				Task task;
				if (rowIndex > 0) {
					task = taskTableModel.getTasks().get(rowIndex);
					switch (columnIndex) {

					case 3:
						taskController.update(task);
						break;

					case 4:
						break;

					case 5:
						taskController.removeById(task.getId());
						taskTableModel.getTasks().remove(task);
						loadTasks();
						reloadTasks(tituloColunasTabela);

						if (taskTableModel.getRowCount() == 0) {
							visibleOff(tituloColunasTabela);
						}
						break;
					}
				}
			}
		});

		initDataController();
		initComponentsModel(menuItem, tituloColunasTabela);
		decoratedTasks();
	}

	public void initDataController() {
		projectController = new ProjectController();
		taskController = new TaskController();

	}

	@SuppressWarnings("rawtypes")
	public void initComponentsModel(JList<?> menuItem, JPanel panel) {
		projectModel = new DefaultListModel();
		loadProjects(menuItem);

		// Se houver primeiro projeto, abrir nele.
		List<Project> projects = projectController.getAll();

		if (projects.size() > 0) {
			setIdProject(projects.get(0).getId());
			taskTableModel = new TaskTableModel();
			tableTasks.setModel(taskTableModel);

			loadTasks();
			if (taskTableModel.getRowCount() > 0) {
				reloadTasks(panel);
			}
		} else {
			visibleOff(panel);
		}

	}

	@SuppressWarnings("unchecked")
	public void loadProjects(JList<?> menuItem) {
		projectModel.clear();
		List<Project> projects = projectController.getAll();

		for (Project p : projects) {
			projectModel.addElement(p);
		}

		menuItem.setModel(projectModel);
	}

	public void loadTasks() {
		List<Task> tasks = taskController.getAll(getIdProject());
		if (tasks.size() > 0) {
			taskTableModel.setTasks(tasks);
		}
	}

	// Verificar se a lista de tarefas está vazia
	public boolean countTasks() {
		List<Task> tasks = taskController.getAll(getIdProject());
		return tasks.size() > 0;
	}

	// Visibilidade on das tarefas
	public void reloadTasks(JPanel x) {
		visibleOff(x);
		x.setVisible(true);
		tableTasks.setVisible(true);
	}

	// Visibilidade off das tarefas
	public void visibleOff(JPanel x) {
		x.setVisible(false);
		tableTasks.setVisible(false);
	}

	public Integer getIdProject() {
		return idProject;
	}

	@SuppressWarnings("static-access")
	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public void decoratedTasks() {
		tableTasks.getColumnModel().getColumn(2).setResizable(false);
		tableTasks.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableTasks.getColumnModel().getColumn(2).setMinWidth(120);
		tableTasks.getColumnModel().getColumn(2).setMaxWidth(120);
		tableTasks.getColumnModel().getColumn(2).setCellRenderer(new DeadLineColumnCellRenderer());
		tableTasks.getColumnModel().getColumn(3).setResizable(false);
		tableTasks.getColumnModel().getColumn(3).setPreferredWidth(120);
		tableTasks.getColumnModel().getColumn(3).setMinWidth(120);
		tableTasks.getColumnModel().getColumn(3).setMaxWidth(120);
		tableTasks.getColumnModel().getColumn(4).setResizable(false);
		tableTasks.getColumnModel().getColumn(4).setPreferredWidth(65);
		tableTasks.getColumnModel().getColumn(4).setMinWidth(65);
		tableTasks.getColumnModel().getColumn(4).setMaxWidth(65);
		tableTasks.getColumnModel().getColumn(4).setCellRenderer(new ButtonColumnCellRenderer("edit"));
		tableTasks.getColumnModel().getColumn(5).setResizable(false);
		tableTasks.getColumnModel().getColumn(5).setPreferredWidth(65);
		tableTasks.getColumnModel().getColumn(5).setMinWidth(65);
		tableTasks.getColumnModel().getColumn(5).setMaxWidth(65);
		tableTasks.getColumnModel().getColumn(5).setCellRenderer(new ButtonColumnCellRenderer("delete"));
	}

	static {
		idProject = 0;
	}
}
