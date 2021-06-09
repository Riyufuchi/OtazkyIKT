/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy.
 */

import java.awt.Color;


import java.text.Collator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/*******************************************************************************
 * Třída {@code Barva} definuje skupinu základních barev
 * pro použití při kreslení tvarů.
 * Není definována jako výčtový typ,
 * aby uživatel mohl libovolně přidávat vlastní barvy.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 3.02.3736 — 2013-02-17
 */
public class Barva
{
    /** Počet pojmenovaných barev se při konstrukci následujících instancí
     *  načítá, a proto musí být deklarován před nimi. */
    private static int počet = 0;



//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================

    /** Atribut alfa pro průsvitné barvy. */
    private static final int PRŮSVIT = 96;

    /** Minimální velikost složky při změnách světlosti a průhlednosti. */
    private static final int MINC = 32;

    /** Maximální velikost složky při změnách světlosti a průhlednosti. */
    private static final int MAXC = 255;

    /** Koeficient změny velikost složky při změnách světlosti a průhlednosti.*/
    private static final double KC = 0.7;

    /** Mapa všech doposud vytvořených barev klíčovaná jejich názvy. */
    private static final Map<String,Barva> název2barva =
                                             new LinkedHashMap<String,Barva>();

    /** Mapa všech doposud vytvořených barev klíčovaná jejich názvy
     *  s odstraněnou diakritikou. */
    private static final Map<String,Barva> názevBhc2barva =
                                             new LinkedHashMap<String,Barva>();

    /** Mapa všech doposud vytvořených barev klíčovaná jejich barevností. */
    private static final Map<Color,Barva>  color2barva =
                                             new LinkedHashMap<Color,Barva>();

    /** Seznam všech dosud vytvořených barev. */
    private static final List<Barva> seznamBarev =
                                     new ArrayList<Barva>(32);



//########## BARVY #############################################################

    //########## Barvy s ekvivalentními konstantami v java.awt.Color

    /** Černá = RGBA(0, 0, 0, 255); */         public static final Barva
    ČERNÁ   = new Barva(Color.BLACK,           "černá" );

    /** Modrá = RGBA(0, 0, 255, 255); */       public static final Barva
    MODRÁ   = new Barva(Color.BLUE,            "modrá" );

    /** Červená = RGBA(255, 0, 0, 255); */     
    public static final Barva ČERVENÁ = new Barva(Color.RED,"červená");

    /** Fialová = RGBA(255, 0, 255, 255); */   public static final Barva
    FIALOVÁ = new Barva(Color.MAGENTA,         "fialová");

    /** Zelená = RGBA(0, 255, 0, 255); */      public static final Barva
    ZELENÁ  = new Barva(Color.GREEN,           "zelená");

    /** Azurová = RGBA(0, 255, 255, 255); */   public static final Barva
    AZUROVÁ = new Barva(Color.CYAN,            "azurová");

    /** Žlutá = RGBA(255, 255, 0, 255); */     public static final Barva
    ŽLUTÁ   = new Barva(Color.YELLOW,          "žlutá" );

    /** Bílá = RGBA(255, 255, 255, 255); */    public static final Barva
    BÍLÁ    = new Barva(Color.WHITE,           "bílá"  );

    /** Světlešedá = RGBA(192,192,192,255); */public static final Barva
    SVĚTLEŠEDÁ = new Barva(Color.LIGHT_GRAY,   "světlešedá");  //128 = 0x80

    /** Šedá = RGBA(128, 128, 128, 255); */    public static final Barva
    ŠEDÁ    = new Barva(Color.GRAY,            "šedá"  );

    /** Tmavošedá = RGBA(64,  64,  64, 255);*/ public static final Barva
    TMAVOŠEDÁ = new Barva(Color.DARK_GRAY,     "tmavošedá");  //64 = 0x40

    /** Černá = RGBA(255, 175, 175, 255); */   public static final Barva
    RŮŽOVÁ  = new Barva(Color.PINK,            "růžová");    //175 = 0xAF

