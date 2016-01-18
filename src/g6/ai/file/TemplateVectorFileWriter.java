package g6.ai.file;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TemplateVectorFileWriter implements IFile{
	List<String> templateVector;

	public TemplateVectorFileWriter() {
		// TODO Auto-generated constructor stub
		templateVector = new ArrayList<String>();
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
			writer.println("$TYPE template");
			writer.println("$XDIM 2");
			writer.println("$VEC_DIM "+ templateVector.size());

			int i;
			for( i = 0 ; i < templateVector.size(); i++){

				String word = templateVector.get(i);

				word = word.replace(" ", "-");

				writer.println(i+ " "+word);
			}

			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<String> getTemplateVector() {
		return templateVector;
	}

	public void setTemplateVector(List<String> templateVector) {
		this.templateVector = templateVector;
	}

}
