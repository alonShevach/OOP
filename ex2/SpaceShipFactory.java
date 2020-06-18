/**
 * the SpaceShipFactory class, encharge of creating the spaceships for the game.
 */
public class SpaceShipFactory {
    /* constant variables*/

    /** the string represent each specific kind of spaceship. */
    private static final String HUMAN_SHIP = "h";
    private static final String BASHER_SHIP = "b";
    private static final String RUNNER_SHIP = "r";
    private static final String DRUNKARD_SHIP = "d";
    private static final String SPECIAL_SHIP = "s";
    private static final String AGGRESSIVE_SHIP = "a";

    /**
     * the method creating the ships one by one, by their ship type
     * given from the user.
     * @param args a list of strings, that represents ship types.
     * @return the array of the Ships created.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceShips = new SpaceShip[args.length];
        for(int i=0; i<spaceShips.length; i++){
            switch(args[i]){
                case(HUMAN_SHIP):
                    spaceShips[i] = new HumanShip();
                    break;
                case(BASHER_SHIP):
                    spaceShips[i] = new BasherShip();
                    break;
                case(RUNNER_SHIP):
                    spaceShips[i] = new RunnerShip();
                    break;
                case(DRUNKARD_SHIP):
                    spaceShips[i] = new DrunkardShip();
                    break;
                case(SPECIAL_SHIP):
                    spaceShips[i] = new SpecialShip();
                    break;
                case(AGGRESSIVE_SHIP):
                    spaceShips[i] = new AggressiveShip();
                    break;
                default:
                    /* if the user has given a bad input */
                    spaceShips[i] = null;

            }
        }
        return spaceShips;
    }
}
