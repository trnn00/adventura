/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
/*******************************************************************************
 * Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Nikola Trníková
 * @version   0.00.000
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;
    /***************************************************************************
     *  Konstruktor 
     *  @param plan, herní plán, v ktorom je možné v hre skúmať veci/miestnosti...
     */
    public PrikazProzkoumej(HerniPlan plan){

        this.plan = plan;
    }
    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda prozkoumej
     * 
     *  @param parametry, počet param - 1, treba zadať názov veci
     *  @return fráza, ktorá sa vypíše hráčovi
     *  
     */
    public String proved(String... parametry){
        if (parametry.length < 1) {
            //v prípade, že zadáme len príkaz prozkoumej
            return "Neviem, čo mám preskúmať.";
        }
        String nazevVeci = parametry[0];
        Vec vec = plan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            return "Vec '" + nazevVeci + "' tu nie je";
        }

        plan.getAktualniProstor().vlozVec(vec);
        return nazevVeci + ": " + vec.getPopis();
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return názov príkazu
     */
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
