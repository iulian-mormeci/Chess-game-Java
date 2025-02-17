package main;

import java.awt.*;


/*classe board che useremo per disegnare la tavola degli scacchi*/
public class Board {

    final int MAX_COL = 8; /*variabile con il numero massimo di colonne*/
    final int MAX_ROW = 8;  /*variabile con numero massimo di righe*/

    public static final int SQUARE_SIZE = 100;      /*variabile statica con la dimesione del quadrato della tavola
    degli scacchi che sara di 100 pixels*/
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE / 2; /*variable con all interno assegnato il valore di
    mezzo quadrato*/

    /*metodo per disegnare la tavola degli scacchi*/
    public void draw(Graphics2D g2){
        int c = 0;  /*variabile per settare i due colori della tavola */

        /*loop per disegnare la tavola degli scacchi passando le dimensioni impostate prima*/
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {

                /*condizione per poter disegnare la tavola con i due colori usando la variabile creata prima */
                if( c == 0){
                    /*colorazione del quadrato usando i colori rgb*/
                    g2.setColor(new Color(210,165,125));
                    c = 1; /*riassegnazione della variabile c cosi al prossimo ciclo andra a usare il secondo colore */
                }
                else{
                    /*condizione che viene eseguita quando c non e uguale a 0*/
                    g2.setColor(new Color(175,115,70));
                    c = 0;  /*riassegnazione valore alla variabile per ricominciare la sequenza*/
                }

                /*all interno del metodo fillrect passiamo la posizione della colonna e della riga e poi passiamo la
                grandezza atraverso l atributo impostato prima */
                g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }

            /*condizione al di fuori del loop per il disegno che va a reimpostare la variabile c per riusare l ultimo
             colore alla fine del secondo loop che disegna le colonne altrimenti non viene a scacchi*/
            if(c == 0 ){
                c = 1;
            }
            else{
                c = 0;
            }
        }

    }

}
