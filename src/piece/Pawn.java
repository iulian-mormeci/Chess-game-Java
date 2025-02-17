package piece;

import java.awt.*;

/*classe pedone o pawn, che estende la classe piece creata in precedenza come blueprint per tutti i pezzi*/
public class Pawn extends Piece{

    /*funzione costruct erreditata dalla classe piece che estende*/
    public Pawn(int color, int col, int row) {
        super(color, col, row); /*passaggio degli stessi parametri alla classe piece che estende per poter usare i
        metodi al uso interno per il posizionamento dei pezzi e del colore degli stessi*/

    }
}
