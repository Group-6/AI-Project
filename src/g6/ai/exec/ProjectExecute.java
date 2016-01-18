package g6.ai.exec;

import g6.ai.facade.AIFacade;

public class ProjectExecute {

	public static void main(String[] args) {
		AIFacade aiFacade = new AIFacade();
		//entry point of execute starts by invoking the control method on the Facade
		aiFacade.executeSOM();
	}

}
