package srpfacadelab;

import java.util.List;
//Make things public
public interface IGameEngine {

    public void playSpecialEffect(String effectName);
    List<IEnemy> getEnemiesNear(Inventory inventory);

}
