/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
//importy
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author niku
 * class služiaca na to, aby sme v paneli videli východy
 *
 */
public class VychodyPane extends VBox implements Observer{
    private IHra hra;
    private Main main;
    public VychodyPane(Main main){
        hra = main.getHra();
        this.main = main;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    // pridanie názvu východy a následný button pre možné východy
    private void init(){
        this.getChildren().add(new Label("Vychody"));
        hra.getHerniPlan().getAktualniProstor().getVychody().forEach(item -> {
            Button button;
            button = new Button(item.getNazev());
            
            button.setOnAction(event -> {
                main.centralText.appendText("\n" + hra.zpracujPrikaz("jdi " + item.getNazev()));
                
                
                

                
                });
            this.getChildren().add(button);
        });
        
        
        
        
        
    }
//update
    @Override
    public void update() {
        this.getChildren().clear();
        init();
    }
//register a delete observerov
    @Override
    public void novaHra(IHra hra) {
        hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
}
