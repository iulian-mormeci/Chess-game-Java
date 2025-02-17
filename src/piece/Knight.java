package piece;

import main.GamePanel;

public class Knight extends Piece {
    public Knight(int color, int col, int row) {
        /*passaggio dei parametri alla classe piece*/
        super(color, col, row);

        /*condizione che ritorna un file di tipo bianco o nero in base al color impostato atraverso il paramentro
        passato alla creazione dell oggetto knight usando il metodo get image creato all interno della classse piece*/
        if (color == GamePanel.WHITE) {
            image = getImage("/piece/w-knight");
        }
        else{
            image = getImage("/piece/b-knight");
        }
    }
}
