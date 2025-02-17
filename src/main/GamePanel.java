package main;

import javax.swing.JPanel;
import java.awt.*;

/*implementato anche il runnable per usare il thread e creare un loop*/
public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1100; /*parametro per settare la larghezza della finestra*/
    public static final int HEIGHT = 800;   /*parametro per settare l altezza della finestra*/
    final int FPS = 60; /*parametro per settare il numero di update della finestra al secondo*/
    /*function construct della classe creata*/
    Thread gameThread; /*creazione di un thread per il loop del gioco*/
    Board board = new Board(); /*instance della classe board che abbiamo creato per disegnare la tavola degli sccachi*/

    public GamePanel() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT)); /*impostazione della dimesione della finestra*/
        setBackground(Color.BLACK); /*impostazione del colore di background*/

    }

    /*creazione del instaza del thread*/
    public void avviaGioco(){
        gameThread = new Thread(this);  /*creazione del instance*/
        gameThread.start();     /*chiamata del metodo start che andra a chiamare il metodo run implementato dal
        runnable*/
    }

    /*metodo che viene inserito in automatico quando si va a implementare il runnable e il thread all interno andremo
     a inserire un game loop*/
    @Override
    public void run() {
        /*game loop*/
        /*tramite l uso di system nano time andiamo a misurare il tempo trascorso e chiamiamo il metodo update e
        repaint  ogni 1/60 secondi*/
        double drawInterval = 1000000000/FPS; /*tempo per frame espresso in nano secondi*/
        double delta = 0 ;      /*accumulatore del tempo da usare al interno del loop per avviare o meno i metodi
        update e repaint*/
        long lastTime = System.nanoTime();  /*tempo in nano secondi*/
        long currentTime;   /*variabile che andra usata al interno del loop e avra il tempo aggiornato ad ogni ciclo*/

        /*loop finche il tread e ativo*/
        while( gameThread != null){
            currentTime = System.nanoTime();    /*assegnazione del tempo in nano secondi*/

            delta += (currentTime - lastTime)/drawInterval; /*calcolo del delta*/
            lastTime = currentTime; /*assegnazione alla variabile last time la variabile curent time*/

            if(delta >= 1 ){    /*condizione per cui se il delta e maggiore o uguale a uno fa partire i metodi update
             e repaint*/
                update(); /*chiamata del metodo update che abbiamo inserito */
                repaint();  /*chiamata del metodo paint che abbiamo inserito per poter disegnare oggetti all interno
                della jpanel*/
                delta--;    /*decremento del delta per avere fps costanti*/
            }
        }
    }


    /*metodo che andra a occuparsi di fare l update di quello che succede all interno dell app come per esempio la
    posizione degli scacchi*/
    private void update(){

    }

    /*metodo che si occupa di disegnare i pezzi all interno della finestra*/
    public void paint(Graphics g){
        super.paintComponent(g);    /*metodo in jcomponent che jpanel eredita e viene usato per disegnare oggetti all
         interno del pannello*/

        Graphics2D g2 = (Graphics2D) g; /*cambiamento di graphics g in graphics 2d prima di chiamare il metodo draw
        che riceve come parametro grapichs 2d*/

        board.draw(g2); /*richiamo del metodo draw all interno della classe Board tramite l oggetto creato prima
        board*/

    }


}
