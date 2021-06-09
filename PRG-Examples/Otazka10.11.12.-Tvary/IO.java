
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/*******************************************************************************
 * Knihovní třída {@code IO} obsahuje sadu metod
 * pro jednoduchý vstup a výstup prostřednictvím dialogovýách oken
 * spolu s metodou zastavující běh programu na daný počet milisekund
 * a metodu převádějící texty na ASCII jednoduchým odstraněním diakritiky.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 3.02.3736 — 2013-02-17
 */
public final class IO
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================

    /** Přepravka pro nulové veikosti okrajů. */
    private static final Insets NULOVÝ_OKRAJ = new Insets(0, 0, 0, 0);

    /** Rozdíl mezi tlouštkou rámečku okna ohlašovanou před a po
     *  volání metody {@link #setResizable(boolean)}.
     *  Tento rozdíl ve Windows ovlivňuje nastavení velikosti a pozice.
     *  Při {@code setResizable(true)} jsou jeho hodnoty větší,
     *  a proto se spočte se jako "true" - "false". */
    private static final Insets INSETS_DIF;

    /** Informace o tom, budou-li se opravovat pozice a rozměry oken. */
    private static final boolean OPRAVOVAT;



//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================

    /** Pozice dialogových oken. */
    private static Point poziceOken = new Point(0,0);

    /** Příznak testovacího režimu - je-li nastaven na {@code true},
     *  metoda {@link #zpráva(Object)} neotevírá dialogové okno
     *  a metoda {@link #čekej(int)} nečeká. */
    private static boolean testujeme = false;



//== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ========================

    /** Windows Vista + Windows 7 se neumějí dohodnout s Javou na skutečné
     *  velikosti oken a jejich rámů a v důsledku toho nefunguje správně
     *  ani umísťování oken na zadané souřadnice.
     *  Následující statický konstruktor se snaží zjistit chování aktuálního
     *  operačního systému a podle toho připravit potřebné korekce.
     *  Doufejme, že záhy přestane být potřeba.
     */
    static {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            JFrame okno = new JFrame();
            okno.setLocation(-1000, -1000);
            okno.setResizable(true);
            okno.pack();
            Insets insTrue  = okno.getInsets();
            okno.setResizable(false);
            Insets insFalse = okno.getInsets();
            Insets insets;
            insets = new Insets(insTrue.top    - insFalse.top,
                                insTrue.left   - insFalse.left,
                                insTrue.bottom - insFalse.bottom,
                                insTrue.right  - insFalse.right);
            if (NULOVÝ_OKRAJ.equals(insets)) {
                //Nevěřím mu, určitě kecá
                int úbytek = (insTrue.left == 8)  ?  5  :  1;
                insets = new Insets(úbytek, úbytek, úbytek, úbytek);
            }
            INSETS_DIF = insets;
//            OPRAVOVAT = true;
            OPRAVOVAT = ! NULOVÝ_OKRAJ.equals(INSETS_DIF);
        }
        else {
            INSETS_DIF = NULOVÝ_OKRAJ;
            OPRAVOVAT  = false;
        }
    }



