package geneticsjobshop;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class DataReader {
    Data[][]data = null;

    public DataReader(File f) {
        try {
            FileInputStream fis = new FileInputStream(f);
            Scanner sc = new Scanner(fis, "UTF-8");

            sc.nextLine();
            sc.nextLine();
            sc.nextLine();
            sc.nextLine();

            String baris = sc.nextLine();
            String[] segmen = baris.split("\\s+");

            String s0 = segmen[0];
            String s1 = segmen[1];

            int nJob = Integer.parseInt(s0);// banyaknya Job di dataset
            int nProc = Integer.parseInt(s1);// banyaknya proses di dataset
            int nMac = nProc;// banyaknya msein di dataset

            System.out.println("------------------------------------------------");
            System.out.println("DATASET");
            System.out.println("Banyaknya Job   : " + nJob);
            System.out.println("Banyaknya Proses: " + nProc);
            System.out.println("Banyaknya Mesin : " + nMac);
            System.out.println("");

            data = new Data[nJob][nProc];

            //baca data mesin dan waktu baris demi baris
            System.out.println("ISI DATASET");
            for (int i = 0; i < nJob; i++) {
                baris = sc.nextLine();
                segmen = baris.split("\\s+");//split string baris ke dalam array segmen. simbol \\s+ berarti bahwa simbol pemisah yang kita gunakan untuk melakukan split adalah simbol spasi
                for (int k = 0; k < nProc; k++) {
                    String sMac = segmen[2 * k];
                    String sDur = segmen[2 * k + 1];
                    int iMesin = Integer.parseInt(sMac);
                    int durasi = Integer.parseInt(sDur);
                    data[i][k] = new Data(iMesin, durasi);
                }

            }//sampai disini dataset telah didistribusikan ke tabel mesin dan tabel waktu

            System.out.println("");
            System.out.println("TABEL MESIN");
            for (int i = 0; i < nJob; i++) {
                for (int j = 0; j < nProc; j++) {
                    System.out.print(data[i][j].iMesin + " ");
                }
                System.out.println("");
            }

            System.out.println("");
            System.out.println("TABEL WAKTU untuk TIAP MESIN pada JOB-PROSES");
            for (int i = 0; i < nJob; i++) {
                for (int j = 0; j < nProc; j++) {
                    System.out.print(data[i][j].durasi + " ");
                }
                System.out.println("");
            }
            System.out.println("------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
