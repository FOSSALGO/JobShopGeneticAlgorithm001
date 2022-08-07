package geneticsjobshop;

import java.io.File;

public class MainGAPopulasi {

    public static void main(String[] args) {
        File f = new File("dataset/dataset_001.txt");

        //baca data
        DataReader dr = new DataReader(f);
        Data[][] data = dr.data;

        //Elitism
        Individu individuElitism = null;
        double bestFitness = Double.MIN_VALUE;

        //POPULASI
        int nPopulasi = 1000000;
        Individu[] populasi = new Individu[nPopulasi];
        //inisialisasi populasi awal
        for (int p = 0; p < populasi.length; p++) {
            Individu individu = new Individu(data);
            individu.validasi();
            populasi[p] = individu;
            if (bestFitness < individu.fitness) {
                bestFitness = individu.fitness;
                individuElitism = individu;
            }
            //System.out.println("Individu-" + (1 + p));
            //System.out.println(individu);
        }
        
        //hanya dengan operasi elitism di generasi awal kita
        System.out.println("Individu Elitism");
        System.out.println(individuElitism);

    }
}
