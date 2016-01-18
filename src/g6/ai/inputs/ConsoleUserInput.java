package g6.ai.inputs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserInput {
	
	public int getVectorCreationUserOption(){
		//option 1 by default
		int selectedOption = 1;
		
		System.out.println(" Enter 1 to create vectors by Co-occurence plus individual terms");
		System.out.println(" Enter 2 to create vectors by 2-level Co-occurence  terms");
		System.out.println(" Enter 3 to create vectors by Term frequency - inverse document frequency");		
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String inputString = br.readLine();
			selectedOption = Integer.parseInt(inputString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(" Class : ConsoleUserInput, Method : getVectorCreationUserOption "
					+ " Exception while taking choice inputs from the user :  "+e);
			e.printStackTrace();
		};
		
		return selectedOption;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
