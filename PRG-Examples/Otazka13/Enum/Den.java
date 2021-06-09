 

 

 

public enum Den 
{
    //== HODNOTY VÝČTOVÉHO TYPU ================================================
    POND�L�("","",""),
    �TER�("9:20","11:30","  �KOLEN�"),
    ST�EDA("","",""),
    �TVRTEK("","",""),
    P�TEK("","",""),
    SOBOTA("","",""),
    NED�LE("","","");
    ////=====  N�?POVĚDA: KONSTRUKTOR BEZ PARAMETRŮ  ==============
    
    //
    ////=====  N�?POVĚDA: KONSTRUKTOR S PARAMETRY  ================
    //     JARO(par), LETO(par), PODZIM(par), ZIMA(par);
    
    
    //== KONSTANTNÍ ATRIBUTY T�?ÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY T�?ÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== P�?ÍSTUPOVÉ METODY VLASTNOSTÍ T�?ÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY T�?ÍDY =======================================
    
    //##########################################################################
    //== KONSTRUKTORY A TOV�?RNÍ METODY =========================================
    String odKdy;
    String doKdy;
    String poznamka;
    private Den(String odK, String doK, String pzn)
    {
       this.odKdy=odK;
       this.doKdy=doK;
       this.poznamka=pzn;
    }
   

    public String getRozvrh()
    {
      return odKdy+" "+doKdy+" "+poznamka; 
    }
    
    public void pridejTermin(String odKdy, String doKdy, String pzn)
    {
      this.odKdy = odKdy;
      this.doKdy = doKdy;
      this.poznamka = pzn; 
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
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
