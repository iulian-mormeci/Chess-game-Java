package piece;

import main.GamePanel;

public class Queen extends Piece {
    public Queen(int color, int col, int row) {
        /*passaggio dei parametri passatti alla creazione del oggetto queen alla classe piece*/
        super(color, col, row);

        /*condizione per la scelta del colore della regina atraverso il parametro color passato alla creazione del
        oggetto queen */
        if (color == GamePanel.WHITE){
            image = getImage("/piece/w-queen");
        }
        else{
            /*se il color viene impostato su 1 viene ritornato l immagine della regina nera*/
            image = getImage("/piece/b-queen");
        }
    }
}
