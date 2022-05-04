package joaoduarte.samples.javadatprop;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class PropertiesFilesManager {
    private Properties prop = new Properties();

    //prop.setProperty("IP", aIP);
    //prop.setProperty("Porto", aPorto);
    //prop.setProperty("Nome_BaseDados", aNomeBD);
    //prop.setProperty("Username", aUser);
    //prop.setProperty("Password", aPass);
    //prop.store(new FileOutputStream(aFileName+".properties"), null);

    public boolean createPRPFile(String aFileName, String aFilePath) {
        try {
            new FileOutputStream(new File(aFilePath + "/" + aFileName + ".properties"));
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void savePropOnPropF(String aFile, String aPropName, String aPropValue){
        try{
            prop.setProperty(aPropName, aPropValue);
            prop.store(new FileOutputStream(aFile),null);
        }catch (Exception e){
            System.out.println(e);
        }
    }

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
            System.out.println("  !!! FILE NOT FOUND, please try again !!!");
        }catch (IOException ex) {
            System.out.println("   !!! An ERROR OCURRED, please try again !!!");
        }
    }

}
