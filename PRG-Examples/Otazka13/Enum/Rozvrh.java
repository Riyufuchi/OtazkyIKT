
import java.util.*;
public class Rozvrh
{
    //== KONSTANTNÍ ATRIBUTY T�?ÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY T�?ÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== P�?ÍSTUPOVÉ METODY VLASTNOSTÍ T�?ÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY T�?ÍDY =======================================
    
    //##########################################################################
    //== KONSTRUKTORY A TOV�?RNÍ METODY =========================================

    /***************************************************************************
     *
     */
    public Rozvrh()
    {
    }



    //== ABSTRAKTNÍ METODY =====================================================
    //== P�?ÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================

    
    
    //== SOUKROMÉ A POMOCNÉ METODY T�?ÍDY =======================================
    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== TESTOVACÍ METODY A T�?ÍDY ==============================================
    //
    //     /********************************************************************
    //      * Testovací metoda.
    //      */
    //     public static void test()
    //     {
    //         VypisDny instance = new VypisDny();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    public static void main(String[] args)  
    { 
        Den pond�l� =  Den.POND�L�;
        pond�l�.pridejTermin("8:00","13:50", "Jsem u l�ka�e");
        //for (Den d : EnumSet.range(Den.PONDĚLÍ, Den.NEDĚLE))
        for (Den d : Den.values())
        System.out.println(d+"   "+d.getRozvrh());
    }
}
