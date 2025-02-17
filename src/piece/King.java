package piece;

import main.GamePanel;

public class King extends Piece{
    public King(int color, int col, int row) {
        super(color, col, row); /*passaggio dei parametri inseriti all interno della creazione dell oggetto king e
        passaggio alla classe piece*/

        /*condizione per prendere il file del re bianco o nero usando il parametro passato alla creazione dell
        oggetto e usando il metodo creato all interno della classe piece get image con passagio di parametro string
        con all interno il percorso dei file*/
        if (color == GamePanel.WHITE) {
            image = getImage("/piece/w-king");
        }
        else{
            image = getImage("/piece/b-king");
        }

    }
}
