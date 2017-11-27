/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
/*******************************************************************************
 * Třída PrikazVezmi implementuje pro hru příkaz vezmi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Nikola Trníková
 * @version   15.12.2016
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    private HerniPlan plan;
    /***************************************************************************
     * Konsturktor
     * @param plan herný plán, v ktorom sa hra prechádza
     */
    public PrikazVezmi(HerniPlan plan) {
        this.plan = plan;
    }
    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda pro provedení příkazu ve hře. Metoda pridá vec do batohu, ak je to možné. 
     *  
     *  @param parametry počet parametrov =1, zadává se názov veci
     *  @return veta, fráza, ktorá sa hráčovi vypíše
     *  
     */
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            //ak hráč nešpecifikuje, čo chce vziať
            return "Neviem, čo mám zobrať. Treba zadať názov veci";
        }
        String nazevVeci = parametry[0].toLowerCase();
        Prostor aktualniProstor = plan.getAktualniProstor();
        Batoh batoh = plan.getBatoh();
        if (aktualniProstor.jeVecVProstoru(nazevVeci)) {
            Vec vec = aktualniProstor.najdiVecVProstoru(nazevVeci);
            if (vec == null) {
                return "vec '" + nazevVeci + "' tu nie je. \n";
            }
            else {

                if (batoh.jeVolneMisto() > 0) {
                    int vlozenie = batoh.vlozVecDoBatohu(vec);
                    if (vlozenie == 1) {
                        aktualniProstor.odeberVec(nazevVeci);
                        return "Vložil/a si túto vec do batohu. \n"
                        + batoh.nazvyVeciVBatohu(); //vypíše obsah batohu
                    } else if (vlozenie == 2) {
                        return "Tuto vec nemožno vziať, je neprenositelná. \n";                                               
                    } 
                }
                else {
                    return "To sa ti už do batohu nezmestí! \n" 
                    + "Ak chceš niečo zobrať, musíš najskôr niečo z batohu zahodiť. \n"; 
                }

                return "Tento predmet nepoznám.";
            }
        }
        return "Tato vec tu nie je.";
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