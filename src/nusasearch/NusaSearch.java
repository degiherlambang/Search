package nusasearch;


import java.util.ArrayList;
import nusasearch.DB;
import nusasearch.Nasabah;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
public class NusaSearch {

    private ArrayList<Nasabah> listNasabah;
    private ArrayList<Nasabah> resultSearch;
    private Nasabah nsb;
    
    private boolean found;
    private int charLength;
    static Scanner input = new Scanner(System.in);
    public NusaSearch() {
        
    }

    public ArrayList<Nasabah> getDbList() {
        listNasabah = new ArrayList<Nasabah>();
        try {
            PreparedStatement ps = DB.getConnection()
                    .prepareStatement("select*from nasabah");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nsb = new Nasabah();
                nsb.setId(rs.getInt("id_nasabah"));
                nsb.setNama(rs.getString("nama"));
                nsb.setNik(rs.getInt("nik"));
                nsb.setUsername(rs.getString("username_nasabah"));
                listNasabah.add(nsb);
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNasabah;
    }

    public ArrayList<Nasabah> doSearchStartsWith(ArrayList<Nasabah> obj, String category, String key) {
        listNasabah = obj;
        resultSearch = new ArrayList<Nasabah>();
        if (category.equalsIgnoreCase("id")){
        } else if (category.equalsIgnoreCase("name")) {
            for (int i = 0; i < listNasabah.size(); i++) {
                //listNasabah.get(i).getNama().startsWith(key)
                if (startsWithIgnoreCase(listNasabah.get(i).getNama(),key)){
                    found = true;
                    resultSearch.add(listNasabah.get(i));
                }
            }
        } else if (category.equalsIgnoreCase("nik")) {
        } else if (category.equalsIgnoreCase("username")) {   
        }
        if (!found) { 
            System.out.println("---data not found----");
        } else {
            return resultSearch;
        }
        return resultSearch;
    }
    
    public ArrayList<Nasabah> doSearchEndsWith(ArrayList<Nasabah> obj, String category, String key) {
        listNasabah = obj;
        resultSearch = new ArrayList<Nasabah>();
        if (category.equalsIgnoreCase("id")){
        } else if (category.equalsIgnoreCase("name")) {
            for (int i = 0; i < listNasabah.size(); i++) {
                //listNasabah.get(i).getNama().startsWith(key)
                if (endsWithIgnoreCase(listNasabah.get(i).getNama(),key)){
                    found = true;
                    resultSearch.add(listNasabah.get(i));
                }
            }
        } else if (category.equalsIgnoreCase("nik")) {
        } else if (category.equalsIgnoreCase("username")) {   
        }
        if (!found) { 
            System.out.println("---data not found----");
        } else {
            return resultSearch;
        }
        return resultSearch;
    }
    
    
   /*public ArrayList<ModelNasabah> BinarySearch(ArrayList<ModelNasabah> x) {
        int batasAtas = x.size() - 1;
        int batasBawah = 0;
 
        System.out.print("Masukkan nama yang dicari :");
        
        String cari = input.next();
        boolean notFound = true;
        while (notFound) {
            int posisiSekarang = (batasAtas + batasAtas) / 2;
            if (x.get(posisiSekarang).equals(cari)) {
                notFound = false;
                System.out.println("ditemukan " + cari);
            } else if (batasBawah > batasAtas) {
                System.out.println("Tidak ditemukan " + cari);
                break;
            } else {
                if (x.get(posisiSekarang).getNama().compareTo(cari) < 0)
                    batasBawah = posisiSekarang + 1;
                else    
                    batasAtas = posisiSekarang - 1;
            }
        }
        return x;
    }   */
    
     public ArrayList<Nasabah> doMergeSearch(ArrayList<Nasabah> obj, String category, String key) {
        listNasabah = obj;
        resultSearch = new ArrayList<Nasabah>();
        int nasabahIndex = -1;
        int low = 0;
        int high = listNasabah.size();
        int mid = (low + high)/2;
        while((low<high) && 
              (!startsWithIgnoreCase(listNasabah.get(mid).getNama(), key)))
  {
            if (startsWithIgnoreCase(listNasabah.get(mid).getNama(), key)) {
                low = mid + 1;
            } else {
               high = mid - 1;
            }
            mid = (low + high) / 2;
            if (low > high) {
                nasabahIndex = mid;
                resultSearch.add(listNasabah.get(nasabahIndex));
            }
        }
        return listNasabah;
    }
    
    public ArrayList<Nasabah> doSearchContainsWith(ArrayList<Nasabah> x, String key){
    
           if(x.contains(key)){
               System.out.println(x);
           }
      
       return x;
    }

 
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return str.regionMatches(true, 0, prefix, 0, prefix.length());
    }
    
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        int suffixLength = suffix.length();
        return str.regionMatches(true, str.length() - suffixLength, suffix, 0, suffixLength);
    }
    
   
}
