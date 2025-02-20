package piece;

import main.Board;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {

    /*atributi della classe piece */
    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int color;
    public Piece hittingP;

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

    /*metodo per resettare la posizione del pezzo*/
    public void resetPosition(){
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }

    /*metodo che ritorna la colonna del oggetto e vuole come parametro un int*/
    public int getCol(int x){
        return (x + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
    }

    /*metodo che ritorna la riga dell oggetto e vuole come parametro un int*/
    public int getRow(int y){
        return (y + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
    }

    /*metodo per andare ad aggiornare la posizione del pezzo quando si rilascia il tasto del mouse*/
    public void updatePosition(){
        x = getX(col);  /*andiamo a richiamare il metodo getx e passiamo all interno del metodo la col per andare a
      calcolare la posizione della colonna*/
        y = getY(row);  /*andiamo a richiamare il metodo gety e passiamo al interno del metodo la row per andare a
        calcolare la riga */
        preCol = getCol(x); /*andiamo anche ad aggiornare la position negli atributi che tengono la position
        precedente*/
        preRow = getRow(y); /*andiamo ad aggiornare anche la position negli atributi che tengono la position della
        riga precedente*/
    }

    /*metodo per inserire i file all interno del jpanel*/
    public void draw(Graphics2D g2){
        /*inserimento dei file all interno della tavolo di gioco usando il metodo drawimage che e un metodo astrato
        di java, passando come parametri l immagine che viene restituita dal metodo get image, la posizione che viene
         data dagli atributi x e y che all loro interno hanno un chiamata a dei metodi creati da me per avere la
         posizione in pixel all interno del jpanel*/
        g2.drawImage(image,x,y,Board.SQUARE_SIZE,Board.SQUARE_SIZE, null);
    }

    /*metodo che andra a ritornare un booleano se il player sta andando a spostare un pezzo al interno della tavola
    degli scacchi o meno*/
    public boolean isWithInBoard(int targetCol, int targetRow){
        /*condizione per verificare se le coordinate che il giocatore sta andando a selezionare sono o no all interno
         della tavola e se e all interno della tavola ritorna valore booleano true altrimenti ritorna un valore false*/
        if (targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7){
            return true;
        }
        return false;
    }

    /*metodo che andra a ritornare una valore booleano se il pezzo si potra muovere o meno nella nuova posizione*/
    public boolean canMove(int targetCol, int targetRow){
        return false;
    }

    /*metodo per verificare se il pezzo che abbiamo selezionato ne sta colpendo un altro atraverso i parametri che
    stiamo passando al metodo, andra al interno della lista dei pezzi e ne verifichera la posizione*/
    public Piece getHittingP(int targetCol, int targetRow){
        /*con questo loop stiamo controlliamo la posizione dei vari pezzi*/
        for(Piece piece : GamePanel.simPieces){
            /*con questa condizione andiamo a vedere se ci sono pezzi che hanno la stessa colonna o la stessa riga
            tranne il pezzo attivo che abbiamo selezionato*/
            if(piece.col == targetCol && piece.row == targetRow && piece != this){
                /*se trova una corrispondenza ritorna il pezzo*/
                return piece;
            }
        }
        /*se non si trovano corrispondenze ritorna null*/
        return null;
    }

    /*metodo che ritorna un valore booleano, se il il metodo hitting p che stiamo richiamando ritorna un valore null
    significa che quell quadrato non e occupato ed e valido*/
    public boolean isValidSquare(int targetCol, int targetRow){
        /*atributo che andra a contenere il valore di ritorno del metodo gethittingp*/
        hittingP = getHittingP(targetCol, targetRow);

        /*se il valore di hitting p e nullo significa che il quadrato scelto e disponibile e possiamo usarlo*/
        if(hittingP == null){
            /*se la condizione e true ritorna un valore booleano true*/
            return true;
        }
        /*se la condizione non e true ritorna un valore di tipo false*/
        return false;
    }

}
