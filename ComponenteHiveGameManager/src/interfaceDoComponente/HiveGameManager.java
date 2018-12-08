package interfaceDoComponente;

import javax.swing.JFrame;

import cip.ComponentInterface;
import estruturaInterna.InternalManager;
import estruturaInterna.ManagerGUI;

public class HiveGameManager extends ComponentInterface {
	protected ManagerGUI managerWindow;
	protected InternalManager manager;
		
	public HiveGameManager(String argId) {
		id = argId;
	}

	@Override
	public void initialize() {
		PortLogic portLogic;
		PortManagerProxy portProxy;
		manager = new InternalManager();
		portLogic = new PortLogic("logic");
		portLogic.initialize();
		portProxy = new PortManagerProxy("proxy1");
		portProxy.initialize();
		portProxy.setInternalReference (manager);
		ports.add(portProxy);
		ports.add(portLogic);
		managerWindow = new ManagerGUI();
		manager.addProxyPort(portProxy);
		manager.setLogicPort(portLogic);	
		managerWindow.setManager(manager);
		managerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managerWindow.setVisible(true);
	}

}