    /** Oranžová = RGBA(255, 200, 0, 255); */  public static final Barva
    ORANŽOVÁ= new Barva(Color.ORANGE,          "oranžová");


    //########## Barvy bez ekvivalentních konstant v java.awt.Color

    /** Stříbrná = RGBA(216, 216, 216, 255); */public static final Barva
    STŘÍBRNÁ = new Barva(0xD8, 0xD8, 0xD8, 0xFF, "stříbrná");

    /** Zlatá = RGBA(255, 224,  0, 255); */    public static final Barva
    ZLATÁ = new Barva(0xFF, 0xE0, 0x00, 0xFF,  "zlatá");

    /** Cihlová = RGBA(255, 102, 0, 255); */   public static final Barva
    CIHLOVÁ = new Barva(0xFF, 0x66, 0, 0xFF,   "cihlová");

    /** Hnědá = RGBA(153, 51, 0, 255); */      public static final Barva
    HNĚDÁ = new Barva(0x99, 0x33, 0x00, 0xFF,  "hnědá" );

    /** Krémová = RGBA(255, 255, 204, 255); */ public static final Barva
    KRÉMOVÁ = new Barva(0xFF, 0xFF, 0xCC, 0xFF,"krémová");

    /** Khaki = RGBA(153, 153, 0, 255); */     public static final Barva
    KHAKI = new Barva(0x99, 0x99, 0x00, 0xFF,  "khaki" );

    /** Ocelová = RGBA(0, 153, 204, 255); */   public static final Barva
    OCELOVÁ = new Barva(0x00, 0x99, 0xCC, 0xFF,"ocelová");

    /** Okrová = RGBA(255, 153, 0, 255); */    public static final Barva
    OKROVÁ = new Barva(0xFF, 0x99, 0x00, 0xFF, "okrová" );


    //########## Průsvitné barvy

    /** Mléčná=RGBA(255, 255, 255, 128) - polovičně průsvitná bílá. */
    public static final Barva
    MLÉČNÁ  = new Barva(0xFF, 0xFF, 0xFF, 0x80, "mléčná");

    /** Kouřová = RGBA(128, 128, 128, 128) - polovičně průsvitná šedá. */
    public static final Barva
    KOUŘOVÁ = new Barva(0x80, 0x80, 0x80, 0x80, "kouřová");

    /** Žádná = RGBA(0, 0, 0, 0) - průhledná, neviditelná barva */
    public static final Barva
    ŽÁDNÁ  = new Barva(0, 0, 0, 0, "žádná");



//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================

    /** Příznak velikosti písmen, jimiž se vypisují názvy barev. */
    private static boolean velkými = false;



//== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ========================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    /** Název barvy zadávaný konstruktoru. */
    private final String název;

    /** Instance třídy {@link java.awt.Color} představující stejnou barvu. */
    private final Color  color;

    /** Index barvy v seznamu dosud vytvořených barev. */
    private final int index = počet++;



//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================

    /***************************************************************************
     * Vrátí kolekci doposud definovaných barev.
     *
     * @return  Kolekce doposud definovaných barev
     */
    private
    static List<Barva> getSeznamBarev()
    {
        return Collections.unmodifiableList(seznamBarev);
    }


    /***************************************************************************
     * Vrátí seznam názvů doposud definovaných barev.
     *
     * @return  Seznam názvů doposud definovaných barev
     */
    private
    static List<String> getSeznamNázvůBarev()
    {
        return Arrays.asList(getPoleNázvůBarev());
    }


    /***************************************************************************
     * Vrátí pole doposud definovaných barev.
     *
     * @return  Pole doposud definovaných barev
     */
    public static Barva[] getPoleBarev()
    {
        return seznamBarev.toArray(new Barva[seznamBarev.size()]);
    }


    /***************************************************************************
     * Vrátí vektor řetězců s dopsud definovanými názvy barev.
     *
     * @return  Vektor řetězců s dopsud definovanými názvy barev
     */
    public static String[] getPoleNázvůBarev()
    {
        String[] názvy = název2barva.keySet()
                         .toArray(new String[název2barva.size()]);
        if (velkými) {
            for (int i = 0;   i < názvy.length;   i++) {
                názvy[i] = názvy[i].toUpperCase();
            }
        }
        Arrays.sort(názvy, Collator.getInstance());
        return názvy;
    }


