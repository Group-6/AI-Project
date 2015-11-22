package g6.ai.singleton;

import java.util.HashMap;
import java.util.Map;

public class DataSingleton {

	public Map<Integer, String> docWordMap = new HashMap<Integer, String>();

	private static  DataSingleton instance;

	private DataSingleton(){

	}

	public static DataSingleton getDataSingleton(){
		if(instance == null){
			instance =  new DataSingleton();

		} 
		return instance;

	}


}
