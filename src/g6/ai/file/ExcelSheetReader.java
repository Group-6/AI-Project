package g6.ai.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetReader implements IFile{

	// map of integers and documents
	private Map<Integer, String> documentMap;

	public ExcelSheetReader() {
		//initializing the Map
		documentMap = new HashMap<Integer, String>();
	}

	private XSSFWorkbook workbook;

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub
		try{

			//list of word Index and word comment
			Map<Integer, String> documentMap = this.getDocWordMap();

			// wrapping file in FileInputStream because few Tomcat had 
			// problems with the java.io.File support
			FileInputStream fis = new FileInputStream(new File(fileName));

			workbook = new XSSFWorkbook(fis);

			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//iterating through each row in the  sheet 
			Iterator<Row> rows  = sheet.iterator();

			//dcoument Index
			int documentIndex = 0 ;

			//iterating each row
			while(rows.hasNext()){

				//get each row
				Row row = rows.next();

				if(row.getRowNum() == 0){
					continue;
				} else {
					// get the first cell Cell
					Cell cell = row.getCell(0);

					//set cell value
					String cellValue = getCellValue(cell);		

					//store the document by documentNumber
					documentMap.put(documentIndex++, cellValue);
				}
			}

			workbook.close();

		} catch(Exception e){
			System.out.println(" Exception in writeIntoFile method of ExcelSheetReader : "+e);
			e.printStackTrace();
		} finally {

		}
	}

	@Override
	public void writeIntoFile(String fileName) {

	}

	/**
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellValue(Cell cell){
		String cellValue = "";
		if(cell != null){
			switch(cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = cell.getBooleanCellValue()+"";

				break;
			case Cell.CELL_TYPE_NUMERIC:        	
				cellValue = cell.getNumericCellValue() + "";
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue() + "";
				break;
			}
		}
		return cellValue;
	}

	//gets the document Map
	public Map<Integer,String> getDocWordMap(){
		return documentMap;
	}

}