    /***************************************************************************
     * Nastaví, zda se budou názvy barev vypisovat velkými písmeny.
     *
     * @param velkými {@code true} mají-li se názvy vypisovat velkými písmeny,
     *                jinak {@code false}
     * @return Půvdoní nastavení
     */
    public static boolean setVelkými(boolean velkými)
    {
        boolean původní = Barva.velkými;
        Barva.velkými = velkými;
        return původní;
    }



//== OSTATNÍ NESOUKROMÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Otevře dialogové okno, v němž vypíše všechny doposud definované
     * názvy barev. Jména jsou lexikograficky seřazena.
     */
    public static void vypišZnáméNázvy()
    {
        final int MAX_V_ŘÁDKU = 64;
        String[] názvy = getPoleNázvůBarev();
        StringBuilder sb = new StringBuilder();
        for (int i=0, vŘádku=0;   i < názvy.length;   i++) {
            String text = názvy[i];
            int textLength = text.length();
            if ((vŘádku + textLength)  >=  MAX_V_ŘÁDKU) {
                sb.append('\n');
                vŘádku = 0;
            }
            sb.append(text);
            vŘádku += textLength + 2;
            if (i < názvy.length) {
                sb.append(", ");
            }
        }
//        System.out.println("Barvy:\n" + sb);
        IO.zpráva(sb);
    }



//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří instanci barvy se zadanou velikostí barevných složek
     * a hladinou průhlednosti nastavovanou v kanále alfa
     * a se zadaným názvem.
     *
     * @param red       Velikost červené složky
     * @param green     Velikost zelené složky
     * @param blue      Velikost modré složky
     * @param alpha     Velikost složky alfa = hladina průhlednosti:
     *                  0=průhledná, 255=neprůhledná
     * @param název     Název vytvořené barvy
     */
    private Barva(int red, int green, int blue, int alpha, String název)
    {
        this(new Color(red, green, blue, alpha),  název);
    }


    /***************************************************************************
     * Vytvoří barvu ekvivalentní zadané instanci třídy
     * {@link java.awt.Color} se zadaným názvem.
     * Účelem tohoto kontruktoru je co nejjednodušší předání komponent barev,
     * které již mají definované ekvivalentní názvy
     * ve třídě {@link java.awt.Color}.
     *
     * @param c      Instance třídy {@link java.awt.Color} požadované barvy
     * @param název  Název vytvářené barvy; {@code null} oznaučje,
     *               že se má pro barvu vytvořit generický název
     */
    private Barva(Color c, String název)
    {
        this.color = c;
        this.název = název.toLowerCase();

        if (název2barva.containsKey(název)   ||
            color2barva.containsKey(c))
        {
            throw new IllegalArgumentException(
                "\nInterní chyba - barva " + getNázev() + " a/nebo " +
                getCharakteristikaHex() + " již existují");
        }

        Barva já = this;
        název2barva.put(název, já);
        color2barva.put(color, já);
        seznamBarev.add(já);

        přidejNázevBezDiakritiky();
    }


    /***************************************************************************
     * Převede název barvy na příslušný objekt typu Barva.
     *
     * @param názevBarvy  Název požadované barvy -- seznam známých názvů
     *                    je možno získat zavoláním metody getZnáméNázvy()
     * @return Instance třídy Barva reprezentující zadanou barvu
     * @throws IllegalArgumentException
     *         Není-li barva zadného názvu mezi známými barvami
     */
    public static Barva getBarva(String názevBarvy)
    {
        Barva barva = název2barva.get(názevBarvy.toLowerCase());
        if (barva != null) {
            return barva;
        }
        else {
            throw new IllegalArgumentException(
                    "\nTakto pojmenovanou barvu neznám: " + názevBarvy);
        }
    }


