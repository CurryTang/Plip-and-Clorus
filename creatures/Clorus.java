package creatures;
import java.awt.Color;
import huglife.HugLifeUtils;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;
import huglife.Action;
import huglife.Empty;
import java.util.Map;
import java.util.List;



public class Clorus extends Creature{
    //RGB settings.

    private int r;
    private int g;
    private int b;

    /** Create clorus with energy equal to E */
    public Clorus(double e){
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /** Default constructor for Clorus: with energy 1 */
    public Clorus(){
        this(1.0);
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public void move(){
        energy -= 0.03;
    }

    public void stay(){
        energy -= 0.01;
    }

    public void attack(Creature c){
        double cEnergy = c.energy();
        energy += cEnergy;
    }

    public Clorus replicate() {
        Clorus newC = new Clorus(energy / 2);
        energy /= 2;
        return newC; 
    }

    /**Cloruses should obey exactly the following behavioral rules
     * If there are no empty squares, the Clorus will STAY (even if there are Plips nearby they could attack).
     * Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
     * Otherwise, the Clorus will MOVE to a random empty square. 
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors){
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (empties.size() == 0){
            return new Action(Action.ActionType.STAY);
        } else {
            if (plips.size() > 0){
                int randomIndex = HugLifeUtils.randomInt(0, plips.size() - 1);
                Direction moveDir = plips.get(randomIndex);
                return new Action(Action.ActionType.ATTACK, moveDir);
            } else if (energy >= 1.0){
                int randomIndex = HugLifeUtils.randomInt(0, empties.size() - 1);
                Direction moveDir = empties.get(randomIndex);
                return new Action(Action.ActionType.REPLICATE, moveDir);
            } else {
                int randomIndex = HugLifeUtils.randomInt(0, empties.size() - 1);
                Direction moveDir = empties.get(randomIndex);
                return new Action(Action.ActionType.MOVE, moveDir);                                               
            }
        }
    }



}