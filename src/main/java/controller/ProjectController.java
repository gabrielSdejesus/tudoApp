package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Project;
import util.ConnectionFactory;

public class ProjectController {

	public void save(Project project) {

		String sql = "INSERT INTO PROJECTS " + "(name, " + "description," + "createat," + "updateat) "
				+ "VALUES (?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement statement = null;

		try {

			// Estabelencendo conexao com o banco de dados
			connection = ConnectionFactory.getConnection();

			// Preparando a query
			statement = connection.prepareStatement(sql);

			// Setando os valores do statement
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setDate(3, new Date(project.getCreateAt().getTime()));
			statement.setDate(4, new Date(project.getCreateAt().getTime()));

			// Executando a query
			statement.execute();

		} catch (Exception e) {

			throw new RuntimeException("Erro ao salvar a tarefa " + e.getMessage(), e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void update(Project project) {

		String sql = "UPDATE FROM PROJECTS " 
				+ "name = ?, " 
				+ "description = ?, " 
				+ "createAt = ?, " 
				+ "updateAt = ? "
				+ "WHERE id = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;

		try {

			// Estabelencendo conexao com o banco de dados
			connection = ConnectionFactory.getConnection();

			// Preparando a query
			statement = connection.prepareStatement(sql);
			
			//Setando os valores do statement
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setDate(3, new Date(project.getCreateAt().getTime()));
			statement.setDate(4, new Date(project.getUpdateAt().getTime()));
			statement.setInt(5, project.getId());
			
			//Executando a query
			statement.execute();
			
		} catch (Exception e) {

			throw new RuntimeException("Erro ao fazer update " + e.getMessage(), e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void removeById(int projectId) {
		
		String sql = "DELETE FROM PROJECTS WHERE ID = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			//Estabelencendo conexao com o banco de dados
			connection = ConnectionFactory.getConnection();
			
			//Preparando a query
			statement = connection.prepareStatement(sql);
			
			//Setando os valores da statement
			statement.setInt(1, projectId);
			
			//Executando a query
			statement.execute();
			
		} catch (Exception e) {

			throw new RuntimeException("Erro ao deletar a tarefa!");
		} finally {

			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public List<Project> getAll() {
		
		String sql = "SELECT * FROM PROJECTS";
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		//Lista que sera devolvida no getAll
		List<Project> listAll = new ArrayList<Project>();
		
		try {
			
			//Estabelecendo conxão com o banco de dados
			connection = ConnectionFactory.getConnection();
			
			//Preparando a query
			statement = connection.prepareStatement(sql);
			
			//Valor retornado pela execucao da querry
			resultSet = statement.executeQuery();
			
			//Setando os valores do resultado da query em um objeto e definindo na lista
			while(resultSet.next()) {
				
				Project project = new Project();
				
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setDescription(resultSet.getString("description"));
				project.setCreateAt(resultSet.getDate("createAt"));
				project.setUpdateAt(resultSet.getDate("updateAt"));
				
				listAll.add(project);
			}
			
			return listAll;
		} catch (Exception e) {
			
			throw new RuntimeException("Erro ao buscar todos: " + e.getMessage(), e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

}