    /***************************************************************************
     * Vrátí neprůhlednou instanci barvy se zadanými velikostmi složek.
     * Není-li barva ještě definována, vytvoří ji
     * a přiřadí jí název odvozený z velikostí jejích barevných složek.
     *
     * @param red   Velikost červené složky
     * @param green Velikost zelené složky
     * @param blue  Velikost modré složky
     * @return Barva se zadanými velikostmi jednotlivých složek
     */
    private
    static Barva getBarva(int red, int green, int blue)
    {
        return getBarva(red, green, blue, 0xFF);
    }


    /***************************************************************************
     * Vrátí instanci barvy se zadanými velikostmi složek a průhledností.
     * Není-li barva ještě definována, vytvoří ji
     * a přiřadí jí název odvozený z velikostí jejích složek.
     *
     * @param red    Velikost červené složky
     * @param green  Velikost zelené složky
     * @param blue   Velikost modré složky
     * @param alpha  Koeficient alfa = hladina průhlednosti;
     *               0=průhledná, 255=neprůhledná
     * @return Barva se zadanými velikostmi jednotlivých složek
     */
    public
    static Barva getBarva(int red, int green, int blue, int alpha)
    {
        Color color = new Color(red, green, blue, alpha);
        Barva barva = color2barva.get(color);
        if (barva != null) {
            return barva;
        }
        String název = "Barva(r=" + red + ",g=" + green +
                       ",b=" + blue + ",a=" + alpha + ")" ;
        return getBarva(red, green, blue, alpha, název);
    }


    /***************************************************************************
     * Vrátí instanci neprůhledné barvy se zadanými barevnými složkami
     * a zadným názvem. Pokud takováto barva neexistuje, vytvoří ji.
     * Existuje-li barva se zadaným názvem ale jinými složkami, anebo
     * existuje-li barva se zadanými složkami, ale jiným názvem,
     * vyhodí výjimku {@link IllegalArgumentException}.
     *
     * @param red       Velikost červené složky
     * @param green     Velikost zelené složky
     * @param blue      Velikost modré složky
     * @param název     Název vytvořené barvy
     *
     * @return Barva se zadaným názvem a velikostmi jednotlivých složek
     * @throws IllegalArgumentException má-li některé ze známých barev některý
     *         ze zadaných názvů a přitom má jiné nastavení barevných složek
     *         nebo má jiný druhý název.
     */
    public static Barva getBarva(int red, int green, int blue,
                                 String název)
    {
        return getBarva(red, green, blue, 0xFF, název);
    }


    /***************************************************************************
     * Vrátí instanci barvy se zadanými barevnými složkami, průhledností
     * a názvem. Pokud takováto barva neexistuje, vytvoří ji.
     * Existuje-li barva se zadaným názvem ale jinými složkami, anebo
     * existuje-li barva se zadanými složkami, ale jiným názvem,
     * vyhodí výjimku {@link IllegalArgumentException}.
     *
     * @param red       Velikost červené složky
     * @param green     Velikost zelené složky
     * @param blue      Velikost modré složky
     * @param alpha     Hladina průhlednosti (kanál alfa):
     *                  0=průhledná, 255=neprůhledná
     * @param název     Název vytvořené barvy
     * @return Instance třídy Barva reprezentující zadanou barvu.
     * @throws IllegalArgumentException Má-li některá ze definovaných barev
     *         zadaný název, a přitom má jiné nastavení barevných složek, anebo
     *         má shodnou velikost složek, ale jiný druhý název,
     *         anebo je jako název zadán prázdný řetězec.
     * @throws NullPointerException  Je-li {@code název} {@code null}.
     */
    public static Barva getBarva(int red, int green, int blue, int alpha,
                                  String název)
    {
        název = název.trim().toLowerCase();
        if ((název == null)  ||  název.equals(""))  {
            throw new IllegalArgumentException(
                "\nBarva musí mít zadán neprázdný název");
        }
        Color color = new Color(red, green, blue, alpha);
        Barva barvaN = barvaSNázvem   (název);
        Barva barvaC = color2barva.get(color);

        if ((barvaN != null)  &&  (barvaN == barvaC)) {
            //Je požadována již existující barva
            return barvaN;
        }
        if ((barvaN == null)  &&  (barvaC == null)) {
            //Je požadována dosud neexistující barva
            return new Barva(red, green, blue, alpha, název);
        }
        //Zjistíme, s kterou existující barvou požadavky kolidují
        Barva b = (barvaC != null)  ?  barvaC  :  barvaN;
        Color c = b.color;
        throw new IllegalArgumentException(
            "\nZadané parametry barvy kolidují s parametry existující barvy "+
            "[existující × zadaná]:" +
            "\nNázev:          " + b.getNázev()  + " × " + název +
            "\nČervená složka: " + c.getRed()    + " × " + red   +
            "\nZelená  složka: " + c.getGreen()  + " × " + green +
            "\nModrá   složka: " + c.getBlue()   + " × " + blue  +
            "\nPrůhlednost:    " + c.getAlpha()  + " × " + alpha
          );
    }



//== ABSTRAKTNÍ METODY =========================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =====================================

