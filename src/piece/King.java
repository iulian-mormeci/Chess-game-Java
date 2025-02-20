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

    /*metodo del pezzo re, che andra a ritornare e sovrascrivere il metodo all interno della classe piece se puo o
    meno muoversi basandosi sui parametri in ingresso */
    public boolean canMove(int targetCol, int targetRow){
        /*condizione che richiama il metodo all interno della classe piece per verificare che il giocatore sta
        andando a posizionare il pezzo all interno della tavola di gioco, se il metodo ritorna true prosegue*/
        if(isWithInBoard(targetCol, targetRow)){
            /*condizione che verra eseguita se la precendente e true, questa condizione verifica che il re si sta
            muovendo soltanto di un quadrato seguendo le regole del gioco, andando a fare il calcolo tra la colonna
            che si sta scegliendo e la colonna precedente, se la condizione e true ritorna un valore booleano true*/
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 || Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1){
                return true;
            }
        }

        return false;
    }
}
