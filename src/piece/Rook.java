package piece;

import main.GamePanel;

public class Rook extends Piece {
    public Rook(int color, int col, int row) {
        /*passsaggio dei parametri inseriti alla creazione del oggetto rook alla classe piece*/
        super(color, col, row);

        /*condizione che ritorna un file di colore diverso in base al parametro che si passa all interno della
        creazione dell oggetto rook, se zero viene passatto il file bianco altrimenti quello nero usando il metodo
        get image creato all interno della classe piece e passando come parametro una stringa contenente il percorso
        dell file*/
        if (color == GamePanel.WHITE){
            image = getImage("/piece/w-rook");
        }
        else {
            image = getImage("/piece/b-rook");
        }
    }
}