    /***************************************************************************
     * Převede námi používanou barvu na typ používaný kreslítkem.
     * Metoda je používaná ve třídě {@code SprávcePlátna}.
     *
     * @return Instance třídy {@link Color} reprezentující zadanou barvu
     */
    public Color getColor()
    {
        return color;
    }


    /***************************************************************************
     * Vrátí řetězec s charakteritikou dané barvy obsahující
     * název a hodnoty barevných složek uvedené v desítkové soustavě
     *
     * @return Řetězec s dekadickou charakteristikou barvy
     */
    private
    String getCharakteristikaDec()
    {
        return String.format("%s(Dec:R=%d,G=%d,B=%d,A=%d)", název,
                             color.getRed(),  color.getGreen(),
                             color.getBlue(), color.getAlpha()
                           );
    }


    /***************************************************************************
     * Vrátí řetězec s charakteritikou dané barvy obsahující název a
     * hodnoty barevných složek uvedené v šestnáctkové soustavě
     *
     * @return Řetězec s hexadecimální charakteristikou barvy
     */
    private
    String getCharakteristikaHex()
    {
        return getCharakteristikaHexPrivate();
    }


    /***************************************************************************
     * Vrátí index barvy v seznamu dosud vytvořených barev.
     *
     * @return Index dané barvy
     */
    public int getIndex()
    {
        return index;
    }


    /***************************************************************************
     * Vrátí název barvy.
     *
     * @return Název barvy
     */
    public String getNázev()
    {
        return getNázevPrivate();
    }



//== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ========================================

    /***************************************************************************
     * Vrátí průsvitnou verzi dané barvy,
     * tj. barvu se stejnými barvenými složkami a průsvitností 0,5.
     *
     * @return Průsvitná verze dané barvy
     */
    public Barva průsvitná()
    {
        Color newColor = new Color (color.getRed(),  color.getGreen(),
                                    color.getBlue(), PRŮSVIT);
        Barva barva = color2barva.get(newColor);
        if (barva == null) {
            String název2 = "průsvitná_" + this.název;
            barva = název2barva.get(název2);
            if (barva == null) {
                barva = getBarva(
                        color.getRed(),  color.getGreen(),
                        color.getBlue(), PRŮSVIT,  název2);
            }
        }
        return barva;
    }


    /***************************************************************************
     * Vrátí barvu inverzní k zadané barvě, tj. barvu s inverzními
     * hodnotami jednotlivých barevných složek, ale se stejnou průhledností.
     *
     * @return Inverzní barva
     */
    private
    Barva inverzní()
    {
        return getBarva(MAXC - color.getRed(),
                         MAXC - color.getGreen(),
                         MAXC - color.getBlue(), color.getAlpha());
    }


