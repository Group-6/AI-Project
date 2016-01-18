/**
 * 
 * Author : Prashant Patel and Monisha LakshmiPathi
 * 
 * Description : Create the Co-occurence based on adjacent terms and individual terms.
 * 
 */


package g6.ai.vectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import g6.ai.adapter.FileAdapter;
import g6.ai.stemmer.WordStemmer;
import helper.GenericHelper;
import constants.StemConstants;
import constants.VectorConstants;

public class InputVectorCooccurenceDiagonalCreation implements IVectorCreation {
	FileAdapter fileAdapter;

	public InputVectorCooccurenceDiagonalCreation() {
		// TODO Auto-generated constructor stub
		fileAdapter = new FileAdapter();
	}

	@Override
	public void createInputVectors(Map<Integer, String> inputDataMap,Map<Integer, String> stemmedDataMap,
			Map<Integer, List<Double>> inputVectorDataMap,List<String> templateVectors, VectorConstants.VECTOR_CREATION_TYPE creationType, String fileName){		

		inputDataMap = fileAdapter.getDataMapFromInputExcelFile(fileName);
		
		
		createStemmedTextAndGenerateVectors(inputDataMap, stemmedDataMap, inputVectorDataMap, templateVectors);

	}

	/**
	 * 
	 * create Cooccurences 
	 * 
	 * @param inputDataMap
	 * @param stemmedDataMap
	 * @param inputVectorDataMap
	 */
	public void createStemmedTextAndGenerateVectors(Map<Integer, String> inputDataMap,Map<Integer, String> stemmedDataMap,
			Map<Integer, List<Double>> inputVectorDataMap, List<String> templateVectors){

		List<String> totalSelectedWords = new ArrayList<String>();		

		for(Map.Entry<Integer, String> dataMap : inputDataMap.entrySet()){

			List<String> selectedWordsForEachDocument = new ArrayList<String>();

			Integer key = dataMap.getKey();

			String data = dataMap.getValue();

			//stem each word for this text 
			storeStemmedDataIntoList(data, totalSelectedWords, selectedWordsForEachDocument);

			//create the stemmed word text for 
			String stemmedText = GenericHelper.convertListToString(selectedWordsForEachDocument);

			stemmedDataMap.put(key, stemmedText);
		}

		createInputVectorFromCooccurences(totalSelectedWords, stemmedDataMap, inputVectorDataMap, templateVectors);
	}

	/**
	 * 
	 * @param totalSelectedWords
	 * @param stemmedDataMap
	 * @param inputVectorDataMap
	 * @param templateVectors
	 */
	public void createInputVectorFromCooccurences(List<String> totalSelectedWords, Map<Integer, String> stemmedDataMap,
			Map<Integer, List<Double>> inputVectorDataMap, List<String> templateVectors){
		//create the template Vectors
		createTemplateVectors(totalSelectedWords, templateVectors);

		//iterate every stemmed text to create the Input Vector
		for(Map.Entry<Integer, String> entry : stemmedDataMap.entrySet()){

			Integer key = entry.getKey();

			String stemmedInputString = stemmedDataMap.get(key);

			//input vector
			List<Double> vector = new ArrayList<Double>();

			//iterate through the template vector to check for occurrence
			for(String dimension : templateVectors){

				//if the dimension exists in the input vector string
				if(stemmedInputString.indexOf(dimension) != -1){
					//store the occurence of the dimension
					vector.add(new Double(GenericHelper.getCountOfOccurences(stemmedInputString, dimension)));
				} else {
					vector.add(new Double(0));
				}

			}

			//store this data into input vector Map
			inputVectorDataMap.put(key, vector);
		}


	}


	/**
	 * creating the template 
	 * 
	 * @param totalSelectedWords
	 * @param templateVectors
	 */
	public void createTemplateVectors(List<String> totalSelectedWords, List<String> templateVectors){

		int index = 0 ;

		while(index < totalSelectedWords.size()){
			String stemmedWord  = totalSelectedWords.get(index);

			templateVectors.add(stemmedWord);

			// if the next index leads to the end of the String
			if(index != totalSelectedWords.size() -1 ){
				String nextStemmedWord = totalSelectedWords.get(index + 1 );

				templateVectors.add(stemmedWord+" "+nextStemmedWord);
			}

			//incrementing the  index
			index++;

		}

	}

	/**
	 * stem each word from the document
	 * 
	 * @param data
	 * @param totalSelectedWords
	 * @param selectedWordsForEachDocument
	 */
	public void storeStemmedDataIntoList(String data, List<String> totalSelectedWords,
			List<String> selectedWordsForEachDocument ){
		//split by simple space, we will then remove stopwords
		String[] dataArray = data.split("\\s");

		//filtering the text for stop words
		for(String keyword : dataArray){				
			//if it is not in the list of stopwords
			if(!StemConstants.STOP_WORDS_LIST.contains(keyword)){
				//create the stemmer
				WordStemmer wordStemmer = new WordStemmer();
				String stemmedWord = wordStemmer.getStemmedWord(keyword);
				if(!totalSelectedWords.contains(stemmedWord)){
					totalSelectedWords.add(stemmedWord);
				}
				selectedWordsForEachDocument.add(stemmedWord);
			}
		}

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
