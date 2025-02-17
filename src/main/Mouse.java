package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*classe che estende una classe astratta di java e all interno abbiamo 4 metodi per l uso del mouse all interno del
gioco*/
public class Mouse extends MouseAdapter {

    /*atributi int x e y serviranno per la posizione del mouse cosi da sapere quando siamo sopra un pezzo*/
    public int x,y;
    /*atributo di tipo booleano che serve per sapere se abbiamo cliccato il mouse oppure no*/
    public boolean pressed;

    /*metodo del mouse cliccato, all interno l atributo booleano viene assegnato il valore true*/
    @Override
    public void mousePressed(MouseEvent event){

        pressed = true;
    }

    /*metodo rilascio del click del mouse e assegnazione del atributo booleano in false*/
    @Override
    public void mouseReleased(MouseEvent event){

        pressed = false;
    }

    /*metodo per il movimento del mouse e trascinamento*/
    @Override
    public void mouseDragged(MouseEvent event){
        /*atributi x e y calcolati usandi il metodo getx e gety */
        x = event.getX();
        y = event.getY();
    }

    /*metodo del mouse soltanto per il movimento*/
    @Override
    public void mouseMoved(MouseEvent event){

        /*atributi x e y calcolati usando il metodo getx e get y*/

        x = event.getX();
        y = event.getY();

    }

}