    /***************************************************************************
     * Vrátí méně průhlednou verzi dané barvy.
     * Pozor, vzhledem k zaokrouhlovacím chybám není oparace plně reverzibilní.
     *
     * @return Méně průhledná verze barvy
     */
    private
    Barva neprůhlednější()
    {
        int a = Math.max(Math.min((int)(color.getAlpha()/KC), MAXC), MINC);
        return getBarva(
            new Color(color.getRed(), color.getGreen(),
                      color.getBlue(), a));
    }


    /***************************************************************************
     * Vrátí průhlednější verzi dané barvy.
     * Pozor, vzhledem k zaokrouhlovacím chybám není oparace plně reverzibilní.
     *
     * @return Průhlednější verze barvy
     */
    private
    Barva průhlednější()
    {
        int a = (int)(color.getAlpha() * KC);
        return getBarva(
            new Color(color.getRed(), color.getGreen(),
                      color.getBlue(), a));
    }


    /***************************************************************************
     * Vrátí světlejší verzi dané barvy. Pozor, vzhledem k zaokrouhlovacím
     * chybám nejsou operace světlejší a tmavší zcela reverzní.
     *
     * @return Světlejší verze barvy
     */
    private
    Barva světlejší()
    {
        Color c = color.brighter();
        if (c.equals(color)) {
            c = new Color(Math.max(c.getRed(),   MINC),
                           Math.max(c.getGreen(), MINC),
                           Math.max(c.getBlue(),  MINC),  c.getAlpha());
        }
        return getBarva(c);
    }


    /***************************************************************************
     * Vrátí tmavší verzi dané barvy. Pozor, vzhledem k zaokrouhlovacím
     * chybám nejsou operace světlejší a tmavší zcela reverzní.
     *
     * @return Tmavší verze barvy
     */
    private
    Barva tmavší()
    {
        return getBarva(color.darker());
    }


    /***************************************************************************
     * Vrátí název barvy.
     *
     * @return  Název barvy
     */
    @Override
    public String toString()
    {
        return getNázev();
    }



//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Vrátí řetězec s charakteritikou dané barvy obsahující název a
     * hodnoty barevných složek uvedené v šestnáctkové soustavě
     *
     * @return Řetězec s hexadecimální charakteristikou barvy
     */
    private String getCharakteristikaHexPrivate()
    {
        return String.format("%s(Hex:R=%02X,G=%02X,B=%02X,A=%02X)", název,
                             color.getRed(),  color.getGreen(),
                             color.getBlue(), color.getAlpha()
                           );
    }


    /***************************************************************************
     * Vrátí název barvy.
     *
     * @return Název barvy
     */
    private String getNázevPrivate()
    {
        return (velkými  ?  název.toUpperCase()  :  název);
    }


    /***************************************************************************
     * Vrátí barvu s daným názvem přičem je schopen ignorovat diakritiku.
     *
     * @param název Název hledané barvy
     * @return Barva se zadaným názvem nebo {@code null}.
     */
    private static Barva barvaSNázvem(String název) {
        název = název.toLowerCase();
        Barva barva = název2barva.get(název);
        if (barva == null) {
            barva = názevBhc2barva.get(název);
        }
        return barva;
    }


    /***************************************************************************
     * Vrátí barvu se stejnýmio hodnotami komponent jako má parametr.
     * Není-li taková barva ještě definována, vytvoří jí
     * a pojmenuje ji s využitím hodnota jejích složek.
     *
     * @param c  Barva, která je instancí třídy {@link java.awt.Color}
     * @return Odpovídající barva třídy {@code Barva}
     */
    private static Barva getBarva(Color c)
    {
        Barva b = color2barva.get(c);
        if (b != null) {
            return b;
        }
        else {
            return getBarva(c.getRed(),  c.getGreen(),
                             c.getBlue(), c.getAlpha());
        }
    }



//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================

    /***************************************************************************
     * Obsahuje-li název diakritiku,
     * uloží do příslušné mapy jeho verzi bez diakritiky.
     */
    private void přidejNázevBezDiakritiky() {
        String bhc = IO.odháčkuj(název);
        if (! název.equals(bhc)) {
            názevBhc2barva.put(bhc, this);
        }
    }



//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
