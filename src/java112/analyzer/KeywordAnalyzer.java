package java112.analyzer;
import java.util.*;
import java.io.*;
import java.util.*;
import java112.utilities.*;

	/**
     *  KeywordAnalyzer
     *  This class determines where keywords are in the input file     
     *  @author Jill Eliceiri
	 */
public class KeywordAnalyzer implements Analyzer {

    //instance variables
    private Map<String, List<Integer>> keywordMap;
    private Properties properties;
    private int tokenOccurence;

    //no arg constructor
    public KeywordAnalyzer(){
        keywordMap = new TreeMap<String, List<Integer>>();
    }

    //constructor with Properties parameter
    public KeywordAnalyzer(Properties properties){
        this();
        this.properties = properties;
        //initialize the place where tokens occur to be 1
        tokenOccurence = 1;

        //load a file of keywords, parse the file, call the loadMap 
        try (BufferedReader input = new BufferedReader
            (new FileReader(properties.getProperty("file.path.keywords")))){
                while(input.ready()){
                String inputLine = "";
                String[] tokenArray = null;
                
                inputLine = input.readLine();
                tokenArray = inputLine.split("\\W+");
                loadMap(tokenArray);
                }                                                                                            
        } catch (java.io.FileNotFoundException fileNotFound) {
            System.out.println("The file not found");
            fileNotFound.printStackTrace();
        } catch (IOException ioException) {
            System.out.println("There was a problem opening the file.");
            ioException.printStackTrace();
        } catch (Exception exception) {
            System.out.println("There was a General Exception Error");
            exception.printStackTrace();
        }
    }
    
    /**
     *  This method returns keyWordMap
     *  @return keywordMap the Map of key words
     */   
    public Map<String, List<Integer>> getKeywordMap() {
        return keywordMap;
    }
    /**
     *  This method loops through the String [] of tokens, initializes an
     *  empty ArraList, and puts the token and ArrayList into the keywordMap
     *  @param tokenArray the String[] of tokens
     */  
    public void loadMap(String[] tokenArray){
        for (int i = 0; i < tokenArray.length; i++) {  
            //grab the current token
            String token = tokenArray[i];
            
            ArrayList<Integer> myArrayList = new ArrayList<Integer>();
            //put the token and myArrayList into the keywordMap
            keywordMap.put(token, myArrayList);
        }
    }
    /**
     *  This method checkes to see if the token is one of the kewords that
     *  were preloaded. If it is, then the number positions of the token in the
     *  input file will be added to the List associated with the keyword in the Map.
     *  @param token the String token
     */  
    public void processToken(String token) {
		if (getKeywordMap().containsKey(token)) {
            //map.get(id).add(value); //adds value to list.
            getKeywordMap().get(token).add(tokenOccurence);
        }
        //increment token occurence
        tokenOccurence++;
	}

   /** This method will write the output file
     * @param inputFilePath the input file path
     */
    public void writeOutputFile(String inputFilePath) {

		try (PrintWriter out = new PrintWriter(new BufferedWriter
                (new FileWriter(properties.getProperty("output.dir")
				+ properties.getProperty("output.file.keyword"))))) {

                //loop through the treemap
                for (Map.Entry<String, List<Integer>> entry : keywordMap.entrySet()) {
                    //get the key
                    String key = entry.getKey();
                    //put the values into a list
                    List<Integer> value = entry.getValue();
                    //print the key
                    out.println(key + " =");
                    out.print("[");
                    //loop through the list of values
                    for (int i = 0; i < value.size(); i++){

                        //if last one in list, print close bracket
                        if (i == value.size() - 1){
                            out.print(value.get(i) + "]" + "\n");
                        }
                        //if not last one in list, print comma
                        if (i != value.size() - 1){
                            out.print(value.get(i) + ", ");
                        }
                        //if divisible by 9 or not the first one, print new line
                        if ((i % 9 == 0) && (i != 0)){
                            
                            out.print("\n");
                        } 
                                             
                    }
                    //if the list is empty, print the closing bracket
                    if (value.isEmpty()){
                            out.print("]" + "\n");
                    }
                    // + "]" + "\n");
                    //out.print("]");
                    out.println();
                }               

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Could not create file uniqueToken writeoutputFiles.");
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                System.out.println("Could not write to file uniqueToken writeoutputFiles.");
                ioException.printStackTrace();
            } catch (Exception exception) {
                System.out.println("There was a problem uniqueToken writeoutputFiles.");
                exception.printStackTrace();
            }
    }
}


