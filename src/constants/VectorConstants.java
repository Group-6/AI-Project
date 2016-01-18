package constants;

import java.util.HashMap;

public class VectorConstants extends GenericConstants{


	// different type of methods related to vector creation
	public static  enum VECTOR_CREATION_TYPE {COOCCURENCE, COOCCURENCE_DIAGONAL,TERM_FREQUENCY};
	
	//mappings of option and vector creation type
	public static final HashMap<Integer,VECTOR_CREATION_TYPE> VECTOR_CREATION_TYPE_MAP = new HashMap<Integer,VECTOR_CREATION_TYPE>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(1, VECTOR_CREATION_TYPE.COOCCURENCE_DIAGONAL);
			put(2, VECTOR_CREATION_TYPE.COOCCURENCE);
			put(3, VECTOR_CREATION_TYPE.TERM_FREQUENCY);

		}};
	

}