//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================
//== OSTATNÍ NESOUKROMÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Počká zadaný počet milisekund.
     * Na přerušení nijak zvlášť nereaguje - pouze skončí dřív.
     * Před tím však nastaví příznak, aby volající metoda poznala,
     * že vlákno bylo žádáno o přerušení.
     *
     * @param milisekund   Počet milisekund, po něž se má čekat.
     */
    public static void čekej(int milisekund)
    {
        if (testujeme) {
            Zpravodaj.zpravodaj.čekej(milisekund);
        }
        else {
            try {
                Thread.sleep(milisekund);
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    /***************************************************************************
     * Při splnění zadané podmínky otevře dialogové okno s nápisem KONEC
     * a po jeho zavření ukončí program.
     *
     * @param platí Podmínka, při jejímž splnění se program ukončí
     */
    public static void konecKdyž(boolean platí)
    {
        konecKdyž(platí, null);
    }


    /***************************************************************************
     * Při splnění zadané podmínky otevře dialogové okno se zadanou zprávou
     * a po jeho zavření ukončí program.
     *
     * @param platí  Podmínka, při jejímž splnění se program ukončí
     * @param zpráva Zpráva vypisovaná v dialogovém okně. Je-li {@code null}
     *               nebo prázdný řetězec, vypíše <b>{@code KONEC}</b>.
     */
    public static void konecKdyž(boolean platí, String zpráva)
    {
        if (platí) {
            if ((zpráva == null)  ||  (zpráva.equals(""))) {
                zpráva = "KONEC";
            }
            zpráva(zpráva);
            System.exit(0);
        }
    }


    /***************************************************************************
     * Zbaví zadaný text diakritických znamének; současně ale odstraní také
     * všechny další znaky nespadající do tabulky ASCII.
     *
     * @param text Text určený k "odháčkování"
     * @return  "Odháčkovaný" text
     */
    public static String odháčkuj(String text)
    {
        return Odháčkuj.text(text);
    }


    /***************************************************************************
     * Nastaví pozici příštího dialogového okna.
     *
     * @param x  Vodorovná souřadnice
     * @param y  Svislá souřadnice
     */
    public static void oknaNa(int x, int y)
    {
        poziceOken = new Point(x, y);
        if (OPRAVOVAT) {
            poziceOken.x += INSETS_DIF.left;
            poziceOken.y += INSETS_DIF.top + INSETS_DIF.bottom;
        }
    }


    /***************************************************************************
     * Zobrazí dialogové okno se zprávou a umožní uživateli odpovědět
     * ANO nebo NE. Vrátí informaci o tom, jak uživatel odpověděl.
     * Neodpoví-li a zavře dialog, ukončí program.
     *
     * @param dotaz   Zobrazovaný text otázky.
     * @return <b>{@code true}</b> Odpověděl-li uživatel <b>ANO</b>,
     *         <b>{@code false}</b> odpověděl-li <b>NE</b>
     */
    public static boolean souhlas(Object dotaz)
    {
        JOptionPane jop = new JOptionPane(
                                dotaz,
                                JOptionPane.QUESTION_MESSAGE,   //Message type
                                JOptionPane.YES_NO_OPTION       //Option type
                              );
        processJOP(jop);
        int answer = (Integer)jop.getValue();
        return (answer == JOptionPane.YES_OPTION);
    }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání reálné hodoty;
     * při zavření okna zavíracím tlačítkem ukončí aplikaci.
     *
     * @param výzva        Text, který se uživateli zobrazí.
     * @param doubleImpl   Implicitní hodnota.
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota.
     */
    public static double zadej(Object výzva, double doubleImpl)
    {
        return Double.parseDouble(zadej(výzva, ""+doubleImpl).trim());
    }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání celočíselné hodoty;
     * při zavření okna nebo stisku tlačítka Cancel
     * se celá aplikace ukončí.
     *
     * @param výzva     Text, který se uživateli zobrazí.
     * @param intImpl   Implicitní hodnota.
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota.
     */
    public static int zadej(Object výzva, int intImpl)
    {
        return Integer.parseInt(zadej(výzva, ""+intImpl).trim());
    }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání textové hodoty;
     * při zavření okna nebo stisku tlačítka Cancel
     * se celá aplikace ukončí.
     *
     * @param výzva        Text, který se uživateli zobrazí.
     * @param stringImpl   Implicitní hodnota.
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota.
     */
    public static String zadej(Object výzva, String stringImpl)
    {
        JOptionPane jop = new JOptionPane(
                              výzva,
                              JOptionPane.QUESTION_MESSAGE,   //Message type
                              JOptionPane.DEFAULT_OPTION  //Option type - OK
                            );
        jop.setWantsInput(true);
        jop.setInitialSelectionValue(stringImpl);
        processJOP(jop);
        String answer = jop.getInputValue().toString();
        return answer;
    }


    /***************************************************************************
     * Zobrazí dialogové okno se zprávou a počká,
     * až uživatel stiskne tlačítko OK;
     * při zavření okna zavíracím tlačítkem ukončí celou aplikaci.
     *
     * @param text   Zobrazovaný text.
     */
    public static void zpráva(Object text)
    {
        if (testujeme) {
            Zpravodaj.zpravodaj.zpráva(text);
        }
        else {
            JOptionPane jop = new JOptionPane(
                              text,                            //Sended message
                              JOptionPane.INFORMATION_MESSAGE  //Message type
                            );
            processJOP(jop);
        }
    }



//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Třída IO je knihovní třídou a proto není určena k tomu,
     * aby měla nějaké instance.
     */
    private IO() {}



//== ABSTRAKTNÍ METODY =========================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =====================================
//== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ========================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Creates a dialog from the given {@link JOptionPane}, makes it non-modal
     * and waits for its closing leaving the entered value in the parameter's
     * attribute {@code value}. If the user closed the dialog
     * from the window's system menu, exit the whole application.
     *
     * @param jop Zpracovávaný {@link JOptionPane}
     */
    private static void processJOP(JOptionPane jop)
    {
        final int WAITING=0, CANCELLED=1;
        final Boolean[] USER = {true, false};

        final JDialog jd = jop.createDialog((JDialog)null, "Information");

        jd.addWindowListener(new WindowAdapter()
        {
            /** Set the information about closing the window from its
             *  systme menu - the application will be cancelled. */
            @Override
            public void windowClosing(WindowEvent e) {
                synchronized(USER) {
                    USER[CANCELLED] = true;
                    System.exit(1);
                }
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                poziceOken = jd.getLocation();
                if (jd.isShowing()) {
                    return;
                }else{
                    jd.dispose();
                    synchronized(USER) {
                        USER[WAITING] = false;
                        USER.notifyAll();
                    }
                }
            }
         });

        jd.setModal(false);
        jd.setVisible(true);
        jd.setLocation(poziceOken);
        jd.toFront();
        jd.setAlwaysOnTop(true);
//        jd.setAlwaysOnTop(false);

        //Waiting until the user answers or closes the dialog
        synchronized(USER) {
            while (USER[WAITING]) {
                try {
                    USER.wait();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }



//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== INTERNÍ DATOVÉ TYPY =======================================================

    /***************************************************************************
     * Třída {@code Oprava} je knihovní třídou poskytující metody
     * pro opravy nejrůznějších nesrovnalostí týkajících se práce
     * s grafickým vstupem a výstupem.
     */
    static class Oprava
    {
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================

        /***********************************************************************
         * Ve Windows 7 používajících definuje Java jinou velikost okna,
         * než odpovídá velikosti panelu obrázku.
         *
         * @param cont   Kontejner, jehož rozměry upravujeme
         */
        public static void poziceOkna(Container cont)
        {
            Point  loc;
            if (OPRAVOVAT) {
                loc = cont.getLocation();
                cont.setLocation(loc.x + INSETS_DIF.left,
                                 loc.y + INSETS_DIF.top);
            }
        }


        /***********************************************************************
         * Ve Windows 7 definuje Java jinou velikost okna,
         * než odpovídá velikosti panelu obrázku.
         *
         * @param cont     Kontejner, jehož rozměry upravujeme
         */
        public static void rozměrOkna(Container cont)
        {
            Dimension dim;
            if (OPRAVOVAT) {
                dim = cont.getSize();
                cont.setSize(dim.width - INSETS_DIF.left - INSETS_DIF.right,
                             dim.height- INSETS_DIF.top  - INSETS_DIF.bottom);
            }
        }



    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

       /** Soukromy konstruktor bránící vytvoření instance. */
        private Oprava() {}


    //== ABSTRAKTNÍ METODY =====================================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================
    //== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================
    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== TESTY A METODA MAIN ===================================================
    }



///#############################################################################
///#############################################################################
///#############################################################################

    /***************************************************************************
     * Instance třídy {@code Zpravodaj} obstarává komunikaci mezi
     * testovanými a testovacími objekty.
     */
    private
    static class Zpravodaj
    {
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================

        /** Prostředník, který přihlášeným testovacím programům přeposílá
         *  zprávy o zavolání definovaných metod. */
        public static final Zpravodaj zpravodaj = new Zpravodaj();



    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================

        /** Seznam přihlášených testovacích programů,
         *  kterým budou přeposílány zprávy o volání zadaných metod. */
        private final List<ITester> seznam = new ArrayList<ITester>();



    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

       /** Soukromy konstruktor bránící vytvoření instance. */
        private Zpravodaj() {}


    //== ABSTRAKTNÍ METODY =====================================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================

        /***********************************************************************
         * Přidá zadaný objekt mezi objekty,
         * kterým oznamuje zavolání definovaných metod.
         *
         * @param tester Přidávaný testovací objekt
         */
        public void přihlaš(ITester tester)
        {
            if (seznam.contains(tester)) { return; }
            seznam.add(tester);
            testujeme = true;
        }


        /***********************************************************************
         * Odebere zadaný objekt ze seznamu objetků,
         * kterým oznamuje zavolání definovaných metod.
         *
         * @param tester Odebíraný testovací objekt
         */
        public void odhlaš(ITester tester)
        {
            seznam.remove(tester);
            if (seznam.isEmpty()) {
                testujeme = false;
            }
        }



    //== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================
    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================

        /***********************************************************************
         * Oznámí zavolání metody {@link IO.čekej(int)}
         * a předá v parametru zadanou dobu čekání.
         *
         * @param ms Zadaná doba čekání v milisekundách
         */
        private void čekej(int ms)
        {
            for (ITester it : seznam) {
                it.čekej(ms);
            }
        }


        /***********************************************************************
         * Oznámí zavolání metody {@link IO.zpráva(Object)}
         * a předá v parametru vypisovaný text.
         *
         * @param zpráva Zobrazovaný text
         */
        private void zpráva(Object zpráva)
        {
            for (ITester it : seznam) {
                it.zpráva(zpráva);
            }
        }



    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== TESTY A METODA MAIN ===================================================
    }



///#############################################################################
///#############################################################################
///#############################################################################


    /***************************************************************************
     * Instance rozhraní {@code ITester} představují testovací objekty,
     * které chtějí být zpravovány o zajímavých událostech.
     */
    private
    interface ITester
    {
    //== KONSTANTY =============================================================
    //== DEKLAROVANÉ METODY ====================================================

        /***********************************************************************
         * Oznámí zavolání metody {@link IO#čekej(int)}
         * a předá v parametru zadanou dobu čekání.
         *
         * @param ms Zadaná doba čekání v milisekundách
         */
        public void čekej(int ms);


        /***********************************************************************
         * Oznámí zavolání metody {@link IO#zpráva(Object)}
         * a předá v parametru vypisovaný text.
         *
         * @param zpráva Zobrazovaný text
         */
        public void zpráva(Object zpráva);



    //== ZDĚDĚNÉ METODY ========================================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    }



///#############################################################################
///#############################################################################
///#############################################################################

    /***************************************************************************
     * Třída {@code Odháčkuj_RUP} je knihovní třídou poskytující metodu na
     * odstranění diakritiky ze zadaného textu a následné převedení všech znaků,
     * jejichž kód je stále větší než 127, na příslušné kódové
     * únikové posloupnosti (escape sekvence).
     */
    private static class Odháčkuj
    {
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================

        /** Mapa s převody znaků do ASCII. */
        private static final Map<Character,String> PŘEVOD =
                                           new HashMap<Character, String>(64);



    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
        static {
            String[][] dvojice = {

                {"Á", "A"},  {"á", "a"},    {"Ä", "AE"}, {"ä", "ae"},
                {"Č", "C"},  {"č", "c"},
                {"Ď", "D"},  {"ď", "d"},    {"Ë", "E"},  {"ë", "e"},
                {"É", "E"},  {"é", "e"},
                {"Ě", "E"},  {"ě", "e"},
                {"Í", "I"},  {"í", "i"},    {"Ï", "IE"}, {"ï", "ie"},
                {"Ĺ", "L"},  {"ĺ", "l"},    {"Ľ", "L"},  {"ľ", "l"},
                {"Ň", "N"},  {"ň", "n"},
                {"Ó", "O"},  {"ó", "o"},    {"Ö", "OE"}, {"ö", "oe"},
                {"Ô", "O"},  {"ô", "o"},
                {"Ŕ", "R"},  {"ŕ", "r"},    {"Ř", "R"},  {"ř", "r"},
                {"Š", "S"},  {"š", "s"},
                {"Ť", "T"},  {"ť", "t"},
                {"Ú", "U"},  {"ú", "u"},    {"Ü", "UE"}, {"ü", "ue"},
                {"Ů", "U"},  {"ů", "u"},
                {"Ý", "Y"},  {"ý", "y"},    {"Ÿ", "YE"}, {"ÿ", "ye"},
                {"Ž", "Z"},  {"ž", "z"},
                {"ß", "ss"},
                {"«", "<<"}, {"»", ">>"},
//                {"",""},
            };
            for (String[] ss : dvojice) {
                PŘEVOD.put(new Character(ss[0].charAt(0)),  ss[1]);
            }
            dvojice = null;
        }



    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================

        /***********************************************************************
         * Zbaví zadaný text diakritických znamének - <b>POZOR</b> -
         * Spolu s nimi odstraní také všechny znaky s kódem větším než 127.
         *
         * @param text Text určený k "odháčkování"
         * @return  "Odháčkovaný" text
         */
        public static String text(CharSequence text)
        {
            final int DÉLKA = text.length();
            final StringBuilder sb = new StringBuilder(DÉLKA);
            for (int i = 0;   i < DÉLKA;   i++) {
                char c = text.charAt(i);
                if (c < 128) {
                    sb.append(c);
                }else if (PŘEVOD.containsKey(c)) {
                    sb.append(PŘEVOD.get(c));
                }else {
                    sb.append(rozepiš(c));
                }
            }
            return sb.toString();
        }



    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

       /** Soukromy konstruktor bránící vytvoření instance. */
        private Odháčkuj() {}


    //== ABSTRAKTNÍ METODY =====================================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================
    //== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================

        /***********************************************************************
         * Rozepíše zadaný znak do příslušné ńikové k´dové posloupnosti.
         *
         * @param c Převáděný znak
         * @return Text ve formátu \\uXXXX
         */
        private static String rozepiš(char c) {
            return String.format("\\u%04x", (int)c);
        }



    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== TESTY A METODA MAIN ===================================================
    }



///#############################################################################
///#############################################################################
///#############################################################################

//== TESTY A METODA MAIN =======================================================
}
