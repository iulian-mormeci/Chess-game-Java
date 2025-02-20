package main;

import piece.*;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

/*implementato anche il runnable per usare il thread e creare un loop*/
public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1100; /*parametro per settare la larghezza della finestra*/
    public static final int HEIGHT = 800;   /*parametro per settare l altezza della finestra*/
    final int FPS = 60; /*parametro per settare il numero di update della finestra al secondo*/
    /*function construct della classe creata*/
    Thread gameThread; /*creazione di un thread per il loop del gioco*/
    Board board = new Board(); /*instance della classe board che abbiamo creato per disegnare la tavola degli sccachi*/
    Mouse mouse = new Mouse();  /*instance della classe mouse*/


    /*array list con all interno i pezzi*/
    public static ArrayList<Piece> pieces = new ArrayList<>();  /*lista che verra usata come back per resettare le
    scelte dell utente*/
    public static ArrayList<Piece> simPieces = new ArrayList<>();   /*lista che verra usata durante il gioco*/
    Piece activeP;

    /*atributi per la scelta dei colori*/
    public static final int WHITE = 0;      /*attributo per la scelta del colore bianco con valore int 0*/
    public static final int BLACK = 1;      /*attributo per la scelta del colore nero con valore int 1*/
    int currentColor = WHITE;   /*colore di partenza*/

    /*atributi di tipo booleano*/
    boolean canMove;
    boolean validSquare;

    public GamePanel() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT)); /*impostazione della dimesione della finestra*/
        setBackground(Color.BLACK); /*impostazione del colore di background*/

        /*mouse*/
        addMouseMotionListener(mouse);      /*usando questi metodi possiamo avere il movimento del mouse*/
        addMouseListener(mouse);        /*usando questo metodo possiamo avere invece le action del mouse come per
        esempio il click di un tasto*/


        /*richiamo del metodo setpieces per inserire all interno della array list, i vari pezzi*/
        setPieces();
        /*richiamo del metodo creato copypieces e passaggio di parametro all interno, in questo caso la array list
        pieces come source e la simpieces array list come target dove andranno ricopiati gli elementi*/
        copyPieces(pieces, simPieces);
    }

    /*creazione del instaza del thread*/
    public void avviaGioco(){
        gameThread = new Thread(this);  /*creazione del instance*/
        gameThread.start();     /*chiamata del metodo start che andra a chiamare il metodo run implementato dal
        runnable*/
    }

    /*inserimento dei pezzi all interno delle liste array*/
    public void setPieces(){
        /*inserimento dei pezzi bianchi e posizionamento all interno della tavola*/
        pieces.add(new Pawn(WHITE,0,6));    /*creazione dell oggetto pawn e passaggio di parametri richiesti per la
        costruzione dello stesso, colore, colonna e riga di posizionamento*/
        pieces.add(new Pawn(WHITE,1,6));
        pieces.add(new Pawn(WHITE,2,6));
        pieces.add(new Pawn(WHITE,3,6));
        pieces.add(new Pawn(WHITE,4,6));
        pieces.add(new Pawn(WHITE,5,6));
        pieces.add(new Pawn(WHITE,6,6));
        pieces.add(new Pawn(WHITE,7,6));
        pieces.add(new Rook(WHITE, 0, 7));
        pieces.add(new Knight(WHITE,1,7));
        pieces.add(new Bishop(WHITE,2,7));
        pieces.add(new Queen(WHITE,3,7));
        pieces.add(new King(WHITE,4,7));
        pieces.add(new Bishop(WHITE,5,7));
        pieces.add(new Knight(WHITE,6,7));
        pieces.add(new Rook(WHITE,7,7));

        /*inserimento dei pezzi neri e posizionamento all interno della tavola */
        pieces.add(new Pawn(BLACK,0,1));
        pieces.add(new Pawn(BLACK,1,1));
        pieces.add(new Pawn(BLACK,2,1));
        pieces.add(new Pawn(BLACK,3,1));
        pieces.add(new Pawn(BLACK,4,1));
        pieces.add(new Pawn(BLACK,5,1));
        pieces.add(new Pawn(BLACK,6,1));
        pieces.add(new Pawn(BLACK,7,1));
        pieces.add(new Rook(BLACK, 0, 0));
        pieces.add(new Knight(BLACK,1,0));
        pieces.add(new Bishop(BLACK,2,0));
        pieces.add(new Queen(BLACK,3,0));
        pieces.add(new King(BLACK,4,0));
        pieces.add(new Bishop(BLACK,5,0));
        pieces.add(new Knight(BLACK,6,0));
        pieces.add(new Rook(BLACK,7,0));
    }

    /*metodo per copiare la lista dei pezzi all interno dell lista simpieces*/
    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target){
        target.clear();
        for(int i = 0; i < source.size(); i++){
            target.add(source.get(i));
        }
    }


    /*metodo che viene inserito in automatico quando si va a implementare il runnable e il thread all interno andremo
     a inserire un game loop*/
    @Override
    public void run() {
        /*game loop*/
        /*tramite l uso di system nano time andiamo a misurare il tempo trascorso e chiamiamo il metodo update e
        repaint  ogni 1/60 secondi*/
        double drawInterval = 1000000000/FPS; /*tempo per frame espresso in nano secondi*/
        double delta = 0 ;      /*accumulatore del tempo da usare al interno del loop per avviare o meno i metodi
        update e repaint*/
        long lastTime = System.nanoTime();  /*tempo in nano secondi*/
        long currentTime;   /*variabile che andra usata al interno del loop e avra il tempo aggiornato ad ogni ciclo*/

        /*loop finche il tread e ativo*/
        while( gameThread != null){
            currentTime = System.nanoTime();    /*assegnazione del tempo in nano secondi*/

            delta += (currentTime - lastTime)/drawInterval; /*calcolo del delta*/
            lastTime = currentTime; /*assegnazione alla variabile last time la variabile curent time*/

            if(delta >= 1 ){    /*condizione per cui se il delta e maggiore o uguale a uno fa partire i metodi update
             e repaint*/
                update(); /*chiamata del metodo update che abbiamo inserito */
                repaint();  /*chiamata del metodo paint che abbiamo inserito per poter disegnare oggetti all interno
                della jpanel*/
                delta--;    /*decremento del delta per avere fps costanti*/
            }
        }
    }


    /*metodo che andra a occuparsi di fare l update di quello che succede all interno dell app come per esempio la
    posizione degli scacchi*/
    private void update(){

        /*clic del pulsante del mouse*/
        if(mouse.pressed){
            /*se l atributo activep e nullo facciamo un check per vedere  se possiamo prendere un pezzo*/
            if(activeP == null){
                /*for loop*/
                for(Piece piece : simPieces){
                    /*se il mouse e sopra un pezzo del colore corretto, alla stessa posizione x e y allora all atributo
                     activeP verra assegnato il piece*/
                    if(piece.color == currentColor && piece.col == mouse.x/Board.SQUARE_SIZE && piece.row == mouse.y/Board.SQUARE_SIZE){

                        /*assegnazione del pezzo alla variabile activeP che andra a contenere l oggetto */
                        activeP = piece;

                    }
                }
            }
            else{
                /*se il giocatore sta tenendo il pezzo invece vogliamo vedere gli spostamenti simulati all interno
                del jpanel atraverso il metodo simulate*/
                simulate();
            }

        }

        /*condizione se il player rilascia il tasto destro del mouse*/
        if (mouse.pressed == false){
            /*se l atributo activeP non e nullo e quindi ha all interno un oggetto la condizione e vera e si prosegue*/
            if(activeP != null){
                /*se l atributo validsquare e true si prosegue perche significa che il giocatore ha scelto un
                quadrato valido per spostare il pezzo scelto*/
                if(validSquare){
                    /*se ovviamente il quadrato e valido andiamo ad aggiornare la sua posizione*/
                    activeP.updatePosition();
                }
                else{
                    /*se il quadrato scelto al momento del rilascio del mouse non e valido andiamo a resetare la
                    posizione del atributo activeP che e una un atributo che va a tenere temporaneamente l oggetto
                    che andiamo a selezionare con il mouse*/
                    activeP.resetPosition();
                    /*nel momento che non teniamo premuto il tasto del mouse andiamo a rendere nuovamente l atributo
                    activeP nullo per poter andare a selezionare un altro pezzo*/
                    activeP = null;
                }
            }
        }

    }

    private void simulate(){

        canMove = false;
        validSquare = false;

        /*se un pezzo viene tenuto e non rilasciato verra aggiornata la sua posizione per avere a video gli
        spostamenti*/
        activeP.x = mouse.x -  Board.HALF_SQUARE_SIZE;    /*aggiornamento della posizione del activep x con la posizione del mouse x
        a cui
        andremmo a sottrarre la meta della grandezza dell quadrato per avere il mouse al centro e non in alto a
        sinistra*/
        activeP.y = mouse.y -  Board.HALF_SQUARE_SIZE;    /*aggiornamento della posizione del active p y con la posizione del mouse y a
        cui
        sottraiamo la meta del quadrato pe avere il mouse al centro e non in alto a sinistra*/
        /*aggioranamento della posizione del pezzo tramite i due metodi all interno della classe piece get col e get
        row e passaggio all interno dei metodi i parametri necesssari, ovvero la posizione x e y data dal oggetto
        mouse attraverso i listener aggiunti in questa classe*/
        activeP.col = activeP.getCol(activeP.x);    /*aggioranamento della colonna*/

        activeP.row = activeP.getRow(activeP.y);    /*aggiornamento della riga*/

        /*verifica se il giocatore sta con il pezzo su un quadrato valido per il tipo di pezzo che ha selezionato*/
        if(activeP.canMove(activeP.col, activeP.row)){
            canMove = true;
            validSquare = true;
        }

    }

    /*metodo che si occupa di disegnare i pezzi all interno della finestra*/
    public void paint(Graphics g){
        super.paintComponent(g);    /*metodo in jcomponent che jpanel eredita e viene usato per disegnare oggetti all
         interno del pannello*/

        Graphics2D g2 = (Graphics2D) g; /*cambiamento di graphics g in graphics 2d prima di chiamare il metodo draw
        che riceve come parametro grapichs 2d*/

        /*board*/
        board.draw(g2); /*richiamo del metodo draw all interno della classe Board tramite l oggetto creato prima
        board*/

        /*Pezzi*/
        /*inserimento dei pezzi all interno della tavola di gioco usando il metodo drawn e passando come parametro il
         g2 che e un elemento graphics2d*/
        for(Piece piece : simPieces){
            piece.draw(g2);
        }

        /*condizione per andare a colorare il quadrato nuovo che si sta scegliendo con un colore bianco trasparente
        da far capire la selezione*/
        if(activeP != null){
            if(canMove){
                /*impostiamo il colore a bianco*/
                g2.setColor(Color.white);
                /*impostazione della trasparenza a 0.7*/
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            /*uso del metodo fill rectangle e passando come parametri la colonna del pezzo attivo moltiplicato per la
             grandezza del quadrato e lo stesso per la riga, mentre per le dimesioni si passa soltanto gli atributo
             square size della classa board che sono equivalenti a 100 pixel*/
                g2.fillRect(activeP.col * Board.SQUARE_SIZE, activeP.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                /*reset della trasparenza*/
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }

            /*alla fine inseriamo il metodo draw per poter vedere effettivamente sullo schermo l effetto di
            selezione, se lo avessimo messo prima di tutti i settaggi non avremmo visto nulla */
            activeP.draw(g2);
        }

    }


}
