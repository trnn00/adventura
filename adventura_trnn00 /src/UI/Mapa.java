/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

//potrebné importy
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import utils.Observer;
import logika.IHra;
import main.Main;


/**
 *
 * @author niku
 * definovanie class Mapa
 */
public class Mapa extends AnchorPane implements Observer{

private IHra hra;
private Circle tecka;    
public Mapa(IHra hra){   
    
this.hra = hra;  
 hra.getHerniPlan().registerObserver(this);       
        
init();
} 

private void init(){
    
ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"), 400, 400, false, false));


//vytvorenie čiarky
 tecka = new Circle(10,Paint.valueOf("yellow"));
 this.getChildren().addAll(obrazekImageView,tecka);
update();



     


}
    
    
    @Override
    public void update() {
       tecka.setCenterX(hra.getHerniPlan().getAktualniProstor().getPosY());
       tecka.setCenterY(hra.getHerniPlan().getAktualniProstor().getPosX());
    }

    @Override
    public void novaHra(IHra hra) {
        hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
        
    }
    
    
}
