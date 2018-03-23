package java112.analyzer;
	/**
     *  Analyzer Interface
	 *  This interface is named Analyzer and is implemented by any class 
     *  that performs an analysis and has two methods. 
     *  @author Jill Eliceiri            
	 */
public interface Analyzer {

    /** The method to process a token. 
     * @param token a String token
     */
	void processToken(String token);

    /** The method to write an output file.
     * @param inputFilePath the input file path
     */
    void writeOutputFile(String inputFilePath);
}