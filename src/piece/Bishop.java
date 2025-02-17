package piece;

import main.GamePanel;

public class Bishop extends Piece {
    public Bishop(int color, int col, int row) {
        /*passaggio dei parametri alla classe piece*/
        super(color, col, row);

        /*condizione che ritorna un file bianco o nero in base all parametro che si inserisce alla creazione dell
        oggetto bishop, se zero verra ritornato quello bianco altrimenti quello nero, tutto avviene tramite il metodo
         get image creato all interno della classe piece*/
        if (color == GamePanel.WHITE){
            image = getImage("/piece/w-bishop");
        }
        else {
            image = getImage("/piece/b-bishop");
        }
    }
}
