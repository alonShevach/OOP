import java.lang.Math;
/**
 * the Aggressive ship class, this ships will seek enemies and try to
 * distroy them by shooting if they get too close.
 */
public class AggressiveShip extends SpaceShip {
    /* constant variable*/

    /** The minimal rad for the ship to shoot.*/
    private static final double MIN_RAD_TO_SHOOT = 0.21;

    /**
     * the doAction method, which is encharge of the ship.
     * the ship will seek nearby enemies driving straight to them,
     * and when she will get close enough, she will attempt to shoot.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        reduceCooldown(1);
        SpaceShip ClosestShip = game.getClosestShipTo(this);
        double AngleToShip = getPhysics().angleTo(ClosestShip.getPhysics());
        if (AngleToShip < 0){
            getPhysics().move(true, TURN_RIGHT);
        }
        else if (AngleToShip == 0){
            getPhysics().move(true, DO_NOT_TURN);
        }
        else {
            getPhysics().move(true, TURN_LEFT);
        }
        if (Math.abs(AngleToShip) < MIN_RAD_TO_SHOOT) {
            fire(game);
        }
        ChargeEnergy(BASE_ENERGY_CHARGE);

    }

}
