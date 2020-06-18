/**
 * a class for the drunk ship driver, our driver had a few drinks
 * and sadly he fell asleep on the ship's steering wheel, and
 * on the teleport and shield buttons, hopefully he'll learn his lesson.
 */
public class DrunkardShip extends SpaceShip {
    /**
     * the actions of the ship, always accelerating to the left
     * and trying to teleport and to turn on shield.
     * and at the end charging his energy.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        teleport();
        getPhysics().move(true, TURN_LEFT);
        shieldOn();
        ChargeEnergy(BASE_ENERGY_CHARGE);
        }

    }

