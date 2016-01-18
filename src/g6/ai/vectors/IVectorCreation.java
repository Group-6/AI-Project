package g6.ai.vectors;

import java.util.List;
import java.util.Map;

import constants.VectorConstants;

public interface IVectorCreation {
	
	/**
	 * 
	 * creates the input vectors based on VECTOR_CREATION_TYPE and input data
	 * 
	 * 
	 * @param inputDataMap
	 * @param stemmedDataMap
	 * @param inputVectorDataMap
	 * @param templateVectors
	 * @param creationType
	 * @param fileName
	 */
	 void createInputVectors(Map<Integer, String> inputDataMap,Map<Integer, String> stemmedDataMap,
			Map<Integer, List<Double>> inputVectorDataMap,List<String> templateVectors, 
			VectorConstants.VECTOR_CREATION_TYPE creationType, String fileName);	

}
