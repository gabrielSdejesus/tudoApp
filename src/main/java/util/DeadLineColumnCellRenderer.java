package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.Task;

public class DeadLineColumnCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		label.setHorizontalAlignment(CENTER);
		label.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		TaskTableModel taskModel = (TaskTableModel) table.getModel();
		Task task = taskModel.getTasks().get(row);
		
		if (task.getDeadLine().after(new Date())) {
			label.setBackground(Color.GREEN);
		} else {
			label.setBackground(Color.RED);
		}

		return label;
	}

}
