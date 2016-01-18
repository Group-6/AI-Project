/**
 * 
 */
package g6.ai.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constants.FileConstants;
import g6.ai.file.ExcelSheetReader;
import g6.ai.file.FileFactory;
import g6.ai.file.IFile;
import g6.ai.file.InputVectorFileWriter;
import g6.ai.file.TemplateVectorFileWriter;

/**
 * @author prashant
 *
 */
public class FileAdapter {

	/**
	 * 
	 * @param fileName
	 * @return the input file data in the form of the Map - documentNumber : document Data
	 */
	public Map<Integer, String> getDataMapFromInputExcelFile(String fileName){
		Map<Integer,String> dataMap = new HashMap<Integer, String>();

		IFile inputFile = FileFactory.getFileInstance(FileConstants.EXCEL_FILE);

		//read the excel File and process the data
		inputFile.readFromFile("input-data.xlsx");

		if(inputFile instanceof ExcelSheetReader){
			dataMap.putAll(((ExcelSheetReader) inputFile).getDocWordMap());
		}

		return dataMap;
	}

	/**
	 * 
	 * @param inputVectorDataMap
	 * @param templateVectors
	 */
	public void createInputAndTemplateVectorFiles(Map<Integer, List<Double>> inputVectorDataMap,
			List<String> templateVectors){
		//creating and writing into input vector file
		//getting the file instance
		IFile inputVectorFile = FileFactory.getFileInstance(FileConstants.INPUT_VECTOR_FILE_WRITER);
		if(inputVectorFile instanceof InputVectorFileWriter){
			InputVectorFileWriter inputVectorFileWriter = (InputVectorFileWriter)inputVectorFile;
			inputVectorFileWriter.setInputValuesMap(inputVectorDataMap);			
			inputVectorFileWriter.writeIntoFile(FileConstants.INPUT_VECTOR_FILE_NAME);
		}

		//creating and writing into template vector file
		//getting the file instance
		IFile templateVectorFile = FileFactory.getFileInstance(FileConstants.TEMPLATE_VECTOR_FILE_WRITER);
		if(templateVectorFile instanceof TemplateVectorFileWriter){
			TemplateVectorFileWriter templateVectorFileWriter = (TemplateVectorFileWriter)templateVectorFile;
			templateVectorFileWriter.setTemplateVector(templateVectors);	
			templateVectorFileWriter.writeIntoFile(FileConstants.TEMPLATE_VECTOR_FILE_NAME);
		}

	}

}
