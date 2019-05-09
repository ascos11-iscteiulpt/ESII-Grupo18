package administrador;

import java.awt.EventQueue;

import database.ConnectionsAdmin;

public class MainAdministrador {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAdministrador gui = new GUIAdministrador();
					gui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
