package joaoduarte.samples.javadatprop;

import java.sql.SQLOutput;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private static final Scanner keyboard = new Scanner(System.in);
    private static final PropertiesFilesManager propertiesManager = new PropertiesFilesManager();
    private static final DatFilesManager datManager = new DatFilesManager();
    private static String lastPropFile = null;

    public static void main(String[] args) {
	    appBanner();
        System.out.println();
        while (true){
            mainMenu();
        }
    }

    private static void appBanner(){
        System.out.println("### Java DatProp - File Manager ###");
    }

    private static void mainMenu(){
        try{
            System.out.println(" Main Menu:\n     1 - DAT Files Management\n     2 - PROPERTIES Files Management Text\n     3 - Exit");
            switch(Integer.parseInt(readString(keyboard,"Option: "))){
                case 1:{
                    datMenu();
                    break;
                }
                case 2:{
                    propMenu();
                    break;
                }
                case 3:{
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println(" !! Invalid Option, Try Again!");
                    mainMenu();
                }
            }
        }catch (NumberFormatException nfe){
            System.out.println("\n   !!! This field ONLY accepts NUMBERS! Please try again !!!\n");
            mainMenu();
        }catch (Exception e){
            System.out.println("\n   !!! An ERROR OCURRED, please try again !!!\n");
            mainMenu();
        }
    }

    private static void datMenu(){
        try{
            System.out.println("  Main Menu > DAT FILES Menu:\n          1 - Create DAT File\n          2 - Read DAT File\n          3 - Back to Main Menu");
            switch(Integer.parseInt(readString(keyboard," Option: "))){
                case 1:{
                    datMenu();
                    break;
                }
                case 2:{
                    propMenu();
                    break;
                }
                case 3:{
                    mainMenu();
                }
                default:{
                    System.out.println("  !! Invalid Option, Try Again!");
                    datMenu();
                }
            }
        }catch (NumberFormatException nfe){
            System.out.println("\n      !!! This field ONLY accepts NUMBERS! Please try again !!!\n");
            datMenu();
        }catch (Exception e){
            System.out.println("\n      !!! An ERROR OCURRED, please try again !!!\n");
            datMenu();
        }
    }

    private static void propMenu(){
        try{
            System.out.println("  Main Menu > PROPERTIES FILES Menu:\n          1 - Create PROPERTIES File\n          2 - Write on PROPERTIES File\n          3 - Read PROPERTIES File1\n          4 - Back to Main Menu");
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
                    mainMenu();
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

    private static void readPRPFile(){
        propertiesManager.printAll(readString(keyboard, "  Properties File Path? "));
    }


    public static String readString(Scanner aKeyboard, String aMsg) {
        System.out.print(" " + aMsg);
        return aKeyboard.nextLine();
    }
}
