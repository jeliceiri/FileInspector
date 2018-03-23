package java112.analyzer;
import java.util.*;
import java.io.*;
	/**
     *  TokenCountAnalyzer
	 *  This class creates the token count file
     *  @author Jill Eliceiri            
	 */
public class TokenCountAnalyzer implements Analyzer { 

   // Only allowed instance variables
    private Properties properties;
    private Map<String, Integer> tokenCounts;
  
    //empty constructor initializes tokenCounts
    public TokenCountAnalyzer(){
        tokenCounts = new TreeMap<String, Integer>();
    }
    //constructor with 1 parameter and calls default constructor
	public TokenCountAnalyzer(Properties properties) {
        this();		
        this.properties = properties;
	}
    /** This method writes out to a file the count of each token
     * @param inputFilePath the input file path
     */
    public void writeOutputFile(String inputFilePath) {

		try (PrintWriter out =
            new PrintWriter(new BufferedWriter
                (new FileWriter(properties.getProperty("output.dir") 
                + properties.getProperty("output.file.token.count"))))) 
            {
                //loop through the Map and write out the keys and values
                for (Map.Entry<String, Integer> entry:  getTokenCounts().entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    out.println(key + "\t" + value);
                }

		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("TokenCountAnalyzer: could not create file");
			fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            System.out.println("TokenCountAnalyzer: could not write to file.");
            ioException.printStackTrace();
        } catch (Exception exception) {
            System.out.println("TokenCountAnalyzer: there is a general exception.");
            exception.printStackTrace();
        }
	}
    /** This method adds the token to the Map and increments the count value
     * @param token the token to be processed
     */
	public void processToken(String token) {
        //check if tokenCounts TreeMap contains the word
		if (tokenCounts.containsKey(token)) {
            //place key and value into TreeMap (increment one to the value)
            tokenCounts.put(token, tokenCounts.get(token) + 1);

		} else {
			tokenCounts.put(token, 1);
		}
	}
    /** This method returns the tokenCounts Map
     * @return tokenCounts the Map to be returned
     */
    public Map<String, Integer> getTokenCounts() {
        return tokenCounts;
    }
}