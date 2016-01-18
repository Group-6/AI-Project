package g6.ai.vectors;


import constants.VectorConstants;

public class InputVectorCreationFactory {
	
	/**
	 * 
	 * @param creationType
	 * @return the appropriate instance for getting the instance for creating the vector
	 */
	public static IVectorCreation getVectorCreationInstance( VectorConstants.VECTOR_CREATION_TYPE creationType){
		IVectorCreation iVectorCreation = null;
		if(VectorConstants.VECTOR_CREATION_TYPE.COOCCURENCE_DIAGONAL == creationType){
			iVectorCreation = new InputVectorCooccurenceDiagonalCreation();
		} else if(VectorConstants.VECTOR_CREATION_TYPE.COOCCURENCE == creationType){
			iVectorCreation = new InputVectorPlainCooccurenceCreation();
		}  else if(VectorConstants.VECTOR_CREATION_TYPE.TERM_FREQUENCY == creationType){
			iVectorCreation = new InputVectorTfIdfCreation();
		}	
		return iVectorCreation;
	}


}
