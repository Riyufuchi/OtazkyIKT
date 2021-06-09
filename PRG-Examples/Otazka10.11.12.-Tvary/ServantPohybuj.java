

public class ServantPohybuj
{
    public ServantPohybuj()
    {
        
    }
    
    public void pohybNa(IPohyb objekt, int x, int y)
    {
       objekt.setPozice(x, y); 
    }
    
    public void pohybO(IPohyb objekt, int x, int y)
    {
       objekt.posunDolů(y); 
       objekt.posunVpravo(x);
    }
}
