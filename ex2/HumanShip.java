import java.awt.Image;
import oop.ex2.*;

/**
 * the human driven ship, getting its commands from the user.
 */
public class HumanShip extends SpaceShip {
    /**
     * this do action does the action given by the buttoms pressed by the user.
     * and at the end charging his energy.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        /* those variables are to detemine whether the user wants to
        turn, accelerate or do both.
         */
        int turnSide = 0;
        boolean accelerate = false;
        removeShield();
        reduceCooldown(1);
        if (game.getGUI().isTeleportPressed()) {
            teleport();
        }
        if (game.getGUI().isUpPressed()) {
            accelerate = true;
        }
        if (game.getGUI().isRightPressed()){
            turnSide -= 1;
        }
        if (game.getGUI().isLeftPressed()){
            turnSide += 1;
        }
        getPhysics().move(accelerate, turnSide);
        if (game.getGUI().isShieldsPressed()) {
            shieldOn();
        }
        if (game.getGUI().isShotPressed()) {
            fire(game);
        }
    ChargeEnergy(BASE_ENERGY_CHARGE);
    }

    /**
     * overloading the get image from the SpaceShip, for the human ship.
     * this is because the human ship has a different kind of ship.
     * @return the image of this ship.
     */
    public Image getImage(){
        if (IsShielded()){
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        }
        else{
            return GameGUI.SPACESHIP_IMAGE;
        }
    }
}
