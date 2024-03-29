package joaoduarte.samples.javadatprop;

import java.util.Scanner;

/**
 * @author João Duarte (LinkedIn: www.linkedin.com/in/joão-duarte-453bb9199 | GitHub: https://github.com/jduarte98)
 */
public class Main {
    private static final Scanner keyboard = new Scanner(System.in);
    private static final PropertiesFilesManager propertiesManager = new PropertiesFilesManager();
    private static String lastPropFile = null;

    /**
     * Main Class
     * @param args Not Used
     */
    public static void main(String[] args) {
        System.out.println("### JavaProp - <<.properties>> File Manager ###\n");
        while (true){
            propMenu();
        }
    }

    /**
     * Prop Menu - responsible for the logic of creating a properties file and launching the functions responsible for the file Writing and Reading.
     */
    private static void propMenu(){
        try{
            System.out.println("  Main Menu:\n          1 - Create PROPERTIES File\n          2 - Write on PROPERTIES File\n          3 - Read PROPERTIES File1\n          4 - Exit");
            switch(Integer.parseInt(readString(keyboard," Option: "))){
                case 1:{
                    String fname = readString(keyboard,"   File Name? "), fpath = readString(keyboard, "   File Saving Path (without final /) ? ");
                    if(propertiesManager.createPRPFile(fname,fpath)){
                        lastPropFile = fpath + "/" + fname + ".properties";
                        System.out.println("     > File Created Sucessfully!\n       > Operation Summary:\n           - Name: " + fname + ".properties;\n           - Simple Path: " + fpath + ";\n           - Full Path: " + lastPropFile + ";");
                    }else{
                        System.out.println("     !!! Error Creating the File, please try again !!!");
                    }
                    propMenu();
                    break;
                }
                case 2:{
                    writeOnPropFile();
                    propMenu();
                    break;
                }
                case 3:{
                    readPRPFile();
                    propMenu();
                    break;
                }
                case 4:{
                    System.exit(0);
                }
                default:{
                    System.out.println("  !! Invalid Option, Try Again!");
                    propMenu();
                }
            }
        }catch (NumberFormatException nfe){
            System.out.println("\n      !!! This field ONLY accepts NUMBERS! Please try again !!!\n");
            propMenu();
        }catch (Exception e){
            System.out.println("\n      !!! An ERROR OCURRED, please try again !!!\n");
            propMenu();
        }
    }

    /**
     * Write on previous created (with option 1) or new properties file
     */
    private static void writeOnPropFile(){
        boolean continuePropAdding = true;
        if(lastPropFile != null){
            if(readString(keyboard, "  Do you want to write on the last created Properties File\n   ("+lastPropFile+") Y/N ? ").equalsIgnoreCase("y")){
                while(continuePropAdding){
                    propertiesManager.savePropOnPropF(lastPropFile,readString(keyboard, "  Propertie Name? "), readString(keyboard,"  Propertie Value? "));
                    if(readString(keyboard,"  Continue Adding Propeeties (Y/N)? ").equalsIgnoreCase("n")){
                        continuePropAdding = false;
                    }
                }
            }else{
                lastPropFile = null;
                writeOnPropFile();
            }
        }else{
            boolean continueAdding = true;
            String path = readString(keyboard, "  Properties File Path? ");
            while(continueAdding){
                propertiesManager.savePropOnPropF(path,readString(keyboard, "  Propertie Name? "), readString(keyboard,"  Propertie Value? "));
                if(readString(keyboard,"  Continue Adding Propeeties (Y/N)? ").equalsIgnoreCase("n")){
                    continueAdding = false;
                }
            }
        }
    }

    /**
     * Read the full contents of a properties file
     */
    private static void readPRPFile(){
        try{
            propertiesManager.printAll(readString(keyboard, "  Properties File Path? "));
        }catch (Exception e){
            System.out.println("      !!! An ERROR OCURRED while reading the file, please try again !!!");
        }
    }

    /**
     * Read User Input
     * @param aKeyboard Scanner Object
     * @param aMsg Prompt message
     * @return String
     */
    public static String readString(Scanner aKeyboard, String aMsg) {
        System.out.print(" " + aMsg);
        return aKeyboard.nextLine();
    }
}
