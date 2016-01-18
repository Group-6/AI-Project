package g6.ai.commands;

import constants.CommandLineConstants;


public class CommandExecuter {
	
	public void runGrowingSOMAndSOMViewerCommandForSOMToolbox(){
		//executing the command to create DWM Files
		try{
			String command = CommandLineConstants.RUN_GROWING_SOM_COMMAND;			
			Process p = Runtime.getRuntime().exec(command);	
			p.waitFor();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public void runSOMViewerCommandForSOMToolbox(){
		//executing the command to create DWM Files
		try{
			String command = CommandLineConstants.RUN_SOM_VIEWER_COMMAND;			
			Process p = Runtime.getRuntime().exec(command);	
			p.waitFor();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
