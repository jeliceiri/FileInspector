package java112.analyzer;
import java.util.*;
import java.io.*;
/**
 *  SummaryReport
 *  This class creates the summary report
 *  @author Jill Eliceiri
 */
public class SummaryReport implements Analyzer {
    
	private int totalTokensCount;
    private Properties properties;

    // Empty constructor example
    public SummaryReport() {
    }

    // Constructor with one Properties parameter
    public SummaryReport(Properties properties) {
        this();
        this.properties = properties;
    }

    /** This method adds a token to the total token count.
     * @param token the token to add
     */
	public void processToken(String token) {
		totalTokensCount++;
    }
    /** This method creates an output summary file.
     * @param in the input file
     */
	public void writeOutputFile(String in) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter
            (new FileWriter(properties.getProperty("output.dir")
            + properties.getProperty("output.file.summary"))))){

            File inputFile = new File(in);
            java.util.Date date=new java.util.Date();
            out.println("Application Name: " + properties.getProperty("application.name"));
            out.println("Author: " + properties.getProperty("author"));
            out.println("Email: " + properties.getProperty("author.email.address"));
            out.println("Input file: " + inputFile.getAbsolutePath());
            out.println("Analyzed on: " + date);
            out.println("Total token count: " + getTotalTokensCount());

		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("There was a problem creating the file Summary writeoutputFiles.");
			fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            System.out.println("There was a problem writing to the file Summary writeoutputFiles.");
            ioException.printStackTrace();
        } catch (Exception exception) {
            System.out.println("There was some other problem in Summary writeoutputFiles.");
            exception.printStackTrace();
        }
	}

	/**
	 * Gets the current token count total for this report.
	 * @return totalTokensCount the token count total
	 */
	public int getTotalTokensCount() {
		return totalTokensCount;
	}
}