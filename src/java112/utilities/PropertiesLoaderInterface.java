package java112.utilities;

import java.io.*;
import java.util.*;

/**
 * Project 1 Advanced Java
 * PropertiesLoaderInterface
 * The interface that loads the properties file
 * @author Paula Waite
 */

public interface PropertiesLoaderInterface {

    /**
     *  This default method will load the properties using the properties file to be read
     *  @param propertiesFilePath the properties file path
     *  @return properties the properties object
     */ 

    default Properties loadProperties(String propertiesFilePath)  {
        
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch(IOException ioException) {
            System.out.println("Can't load the properties file" + ioException);
            ioException.printStackTrace();
            return null;
        } catch(Exception exception) {
            System.out.println("Problem: " + exception);
            exception.printStackTrace();
            return null;
        }
        return properties;
    }
}