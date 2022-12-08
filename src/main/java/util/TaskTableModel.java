package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Task;

public class TaskTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private String[] columns = { "Nome", "Descrição", "Prazo", "Tarefa Concluída", "Editar", "Excluir" };
	private List<Task> tasks = new ArrayList<Task>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public int getRowCount() {
		return tasks.size();
	}

	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 3 ? true : false;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		
		if(tasks.isEmpty()) {
			return Object.class;
		}
		
		return this.getValueAt(0, columnIndex).getClass();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		tasks.get(rowIndex).setComplete((Boolean) aValue);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {

		case 0:
			return tasks.get(rowIndex).getName();

		case 1:
			return tasks.get(rowIndex).getDescription();

		case 2:
			return sdf.format(tasks.get(rowIndex).getDeadLine());

		case 3:
			return tasks.get(rowIndex).getComplete();

		case 4:
			return "";

		case 5:
			return "";

		default:
			return "Dados não encontrados";
		}
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String[] getColumns() {
		return columns;
	}

}
