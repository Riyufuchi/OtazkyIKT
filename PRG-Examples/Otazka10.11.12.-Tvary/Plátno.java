/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Shape;

import java.awt.geom.Rectangle2D;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;

import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/*******************************************************************************
 * Instnace třídy {@code Plátno} (jedináček) slouží jako virtuální plátno,
 * na něž mohou být kresleny jednotlivé obrazce.
 * <p>
 * Třída neposkytuje veřejný konstruktor,
 * protože chce, aby její instance byla jedináček,
 * tj. aby se všechno kreslilo na jedno a to samé plátno.
 * Jediným způsobem, jak získat odkaz na instanci třídy Plátno,
 * je volaní statické metody {@link #getInstance()}.</p>.
 * <p>
 * Aby bylo možno na plátno obyčejné kreslit
 * a nebylo nutno kreslené objekty přihlašovat,
 * odmazané časti obrazců se automaticky neobnovují.
 * Je-li proto při smazání některého obrazce odmazána část jiného obrazce,
 * je třeba příslušný obrazec explicitně překreslit.</p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 3.02.3736 — 2013-02-17
 */
public final class Plátno
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================

    /** Titulek v záhlaví okna plátna. */
    private static final String TITULEK  = "Jednoduché plátno";

    /** Počáteční šířka plátna v bodech. */
    private static final int ŠÍŘKA_0 = 300;

    /** Počáteční výška plátna v bodech. */
    private static final int VÝŠKA_0 = 300;

    /** Počáteční barva pozadí plátna. */
    private static final Barva POZADÍ_0 = Barva.KRÉMOVÁ;



//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================

    /** Jediná instance třídy Plátno. */
    private static volatile Plátno jedináček;



//== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ========================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

    //Z venku neovlivnitelné Atributy pro zobrazeni plátna v aplikačním okně

        /** Aplikační okno animacniho plátna. */
        private JFrame okno;

        /** Instance lokální třídy, která je zřízena proto, aby odstínila
         *  metody svého rodiče JPanel. */
        private JPanel vlastníPlátno;

        /** Vše se kreslí na obraz -
         *  ten se snadněji překreslí. */
        private Image obrazPlátna;

        /** Kreslítko získané od obrazu plátna, na nějž se vlastně kreslí. */
        private Graphics2D kreslítko;


    //Atributy přímo ovlivnitelné uživatelem

        /** Barva pozadí při kreslení. */
        private Barva barvaPozadí;

        /** Šířka plátna v bodech. */
        private int šířka;

        /** Výška plátna v bodech. */
        private int výška;

        /** Pozice plátna na obrazovace - při používání více obrazovek
         *  je občas třeba ji po zviditelnění obnovit. */
        Point pozicePlátna;



//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================
//== OSTATNÍ NESOUKROMÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Smaže plátno, přesněji smaže všechny obrazce na plátně.
     * Tato metoda by měla býr definována jako metoda instance,
     * avšak protože je instance jedináček,
     * byla metoda pro snazší dostupnost definovaná jako metoda třídy,
     * aby nebylo potřeba před žádostí o smazání plátna vytvářet jeho instanci.
     */
    public static void smažPlátno()
    {
        jedináček.smaž();
    }



