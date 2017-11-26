/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
//importy
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author niku
 * panel okoli, realizovano pomocou extends Vbox na základe požiadavku
 */
public class OkoliPane extends VBox implements Observer{
    
    private IHra hra;
    private Main main;
    public OkoliPane(Main main){
        hra = main.getHra();
        this.main = main;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    //veci okolo - veci, ktoré nie su v batohu
    private void init(){
        if(hra.konecHry()){
            main.novaHra();
        }
        this.getChildren().add(new Label("Veci okolo"));
        hra.getHerniPlan().getAktualniProstor().getVeci().values().forEach(item -> {
            String resource = "/zdroje/"+item.getJmeno()+".png";
            ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream(resource), 32, 32, false, false));
            Button button;
            button = new Button("",obrazekImageView);   
            button.setOnAction(event -> {
                main.centralText.appendText("\n" + hra.zpracujPrikaz("vezmi " + item.getJmeno()));
                hra.getHerniPlan().notifyAllObservers();
                });
            this.getChildren().add(button);
        });
   
    } 
//služí na samostatný update
    @Override
    public void update() {
        this.getChildren().clear();
        init();
    }
//delete a register observerov
    @Override
    public void novaHra(IHra hra) {
        hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
}
