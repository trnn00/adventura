/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
/*******************************************************************************
 * Instance třídy {@code Postava} představují osoby v mojej hre s ich odpoveďami.
 * @author    Nikola Trníková
 * @version   15.12.2016
 */
public class Postava
{
    private String jmeno;
    private String proslov;
    /***************************************************************************
     *Konstruktor nastaví jméno a proslov postáv
     *@param jmeno - meno postavy
     *@param proslov - dostane preslov postavy
     */
    public Postava(String jmeno, String proslov)
    {
        this.jmeno = jmeno;
        this.proslov = proslov;
    }

    /**
     * Metoda vrací jméno postavy.
     * 
     * @return   String jméno postavy.
     */
    public String getJmeno() {
        return jmeno; 
    }

    /**
     * Metoda vrací proslov postavy.
     * 
     * @return   vráti proslov postavy.
     */
    public String getProslov() {
        return proslov; 
    }

    /**
     * Metoda nastavi proslov postavy.
     * 
     * @param proslov - proslov postavy.
     */
    public void setProslov(String proslov) {
        this.proslov = proslov;  
    }

}

