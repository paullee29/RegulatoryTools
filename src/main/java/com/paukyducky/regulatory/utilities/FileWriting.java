package com.paukyducky.regulatory.utilities;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileWriting {

    public static void fileCreator (String directoryName, String fileName) {

        // defines where the text file will be stored
//	    String directoryName = "c://temp//";


        File directory = new File(directoryName);

        try {
            if (!directory.exists()){
                directory.mkdirs();
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
                System.out.println("Directory Created");

            }
        } catch (SecurityException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        File file = new File(directoryName+fileName);

        //Create the file
        try {
            if (file.createNewFile())
            {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists, deleting previous one...");
                file.delete();
                file.createNewFile();
                System.out.println("Creating New File");
                System.out.println(fileName + " Created");
            }
        } catch (IOException e) {
            System.out.println("Exeception creating a new file has occured");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void textWriter (String directoryName, String fileName, String text) {
        File file = new File(directoryName+fileName);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(file, true));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            out.write(text);
            out.newLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        try {
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

}


