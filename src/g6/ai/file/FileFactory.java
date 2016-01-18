package g6.ai.file;

import constants.FileConstants;

public class FileFactory {


public static IFile getFileInstance( String fileType){
	IFile file = null;
	if(FileConstants.EXCEL_FILE.equals(fileType )){
		file = new ExcelSheetReader();
	} else if(FileConstants.INPUT_VECTOR_FILE_WRITER.equals(fileType )){
		file = new InputVectorFileWriter();
	} else if(FileConstants.TEMPLATE_VECTOR_FILE_WRITER.equals(fileType )){
		file = new TemplateVectorFileWriter();
	}	
	return file;
}





}
