package csc1035.project3;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemArray{

    private ArrayList<Items> items = new ArrayList<>();

    public static void main(String args[]){
        ItemArray array1 = new ItemArray();
        array1.itemArray();
    }

    public void itemArray(){
        try{
            FileReader fr = new FileReader("src/main/resources/stock.sample.csv");
            Scanner s = new Scanner(fr).useDelimiter(",");
            while(s.hasNext()){
                String[] temp = new String[6];
                temp = s.nextLine().split(",");
                items.add(new Items(temp[0], temp[1], Boolean.parseBoolean(temp[2]), Double.parseDouble(temp[3]), Integer.parseInt(temp[4]), Double.parseDouble(temp[5])));
            }
            s.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
