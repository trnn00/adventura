package logika;
/*******************************************************************************
 * Třída PrikazMluv implementuje pro hru příkaz mluv.
 * Tato třída je součástí jednoduché textové hry.
 * @author    Nikola Trníková
 * @version   15.12.2016
 */
public class PrikazMluv implements IPrikaz
{
    //== DATOVÉ ATRIBUTY (STATICKÉ I INSTANCÍ)==================================
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /***************************************************************************
     * Konstruktor
     * @param plan, herní plán, ve kterém je možné v hre "mluvit"
     */
    public PrikazMluv(HerniPlan plan){
        this.plan = plan;
    }
    /**
     *  Provádí příkaz "mluv". Vďaka tomuto príkazu niektoré postavy mluví a tak pomohou 
     *  hráčovi ďalej v hre
     *  @param parametry meno postavy
     *  @return - odpoveď postavy
     *
     */ 
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Treba zadať názov postavy, s ktorou chceš hovoriť.";
        }
        String jmeno = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava postava = aktualniProstor.najdiPostavu(jmeno);
        if (postava == null) {
            return "Táto postava tu není.";
        }
        return postava.getProslov();  
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