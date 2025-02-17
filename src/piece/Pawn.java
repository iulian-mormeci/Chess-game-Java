package piece;

import main.GamePanel;

import java.awt.*;

/*classe pedone o pawn, che estende la classe piece creata in precedenza come blueprint per tutti i pezzi*/
public class Pawn extends Piece{

    /*funzione costruct erreditata dalla classe piece che estende*/
    public Pawn(int color, int col, int row) {
        super(color, col, row); /*passaggio degli stessi parametri alla classe piece che estende per poter usare i
        metodi al uso interno per il posizionamento dei pezzi e del colore degli stessi*/

        /*condizione per la scelta del colore dei pezzi*/
        /*se il color che andiamo a passare come parametro alla creazione del oggetto equivale a 0 inserira un pedone
         di colore bianco*/
        if (color == GamePanel.WHITE) {
            /*variabile image, contenente l immagine del pedone bianco presa usando il metodo get image creato all
            interno della classe piece e passaggio di parametro stringa con all interno il path dell immagine del
            pedone bianco, nel metodo alla fine del nome del file aggiunge .png*/
            image = getImage("/piece/w-pawn");
        }
        /*condizione che verra eseguita se il color e equivalente a uno e verra inserito un pedone nero*/
        else{
            /*var image con immagine del pedone nero richiesta usando il metodo get image inserito all interno della
            classe piece*/
            image = getImage("/piece/b-pawn");
        }

    }
}
