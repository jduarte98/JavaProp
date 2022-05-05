package joaoduarte.samples.javadatprop;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author João Duarte (LinkedIn: www.linkedin.com/in/joão-duarte-453bb9199 | GitHub: https://github.com/jduarte98)
 */
public class PropertiesFilesManager {
    private Properties prop = new Properties();

    /**
     * Create Properties File
     * @param aFileName File Name
     * @param aFilePath File Path (insert without the / at the end)
     * @return True if the creation is sucessfully, false if not.
     */
    public boolean createPRPFile(String aFileName, String aFilePath) {
        try {
            new FileOutputStream(new File(aFilePath + "/" + aFileName + ".properties"));
            return true;
        } catch (IOException ex) {
            System.out.println("   !!! ERROR WHILE CREATING PROPERTIES FILE, please try again !!!");
            return false;
        }catch(Exception e){
            System.out.println("   !!! ERROR WHILE CREATING PROPERTIES FILE, please try again !!!");
            return false;
        }
    }

    /**
     * Write Properties on Properites File
     * @param aFile Comple File Path
     * @param aPropName Propertie Key
     * @param aPropValue Propertie Value
     */
    public void savePropOnPropF(String aFile, String aPropName, String aPropValue){
        try{
            prop.setProperty(aPropName, aPropValue);
            prop.store(new FileOutputStream(aFile),null);
        }catch (Exception e){
            System.out.println("   !!! ERROR WHILE SAVING PROPERTIE, please try again !!!");
        }
    }

    /**
     * Print contents of Propertie file on screen
     * @param aFilename Complete Pripertie File Path
     */
    public void printAll(String aFilename) {
        try  {
            prop.load(new FileInputStream(aFilename));
            Enumeration enumerator = prop.propertyNames();
            System.out.println("     --- PROPERTIES FILE CONTENT BEGIN ---");
            while (enumerator.hasMoreElements()) {
                String key = (String) enumerator.nextElement();
                String value = prop.getProperty(key);
                System.out.println("          " + key + ": " + value + ";");
            }
            System.out.println("     --- PROPERTIES FILE CONTENT END ---");
        } catch(FileNotFoundException fnfe){
            System.out.println("   !!! FILE NOT FOUND, please try again !!!");
        }catch (IOException ex) {
            System.out.println("   !!! An ERROR OCURRED, please try again !!!");
        }
    }

}
