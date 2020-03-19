
package nusasearch;

import java.util.ArrayList;
import nusasearch.DB;
import nusasearch.Nasabah;
import nusasearch.NusaSearch;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        NusaSearch nsc = new NusaSearch();

        Scanner input = new Scanner(System.in);
        Scanner inputKey = new Scanner(System.in);
        Scanner inputCategory = new Scanner(System.in);
        
        int option = 0;
        String key = "";
        String category = "";

        ArrayList<Nasabah> dataNasabah = new ArrayList<Nasabah>();

        ArrayList<Nasabah> Search = new ArrayList<Nasabah>();

        dataNasabah = nsc.getDbList();


        System.out.println("---------ArrayList----------");
        for (int i = 0; i < dataNasabah.size(); i++) {
            System.out.println(dataNasabah.get(i).getNama());
        }

        System.out.println("Pilih");
        System.out.println("1.Berawalan Huruf");
        System.out.println("2.Mengangdung Huruf");
        System.out.println("3.Berakhiran Huruf");
        System.out.println("Pilih: ");
        option = input.nextInt();
        

        if (option == 1) {
            
            System.out.println("---------------");
            System.out.print("Search: ");
            key = inputKey.nextLine();

            // result utk linear search
            System.out.println("Hasil");
            System.out.println("---------------");
            Search = nsc.doSearchStartsWith(dataNasabah,"name", key);
            for (int i = 0; i < Search.size(); i++) {
                System.out.println(Search.get(i).getNama());
            }
            
        } else if (option == 2) {
            System.out.println("---------------");
            System.out.print("Search: ");
            key = inputKey.nextLine();


            System.out.println("Hasil");
            System.out.println("---------------");
            Search = nsc.doSearchContainsWith(dataNasabah, key);
            for (int i = 0; i < Search.size(); i++) {
                System.out.println(Search.get(i).getNama());
            }
        } else if (option == 3) {
            System.out.println("---------------");
            System.out.print("Search: ");
            key = inputKey.nextLine();


            System.out.println("Hasil");
            System.out.println("---------------");
            Search = nsc.doSearchEndsWith(dataNasabah,"name", key);
            for (int i = 0; i < Search.size(); i++) {
                System.out.println(Search.get(i).getNama());
            }
        } else if(option == 4){
           System.out.println("---------------");
            System.out.print("Search: ");
            key = inputKey.nextLine();
            System.out.println("Hasil");
            System.out.println("---------------");
            Search = nsc.doMergeSearch(dataNasabah,"name", key);
            for (int i = 0; i < Search.size(); i++) {
                System.out.println(Search.get(i).getNama());
            
        }
        }
        else {
            System.out.println("UNKNOWN OPTION");
        }

    }
}
