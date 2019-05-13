package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import com.mongodb.DB;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionsInvestigador {

	static Connection conn = null;
	public int idInvestigador;


	public boolean login(String username, String password) {
		try {

			Connection connAux = getMysqlDataSource("root", "chocolate").getConnection();

			PreparedStatement preparedStatement = connAux.prepareStatement("SELECT * FROM investigador WHERE Email=? AND Password=?");
			preparedStatement.setString(1, username+"@gmail.com");
			preparedStatement.setString(2, password);
			preparedStatement.execute();
			ResultSet rs1 = preparedStatement.getResultSet();

			if (rs1.next()) {

				PreparedStatement preparedStatement2 = connAux.prepareStatement("SELECT IDInvestigador FROM investigador WHERE Email=? AND Password=?");
				preparedStatement.setString(1, username+"@gmail.com");
				preparedStatement.setString(2, password);
				preparedStatement.execute();
				ResultSet rs2 = preparedStatement.getResultSet();
				if(rs2.next()) {
					setIdInvestigador(rs2.getInt("IDInvestigador"));
					System.out.println("Login done!");
					System.out.println("ID: "+idInvestigador);
				}

				conn = getMysqlDataSource(username, password).getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT version()");

				//Só como teste de login
				if (rs.next()) {
					System.out.println("Database Version : " + rs.getString(1));
				}
				return true;
				//Aqui tem que enviar um sinal à GUI para mudar de frame

			} else {
				JOptionPane.showMessageDialog(null, "Dados de login incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		};
		return false;
	}

	public void selectCultura() {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM cultura");
			while (rs.next()) {
				System.out.println("ID da Cultura : " + rs.getString(1));
				System.out.println("Nome da Cultura : " + rs.getString("NomeCultura"));
			}
		} catch (SQLException e) {
			//			e.printStackTrace();
		}
	}

	public void createCultura(String cultura, String descricao) {
		Statement stmt;
		try {

			int lastId = 0;
			Statement statement = conn.createStatement();
			statement.execute("SELECT MAX(IDCultura) FROM dba.`cultura`");    
			ResultSet result = statement.getResultSet();
			if (result.next()) {
				lastId = result.getInt(1);
				System.out.println("lastID: "+lastId);
			}
			int newId = lastId+1;
			PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO dba.`cultura` (IDCultura, NomeCultura, DescricaoCultura, IDInvestigador) "+"VALUES (?, ?, ?, ?)");
			preparedStatement.setInt(1, newId);
			preparedStatement.setString(2, cultura);
			preparedStatement.setString(3, descricao);
			preparedStatement.setInt(4, idInvestigador);
			preparedStatement.executeUpdate();

			System.out.println("Foi criada a variavel "+cultura+" com o id "+newId);
			JOptionPane.showMessageDialog(null, "Foi inserida a cultura "+cultura+" com o id "+newId+" na base de dados.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editCultura(String descricao, int selectedId) {
		Statement stmt;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("UPDATE cultura SET DescricaoCultura = ? WHERE IDCultura = ?");
			preparedStatement.setString(1, descricao);
			preparedStatement.setInt(2, selectedId);
			preparedStatement.executeUpdate();

			System.out.println("Foi atualizada a descrição da cultura com o id "+selectedId+ " para "+descricao);
			JOptionPane.showMessageDialog(null,"Foi atualizada a descrição da cultura com o id "+selectedId+ " para "+descricao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnectionInvestigador() {
		return this.conn;
	}
	
	public int getIdInvestigador() {
		return idInvestigador;
	}

	public void setIdInvestigador(int idInvestigador) {
		this.idInvestigador = idInvestigador;
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
