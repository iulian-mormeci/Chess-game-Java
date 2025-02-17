package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        /*Creazione della finestra di gioco e titolo della finestra come parametro*/
        JFrame window = new JFrame("Scacchi");

        /*chiusura del programma alla chiusura della finestra*/
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); /*rendiamo la finestra non modificabile*/

        /*inst della finestra */
        GamePanel gp = new GamePanel(); /*instance di game panel con nome gp*/
        window.add(gp); /*aggiunge gp al instance di jframe*/
        window.pack();  /*la inserisce al interno della finestra e ne prende le dimensioni*/

        window.setLocationRelativeTo(null); /*facciamo aprire la finestra al centro dello schermo*/
        window.setVisible(true); /*rendiamo visibile la finestra*/

        /*il metodo verra avviato dopo la creazione della finestra*/
        gp.avviaGioco();    /*chiamata del metodo avvia gioco con all interno il thread e la chiamata al metodo run
        di runnable*/
    }
}
