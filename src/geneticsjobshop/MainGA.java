package geneticsjobshop;

import java.io.File;

public class MainGA {
    public static void main(String[] args) {
        File f = new File("dataset/dataset_001.txt");
        
        //baca data
        DataReader dr = new DataReader(f);
        Data[][] data= dr.data;
        
        //test generate individu lalu validasi
        Individu individu = new Individu(data);
        individu.validasi();
        System.out.println(individu);
        
    }
}
