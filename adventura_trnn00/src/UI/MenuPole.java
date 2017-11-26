/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
//importy
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 *
 * @author niku
 * class menuPole
 */
public class MenuPole extends MenuBar {

    private Main main;

    public MenuPole(Main main) {
        this.main = main;
        init();
    }
// pridanie častí Nová hra, Konec, o Programu a Nápoveda do menuPole
    private void init() {
        Menu menuSoubor = new Menu("Adventúra");
        MenuItem itemNovaHra = new MenuItem("Nová hra");
        //MenuItem itemNovaHra = new menuItem (new Image("nová hra", new.imageView(Main.class.getResourceAsStream("/zdroje/ikona.png"))));
        itemNovaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));

        MenuItem itemKonec = new MenuItem("Konec");
        // menuSoubor.getItems().addAll(itemNovaHra, itemKonec);
        //this.getMenus().addAll(menuSoubor);
        //itemKonec.setOnAction(new EventHandler <ActionEvent>())

        Menu menuHelp = new Menu("Help");
        MenuItem itemOProgramu = new MenuItem("O programu");
        MenuItem itemNapoveda = new MenuItem("Nápoveda");

        menuSoubor.getItems().addAll(itemNovaHra, itemKonec);
        menuHelp.getItems().addAll(itemOProgramu, itemNapoveda);
        this.getMenus().addAll(menuSoubor, menuHelp);
        //informace k časti o Programu
        itemOProgramu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("O adventúre");
                alert.setHeaderText("Toto je moja adventúra");
                alert.setContentText("Grafická verzia adventúry");
                alert.initOwner(main.getPrimaryStage());
                alert.showAndWait();
            }
        });
        //vloženie napoveda.html do menuPole-čast nápoveda, následne sa spustí odkaz na mnou vytvorenú html
        itemNapoveda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             Stage stage = new Stage ();
             stage.setTitle("Nápoveda");
             WebView webview = new WebView();
             
             webview.getEngine().load(Main.class.getResource("/html/napoveda.html").toExternalForm());
             
             stage.setScene(new Scene(webview,800,600));
             stage.show();
             
            }
        } );
        
        //pri zadani konec sa ukončí hra
        itemKonec.setOnAction(
                new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event
            ) {
                System.exit(0);
            }
        }
        );
        //pri zadani novahra sa spustí nova hra
        itemNovaHra.setOnAction(
                new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event
            ) {
                main.novaHra();
            }
        }
        );

    }

}
