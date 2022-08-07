package geneticsjobshop;

import java.util.Arrays;
import java.util.Random;

public class Individu {

    Data[][] data;
    int[] chromosome;
    int max_duration;
    double fitness;

    public Individu(Data[][] data) {
        this.data = data;
        this.chromosome = generate(data);
    }

    public int[] generate(Data[][] data) {
        int[] individu = null;
        if (data != null) {
            int nJob = data.length;
            int nProc = data[0].length;
            int panjangKromosom = nJob * nProc;
            int[] maxProc = new int[nJob];
            individu = new int[panjangKromosom];
            int k = 0;
            while (k < panjangKromosom) {
                int value = new Random().nextInt(nJob);
                //validasi ada berapa proses yang telah dimiliki oleh setiap Job
                if (maxProc[value] < nProc) {
                    individu[k] = value;
                    maxProc[value]++;
                    k++;
                }
            }
        }
        return individu;
    }//end of generateIndividu(Data[][] data)

    public boolean validasi() {
        boolean valid = false;
        if (this.data != null && this.chromosome != null) {
            this.max_duration = hitungDurasiTotal(chromosome, data);
            //hitung fitness
            double epsilon = Double.MIN_VALUE;
            this.fitness = 1.0 / (this.max_duration + epsilon);

            if (max_duration > 0) {
                valid = true;
            }

        }
        return valid;
    }

    public int hitungDurasiTotal(int[] chromosome, Data[][] data) {
        int MAX_DURATION = 0;
        if (chromosome != null && data != null) {
            int nJob = data.length;
            int nProc = data[0].length;
            int nMac = nProc;
            int[] endTimeMac = new int[nMac];
            int[] endTimeJob = new int[nJob];
            int[] iProc = new int[nJob];//untuk menandai index proses yang sedang dikerjakan di setiap Job

            for (int i = 0; i < chromosome.length; i++) {
                int job = chromosome[i];
                int proc = iProc[job];
                int iMesin = data[job][proc].iMesin;//mesin
                int durasi = data[job][proc].durasi;//durasi mesin
                //masukkan mesin dan durasi ke gantt diagram
                int etMac = endTimeMac[iMesin];//end time untuk mesin
                int etJob = endTimeJob[job];//end time untuk job
                int start = Math.max(etMac, etJob);
                int finish = start + durasi;

                data[job][proc].startTime = start;
                data[job][proc].endTime = finish;

                endTimeMac[iMesin] = finish;
                endTimeJob[job] = finish;

                if (finish > MAX_DURATION) {
                    MAX_DURATION = finish;
                }
                iProc[job]++;//lakukan inkremen untuk index proses pada job yang baru dikerjakan

                //cetak progres gantt diagram
                //System.out.println("Job: " + job + "; Proc: " + proc + "; " + data[job][proc]);
            }
        }
        return MAX_DURATION;
    }

    public String toString() {
        String info = "NULL";
        if (chromosome != null) {
            StringBuffer sb = new StringBuffer();
            sb.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            sb.append("| kromosom     : ");
            sb.append(Arrays.toString(chromosome));
            sb.append("\n");            
            sb.append("| total durasi : "+this.max_duration+"\n");
            sb.append("| fitness      : "+this.fitness+"\n");
            sb.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            info = sb.toString();
        }
        return info;
    }

}
