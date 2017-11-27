package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Nikola Trníková
 * @version    ZS 2016/2017
 */
public class HerniPlan implements Subject {

    public Prostor aktualniProstor;
    public Prostor ambasada;
    public Batoh batoh;
    public Postava turek_dom;
    //tieto miestnosti potrebujem aj z inych tried
    public Prostor banka;
    public Prostor internetova_kaviaren;
    public Prostor letisko;
    //tieto veci potrebujem aj z inych tried
    public Vec peniaze;
    public Vec viza;
    public Vec letenka;
    private Prostor viteznyProstor;
    private List <Observer> listObserveru = new ArrayList<Observer>();

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        this.batoh = new Batoh();
    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví hotel.
     */
    private void zalozProstoryHry() {
        // Vytvorenie priestorov
        //public Prostor(String nazev, String popis)
        Prostor hotel = new Prostor("hotel" , "Možeš ísť jedine do mesta. \n"
                + "Nezabúdaj, že chceš prežiť a dostať sa čo najskôr domov!",28,186);
        Prostor mesto = new Prostor("mesto", "Máš 3 možnosti kam ísť. Čo tak najprv zistiť, kde si? Opýtaj sa.",119,184);
        banka = new Prostor("banka","Everything is about money. Tu ich môžeš vybrať.",169,88);
        Prostor obchod = new Prostor("obchod","Možnosť kúpiť jedlo.",176,305);
        Prostor dom_turka = new Prostor ("dom_turka", "Návšteva domáceho príde vždy vhod. Ver, že pomôže.",207,187);
        ambasada = new Prostor ("ambasada", "Miestnosť pre získanie víz",273,61);
        internetova_kaviaren =new Prostor ("internetova_kaviaren", "Miestnosť pre vytvorenie rezervácie leteniek",293,185);
        letisko = new Prostor("letisko", "Nádej zomiera posledná. Žeby záchrana?",310,323);

        //uzamknutie priestorov
        ambasada.nastavZamek(true);
        internetova_kaviaren.nastavZamek(true);
        letisko.nastavZamek(true);

        //definovanie priestorov pre výhru
        viteznyProstor = letisko;

        // Priradenie prechodov medzi miestnosťami -  priestormi (susediace priestory)
        hotel.setVychod(mesto);
        mesto.setVychod(hotel);
        mesto.setVychod(banka);
        banka.setVychod(mesto);
        mesto.setVychod(obchod);
        obchod.setVychod(mesto);
        mesto.setVychod(dom_turka);
        dom_turka.setVychod(mesto);
        dom_turka.setVychod(ambasada);
        ambasada.setVychod(dom_turka);
        ambasada.setVychod(internetova_kaviaren);
        internetova_kaviaren.setVychod(ambasada);
        internetova_kaviaren.setVychod(letisko);
        letisko.setVychod(internetova_kaviaren);

        // vytvorenie vecí
        //public Vec(String nazev, String popis, boolean prenositelna, boolean pouzitelna)
        Vec flasa_alkoholu = new Vec("flasa_alkoholu", "čo sa to včera dialo?", true, false);
        Vec mobil = new Vec("mobil", "vybitý mobil mi nepomôže", true, false);
        Vec budik = new Vec("budik", "budik patri hotelu, nebuď zlodej", false, false);
        Vec kniha = new Vec("kniha", "poctivý študent?", true, false);
        Vec kreditka = new Vec("kreditka", "peniaze sa vždy hodia, snáď tam niečo bude", true, true);
        Vec pas = new Vec("pas", "dôležitý doklad", true, true);
        Vec zbran = new Vec("zbran", "možnosť niekoho zabiť", true, true);
        Vec jedlo = new Vec("jedlo", "z toho sa Turek naje", true, true);
        Vec peniaze_v_obchode = new Vec("peniaze_v_obchode", "zákony pre zlodejov sú zlé", false, false);
        Vec alkohol = new Vec("alkohol", "zachovaj chladnú hlavu", false, false);
        Vec bageta_maso = new Vec("bageta", "turek nemá rád mäso", true, false);
        Vec rezervacia = new Vec ("rezervacia", "cesta k letenke", true, true);
        letenka = new Vec ("letenka", "home sweet home", true, true);
        peniaze = new Vec("peniaze", "to Turek chce", true, true);
        viza = new Vec ("viza", "treba na odlet", true, true);
        
        // umiestnenie vecí do priestoru - miestností

        hotel.vlozVec(flasa_alkoholu);
        hotel.vlozVec(mobil);
        hotel.vlozVec(budik);
        hotel.vlozVec(kniha);
        hotel.vlozVec(kreditka);
        hotel.vlozVec(pas);
        hotel.vlozVec(zbran);
        obchod.vlozVec(jedlo);
        obchod.vlozVec(peniaze_v_obchode);
        obchod.vlozVec(alkohol);
        obchod.vlozVec(bageta_maso);
        //      //ambasada.vlozVec(viza);
        internetova_kaviaren.vlozVec(rezervacia);

        //Hra začína v priestore Hotel 
        aktualniProstor = hotel;  
        viteznyProstor = letisko;

        //vytvorime nové postavy
        //public Postava(String jmeno, String proslov)
        Postava turista1 = new Postava ("turista1", "Hráč: Where am I? \n"
                + "Turista: 這是土耳其!\n");
        Postava turista2 = new Postava ("turista2", "Hráč: Where am I? \n"
                + "Turista: This is Turkey!\n");
        Postava turek_mesto = new Postava ( "turek_mesto", "Pomôžem ti dostať sa z tohto mesta,  \n"
                + "ak mi prinesieš nejaké jedlo a zároveň \n"
                +"mi dobre zaplatíš. Potom ti poviem plán, ako sa dostať  \n"
                +"bezpečne domov. Myslím, že to veľmi chceš!!! \n"
                +"Nezabúdaj,jedlo a peniaze!!! Vidíme sa čoskoro!!!  \n");    
        turek_dom = new Postava ( "turek_dom", "mas pre mna nieco?\n");

        //vloženie postáv do priestorov    
        mesto.vlozPostavu(turista1);
        mesto.vlozPostavu(turista2);
        mesto.vlozPostavu(turek_mesto);
        dom_turka.vlozPostavu(turek_dom);
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyAllObservers();
    }

    /**
     * Metoda vrací obsah batohu.
     * @return vráti  batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }

    /**
     * Metoda setBatoh.
     * @param batoh - set k batohu
     */
    public void setBatoh(Batoh batoh) {
        this.batoh=batoh;

    }

    /**
     * Metoda hracvyhral.
     * @return true - vyhral false prehral
     */
    public boolean hracVyhral() {
        if (aktualniProstor.equals(viteznyProstor)) {
            return true;
        }

        return false;
    }
    //zaregistrovanie observeru (zmeny)  
    @Override
    public void registerObserver(Observer observer) {
    listObserveru.add(observer);
    }  
    
    //delete observeru (zmeny)
    @Override
    public void deleteObserver(Observer observer) {
        listObserveru.remove(observer);
    }
    
    //notify all 
    public void notifyAllObservers() {
        for (Observer listObserveruItem : listObserveru){
            listObserveruItem.update();
      }
    }
    
    
}

    
        
        
    


