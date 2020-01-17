package srpfacadelab;
import java.util.List;
import java.util.ArrayList;


public class Facade {

  
    private Inventory inventory;

    public Facade(){
        inventory =new Inventory(null);
    }

    public void calculateStats() 
    {
        inventory.calculateStats();

    }
    public void useItem(final Item item){

        inventory.useItem(item);
    }

    public boolean pickUpItem(final Item item) {
        return inventory.pickUpItem(item);
    
    }

    public boolean checkIfItemExistsInInventory(final Item item)
    {
        return inventory.checkIfItemExistsInInventory(item);

    }

    public void calculateInventoryWeight() {
        inventory.calculateInventoryWeight();

    }

    public void takeDamage(final int damage) {
        inventory.takeDamage(damage);
    }





        

}
