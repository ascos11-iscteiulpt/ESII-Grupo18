package administrador;

import java.awt.EventQueue;

import database.ConnectionsAdmin;
import interfacesAdministrador.GUIAdministrador;

public class MainAdministrador {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAdministrador gui = new GUIAdministrador(new ConnectionsAdmin());
					gui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
