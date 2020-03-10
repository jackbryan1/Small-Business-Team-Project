package csc1035.project3;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemArray{

    private static ArrayList<Items> items = new ArrayList<>();

    /**
     *
     * @return Returns the arraylist of converted items
     */
    public static ArrayList<Items> csvLoader(){
        try{ //Creates a try, catch block to catch any errors thrown.
            //Creates a FileReader object to read the specified CSV file.
            FileReader fr = new FileReader("src/main/resources/stock.sample.csv");
            Scanner s = new Scanner(fr).useDelimiter(","); //Scanner reads the file, separating using commas.
            while(s.hasNext()){
                String[] temp; //Creates a string array.
                temp = s.nextLine().split(","); //Stores each entry between commas in the array.
                items.add(new Items(temp[0], temp[1], Boolean.parseBoolean(temp[2]), Double.parseDouble(temp[3]),
                        Integer.parseInt(temp[4]), Double.parseDouble(temp[5]))); //Adds each field into the item array.
            }
            s.close();
            return items; //Returns the item with completed fields.
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param string The string to be converted from csv format into an item
     * @return Returns the converted item
     */
    public static Items stringLoader(String string) {
        String[] temp = string.split(",");
        return new Items(temp[0], temp[1], Boolean.parseBoolean(temp[2]), Double.parseDouble(temp[3]), Integer.parseInt(temp[4]), Double.parseDouble(temp[5]));
    }

}