//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Jediná metoda umožnující získat odkaz na instanci plátna.
     * Protože je však tato instance definována jako jedináček
     * Vrací metoda pokaždé odkaz na stejnou instanci.
     *
     * @return Odkaz na instanci třídy Plátno.
     */
    public static Plátno getInstance()
    {
        if (jedináček == null) {
            synchronized(Plátno.class) {
                if (jedináček == null) {
                    inicializuj();
                }
            }
        }
        jedináček.setViditelné(true);
        return jedináček;
    }


    /***************************************************************************
     * Implicitní (a jediný) konstruktor.
     * Je volán pouze jednou, a to v deklaraci jedináčka.
     *
     * @param pozice Počáteční pozice aplikačního okna
     */
    @SuppressWarnings("serial")     //Kvůli anonymní třídě
    private Plátno(Point pozice)
    {
        pozicePlátna = pozice;
        okno         = new JFrame();

        //Zavřením okna se zavře celá aplikace
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        vlastníPlátno = new JPanel()
        {   /** Povinně překrývaná abstraktní metoda třídy JPanel. */
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(obrazPlátna, 0, 0, null);
            }
        };
        okno.setContentPane(vlastníPlátno);
        barvaPozadí = POZADÍ_0;

        setRozměrSoukr(ŠÍŘKA_0, VÝŠKA_0);  //Připraví a vykreslí prázdné plátno
        okno.setLocation(pozice);
        okno.setTitle(TITULEK);
        IO.Oprava.poziceOkna(okno);
        připravObrázek();
        smaž();

        IO.oknaNa(pozicePlátna.x,
                  pozicePlátna.y + okno.getSize().height);
    }



//== ABSTRAKTNÍ METODY =========================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =====================================

    /***************************************************************************
     * Poskytuje informaci o aktuální viditelnosti okna.
     * Nicméně i viditelná okna mohou být zakryta jinými okny.
     *
     * @return Je-li okno viditelné, vrací <b>true</b>,
     *         jinak vrací <b>false</b>
     */
    public boolean isViditelné()
    {
        return okno.isVisible();
    }


    /***************************************************************************
     * Nastaví viditelnost plátna.
     *
     * @param viditelné {@code true} má-li být plátno viditelné,
     *                  {@code false} má-li naopak přestat být viditelné
     */
    public void setViditelné(boolean viditelné)
    {
        boolean změna = (isViditelné() != viditelné);
        if (změna) {
            if (! viditelné) {
                okno.setVisible(false);
            }
            else {
                if (java.awt.EventQueue.isDispatchThread()) {
                    setViditelnéInterní();
                    return;
                }
                Runnable run = new Runnable() {
                    @Override
                    public void run()
                    {
                        setViditelnéInterní();
                    }
                };
                try {
                    java.awt.EventQueue.invokeAndWait(run);
                }
                catch (Exception ex) {
                    throw new RuntimeException(
                            "\nChyba při nastavování viditelnosti", ex);
                }
            }
        }
    }


    /***************************************************************************
     * Vrátí aktuální barvu pozadí.
     *
     * @return   Nastavena barva pozadí
     */
    public Barva getBarvaPozadí()
    {
        return barvaPozadí;
    }


    /***************************************************************************
     * Nastaví pro plátno barvu pozadí.
     *
     * @param barva  Nastavovaná barva pozadí
     */
    public void setBarvaPozadí(Barva barva)
    {
        barvaPozadí = barva;
        kreslítko.setBackground(barvaPozadí.getColor());
        smaž();
    }


    /***************************************************************************
     * Nastaví pro plátno barvu popředí.
     *
     * @param  barva  Nastavovaná barva popředí
     */
    public void setBarvaPopředí(Barva barva)
    {
        kreslítko.setColor(barva.getColor());
    }


    /***************************************************************************
     * Vrátí šířku plátna.
     *
     * @return  Aktuální šířka plátna v bodech
     */
    public int getŠířka()
    {
        return šířka;
    }


    /***************************************************************************
     * Vrátí výšku plátna.
     *
     * @return  Aktuální výška plátna v bodech
     */
    public int getVýška()
    {
        return výška;
    }


    /***************************************************************************
     * Nastaví nový rozměr plátna zadáním jeho výsky a šířky.
     *
     * @param  šířka  Nova šířka plátna v bodech
     * @param  výška  Nová výška plátna v bodech
     */
    public void setRozměr(int šířka, int výška)
    {
        setRozměrSoukr(šířka, výška);
        setViditelné(true);
        připravObrázek();
        smaž();
    }



//== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ========================================

    /***************************************************************************
     * Aktuální barvou popředí nakreslí na plátno úsečku
     * se zadanými krajními body a barvou.
     *
     * @param  x1    x-ová souřadnice počátku
     * @param  y1    y-ová souřadnice počátku
     * @param  x2    x-ová souřadnice konce
     * @param  y2    x-ová souřadnice konce
     * @param  barva Barva úsečky
     */
    public void kresliČáru(int x1, int y1, int x2, int y2, Barva barva)
    {
        setBarvaPopředí(barva);
        kreslítko.drawLine(x1, y1, x2, y2);
        vlastníPlátno.repaint();
    }


    /***************************************************************************
     * Vypíše na plátno text aktuálním písmem a aktuální barvou popředí.
     *
     * @param text   Zobrazovaný text
     * @param x      x-ová souřadnice textu
     * @param y      y-ová souřadnice textu
     * @param barva  Barva, kterou se zadaný text vypíše
     */
    public void kresliString(String text, int x, int y, Barva barva)
    {
        setBarvaPopředí(barva);
        kreslítko.drawString(text, x, y);
        vlastníPlátno.repaint();
    }


    /***************************************************************************
     * Smaže plátno, přesněji smaže všechny obrazce na plátně.
     */
    public void smaž()
    {
        smaž(new Rectangle2D.Double(0, 0, šířka, výška));
    }


    /***************************************************************************
     * Smaže zadaný obrazec na plátně; obrazec vsak stalé existuje,
     * jenom není vidět. Smaže se totiž tak, že se nakreslí barvou pozadí.
     *
     * @param  obrazec   Obrazec, který má byt smazán
     */
    public void smaž(Shape obrazec)
    {
        Color original = kreslítko.getColor();
        kreslítko.setColor(barvaPozadí.getColor());
        kreslítko.fill(obrazec);       //Smaže jej vyplněním barvou pozadí
        kreslítko.setColor(original);
        vlastníPlátno.repaint();
    }


    /***************************************************************************
     * Vrátí string reprezentující danou instanci (podpis instance).
     * Používá se především při ladění.
     *
     * @return Řetězcová reprezentace dané instance.
     */
    @Override
    public String toString()
    {
        return this.getClass().getName() +
            "(" + šířka + "×" + výška +
            " bodů, barvaPozadí=" + barvaPozadí + ")";
    }


    /***************************************************************************
     * Nakreslí zadaný obrazec a vybarví jej barvou popředí plátna.
     *
     * @param  obrazec  Kreslený obrazec
     */
    public void zaplň(Shape obrazec)
    {
        kreslítko.fill(obrazec);
        vlastníPlátno.repaint();
    }



//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Inicializuje některé parametry z konfiguračního souboru.
     * Tento soubor je umístěn v domovském adresáři uživatele
     * ve složce {@code .rup} v souboru {@code bluej.properties}.
     * Je určen především pro učitele, aby jim usnadnil umiťování oken
     * při práci s několika monitoly, z nichž pouze jeden vidí studenti.
     *
     * @return
     */
    private static Point konfiguraceZeSouboru()
    {
        Point pozice;

        Properties sysProp = System.getProperties();
        String     userDir = sysProp.getProperty("user.home");
        File       rupFile = new File(userDir, ".rup/bluej.properties");
        Properties rupProp = new Properties();
        try {
            Reader reader = new FileReader(rupFile);
            rupProp.load(reader);
            reader.close();
            String sx = rupProp.getProperty("canvas.x");
            String sy = rupProp.getProperty("canvas.y");
            int x = Integer.parseInt(rupProp.getProperty("canvas.x"));
            int y = Integer.parseInt(rupProp.getProperty("canvas.y"));
            pozice = new Point(x, y);
        }catch(Exception e)  {
            pozice = new Point(0, 0);
        }
        return pozice;
    }


    /***************************************************************************
     * Initialize a canvas manager by putting the initializing code
     * into the AWT Event Queue.
     */
    private static void inicializuj()
    {
        final Point   pozice  = konfiguraceZeSouboru();
        final Kutloch kutloch = new Kutloch();

        Runnable připravSP = new Runnable() {
            @Override public void run()
            {
                připravPlátno(pozice, kutloch);
            }
        };
        try {
            java.awt.EventQueue.invokeAndWait(připravSP);
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter  pw = new PrintWriter(sw);

            sw.write("\nCreation of CanvasManager doesn't succeed\n");
            ex.printStackTrace(pw);

            String msg = sw.toString();
            System.err.println(msg);
            JOptionPane.showMessageDialog(null, msg);

            System.exit(1);
        }

        //Správce je vytvořen, budeme umísťovat dialogové okna
        Plátno plátno = kutloch.plátno;
        int x = plátno.okno.getX();
        int y = plátno.okno.getY() + plátno.okno.getHeight();
        IO.oknaNa(x, y);

        //Vše je hotovo, můžeme atribut inicializovat
        jedináček = plátno;

    }


    private static void připravPlátno(Point pozice, Kutloch kutloch)
    {
        kutloch.plátno = new Plátno(pozice);
    }



