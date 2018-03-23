package java112.analyzer;
import java.util.*;
import java.io.*;

	/**
     *  UniqueTokenAnalyzer
	 *  This class creates the unique token report
     *  @author Jill Eliceiri
	 */
public class UniqueTokenAnalyzer implements Analyzer {

    //instance variables
    private Set uniqueTokensList;
    private Properties properties;

    // Empty constructor example
	public UniqueTokenAnalyzer() {
	}

    // Constructor with one Properties parameter
    public UniqueTokenAnalyzer(Properties properties) {
        this.properties = properties;
        //System.out.println("Here are the properties inside the unique token analyzer: " + properties);
        uniqueTokensList = new TreeSet<String>();
    }

    /** This method returns the unique tokens list
     * @return the unique tokens list
     */  
    public Set getUniqueTokensList() {
        return uniqueTokensList;
    }
    /** This method adds a unique token to the token list
     * @param token the String token
     */ 
    public void processToken(String token) {
		//getUniqueTokensList().add(token);
        uniqueTokensList.add(token);
	}

    /** This method will write the output file
     * @param inputFilePath the input file path
     */
    public void writeOutputFile(String inputFilePath) {

		try (PrintWriter out = new PrintWriter(new BufferedWriter
                (new FileWriter(properties.getProperty("output.dir")
				+ properties.getProperty("output.file.unique"))))) {

            Set<String> list = new TreeSet<String>();
            list = getUniqueTokensList();
            //System.out.println(list);
			for (String token : list) {
				out.println(token);
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("Could not create file tokenSize writeoutputFiles.");
			fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            System.out.println("Could not write to file tokenSize writeoutputFiles.");
            ioException.printStackTrace();
        } catch (Exception exception) {
            System.out.println("There was a problem tokenSize writeoutputFiles.");
            exception.printStackTrace();
        }
	}
}

