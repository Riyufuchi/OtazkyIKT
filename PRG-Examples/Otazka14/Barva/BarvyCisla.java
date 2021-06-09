
import java.lang.reflect.Array;
public enum BarvyCisla
{
    Zelená (0,255,0),
    Červená (255,0,0),
    Modrá   (0,0,255);
            
    private int r;
    private int g;
    private int b;
            
    BarvyCisla(int r,int g,int b)
    {
           this.r = r;
           this.g = g;
           this.b = b;
    }
            
    public int getR()
    {
           return this.r;
    }            
    
    public int getG()
    {
           return this.g;
    }
            
    public int getB()
    {
          return this.b;
    }
}
