


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StubCliente {
	private static String nomeServidor = "127.0.0.1";
	private static int porta = 12345;
	private static final String NOMEOBJDIST = "SistemaDistribuido";
	Registry registro;
	InterfaceDoServidor stub;
	public StubCliente() {
		try {
			//Politica de seguran�a
			System.setProperty("java.security.policy", "java.policy");
			if (System.getSecurityManager() == null) {
			 System.setSecurityManager(new SecurityManager());
			}
			
			System.setProperty("java.security.policy","file:java.policy");
			
			// Obtendo refer^encia do servi¸co de registro
			registro = LocateRegistry.getRegistry(nomeServidor, porta);
					
			// Procurando pelo objeto distribu´ıdo registrado previamente com o NOMEOBJDIST
			stub = (InterfaceDoServidor) registro.lookup(NOMEOBJDIST);
		} catch (RemoteException | NotBoundException ex) {
			Logger.getLogger(StubCliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	public ArrayList<String> receberLista() throws RemoteException {
		return stub.receberLista();
	}
	
	public static void main(String args[]) {
		try {
			//Politica de seguran�a
			System.setProperty("java.security.policy", "java.policy");
			if (System.getSecurityManager() == null) {
			 System.setSecurityManager(new SecurityManager());
			}
			
			System.setProperty("java.security.policy","file:java.policy");
			System.out.println("Conectando no servidor "+ nomeServidor);
			
			// Obtendo refer^encia do servi¸co de registro
			Registry registro = LocateRegistry.getRegistry(nomeServidor, porta);
			
			// Procurando pelo objeto distribu´ıdo registrado previamente com o NOMEOBJDIST
			InterfaceDoServidor stub = (InterfaceDoServidor) registro.lookup(NOMEOBJDIST);
			
			// Invocando m´etodos do objeto distribu´ıdo			
			System.out.println(stub.receberLista().size());
			
			System.out.println("Fim da execu��o do cliente!"); 
		} catch (RemoteException | NotBoundException ex) {
			Logger.getLogger(StubCliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
