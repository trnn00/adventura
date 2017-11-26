package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *  
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Riha, Nikola Trníková
 * @version    ZS 2016/2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;
    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     *  @param hra sa odkazuje na hru, s ktorou sa bude pracovať
     */    
    public PrikazJdi(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám ísť? Musíš zadat názov východu";
        }
        String smer = parametry[0];
        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        if (sousedniProstor == null) {
            return "Tam sa odtiaľto nedá ísť!";
        }
        else {
            if (sousedniProstor.jeZamceno()) {
                return "Vstup do miestnosti "+sousedniProstor.getNazev()
                +" ešte nie je povolený ";
            }
            plan.setAktualniProstor(sousedniProstor);

            if (plan.hracVyhral()) {
                hra.setKonecHry(true);
                return "Vyhral si, úspešne sa ti podarilo odletieť domov. Jupí";
            }
            return sousedniProstor.dlouhyPopis();
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */

    public String getNazev() {
        return NAZEV;
    }

}
