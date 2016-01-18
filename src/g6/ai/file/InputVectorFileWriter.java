package g6.ai.file;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputVectorFileWriter implements IFile{
	Map<Integer, List<Double>> inputValuesMap ;
	
	InputVectorFileWriter(){
		inputValuesMap = new HashMap<Integer, List<Double>>();
	}

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeIntoFile(String fileName) {
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.println("$TYPE vec_tfxidf");
			writer.println("$XDIM "+inputValuesMap.size());
			writer.println("$YDIM 1");
			
			//get the number of dimensions
			List<Double> inputVectorSample = inputValuesMap.get(0);
			int size = inputVectorSample.size();
			
			writer.println("$VEC_DIM "+ size);

			for(Map.Entry<Integer, List<Double>> vectorEntry : inputValuesMap.entrySet()){
				int documentNumber = vectorEntry.getKey();
				List<Double> inputVector = vectorEntry.getValue();
				if(inputVector != null &&  ! inputVector.isEmpty()){
					for(Double i : inputVector){
						writer.print(i+" ");
					}
					writer.print("Document_Number_"+documentNumber);
					writer.println();
				}

			}
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public Map<Integer, List<Double>> getInputValuesMap() {
		return inputValuesMap;
	}

	public void setInputValuesMap(Map<Integer, List<Double>> inputValuesMap) {
		this.inputValuesMap = inputValuesMap;
	}

}
