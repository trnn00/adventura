/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
/*******************************************************************************
 * Instance třídy {@code PrikazObsahBatohu} představují ...
 *
 * @author    Nikola Trníková
 * @version   15.12.2016
 */
public class PrikazObsahBatohu implements IPrikaz
{
    private static final String NAZEV = "obsahBatohu";
    private HerniPlan plan;
    /***************************************************************************
     * Konsturktor
     * @param plan, herní plán, ve kterém je možné v hre zobrazit obsah batohu
     */
    public PrikazObsahBatohu (HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "obsahBatohu". Sbírá věci, které se nacházejí v prostoru a které jdou sebrat, 
     *  vkádá je potom do batohu
     *  Pokud nejsou parametry, vypíše se chybové hlášení
     *
     *@param parametry - jako  parametr obsahuje jméno veci
     *@return zpráva, kterou vypíše hra hráči
     */ 
    
    public String proved(String... parametry) {
        return plan.batoh.nazvyVeciVBatohu();
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
