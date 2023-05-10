import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileIO {
    //function used to set empty vector/database to contents of file database if it exists
    public static ArrayList<Part> fileGet(ArrayList<Part> database) {
        String line[];
        try{
            File file = new File("database.txt");
            //checks if the file exists
            if(file.exists()) {
                //reads the file line by line
                Scanner reader = new Scanner(file);
                while(reader.hasNextLine()){
                    //splits each line into segments dictated by ,'s (ie into 3 string objects)
                    line = reader.nextLine().split(",");
                    //adds the line into the database based on its segments
                    database.add(new Part(line[0], Integer.valueOf(line[1]), Double.valueOf(line[2])));
                }
                //closes the file
                reader.close();
            }
        }catch(FileNotFoundException e){
        }
        return database;
    }
    //function used to create a file if one isn't present, and overite the data in it to the data in the vector.
    public static void filePrint(ArrayList<Part> database){
        //first the program tries to overite the data in the specified file
        try {
            FileWriter stream = new FileWriter("database.txt", false);
            //writes the objects in the vector into the file line by line
            for(int x = 0; x < database.size(); x++) {
                stream.write(database.get(x).toString() + "\n");
            }
            stream.close();
            //if no file is present, the code first create the file then writes the data in the vector to the file
        } catch (FileNotFoundException e) {
            File file = new File("database.txt");
            try {
                file.createNewFile();
                //writes the objects in the vector into the file line by line
                FileWriter stream = new FileWriter("database.txt", false);
                for(int x = 0; x < database.size(); x++) {
                    stream.write(database.get(x).toString()+"\n");
                }
                stream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
