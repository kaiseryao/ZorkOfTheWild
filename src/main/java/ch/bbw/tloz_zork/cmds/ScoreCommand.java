package ch.bbw.tloz_zork.cmds;


import ch.bbw.tloz_zork.game.Player;

public class ScoreCommand {
    private Player player;
    /**
     * Constructor for ScoreCommand
     * @param player
     */
    public void score(Player player){
        System.out.println("There is not much to see here yet.");
        // TODO: Show Hearts as Icon instead of text
        System.out.println("Health: " + player.getHearts());
    }
}