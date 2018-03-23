package java112.analyzer;
import java.util.*;
import java.io.*;
/**
 * Project 2 Advanced Java
 * BigWordAnalyzer
 * @author Jill Eliceiri
 */
public class BigWordAnalyzer implements Analyzer {

    //instance variables
	private Properties properties;
	private Set<String> bigWords;
	private int minimumWordLength;

    //empty constructor
    public BigWordAnalyzer(){
        bigWords = new TreeSet<String>();
    }
    //constructor with properties parameter
	public BigWordAnalyzer(Properties properties) {
        this();
        this.properties = properties;
        //set the minimum length from the properties object
        minimumWordLength = Integer.parseInt(properties.getProperty("bigwords.minimum.length"));
	}
    /** This method writes out to a big word analyzer file
     * @param inputFilePath the input file path
     */
	public void writeOutputFile(String inputFilePath) {
		try (PrintWriter out =
				new PrintWriter(new BufferedWriter(
				new FileWriter(properties.getProperty("output.dir") 
                    + properties.getProperty("output.file.bigwords"))))) {
            //loop through the big words set and print out each word
			for (String word : getBigWords()) {
				out.println(word);
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("BigWordAnalyzer: Could not create file.");
			fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            System.out.println("BigWordAnalyzer: Could not write to file.");
            ioException.printStackTrace();
        } catch (Exception exception) {
            System.out.println("BigWordAnalyzer: general exception.");
            exception.printStackTrace();
        }
	}
    /** This method processes the tokens and adds them to the big words set 
     * @param token the token to be processed
     */
	public void processToken(String token) {
		if (token.length() >= minimumWordLength) {
			bigWords.add(token);
		}
	}
    /** This method returns the big words set 
     * @return bigWords the big words set
     */
	public Set<String> getBigWords() {
		return bigWords;
	}
}