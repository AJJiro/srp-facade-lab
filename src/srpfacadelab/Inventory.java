package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

//Put the constructor in the parameter
//Everything in Inventory

public class Inventory {
   
    public final RpgPlayer player;
    public  IGameEngine gameEngine;
    
    public Inventory(final RpgPlayer player) {
        this.player = player;	
    }

    public void calculateStats() {
        for (final Item i: player.getInventory()) {
            player.setArmour(player.getArmour() + i.getArmour());
        }
    }

    public void useItem(final Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            final List<IEnemy> enemies = gameEngine.getEnemiesNear(this);

            for (final IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(final Item item) {
        final int weight = calculateInventoryWeight();
        if (weight + item.getWeight() > player.getCarryingCapacity())
            return false;

        if (item.isUnique() && checkIfItemExistsInInventory(item))
            return false;


        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            player.getHealth += item.getHeal();

            if (player.getHealth > player.maxHealth)
                player.getHealth = player.maxHealth;

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare())
            gameEngine.playSpecialEffect("cool_swirly_particles");

        if(item.isUnique() && item.isRare())
                gameEngine.playSpecialEffect("blue_swirly");

        player.getInventory().add(item);

        calculateStats();

        return true;
    }


   

    

    public boolean checkIfItemExistsInInventory(final Item item) {
        for (final Item i: player.getInventory()) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    public int calculateInventoryWeight() {
        int sum=0;
        for (final Item i: player.getInventory()) {
            sum += i.getWeight();
        }
        return sum;
    }

    public void takeDamage(final int damage) {
        if (damage < player.getArmour())
        {
            gameEngine.playSpecialEffect("parry");
        }

        if(this.calculateInventoryWeight()== player.getCarryingCapacity())
        {
            final int damageToDeal = (int) ((damage-player.getArmour())*0.25);
            player.getHealth -= damageToDeal;
        }

        final int damageToDeal = damage - player.getArmour();
        player.getHealth -= damageToDeal;

        gameEngine.playSpecialEffect("lots_of_gore");
    }

}
