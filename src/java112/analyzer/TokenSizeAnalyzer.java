package java112.analyzer;
import java.util.*;
import java.io.*;

	/**
     *  TokenSizeAnalyzer. 
     *  This analyzer will determine the size distribution of the tokens in the input file.
     *  @author Jill Eliceiri
	 */
public class TokenSizeAnalyzer implements Analyzer {

    //instance variables
    private Map<Integer, Integer> tokenSizes;
    private Properties properties;
    private int maximumSize;

    //no arg constructor
    public TokenSizeAnalyzer(){
    //The length of each token will be the key of the Map.
    //The value of the Map will hold the number of tokens with the key’s length.
        tokenSizes = new TreeMap<Integer, Integer>(); 
    }

    //constructor with properties parameter
    public TokenSizeAnalyzer(Properties properties){
        this();
        this.properties = properties;
    }
   /** This method looks at the length of each token length.
     * If the length exists already in the tokenSizes Map, then
     * it will increment of times that it occurs.
     * If not, then it will set it at one.
     * @param token the token to be processed
    */
    public void processToken(String token){
        //get the token length, 
        int length = token.length();

        //check if this length is in the treemap
        if (getTokenSizes().containsKey(length)){
            //increment the count
            tokenSizes.put(length, tokenSizes.get(length) + 1);
        } else {
            tokenSizes.put(length, 1); 
        }
    }
    /**
     *  This method returns the tokenSizes
     *  @return tokenSizes the Map of tokenSizes
     */ 

    public Map<Integer, Integer> getTokenSizes() {
        return tokenSizes;
    }

    /**
     *  This method returns the tokenSizes
     *  @return maximumSize the maximum size
     */     
    public int getMaximumSize() {
        return maximumSize;
    }

   /** This method will create a PrintWriter object and call two other methods
     * to print the results.
     * @param inputFilePath the input file path
     */
    public void writeOutputFile(String inputFilePath) {

		try (PrintWriter out = new PrintWriter(new BufferedWriter
                (new FileWriter(properties.getProperty("output.dir")
				+ properties.getProperty("output.file.token.size"))))) {

                //set the maximum size
                maximumSize = Collections.max(getTokenSizes().values());              

                printTokenSizes(out);
                printAstericks(out);                     
            } catch (FileNotFoundException fileNotFoundException) {
			System.out.println("Could not create file TokenSizeAnalyzer writeoutputFiles.");
			fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            System.out.println("Could not write to file TokenSizeAnalyzer writeoutputFiles.");
            ioException.printStackTrace();
        } catch (Exception exception) {
            System.out.println("There was a problem TokenSizeAnalyzer writeoutputFiles.");
            exception.printStackTrace();
        }
    }
   /** This method will print the sizes of tokens and the number of tokens that are that size
     * It will loop through the set view of the keys in the map.
     * @param out the PrintWriter object
     */
    public void printTokenSizes(PrintWriter out){
        for (Integer key:getTokenSizes().keySet()) {
            //method is used to return the value to which the specified key is mapped
            out.println(key + "\t" + getTokenSizes().get(key));
        }
        out.println();
    }
   /** This method will display a histogram of the results where each line 
     * doesn’t exceed 80 characters. It always shows at least one “*”
     * @param out the PrintWriter object
     */
    public void printAstericks(PrintWriter out){
        String asterisk = "*";
        double topNumberAstericks = .80;
        
        for(Map.Entry<Integer,Integer> entry : getTokenSizes().entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            //set the number of asterisk to print, use ratio of maximum size
            double numAstericksToPrint = ((Math.ceil((double)value / maximumSize * 100))*.8);
            out.print(key + " ");
            //print an asterisk if there aren't any
            if (numAstericksToPrint < 1){
                out.print(asterisk);
            } 
            //loop through the number of asteriks
            for (double i = 1; i <= numAstericksToPrint; i++){
                out.print(asterisk);
            } 
            out.println();
        }                       
    }
}


