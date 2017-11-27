/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Map;
import java.util.HashMap;

/*******************************************************************************
 * Instance třídy {@code Batoh} představují batoh a jeho metody.
 *
 * @author    Nikola Trníková
 * @version   15.12.2016
 */
public class Batoh
{
    private static final int KAPACITA = 4 ;
    public Map<String, Vec> seznamVeci ;   
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /***************************************************************************
     * Konstruktor seznam věci v batohu
     */
    public Batoh(){
        seznamVeci = new HashMap<>();
    }

    /**
     * Vloží věc do batohu, pokud se tam vejde
     *
     * @param vec - vec
     * @return 1 když se věc vloží,2, pokud je neprenositelna
     */
    public int vlozVecDoBatohu (Vec vec) { 
        if (!vec.isPrenositelna()){
            return 2; //vec je neprenositelna, nemožno ju vziat
        }
        //kontrola voľného miesta v batohu urobená v príkaz vezmi
        seznamVeci.put(vec.getNazev(), vec);
        return 1; //true
    }

    /**
     * Napíše informace o věcech v batohu
     * @param jmenoVeci meno veci
     * @return seznam veci
     */
    public boolean obsahujeVecVBatohu (String jmenoVeci) {
        return seznamVeci.containsKey(jmenoVeci);
    }

    /**
     * Metoda odebere vec z batohu.
     * 
     * @param nazev Parametrem je vec, ktorá sa má zahodit z batohu.
     * @return   Vrátí meno veci, ktorú hráč zahodil.
     */   
    public Vec vyndejVec(String nazev){
        Vec vyndanaVec = null;
        if (seznamVeci.containsKey(nazev)) {
            vyndanaVec = seznamVeci.get(nazev);
            seznamVeci.remove(nazev);
        }
        return vyndanaVec;  
    } 

    /**
     * Vypíše věci, které jsou v batohu
     * @return nazvy
     */
    public String nazvyVeciVBatohu() {
        String nazvy = "veci v batohu: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }
        return nazvy;

    }

    /**
     * Vypíše kapacitu batohu (4)
     * @return vrati kapacitu 4
     */
    public int getKapacitaBatohu() {
        return KAPACITA;

    }

    public Map<String, Vec> getVeci(){
        return seznamVeci;
    }
    
    
    /**
     * Vrací počet volných míst v batohu
     * 
     * @return vrátí číslo, kolik věcí ještě může hráč přidat
     */
    public int jeVolneMisto(){
        return KAPACITA - seznamVeci.size();
    }
}
