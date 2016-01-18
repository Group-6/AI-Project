package g6.ai.vectors;

import g6.ai.adapter.FileAdapter;
import g6.ai.stemmer.WordStemmer;
import helper.GenericHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import constants.StemConstants;
import constants.VectorConstants;

public class InputVectorTfIdfCreation implements IVectorCreation{

	FileAdapter fileAdapter;

	public InputVectorTfIdfCreation() {
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
		
		StringBuffer wholeDocumentString = new StringBuffer("");

		for(Map.Entry<Integer, String> dataMap : inputDataMap.entrySet()){

			List<String> selectedWordsForEachDocument = new ArrayList<String>();

			Integer key = dataMap.getKey();

			String data = dataMap.getValue();			

			//stem each word for this text 
			storeStemmedDataIntoList(data, totalSelectedWords, selectedWordsForEachDocument);

			//create the stemmed word text for 
			String stemmedText = GenericHelper.convertListToString(selectedWordsForEachDocument);
			
			//collect these in a single String Buffer object for idf calculation
			wholeDocumentString.append(stemmedText).append(" ");

			stemmedDataMap.put(key, stemmedText);
		}

		createInputVectorWithTfIdf(totalSelectedWords, stemmedDataMap, inputVectorDataMap, templateVectors, wholeDocumentString);
	}

	/**
	 * 
	 * @param totalSelectedWords
	 * @param stemmedDataMap
	 * @param inputVectorDataMap
	 * @param templateVectors
	 * @param wholeDocumentString
	 */
	public void createInputVectorWithTfIdf(List<String> totalSelectedWords, Map<Integer, String> stemmedDataMap,
			Map<Integer, List<Double>> inputVectorDataMap, List<String> templateVectors, StringBuffer wholeDocumentString){
		//create the template Vectors
		createTemplateVectors(totalSelectedWords, templateVectors);
		double tf = 0, idf = 0, tfIdf = 0;
		
		int totalNoOfDocuments = stemmedDataMap.size();

		//iterate every stemmed text to create the Input Vector
		for(Map.Entry<Integer, String> entry : stemmedDataMap.entrySet()){

			Integer key = entry.getKey();

			String stemmedInputString = stemmedDataMap.get(key);
			

			//input vector
			List<Double> vector = new ArrayList<Double>();

			//iterate through the template vector to check for occurrence
			for(String dimension : templateVectors){

				//if the dimension exists in the input vector string
				if(wholeDocumentString.toString().indexOf(dimension) != -1){
					//calculation tf - term frequency
					//using space as delimiter - count no of words in a document
					int totalNoOfWordsInDocument = GenericHelper.getCountOfOccurences(stemmedInputString, " ");
					//frequency of the word in the document
					int frequencyOfStemmedWordInThisDocument = GenericHelper.getCountOfOccurences(stemmedInputString, dimension);
					//term frequency = totalNoOfWordsInDocument * frequencyOfStemmedWord;
					tf = (double)frequencyOfStemmedWordInThisDocument / totalNoOfWordsInDocument;
					
					//calculation idf - inverse document frequency
					int frequencyOfStemmedWordInAllDocuments  = GenericHelper.getCountOfOccurences(
							wholeDocumentString.toString(), dimension);
					//inverse document frequency = totalNoOfDocuments / frequencyOfStemmedWordInAllDocuments;
					idf = Math.log( (double)totalNoOfDocuments/frequencyOfStemmedWordInAllDocuments);
					
					//calculating tf-idf
					tfIdf = tf*idf;
					//store the  tf-idf of the dimension
					if(tfIdf != Double.NaN){
						vector.add(tfIdf);
					} else {
						vector.add(new Double(0));
					}
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

			//it deals with adding only the individual texts
			templateVectors.add(stemmedWord);

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
