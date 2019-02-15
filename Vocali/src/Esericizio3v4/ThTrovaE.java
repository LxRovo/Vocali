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
 * @brief classe che utilizza un thread per cercare le lettere E presenti in una
 * frase. Estende la classe Thread e collabora con la classe DatiCondivisi.
 */
public class ThTrovaE extends Thread {

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
    private String frase;

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
    
    public ThTrovaE(boolean useSleep, boolean useYield, String s, DatiCondivisi ptrdati, Semaforo mutex,Semaforo visualizza,Semaforo aspetta) {
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
     * TrovaE quando viene richiamato il metodo start presente nella classe
     * Thread.
     *
     */
    public void run() {

        TrovaE();

    }

    /**
     * @author rovelli_andrea
     *
     * @brief funzione che permette di ricercare in una frase tutte le lettere
     * 'e' e 'E' ed in base ai valori di useSleep e useYield richiama i metodi
     * usaSleep e usaYield.Essa garantisce anche la mutua esclusione durante il
     * richiamo del metodo setNumVocali.
     *
     */
    public void TrovaE() {
        int conta = 0;
        for (int i = 0; i < frase.length(); i++) {
            aspetta.Wait();
            if (frase.charAt(i) == 'E' || frase.charAt(i) == 'e') {
                
                mutex.Wait();
                conta = ptrdati.getCountE();
                mutex.Signal();
                conta++;
                mutex.Wait();
                ptrdati.setCountE(conta);
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

            ptrdati.setNumVocali(1, conta);
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
            ThTrovaE.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThTrovaA.class.getName()).log(Level.SEVERE, null, ex);
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
        ThTrovaE.yield();
    }

}
