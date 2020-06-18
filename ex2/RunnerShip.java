
/**
 * the runner ship class, the runner tried to get away from troubles,
 * by driving away from enemy ships and teleporting when feels in danger.
 */
public class RunnerShip extends SpaceShip {
    /* constant variable */

    /** The minimal distance for the ship to teleport.*/
    private static final double MIN_DIST_TO_TP = 0.25;

    /**
     * the do action method, which is encharge of the ship's actions.
     * the ship will try to get away from nearby enemies, and if they gets
     * too close she will teleport.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        SpaceShip ClosestShip = game.getClosestShipTo(this);
        double AngleToShip = ClosestShip.getPhysics().angleTo(getPhysics());
        double DistanceFrom = getPhysics().distanceFrom(ClosestShip.getPhysics());
        if (MIN_DIST_TO_TP >= DistanceFrom) {
            teleport();
        }
            if (AngleToShip == 0){
                getPhysics().move(true, DO_NOT_TURN);
            }
            else if (AngleToShip < 0){
                getPhysics().move(true, TURN_LEFT);
            }
            else {
                getPhysics().move(true, TURN_RIGHT);
            }
        ChargeEnergy(BASE_ENERGY_CHARGE);
    }
}
