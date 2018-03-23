package java112.analyzer;
import java.io.*;
import java.util.*;
import java112.utilities.*;
/**
 * Project 2 Advanced Java
 * AnalyzeFile
 * The main controlling class for the project
 * @author Jill Eliceiri
 */
public class AnalyzeFile implements PropertiesLoaderInterface {

    // constant for the valid number of command-line arguments.
    private static final int VALID_COMMAND_LINE_ARGS = 2;

    // instance variables
    private String inputFilePath;
    private ArrayList<Analyzer> analyzers;
    private Properties properties;   

    /** This method will call other methods to perform the following:
     *  check the correct number of arguments, load the properties, create the analyzer 
     *  instance variables, open and read the input file, and write
     *  the output files.
     *  
     * @param arguments the command line arguments
     */
    public void runAnalysis(String[] arguments) {
        boolean isCorrect = checkCorrectNumberArguments(arguments);
        if (isCorrect) {
        loadProperties(arguments);
        createAnalyzerInstanceVariables();
        openInputFile();
        writeAllOutputFiles();
        }
    }

    /**
     * This method will first test if the correct number of arguments have
     * been entered by the user when running the application.
     * It will assign the entered command line argument to the input path instance
     * variable.
     * @param arguments the command line arguments
     * @return boolean the value of correct number of arguments
     */
    public boolean checkCorrectNumberArguments(String[] arguments) {
        boolean isCorrectArgs = false;
        if (arguments.length != VALID_COMMAND_LINE_ARGS) {
            System.out.println("Enter exactly one valid file to be read");
            return isCorrectArgs;
            //System.exit(0); don't kill here - return boolean to runAnalysis
        } else {
            inputFilePath = arguments[0];
            isCorrectArgs = true;
            return isCorrectArgs;
        }
    }

    /**
     *  This method will load the properties object
     *  @param arguments the String array of command line arguments
     */ 
    public void loadProperties(String[] arguments){

        //assign user input to the variable that is the file to read in
        String propertiesFilePath = arguments[1]; 
        
        //load properties object before instantiating Analyzers
        properties = (loadProperties(propertiesFilePath));

    }

    /**
     *  This method will create an instance of each Analyzer class and assign
     *  them to their instance variables
     */
    public void createAnalyzerInstanceVariables() {

        //Instantiate the analyzers object
        analyzers = new ArrayList<Analyzer>();

        //New way to instantiate your Analyzers
        analyzers.add(new SummaryReport(properties));
        analyzers.add(new UniqueTokenAnalyzer(properties));
        analyzers.add(new BigWordAnalyzer(properties));
        analyzers.add(new TokenCountAnalyzer(properties));
        analyzers.add(new TokenSizeAnalyzer(properties));
        analyzers.add(new KeywordAnalyzer(properties));

    }

    /**
     *  This method will open the input file,loop through
     *  all the lines of the input file and call the process tokens method.
     */ 
    public void openInputFile() {

        try (BufferedReader input = new BufferedReader(new FileReader(inputFilePath))) {
            // loop through the input file one line at a time
            while (input.ready()) {
                String inputLine = "";
                String[] tokenArray = null;
                
                inputLine = input.readLine();
                tokenArray = inputLine.split("\\W+");
                processTokens(tokenArray);
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
     *  This method will generate individual tokens
     *  @param tokenArray the array of tokens
     */ 
    public void processTokens(String[] tokenArray) {
        //loop through the token array and check if it is empty
        for (int i = 0; i < tokenArray.length; i++) {  
            String token = tokenArray[i];
            if (!token.isEmpty()){
                //loop through analyzers object and call proccessToken() method on each one
                for (Analyzer object : analyzers) {
                    object.processToken(token);
                }  
            }
        }
    }
   /** This method create the output file paths and call the WriteOutputFile()
     * methods for each of the Analyzer objects.
     *  check the correct number of arguments, create the analyzer 
     *  instance variables, open and read the input file, and write
     *  the output files.
    */
    public void writeAllOutputFiles() {   

        for (Analyzer object : analyzers) {
			object.writeOutputFile(inputFilePath);
		}  
    }

}