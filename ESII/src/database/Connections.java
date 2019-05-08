package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mongodb.DB;
import com.mysql.cj.jdbc.MysqlDataSource;

public class Connections {

	Connection conn = null;

	public static void main(String[] args) {
		Connections c = new Connections();
		c.login("root", "chocolate");
		//		c.selectVariaveis();
		//		c.createVariavel("VariavelTeste");
		c.deleteVariavel(12);
	}


	public void login(String username, String password) {
		try {
			conn = getMysqlDataSource(username, password).getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT version()");

			if (rs.next()) {
				System.out.println("Database Version : " + rs.getString(1));
			}

		} catch (SQLException e) {
			//e.printStackTrace();
		};
	}

	public void selectVariaveis() {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM variaveis");
			while (rs.next()) {
				System.out.println("ID da Variável : " + rs.getString(1));
				System.out.println("Nome da Variável : " + rs.getString("NomeVariavel"));
			}
		} catch (SQLException e) {
			//			e.printStackTrace();
		}
	}

	public void createVariavel(String variavel) {
		Statement stmt;
		try {

			int lastId = 0;
			Statement statement = conn.createStatement();
			statement.execute("SELECT MAX(IDVariavel) FROM dba.`variaveis`");    
			ResultSet result = statement.getResultSet();
			if (result.next()) {
				lastId = result.getInt(1);
				System.out.println("lastID: "+lastId);
			}
			int newId = lastId+1;
			PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO dba.`variaveis` (IDVariavel, NomeVariavel) "+"VALUES (?, ?)");
			preparedStatement.setInt(1, newId);
			preparedStatement.setString(2, variavel);
			preparedStatement.executeUpdate();

			System.out.println("Foi criada a variavel "+variavel+" com o id "+newId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteVariavel(int id) {
		Statement stmt;
		try {

			PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM variaveis WHERE IDVariavel=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			System.out.println("Foi removida a variável com o id "+id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public DataSource getMysqlDataSource(String username, String password) {
		MysqlDataSource dataSource = new MysqlDataSource();
		try {
			dataSource.setServerTimezone("UTC");
		} catch (SQLException e) {

		}
		// Set dataSource Properties
		dataSource.setServerName("localhost");
		dataSource.setPortNumber(3306);
		dataSource.setDatabaseName("dba");
		//dataSource.setUser("root");
		//dataSource.setPassword("chocolate");
		dataSource.setUser(username);
		dataSource.setPassword(password);
		return dataSource;
	}


}
