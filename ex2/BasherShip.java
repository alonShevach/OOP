/**
 * the basher ship class, the basher always seeks for enemys to bash at.
 */
public class BasherShip extends SpaceShip {
    /* constant variable for when to turn on shield*/

    /** The minimal distance for the ship to turn on the shield.*/
    private static final double MIN_DIST_TO_TURN_SHIELD = 0.19;

    /**
     * the basher always seeks the nearest enemy ship, trying to bash him,
     * also when he gets really close he attempts to turn his shield on
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        removeShield();
        SpaceShip ClosestShip = game.getClosestShipTo(this);
        double AngleToShip = getPhysics().angleTo(ClosestShip.getPhysics());
        double DistanceFrom = getPhysics().distanceFrom(ClosestShip.getPhysics());
        if (AngleToShip < 0){
            getPhysics().move(true, TURN_RIGHT);
        }
        else if (AngleToShip == 0){
            getPhysics().move(true, DO_NOT_TURN);
        }
        else {
            getPhysics().move(true, TURN_LEFT);
        }
        if (MIN_DIST_TO_TURN_SHIELD >= DistanceFrom){
            shieldOn();
        }
        ChargeEnergy(BASE_ENERGY_CHARGE);
    }

}
