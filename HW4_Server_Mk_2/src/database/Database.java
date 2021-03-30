package database;

import java.io.*;
import java.util.StringTokenizer;

public class Database {

    public static String textFile = "src\\database\\accounts.txt";

    /**
     * Writes unique account information to the text file.
     * Function returns if exact account information already exists.
     * @param userID
     * @param password
     * @return
     */
    public static boolean writeAccountInfo(String userID, String password) {
        userID = userID.trim();
        password = password.trim();
        try {
            if (authenticate(userID, password) == true) {
                System.out.println("This account already exists!");
                return false;
            }
            FileWriter writer = new FileWriter(textFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(userID + " " + password);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Authenticates by checking the text file for matching ID and password.
     * @param userID The userID to search
     * @param password The password to search
     * @return True if the matching information exists. False if otherwise.
     */
    public static boolean authenticate(String userID, String password) {
        try {
            FileReader reader = new FileReader(textFile);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);

                String lineID = tokenizer.nextToken();
                String linePW = tokenizer.nextToken();

                if (lineID.equals(userID) && linePW.equals(password)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();

        } catch (IOException e) { e.printStackTrace(); }
        return false;
    }

    /**
     * Prints the contents of the text file.
     */
    public static void printFileContents() {
        try {
            FileReader reader = new FileReader(textFile);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) { e.printStackTrace(); }
    }

//    /**
//     * Main Tester Class for the above methods
//     * @param args
//     */
//    public static void main(String[] args) {
//        writeAccountInfo("ilmi", "789");
//        if (authenticate("ilmi", "789") == true) {
//            System.out.println("Successfully authenticated!");
//        }
//    }
}
