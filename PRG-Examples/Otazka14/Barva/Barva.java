
    /// <summary> 
    /// Třída s parametrem typu barvy (jak budeme barvu reprezentovat).  
    /// </summary> 

    /// <typeparam name="TColor"></typeparam> 
import java.awt.Color;
 
class Barva<TColor>  
{ 
    private TColor color; 
 
    public Barva(TColor color) 
     {  
         // V tomto příkladě nám někdo musí sdělit defaultní barvu, 
         //protože v době psaní třídy ji nemůžeme určit.      
         this.color = color; 
    }
  
    public void setColor(TColor color)  
    { 
         this.color = color; 
     } 
  
    public TColor getColor() 
    { 
        return this.color;  
    } 

} 
