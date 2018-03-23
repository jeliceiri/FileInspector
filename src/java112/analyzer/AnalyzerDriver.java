package java112.analyzer;
/**
 * AnalyzerDriver
 * The project consists of a command line application which will process a text 
 * file and output a series of reports on the contents of the file. 
 * @author Jill Eliceiri
 */
public class AnalyzerDriver {
    /** main method to run the Analyzer application
     * The class will instantiate an instance of the project’s main processing class.
     * will call the main processing method of the main class passing the command 
     * line arguments array to the method.
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        AnalyzeFile analyzeFile = new AnalyzeFile(); 
        analyzeFile.runAnalysis(args);
    }
}