/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Esericizio3v4;

/**
 *
 * @author rovelli_andrea
 * @version 1.0
 *
 * @brief classe che permette la condivisione dei dati fra thread salvandone i
 * valori dati.
 *
 */
public class DatiCondivisi {

    private int countA, countE, countI, countO, countU;
    
    public int getCountA() {
        return countA;
    }

    public void setCountA(int countA) {
        this.countA = countA;
    }

    public int getCountE() {
        return countE;
    }

    public void setCountE(int countE) {
        this.countE = countE;
    }

    public int getCountI() {
        return countI;
    }

    public void setCountI(int countI) {
        this.countI = countI;
    }

    public int getCountO() {
        return countO;
    }

    public void setCountO(int countO) {
        this.countO = countO;
    }

    public int getCountU() {
        return countU;
    }

    public void setCountU(int countU) {
        this.countU = countU;
    }

    /**
     * @author rovelli_andrea
     *
     * attributo contenente il numero di volte si sono ripetute le varie vocali
     *
     */
    private int numVocali[];

    /**
     * @author rovelli_andrea
     *
     * @brief costruttore senza parametri, inizializza la dimensione del vettore
     * numVocali a 5.
     *
     */
    public DatiCondivisi() {
        this.countA = 0;
        this.countE = 0;
        this.countI = 0;
        this.countO = 0;
        this.countU = 0;
        this.numVocali = new int[5];
    }

    /**
     * @author rovelli_andrea
     *
     * @brief metodo che restituisce il vettore numVocali.
     *
     * @return il vettore numVocali.
     */
    public int[] getNumVocali() {
        return numVocali;
    }

    /**
     * @author rovelli_andrea
     *
     * @brief metodo che permette di assegnare al vettore numVocali quante volte
     * si è ripetuta una vocale nella frase.
     *
     * @param posizione indica la posizione nella quale inserire il valore,
     * permette anche di capire di quale vocale sta contenendo il numero di
     * ripetizioni.
     *
     * @param valore indica quante volte si è ripetuta una determinata vocale
     * nella frase data.
     */
    public void setNumVocali(int posizione, int valore) {
        this.numVocali[posizione] = valore;
    }

    /**
     * @author rovelli_andrea
     *
     * @brief metodo che restituisce la vocale che si è ripetuta più volte
     * basandosi sulla posizione.
     *
     * @return la posizione della vocale che si è ripetuta più volte di tutte.
     */
    /*public int trovaMax() {
        int massimo = numVocali[0];
        int posizione = 0;
        for (int i = 1; i <= numVocali.length - 1; i++) {
            if (numVocali[i] > massimo) {
                massimo = numVocali[i];
                posizione = i;
            }
        }
        return posizione;
    }*/
    public String trovaMax() {
        String max = "0,";
        int massimo = numVocali[0];
        for (int i = 1; i < numVocali.length; i++) {
            if (numVocali[i] > massimo) {
                massimo = numVocali[i];
                max = String.valueOf(i) + ",";
            } else if (massimo == numVocali[i]) {
                max += String.valueOf(i) + ",";
            }
        }
        max += "fine";
        return max;
    }

}
