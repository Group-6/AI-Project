package helper;

import java.util.List;

public class GenericHelper {
	
	/**
	 * 
	 * @param str
	 * @param findStr
	 * @return
	 */
	public static int getCountOfOccurences(String str, String findStr){

		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

		    lastIndex = str.indexOf(findStr,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += findStr.length();
		    }
		}
		return count;
	}
	
	/**
	 * 
	 * @param list
	 * @return string by converting the ArrayList to string for stemmed data
	 */
	public static String convertListToString(List<String> list){
		StringBuilder sb = new StringBuilder();
		for (String s : list)
		{
		    sb.append(s);
		    sb.append(" ");
		}
		
		return sb.toString();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
