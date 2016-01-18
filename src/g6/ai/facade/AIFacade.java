/**
 * 
 * Author : Prashant Patel and Monisha Lakshmipati
 * 
 * Description : Facade Design Pattern - presents simple interface of the complex subsystem like
 * 				 user input, vector file creation, Self-organizing map training and running commands
 * 				 by simply exposing the simple interfaces.
 * 				 
 * 
 */
package g6.ai.facade;

import g6.ai.adapter.FileAdapter;
import g6.ai.commands.CommandExecuter;
import g6.ai.inputs.ConsoleUserInput;
import g6.ai.vectors.IVectorCreation;
import g6.ai.vectors.InputVectorCreationFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constants.VectorConstants;

public class AIFacade {
	Map<Integer, List<Double>> inputVectorDataMap;
	Map<Integer, String> stemmedDataMap;
	Map<Integer, String> inputDataMap;
	List<String> templateVectors;

	public AIFacade() {
		// TODO Auto-generated constructor stub
		inputVectorDataMap = new HashMap<Integer, List<Double>>();
		stemmedDataMap = new HashMap<Integer,String>();
		inputDataMap = new HashMap<Integer, String>();
		templateVectors = new ArrayList<>();
	}

	public void executeSOM(){
		
		//take vector creation based user inputs
		int option = new ConsoleUserInput().getVectorCreationUserOption();
		
		//logic to create input vectors
		createVectors(option);

		//logic to write input vector and template vector files
		writeInputVectorAndTemplateVectorFiles();

		//logic to run SOM and display the SOMViewer
		executeSOMToolBoxCommands();
	}

	/**
	 * creating the input vectors
	 */
	public void createVectors(int option){

		VectorConstants.VECTOR_CREATION_TYPE vectorCreationType = VectorConstants.VECTOR_CREATION_TYPE_MAP.get(option);

		IVectorCreation iVectorCreation = InputVectorCreationFactory.getVectorCreationInstance(
				vectorCreationType);

		iVectorCreation.createInputVectors(inputDataMap, stemmedDataMap, inputVectorDataMap, templateVectors,
				VectorConstants.VECTOR_CREATION_TYPE.COOCCURENCE_DIAGONAL, 
				"input-data.xlsx");
		System.out.println(" =============== Input Vectors ===============");
		System.out.println(inputVectorDataMap);
		System.out.println(" =============== Template Vectors ===============");
		System.out.println(templateVectors);
	}

	/**
	 * writing data files
	 */
	public void writeInputVectorAndTemplateVectorFiles(){
		FileAdapter fileAdapter = new FileAdapter();
		fileAdapter.createInputAndTemplateVectorFiles(inputVectorDataMap, templateVectors); 
	}

	/**
	 *  execute the  commands of SOM Toolbox
	 */
	public void executeSOMToolBoxCommands(){
		CommandExecuter commandExecuter = new CommandExecuter();

		//run the command to create dwm file
		commandExecuter.runGrowingSOMAndSOMViewerCommandForSOMToolbox();

		//run the somviewer command to show the visualization
		//commandExecuter.runSOMViewerCommandForSOMToolbox();
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
