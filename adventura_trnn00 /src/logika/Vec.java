/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.Map;
import java.util.HashMap;

/*******************************************************************************
 * Instance třídy Vec představují název popis prenositeľnosť a použiteľnosť vecí, ktoré sú v hre použité.
 *
 * @author    Nikola Trníková
 * @version   15.12.2016
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    public boolean pouzitelna;
    private Map<String,Vec>seznamVeci;
    private static final int KAPACITA = 4;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  @param nazev názov
     *  @param popis popis
     *  @param prenositelna prenositeľnosť veci
     *  @param pouzitelna použiteľnosť veci
     */
    public Vec(String nazev, String popis, boolean prenositelna, boolean pouzitelna)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.pouzitelna = pouzitelna;
        this.prenositelna = prenositelna;
        this.seznamVeci = new HashMap<>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev() {
        return nazev;
    }

     /**
     *  Metoda vrací popis 
     *  
     *  @return popis prikazu
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Vrací true alebo false, podla toho, či je vec prenositeľná
     * @return prenositelnost
     */
    public boolean isPrenositelna() {
        return prenositelna; //vracia prenositeľnosť veci
    }

    /**
     * Vracia true alebo false, podľa toho, či je vec použiteľná
     *@return pouzitelnost
     */
    public boolean isPouzitelna() {
        return pouzitelna; //vracia pouzitelnost veci
    }

    /**
     * Vrací jméno věci
     * @return jmeno veci
     */
    public String getJmeno () {
        return nazev;
    }

    /** 
     * Vloží věc do jiné věci.
     * @param vec vkladá vec
     * @return true alebo false, podľa kapacity
     */
    public boolean vlozVec (Vec vec) {
        if (seznamVeci.size() < KAPACITA ) {
            seznamVeci.put(vec.getJmeno(), vec);
            return true;
        }
        return false;
    }

    /**
     * Zjistí, zda se věc nachazí v jiné věci.
     * 
     * @return vrací true, pokud je věc v jiné.
     * @param jmeno nazov veci
     */
    public boolean obsahujeVec(String jmeno) {
        return seznamVeci.containsKey(jmeno);
    }

    /**
     * Vybere věc z jiné věci.
     * 
     * @return vrátí vybranou věc.
     * @param jmeno nazov veci
     */
    public Vec vyberVec(String jmeno) {
        Vec vec = null;
        if (seznamVeci.containsKey(jmeno)) {
            vec = seznamVeci.get(jmeno);
            if (vec.isPrenositelna()) {
                seznamVeci.remove(jmeno);
            }
        }
        return vec;
    }

    /**
     * Vrací odkaz na seznam věcí,které daná věc obsahuje.
     *  
     * @return seznamVeci
     */
    public String getSeznamVeci() {
        String nazvy = " ";

        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }

        return nazvy;
    }


}
