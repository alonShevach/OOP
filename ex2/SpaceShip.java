import oop.ex2.*;
import oop.ex2.GameGUI;
import java.awt.*;

/**
 * the class that represents a spaceship, this class is abstract, having
 * the other extended classes, this class has all the methods and variables
 * that are for all different kind of spaceships.
 *
 * @author Alon Shevach.
 */
public abstract class SpaceShip{
    /* Constant variables */

    /** The amount of energy the shield takes each turn.*/
    private static final int SHIELD_COST_PER_TURN = 3;

    /** The Default health for each ship to summon with.*/
    private static final int DEFAULT_HEALTH = 22;

    /** The default amount of max energy each ship starts with.*/
    private static final int DEFAULT_MAX_ENERGY= 210;

    /** The default starting energy of each ship*/
    private static final int DEFAULT_STARTING_ENERGY = 190;

    /** The amount of energy the teleport takes.*/
    private static final int TELEPORT_ENERGY_COST = 140;

    /** The number of turns each ship can shoot again, the shots cooldown*/
    private static final int SHOT_COOLDOWN = 7;

    /** The amount of energy the shot takes.*/
    private static final int SHOT_ENERGY_COST = 19;

    /** The amount of energy deducted by getting hit by another ship*/
    private static final int MAX_ENERGY_DEDUCT_IN_HIT = 10;

    /** The amount of energy refunded upon colliding with another ship while
     * having a shield on*/
    private static final int ENERGY_REFUND_ON_COLLIDE = 18;

    /* protected, because it is used only in the inner classes */

    /** The number represents a right turn*/
    protected static final int TURN_RIGHT = -1;

    /** The number represents a left turn*/
    protected static final int TURN_LEFT = 1;

    /** The number represents no turning at all*/
    protected static final int DO_NOT_TURN = 0;

    /** The amount of energy charging on each turn*/
    protected static final int BASE_ENERGY_CHARGE = 1;

    /** The SpaceShipPhysics type that represents the pysics of each ship*/
    private SpaceShipPhysics ShipPhysics;

    /** The maximal energy of a specific ship*/
    private int MaximalEnergy;

    /** The current energy a specific ship has.*/
    private int CurrentEnergy;

    /** The current health a specific ship has.*/
    private int CurrHealth;

    /** a boolean that represents if the ship have or do not have a shield on.*/
    private boolean HasShield;

    /** The shot cooldown a specific ship has.*/
    private int ShotCooldown;

    /**
     * creates the ships, be giving them Max_energy, CurrentEnergy,
     * CurrentHealth, HasShield, shotCooldown and ShipPhysics
     */
    SpaceShip(){
        reset();
    }
    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip(){
        if (HasShield) {
            HasShield = false;
            MaximalEnergy += ENERGY_REFUND_ON_COLLIDE;
            CurrentEnergy += ENERGY_REFUND_ON_COLLIDE;
        }
        else{
            CurrHealth--;
            if (MaximalEnergy >= MAX_ENERGY_DEDUCT_IN_HIT) {
                MaximalEnergy -= MAX_ENERGY_DEDUCT_IN_HIT;
            }
            else{
                MaximalEnergy = 0;
            }
        }
        if (CurrentEnergy > MaximalEnergy) {
            CurrentEnergy = MaximalEnergy;
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        ShipPhysics = new SpaceShipPhysics();
        CurrHealth = DEFAULT_HEALTH;
        MaximalEnergy = DEFAULT_MAX_ENERGY;
        CurrentEnergy = DEFAULT_STARTING_ENERGY;
        HasShield = false;
        ShotCooldown = 0;
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return CurrHealth <= 0;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return ShipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (IsShielded()) {
            HasShield = false;
        }
        else{
            CurrHealth--;
            MaximalEnergy -= MAX_ENERGY_DEDUCT_IN_HIT;
        }
        if (CurrentEnergy > MaximalEnergy) {
            CurrentEnergy = MaximalEnergy;
        }
    }
    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage(){
        if (IsShielded()){
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        else{
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if ((CurrentEnergy >= SHOT_ENERGY_COST) && (ShotCooldown == 0)) {
            CurrentEnergy -= SHOT_ENERGY_COST;
            game.addShot(ShipPhysics);
            ShotCooldown = SHOT_COOLDOWN;
        }
    }

        /**
         * Attempts to turn on the shield.
         */
    public void shieldOn() {
        if (CurrentEnergy > SHIELD_COST_PER_TURN) {
            CurrentEnergy -= SHIELD_COST_PER_TURN;
            HasShield = true;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (CurrentEnergy < TELEPORT_ENERGY_COST) {
            return;
        }
        CurrentEnergy -= TELEPORT_ENERGY_COST;
        ShipPhysics = new SpaceShipPhysics();
    }

    /**
     * charging the amount of energy given to the ship.
     * @param n the amount to charge to the ship.
     */
    protected void ChargeEnergy(int n){
        if (CurrentEnergy + n <= MaximalEnergy){
            CurrentEnergy += n;
        }
        else{
            CurrentEnergy = MaximalEnergy;
        }
    }

    /**
     * tells if the ship is shielded or not
     * @return true if shielded, false otherwise.
     */
    protected boolean IsShielded(){
        return HasShield;
    }

    /**
     * a method removing the ship's shield.
     */
    protected void removeShield(){
        HasShield = false;
    }

    /**
     * a method that reduces the current shotcooldown of the ship,
     * by reducing the given number.
     * @param n a number to reduce from the cooldown
     */
    protected void reduceCooldown(int n){
        if (n >= ShotCooldown){
            ShotCooldown = 0;
        }
        else{
            ShotCooldown -= n;
        }
    }
}
