/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Esericizio3v4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rovelli_andrea
 * @version 1.0
 *
 * @brief classe che utilizza un thread per cercare le lettere O presenti in una
 * frase. Estende la classe Thread e collabora con la classe DatiCondivisi.
 */
public class ThTrovaO extends Thread {

    /**
     * @author rovelli_andrea
     *
     * attributo contentente l'oggetto che permette a questo thread di
     * condividere dati con altri thread.
     *
     */
    private DatiCondivisi ptrdati;

    /**
     * @author rovelli_andrea
     *
     * attributo contentente la frase in cui cercare le lettere.
     *
     */
    private String frase = "Programmare oggi è una gara tra i tecnici del software che lottano per costruire programmi migliori ed a prova di idiota, e l’Universo che cerca di produrre migliori e più grandi idioti. Al momento, l’Universo sta vincendo.” ";

    /**
     * @author rovelli_andrea
     *
     * attributo che permette di utilizzare o meno la funzione usaSleep.
     *
     */
    private boolean useSleep;

    /**
     * @author rovelli_andrea
     *
     * attributo che permette di utilizzare o meno la funzione usaYield.
     *
     */
    private boolean useYield;

    /**
     * @author rovelli_andrea
     *
     * @brief costruttore che assegna agli attributi useSleep e useYield il
     * valore presente nei due parametri useSleep e useYield.
     *
     * @param useSleep parametro che indica se utilizzare o meno la funzione
     * usaSleep.
     * @param useYield parametro che indica se utilizzare o meno la funzione
     * usaYield.
     * @param s parametro che indica la stringa da assegnare a "frase".
     * @param ptrdati parametro che permette alla classe di avere i dati degli
     * altri thread e condividere i propri.
     */
     private Semaforo mutex;
   private Semaforo visualizza;
    private Semaforo aspetta;
    
    public ThTrovaO(boolean useSleep, boolean useYield, String s, DatiCondivisi ptrdati, Semaforo mutex,Semaforo visualizza,Semaforo aspetta) {
        this.useSleep = useSleep;
        this.useYield = useYield;
        this.frase = s;
        this.ptrdati = ptrdati;
        this.mutex = mutex;
        this.visualizza = visualizza;
        this.aspetta = aspetta;
    
    }

    /**
     * @author rovelli_andrea
     *
     * @brief funzione che permette di avviare l'esecuzione della funzione
     * TrovaO quando viene richiamato il metodo start presente nella classe
     * Thread.
     *
     */
    public void run() {

        TrovaO();

    }

    /**
     * @author rovelli_andrea
     *
     * @brief funzione che permette di ricercare in una frase tutte le lettere
     * 'o' e 'O' ed in base ai valori di useSleep e useYield richiama i metodi
     * usaSleep e usaYield. Essa garantisce anche la mutua esclusione durante il
     * richiamo del metodo setNumVocali.
     *
     */
    public void TrovaO() {
        int conta = 0;
        for (int i = 0; i < frase.length(); i++) {
            aspetta.Wait();
            if (frase.charAt(i) == 'O' || frase.charAt(i) == 'o') {
                mutex.Wait();
                conta = ptrdati.getCountO();
                mutex.Signal();
                conta++;
                mutex.Wait();
                ptrdati.setCountO(conta);
                mutex.Signal();
                if (useSleep && useYield) {
                    usaSleep();
                    usaYield();
                } else if (useSleep) {
                    usaSleep();
                } else if (useYield) {
                    usaYield();
                }
            }
            visualizza.Signal();
        }
        synchronized (ptrdati) {
            ptrdati.setNumVocali(3, conta);
        }
    }

    /**
     * @author rovelli_andrea
     *
     * @brief funzione che permette di fermare il thread per un secondo.
     *
     */
    public void usaSleep() {
        try {
            ThTrovaO.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThTrovaO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @author rovelli_andrea
     *
     * @brief funzione che permette di rimettere nella coda di attesa per
     * l'accesso alla cpu il thread.
     *
     */
    public void usaYield() {
        ThTrovaO.yield();
    }

}
