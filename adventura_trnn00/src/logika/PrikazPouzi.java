/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Třída PrikazPouzi implementuje pro hru příkaz pouzi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Nikola Trníková
 * @version   15.12.2016
 */
public class PrikazPouzi implements IPrikaz {
    private static final String NAZEV = "pouzi";
    private HerniPlan plan;
    private Batoh batoh;
    private Hra hra;
    /***************************************************************************
     * Konstruktor
     * @param plan, herní plán, po kterém se ve hře prochází
     * @param hra, sa odkazuje na hru, s ktorou sa bude pracovať
     */
    public PrikazPouzi(HerniPlan plan, Hra hra){
        this.plan = plan;
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "použi". Slúži na používanie vecí
     *  @param parametry meno veci
     *  @return - fráza/pridanie
     *
     */
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // kontrola, či mi prišli nejaké parametre
            return "Co mám použiť? Musíš zadat meno veci";
        }
        String vecNaPouzitie = parametry[0].toLowerCase();
        if (!plan.batoh.obsahujeVecVBatohu(vecNaPouzitie)) {//použije prvú vec z príkazu  
            return "Vec nie je v batohu\n" + plan.batoh.nazvyVeciVBatohu();
        }
        //vec mám v batohu, idem ju použiť, dá sa?
        if (!plan.batoh.seznamVeci.get(vecNaPouzitie).pouzitelna){
            return "Vybraná vec je nepoužiteľná";
        }

        if (vecNaPouzitie.equals("kreditka")){
            return pouzitieKreditky();
        }

        if (vecNaPouzitie.equals("zbran")){
            return pouzitieZbran();
        }
        //ak dam turkovi peniaze a jedlo  - odomkne sa mi ambasada
        if (vecNaPouzitie.equals("jedlo") || vecNaPouzitie.equals("peniaze")) {
            return pouzitieJedloPeniaze();
        }
        //ak pouzijem pas na amabasade -> dostanem viza
        if (vecNaPouzitie.equals("pas")) {
            if (!plan.aktualniProstor.getNazev().equals("ambasada")) {
                return "Toto použiješ na ambasáde";
            }
            //vlozim peniaze do priestoru a hrac ich moze zobrat (neriesim ci je plny batoh)
            Prostor aktualniProstor = plan.getAktualniProstor();
            aktualniProstor.vlozVec(plan.viza);
            plan.internetova_kaviaren.zamcena = false;
            return "Ziskal/a si viza a odomkla sa ti internetova_kaviaren.\nZober si viza.\n" + aktualniProstor.dlouhyPopis();
        }
        if (vecNaPouzitie.equals("rezervacia")) {
            if (!plan.aktualniProstor.getNazev().equals("internetova_kaviaren")) {
                return "Toto pouzijes v internetovej kaviarni";
            }
            //vlozim letenku do priestoru a hrac si ju moze zobrat (neriesim ci je plny batoh)
            Prostor aktualniProstor = plan.getAktualniProstor();
            aktualniProstor.vlozVec(plan.letenka);
            plan.letisko.zamcena = false;
            return "Ziskal/a si letenku. Zober si ju.\n" + aktualniProstor.dlouhyPopis();
        }
        return "Neviem ako to mam pouzit";
    } 

    /**
     *  Metoda použitie kreditky
     *@return fráza,veta ktorá sa vypíše
     */
    private String pouzitieKreditky() {
        //idem pouzit kreditku. mozem v tomto priestore?
        if (!(plan.aktualniProstor.getNazev().equals("banka"))) {
            return "V tomto priestore nie je možné kreditku použiť";
        }
        //vlozim peniaze do priestoru a hrac ich moze zobrat (neriesim ci je plny batoh)
        Prostor aktualniProstor = plan.getAktualniProstor();
        aktualniProstor.vlozVec(plan.peniaze);
        return "Vybral/a si peniaze. Zober ich.\n" + aktualniProstor.dlouhyPopis();
    }

    /**
     *  Metoda použitie zbrane
     *@return fráza,veta ktorá sa vypíše
     */
    private String pouzitieZbran () {
        //idem pouzit zbran, pozriem ci som v miestnosti s osobou
        if ((plan.aktualniProstor.getNazev().equals("dom_turka")||
            ( plan.aktualniProstor.getNazev().equals("mesto")))){
            hra.setKonecHry(true);
            return "Hra ukončená zabitím postavy, prichádza polícia a hráč \n"
            + "zostáva uväznený do konca svojho života...KONEC!";
        }
        return "Nemáš koho zabiť, strielaš naprázdno";
    }

    /**
     *  Metoda použitie jedla aj peňazí
     *@return fráza,veta ktorá sa vypíše
     */
    private String pouzitieJedloPeniaze () {
        if (!plan.aktualniProstor.getNazev().equals("dom_turka")) {
            return "Toto pouzijes v dome turka";
        }
        //skontolujem ci mam aj jedlo aj peniaze
        if (plan.batoh.obsahujeVecVBatohu("jedlo") && plan.batoh.obsahujeVecVBatohu("peniaze")) {
            plan.ambasada.zamcena = false;
            plan.turek_dom.setProslov("Som rád, že si splnil, čo si mal.  \n"
                + "Potrebuješ ísť na ambasádu pre víza. \n"
                +"Následne si musíš vziať rezerváciu v kaviarni. Tak dostaneš letenky.  \n"
                +"Odtiaľ  už len na letisko a odcestovať domov.  \n");
            return "Použil si peniaze a zároveň jedlo,odomkla sa ti miesnosť ambasáda...\n"
            +"Pohovor si s turkom pre ďalšie inštrukcie\n";
        }
        else {
            return "Potrebuješ aj peniaze aj jedlo, skús pohľadať...";
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */

    public String getNazev() {
        return NAZEV;
    }

}
