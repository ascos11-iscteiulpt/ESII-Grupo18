package investigador;

import java.awt.EventQueue;

import database.ConnectionsAdmin;
import database.ConnectionsInvestigador;
import interfacesAdministrador.GUIAdministrador;
import interfacesInvestigador.GUIInvestigador;

public class MainInvestigador {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIInvestigador gui = new GUIInvestigador(new ConnectionsInvestigador());
					gui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
