package csc1035.project3;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemArray{

    private static ArrayList<Items> items = new ArrayList<>();

    public static ArrayList<Items> csvLoader(){
        try{
            FileReader fr = new FileReader("src/main/resources/stock.sample.csv");
            Scanner s = new Scanner(fr).useDelimiter(",");
            while(s.hasNext()){
                String[] temp;
                temp = s.nextLine().split(",");
                items.add(new Items(temp[0], temp[1], Boolean.parseBoolean(temp[2]), Double.parseDouble(temp[3]), Integer.parseInt(temp[4]), Double.parseDouble(temp[5])));
            }
            s.close();
            return items;
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Items stringLoader(String string) {
        String[] temp = string.split(",");
        return new Items(temp[0], temp[1], Boolean.parseBoolean(temp[2]), Double.parseDouble(temp[3]), Integer.parseInt(temp[4]), Double.parseDouble(temp[5]));
    }

}
