/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Esericizio3v4;

/**
 *
 * @author rovelli_andrea
 */
public class ThVisualizzaCount extends Thread {

    private DatiCondivisi ptrdati;

    private Semaforo mutex;
    private Semaforo visualizza;
    private Semaforo aspetta;

    public ThVisualizzaCount(DatiCondivisi ptrdati, Semaforo mutex, Semaforo visualizza, Semaforo aspetta) {
        this.ptrdati = ptrdati;
        this.mutex = mutex;
        this.visualizza = visualizza;
        this.aspetta = aspetta;

    }

    public void run() {

        mutex.Wait();
        int countA = ptrdati.getCountA();
        mutex.Signal();
        mutex.Wait();
        int countE = ptrdati.getCountE();
        mutex.Signal();
        mutex.Wait();
        int countI = ptrdati.getCountI();
        mutex.Signal();
        mutex.Wait();
        int countO = ptrdati.getCountO();
        mutex.Signal();
        mutex.Wait();
        int countU = ptrdati.getCountU();
        mutex.Signal();
        while (true) {
            visualizza.Wait();
            System.out.println("a = " + countA + " e = " + countE + " i = " + countI + " o = " + countO + " u = " + countU);
            aspetta.Signal();
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
    }

}
