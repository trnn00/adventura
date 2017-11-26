/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.BatohPane;
import UI.Mapa;
import UI.MenuPole;
import UI.OkoliPane;
import UI.VychodyPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author trnn00
 */
public class Main extends Application {
    
    
    private Mapa mapa;
    private MenuPole menu;
    public TextArea centralText;
    private IHra hra;
    private TextField zadejPrikazTextField;
    private Stage primaryStage;
    private BatohPane batoh;
    private BorderPane borderPane;
    private BorderPane rightPanel;
    private BorderPane leftPanel;
    private BorderPane centerPanel;
    private OkoliPane okoli;
    private VychodyPane vychody;
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole(this);
        batoh = new BatohPane(this);
        okoli = new OkoliPane(this);
        vychody = new VychodyPane(this);
        
        borderPane = new BorderPane();
        rightPanel = new BorderPane();
        leftPanel = new BorderPane();
        centerPanel = new BorderPane();
        
        /* Centrální text s príbehomm hry je ve formátu text are a není editovatelný - nejde mazat. */
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        centerPanel.setCenter(centralText);
        
        /* Label s textem Zadej prikaz */
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        // Zadanie prikazu
        zadejPrikazTextField = new TextField();
        zadejPrikazTextField.setPromptText("Sem zadej príkaz");
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>(){
           
            @Override
            public void handle(ActionEvent event){
        
            String vstupniPrikaz = zadejPrikazTextField.getText();
            String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
            
            centralText.appendText("\n" + vstupniPrikaz);
            centralText.appendText("\n" + odpovedHry + "\n");
            
      
            
            
            zadejPrikazTextField.setText("");
            if (hra.konecHry()){
                zadejPrikazTextField.setEditable(false);
                centralText.appendText(hra.vratEpilog());
            }
            }


        });
        
    
        /* Co vsetkoo sa nachadza v dolnej liste. */
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        //panel prikaz
        centerPanel.setLeft(mapa);
        centerPanel.setBottom(dolniLista);
        
        //menu adventury
            borderPane.setTop(menu);
            
        leftPanel.setTop(vychody);
        rightPanel.setTop(batoh);
        rightPanel.setCenter(okoli);
        
        rightPanel.setPrefWidth(60);
        leftPanel.setPrefWidth(75);
        
        borderPane.setCenter(centerPanel);
        borderPane.setLeft(leftPanel);
        borderPane.setRight(rightPanel);
        
        Scene scene = new Scene(borderPane, 1000, 450);

        primaryStage.setTitle("Adventura");

        primaryStage.setScene(scene);
        primaryStage.show();
        
        zadejPrikazTextField.requestFocus();
       
    }

    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-.txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    
    public void novaHra (){
        hra = new Hra();  
        centralText.setText(hra.vratUvitani());
        //to isté pre všetky observery- urobiť!!!
        mapa.novaHra(hra);
        batoh.novaHra(hra);
        okoli.novaHra(hra);
        vychody.novaHra(hra);
    }

    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    
    public IHra getHra(){
        return hra;
    }
}