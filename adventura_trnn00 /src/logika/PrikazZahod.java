/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
/*******************************************************************************
 * Třída PrikazZahod implementuje pro hru příkaz zahod.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Nikola Trníková
 * @version   15.12.2016
 */
public class PrikazZahod implements IPrikaz
{
    //==Datové atributy (statické a instancí)============================
    private static final String NAZEV = "zahod";
    private HerniPlan plan;
    private Batoh batoh;

    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /***************************************************************************
     * Konsturktor
     * @param plan, herní plán, po kterém se ve hře prochází
     * @param batoh, batoh, ve kterém jsou veci 
     */
    public PrikazZahod(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     *  Provádí příkaz "zahod". Zahodí veci z batohu.
     *  Pokud nejsou parametry, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno veci
     *@return zpráva, kterou vypíše hra hráči
     */ 
   
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // kontrola, či mi prišli nejaké parametre
            return "Čo mám zahodiť? Musíš zadat meno veci";
        }

        String jmenoVeci = parametry[0];
        this.batoh = plan.getBatoh(); //získam aktuálny obsah batohu
        Vec vec = batoh.vyndejVec(jmenoVeci);

       if (vec == null) {
            return "Nemôžeš zahodiť niečo, čo v batohu nie je!";
        }
        else {
            plan.getAktualniProstor().vlozVec(vec);
            return "Hráč zahodil " + jmenoVeci +" z batohu \n" 
            + batoh.nazvyVeciVBatohu();
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
