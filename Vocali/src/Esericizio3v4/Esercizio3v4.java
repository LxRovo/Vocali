/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Esericizio3v4;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rovelli_andrea
 * @version 1.0
 *
 * @brief questo programma trova le vocali presenti in una frase e le mette su
 * schermo. Collabora con le classi
 * ThTrovaA,ThTrovaE,ThTrovaI,ThTrovaO,ThTrovaU, DatiCondivisi.
 */
public class Esercizio3v4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Semaforo mutex = new Semaforo(1);
        Semaforo visualizza = new Semaforo(-4);
        Semaforo aspetta = new Semaforo(5);
        java.io.BufferedReader console = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        /**
         * @author rovelli_andrea
         *
         * variabile che permette all'utente di scegliere se utilizzare la
         * funzione usaSleep presente nelle classi o meno.
         *
         */
        boolean useSleep = false;

        /**
         * @author rovelli_andrea
         *
         * variabile che permette all'utente di scegliere se utilizzare la
         * funzione usaYield presente nelle classi o meno.
         *
         */
        boolean useYield = true;

        /**
         * @author rovelli_andrea
         *
         * oggetto che permette ai thread di scambiarsi informazioni.
         *
         */
        DatiCondivisi dati = new DatiCondivisi();

        /**
         * @author rovelli_andrea
         *
         * variabile che permette all'utente di selezionare la frase con cui
         * giocare.
         *
         */
        String scelta = "";
        System.out.println("Inserisci una frase");
        try {
            scelta = console.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Esercizio3v4.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * @author rovelli_andrea
         *
         * richiamo dei costruttori con parametri delle varie classi per
         * inizializzare oggetti.
         *
         * @param useSleep parametro che permette di sapere se utilizzare o meno
         * la funzione usaSleep presente nella classe.
         * @param useYield parametro che permette di sapere se utilizzare o meno
         * la funzione usaYield presente nella classe.
         * @param dati parametro che permette alle classi di interagire sugli
         * attributi dello stesso oggetti.
         */
        ThTrovaA thA = new ThTrovaA(useSleep, useYield, scelta, dati, mutex, visualizza, aspetta);
        ThTrovaE thE = new ThTrovaE(useSleep, useYield, scelta, dati, mutex, visualizza, aspetta);
        ThTrovaI thI = new ThTrovaI(useSleep, useYield, scelta, dati, mutex, visualizza, aspetta);
        ThTrovaO thO = new ThTrovaO(useSleep, useYield, scelta, dati, mutex, visualizza, aspetta);
        ThTrovaU thU = new ThTrovaU(useSleep, useYield, scelta, dati, mutex, visualizza, aspetta);
        ThVisualizzaCount thV = new ThVisualizzaCount(dati, mutex, visualizza, aspetta);
        /**
         * @author rovelli_andrea
         *
         * avvio dei vari thread.
         *
         */
        thA.start();
        thE.start();
        thI.start();
        thO.start();
        thU.start();
        thV.start();

        /**
         * @author rovelli_andrea
         *
         * attesa della fine di tutti i thread con messaggio su schermo.
         *
         */
        try {

            thA.join();
            thE.join();
            thI.join();
            thO.join();
            thU.join();

            thV.interrupt();

            System.out.println("Hai 2 secondi per scoprire quale vocale si è ripetuta di più");

            Thread.sleep(2000);

            System.out.println("Quale vocale si è ripetuta di più?");

            /**
             * @author rovelli_andrea
             *
             * confronto con la vocale scelta dall'utente e quella ripetuta più
             * volte.
             *
             */
            try {

                String c;
                c = console.readLine();
                while (!"a".equals(c) && !"i".equals(c) && !"e".equals(c) && !"o".equals(c) && !"u".equals(c)) {
                    System.out.println("Inserisci una vocale");
                    c = console.readLine();
                }
                String confronto = "";
                boolean win = false;
                int i = 0;
                String pos[] = dati.trovaMax().split(",");

                while (win == false && !"fine".equals(pos[i])) {
                    switch (pos[i]) {
                        case ("0"):
                            confronto = "a";
                            break;
                        case ("1"):
                            confronto = "e";
                            break;
                        case ("2"):
                            confronto = "i";
                            break;
                        case ("3"):
                            confronto = "o";
                            break;
                        case ("4"):
                            confronto = "u";
                            break;

                    }

                    if (confronto.equals(c)) {
                        win = true;
                    }
                    i++;
                }
                if (win) {
                    System.out.println("Hai vinto!");
                } else {
                    System.out.println("Hai perso!");
                }
            } catch (IOException ex) {
                Logger.getLogger(Esercizio3v4.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Grazie e arrivederci");
        } catch (InterruptedException ex) {
            Logger.getLogger(Esercizio3v4.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
