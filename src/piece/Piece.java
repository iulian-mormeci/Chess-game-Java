package piece;

import main.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {

    /*atributi della classe piece */
    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int color;

    /*costruttore della classe piece quando andremo a creare gli oggetti dovremmo andare a passare dei parametri*/
    public Piece(int color, int col, int row){
        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);  /*richiamo al metodo get x e passagio del parametro col*/
        y = getY(row);  /*richiamo al metodo get y e passaggio del parametro row*/
        preCol = col;   /*assegnazione del parametro col all atributo che immagazinera la col precedente*/
        preRow = row;   /*assegnazione del parametro row all atributo che immagazzinera la row precedente*/
    }

    /*creazione del metodo getimage per prendere le immagini dei pezzi del gioco passando come variabile una stringa
    contenente il percorso del file*/
    public BufferedImage getImage(String imagePath) {
        BufferedImage image = null; /*impostiamo l atributo image a null prima di procedere*/

        /*uso di una condizione try and catch nel caso ci fossero degli errori*/
        try{
            /*assegnazione del immagine all atributo image*/
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        }catch (IOException e){
            /*in caso di errore vedremo all interno del terminale stampato l errore catturato*/
            e.printStackTrace();
        }
        return image;   /*se tutto e andato a buon fine il metodo ritorna l atributo image con all interno il file
        del pezzo*/
    }

    /*metodo per calcolare la posizione x*/
    public int getX(int col){
        return col * Board.SQUARE_SIZE; /*return della colonna in pixel facendo la molt della colonna per la
        dimensione dello scacchio all interno della classe board*/
    }

    /*metodo per calcolare la posizione y ovvero la riga*/
    public int getY(int row){
        return row * Board.SQUARE_SIZE; /*return della riga in pixel facendo la moltiplicazione della riga per la
        dimensione dello scacchio impostato all interno della classe board*/
    }

}
