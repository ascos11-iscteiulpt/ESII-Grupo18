package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mongodb.DB;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionsAdmin {

	Connection conn = null;

	public static void main(String[] args) {
		ConnectionsAdmin c = new ConnectionsAdmin();
		c.login("root", "chocolate");
//		c.selectVariaveis();
		//c.createVariavel("VariavelTeste");
//		c.deleteVariavel(12);
//		c.selectUtilizadores();
//		c.deleteUtilizador(7);
//		c.createAdministrador("ccc","ccc");
		c.createInvestigador("inve", "nome", "pass");
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
	
	public void selectUtilizadores() { //Está a devolver só os investigadores, para já
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM investigador");
			while (rs.next()) {
				System.out.println("Nome de investigador : " + rs.getString("NomeInvestigador"));
			}
		} catch (SQLException e) {
			//			e.printStackTrace();
		}
	}
	
	public void deleteUtilizador(int id) { // Investigadores só, para já
		Statement stmt;
		try {

			PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM investigador WHERE IDInvestigador=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			System.out.println("Foi removida o investigador com o id "+id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createAdministrador(String username, String pass) { // Investigadores só, para já
		Statement stmt;
		try {
			
			CallableStatement cs = conn.prepareCall("call dba.create_administrador(?, ?)");
			cs.setString(1, username);
			cs.setString(2, pass);
			cs.executeUpdate();

			System.out.println("Foi criado o administrador com o nome "+username);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createInvestigador(String nickname, String nomeInvestigador, String pass) { // Investigadores só, para já
		Statement stmt;
		try {
			
			CallableStatement cs = conn.prepareCall("call dba.create_investigador(?, ?, ?, ?)");
			cs.setString(1, nickname);
			cs.setString(2, nomeInvestigador);
			cs.setString(3, pass);
			cs.setString(4, "investigador");
			cs.executeUpdate();

			System.out.println("Foi criado o investigador com o nome "+nomeInvestigador);
			
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
