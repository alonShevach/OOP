import java.lang.Math;

/**
 * the special ship, made by me, alon shevach.
 * this ship has an OP mode(overpowered mode), which the ships fires
 * several times to the nearest enemy, and then teleporting.
 */
public class SpecialShip extends SpaceShip {
    /* constant variables */

    /** The minimal distance for the ship to turn on the OpMode.*/
    private static final double MIN_DIST_FOR_OP_MODE = 0.21;

    /** The amount of energy refunded when the ships enters the op mode.*/
    private static final int OP_RECHARGE = 250;

    /** The minimal distance for the ship to teleport away.*/
    private static final double MIN_RANGE_TO_TP = 0.19;

    /**
     * the do action, which is encharge of running the ship's porpose.
     * the ships seeks near enemys and when it gets close enough, it turns
     * on the OpMode.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        SpaceShip ClosestShip = game.getClosestShipTo(this);
        double AngleToShip = getPhysics().angleTo(ClosestShip.getPhysics());
        double DistanceFrom = getPhysics().distanceFrom(ClosestShip.getPhysics());
        if (MIN_RANGE_TO_TP >= DistanceFrom){
            teleport();
        }
        if (AngleToShip < 0){
            getPhysics().move(true, TURN_RIGHT);
        }
        else if (AngleToShip == 0){
            getPhysics().move(true, DO_NOT_TURN);
        }
        else {
            getPhysics().move(true, TURN_LEFT);
        }
        if (MIN_DIST_FOR_OP_MODE >= DistanceFrom) {
            ChargeEnergy(OP_RECHARGE);
            reduceCooldown(7);
            fire(game);

        }
        ChargeEnergy(BASE_ENERGY_CHARGE);
    }


}
