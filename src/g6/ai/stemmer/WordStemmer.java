package g6.ai.stemmer;

public class WordStemmer{


	public String getStemmedWord(String word){
		Stemmer stemmer = new Stemmer();
		// iterate through the each character in the word
		for(int i = 0 ; i < word.length() ; i++){
			stemmer.add(word.charAt(i));
		}	

		stemmer.stem();

		return stemmer.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordStemmer wordStemmer = new WordStemmer();
		
		System.out.println(wordStemmer.getStemmedWord(("frustration")));
		System.out.println(wordStemmer.getStemmedWord(("frustrated")));
		System.out.println(wordStemmer.getStemmedWord(("frustrating")));


	}

}