//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================

    /***************************************************************************
     * Připraví obrázek, do nějž se budou všechny tvary kreslit.
     */
    private void připravObrázek()
    {
        obrazPlátna = vlastníPlátno.createImage(šířka, výška);
        kreslítko = (Graphics2D)obrazPlátna.getGraphics();
        kreslítko.setColor(barvaPozadí.getColor());
        kreslítko.fillRect(0, 0, šířka, výška);
        kreslítko.setColor(Color.black);
    }


    /***************************************************************************
     * Nastaví zadaný rozměr plátna, ale pouze ten.
     * Soukromá verze určená pro konstruktor.
     * Veřejná verze přidává ještě zviditelnění plátna a přípravu obrázku.
     *
     * @param šířka  Nastavovaná bodová šířka plátna
     * @param výška  Nastavovaná bodová výška plátna
     */
    private void setRozměrSoukr(int šířka, int výška)
    {
        boolean upravit;
        Dimension dim;
        Insets    ins;

        this.šířka = šířka;
        this.výška = výška;
        okno.setResizable(true);
        vlastníPlátno.setPreferredSize(new Dimension(šířka, výška));
        okno.pack();
        okno.setVisible(true);

//        do {
//            this.šířka = šířka;
//            this.výška = výška;
//            okno.setResizable(true);
//            vlastníPlátno.setPreferredSize(new Dimension(šířka, výška));
//            okno.pack();
//            dim = okno.getSize();
//            ins = okno.getInsets();
////            IO.zpráva(
////                   "Nastavuju: šířka=" + šířka + ", výška=" + výška +
////                 "\nMám: width=" + dim.width + ", height=" + dim.height +
////                 "\nleft=" + ins.left + ", right=" + ins.right +
////                 "\n top=" + ins.top + ", bottom=" + ins.bottom);
//            upravit = false;
//            if (šířka < (dim.width - ins.left - ins.right)) {
//                šířka  = dim.width - ins.left - ins.right + 2;
//                upravit= true;
//            }
//            if (výška < (dim.height - ins.top - ins.bottom)) {
//                výška  = dim.height - ins.top - ins.bottom;
//                upravit= true;
//            }
//        } while (upravit);

        okno.setResizable(false);    //Není možné měnit rozměr pomocí myši
        IO.Oprava.rozměrOkna(okno);
    }


    /***************************************************************************
     * Metoda volána z vlákna událostí.
     */
    private void setViditelnéInterní()
    {
        pozicePlátna = okno.getLocation();
        okno.setVisible(true);

        //Při více obrazovkách po zviditelnění blbne =>
        okno.setLocation(pozicePlátna); //je třeba znovu nastavit pozici
        okno.setAlwaysOnTop(true);
        okno.toFront();
        okno.setAlwaysOnTop(false);

        okno.pack();
    }



//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================

    ////////////////////////////////////////////////////////////////////////////
    /***************************************************************************
     * Přepravka, v níž uzávěr předává vytvořené plátno.
     */
    private static class Kutloch
    {
        volatile Plátno plátno;
    }
    ////////////////////////////////////////////////////////////////////////////



//== TESTOVACÍ TŘÍDY A METODY ==================================================
}
