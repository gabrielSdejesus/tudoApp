package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Task;
import util.ConnectionFactory;

public class TaskController {

	public void save(Task task) {

		String sql = "INSERT INTO TASKS " 
				+ "(project_id, " 
				+ "name, " 
				+ "description, " 
				+ "notes, " 
				+ "complete, "
				+ "deadline, " 
				+ "createat, " 
				+ "updateat) " 
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			
			//Estabelencendo conexao com o banco de dados
			connection = ConnectionFactory.getConnection();
			
			//Preparando a query
			statement = connection.prepareStatement(sql);
			
			//Setando os valores do statement
			statement.setInt(1, task.getIdProject());
			statement.setString(2, task.getName());
			statement.setString(3, task.getDescription());
			statement.setString(4, task.getNotes());
			statement.setBoolean(5, task.getComplete());
			statement.setDate(6, new Date(task.getDeadLine().getTime()));
			statement.setDate(7, new Date(task.getCreatedAt().getTime()));
			statement.setDate(8, new Date(task.getUpdateAt().getTime()));
			
			//Executando a query
			statement.execute();

		} catch (Exception e) {

			throw new RuntimeException("Erro ao salvar a tarefa " + e.getMessage(), e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);

		}
	}

	public void update(Task task) {

		String sql = "UPDATE TASKS SET " 
				+ "project_id = ?, " 
				+ "name = ?, " 
				+ "description = ?, " 				
				+ "notes = ?, " 
				+ "complete = ?, "
				+ "deadline = ?, " 
				+ "createat = ?, " 
				+ "updateat = ? " 
				+ "WHERE id = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			java.util.Date data = new java.util.Date();
			
			//Estabelencendo conexao com o banco de dados
			connection = ConnectionFactory.getConnection();
			
			//Preparando a query
			statement = connection.prepareStatement(sql);
			
			//Setando os valores do statement
			statement.setInt(1, task.getIdProject());
			statement.setString(2, task.getName());
			statement.setString(3, task.getDescription());
			statement.setString(4, task.getNotes());
			statement.setBoolean(5, task.getComplete());
			statement.setDate(6, new Date(task.getDeadLine().getTime()));
			statement.setDate(7, new Date(task.getCreatedAt().getTime()));
			statement.setDate(8, new Date(data.getTime()));
			statement.setInt(9, task.getId());
			
			//Executando a query
			statement.execute();

		} catch (SQLException e) {

			throw new RuntimeException("Erro ao fazer update " + "Error: " + e.getMessage(), e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}

	}

	public void removeById(int taskId) {

		String sql = "DELETE FROM TASKS WHERE ID = ?";

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			
			//Estabelencendo conexao com o banco de dados
			connection = ConnectionFactory.getConnection();
			
			//Preparando a query
			statement = connection.prepareStatement(sql);
			
			//Setando os valores da statement
			statement.setInt(1, taskId);
			
			//Executando a query
			statement.execute();
			
		} catch (Exception e) {

			throw new RuntimeException("Erro ao deletar a tarefa!");
		} finally {

			ConnectionFactory.closeConnection(connection, statement);
		}

	}

	public List<Task> getAll(int idProject) {
		
		String sql = "SELECT * FROM TASKS WHERE PROJECT_ID = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		//Lista que sera devolvida no getAll
		List<Task> listAll = new ArrayList<Task>();
		
		try {
			
			//Estabelencendo conexao com o banco de dados
			connection = ConnectionFactory.getConnection();
			
			//Preparando a query
			statement = connection.prepareStatement(sql);
			
			//Setando os valores do statement
			statement.setInt(1, idProject);
			
			//Valor retornado pela execucao da querry
			resultSet = statement.executeQuery();
			
			
			//Setando os valores do resultado da query em um objeto e definindo na lista
			while(resultSet.next()) {
				Task task = new Task();

                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("project_id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));        
                task.setComplete(resultSet.getBoolean("complete"));
                task.setDeadLine(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createat"));
                task.setCreatedAt(resultSet.getDate("updateAt"));
                
                listAll.add(task);
                
			}
		} catch (Exception e) {
			
			throw new RuntimeException("Erro ao buscar todos pelo id = " + idProject + " error: " + e.getMessage(), e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		
		//Lista que esta retornando com os dados alterados
		return listAll;
	}
}
