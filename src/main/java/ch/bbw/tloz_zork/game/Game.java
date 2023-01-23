package ch.bbw.tloz_zork.game;

import ch.bbw.tloz_zork.enemies.Enemy;
import ch.bbw.tloz_zork.cmds.CommandHandler;
import ch.bbw.tloz_zork.exceptions.InvalidCommandException;
import ch.bbw.tloz_zork.exceptions.InvalidDirectionException;
import ch.bbw.tloz_zork.items.Item;
import ch.bbw.tloz_zork.locations.Dungeon;
import ch.bbw.tloz_zork.locations.Location;
import ch.bbw.tloz_zork.riddles.Riddle;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Game {
    private Player player;
    private Location castle_ruin, woodland, castle, cave, desert, underwater_temple;
    private CommandHandler commandHandler;

    //private Place places; // oder auch Räume
    public void startGame() {
        player = new Player(3, 1, 5, null, 20.0, null);
        commandHandler = new CommandHandler();
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("     /\\");
        System.out.println("    /  \\");
        System.out.println("   /____\\");
        System.out.println("  /\\    /\\");
        System.out.println(" /  \\  /  \\");
        System.out.println("/____\\/____\\");
        System.out.println("Welcome to");
        System.out.println("\"The Legends of Zelda: Zork of the Wild\"");
        System.out.println("");
        System.out.println("Type 'start' to start the game");
        System.out.print("》 ");

        while (!(command = scanner.nextLine()).equalsIgnoreCase("start")) {
            System.out.println("Invalid command. Please type 'start' to begin the game.");
            System.out.print("》 ");
        }
        System.out.println("Is this your first time playing The Legends of Zelda: Zork of the Wild?");
        System.out.print("》 ");
        switch (scanner.nextLine().toLowerCase()) {
            case "yes":
                CombatTutorial combatTutorial = new CombatTutorial();
                combatTutorial.dummyTutorial();
            case "no":
                System.out.println("Then let's jump right into your adventure!");
                initializeGame();
                System.out.println("");
                loading(1000);
                System.out.print(".");
                loading(1000);
                System.out.print(".");
                loading(1000);
                System.out.print(".");
                System.out.println("Link, are you awake? You're currently in the castle ruin. You have to find a way out of here.");
                // Hier startet das Spiel
                while (true) {
                    System.out.print("》 ");
                    command = scanner.nextLine();
                    try {
                        commandHandler.handleCommand(command, player);
                    } catch (InvalidCommandException | InvalidDirectionException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            default:
                System.out.println("I need a yes or a no");
                break;
        }
    }

    private void initializeGame() {
        // Hier wird alles initialisiert
        Item bow = new Item("Bow", "A ranged weapon used to defeat enemies and hit distant targets.", 1.2, "\uD83C\uDFF9");
        Item sword = new Item("Sword", "A melee weapon used to defeat enemies and hit close targets.", 1.8, "\uD83D\uDDE1");
        Item shield = new Item("Shield", "A defensive item used to protect the player from enemy attacks.", 6.5, "\uD83D\uDEE1");
        Item banana = new Item("Banana", "A healing item used to restore health.", 0.5, "\uD83D\uDC9F");
        Item apple = new Item("Apple", "A healing item used to restore health.", 0.2, "\uD83C\uDF4E");
        Item boomerang = new Item("Boomerang", "A ranged weapon used to defeat enemies and hit distant targets.", 1.2, "\uD83E\uDE83");

        // Initialisierung Rätselelemente
        Riddle zelda_name_riddle = new Riddle("What is the name of the princess of Hyrule?", null, "Zelda");
        Riddle master_sword_riddle = new Riddle("How many heart chambers does it take to pull the master sword out of the stone?", null, "13");

        // Initialisierung Räume
        castle_ruin = new Location("Castle Ruin", "\uD83C\uDFDB", "A mysterious, crumbling castle awaits exploration, filled with dangerous enemies and valuable treasures.", "castle_ruin");
        woodland = new Location("Woodland", "\uD83C\uDF33", "A dense forest filled with dangerous enemies and valuable treasures. Location of the master sword.", "woodland");
        castle = new Location("Castle", "\uD83C\uDFF0", "A grand and imposing castle stands at the center of the kingdom, guarded by powerful enemies and holding secrets of ancient power.", "castle");
        cave = new Location("Cave", "\uD83E\uDEA8", "A dark and treacherous cave system winds deep into the earth, filled with dangerous creatures and hidden treasures.", "cave");
        desert = new Location("Desert", "\uD83C\uDFDC️", "A vast and scorching desert stretches as far as the eye can see, with hidden oases, ancient ruins, and deadly sandstorms.", "desert");
        underwater_temple = new Location("Underwater Temple", "\uD83D\uDED5", "A mysterious underwater temple lies beneath the waves, filled with treacherous currents, ancient technology and deadly guardians.", "underwater_temple");

        // Initialisierung Dungeon
        Dungeon temple_of_time = new Dungeon("Temple of Time", "⌛", "The Temple of Time is an impressive building located in the castle ruin of Hyrule. It is surrounded by a majestic waterfall and has a magnificent architecture reminiscent of ancient temples", "temple_of_time", false, sword, master_sword_riddle);
        Dungeon shadow_dungeon = new Dungeon("Shadow Dungeon", "🕳️", "A mysterious dungeon, between trees in the woodland, right next to the master sword place.", "shadow_temple", false, shield, zelda_name_riddle);
        Dungeon spirit_dungeon = new Dungeon("Spirit Dungeon", "\uD83D\uDC7B", "A mysterious temple lies in the underground, hidden in the cave.", "spirit_temple", false, sword, null); // TODO: Fill with enemies
        Dungeon desert_dungeon = new Dungeon("Water Dungeon", "\uD83D\uDCA6", "An underwater dungeon, which is located directly in the underwater temple", "water_temple", false, sword, null); // TODO: Fill with enemies

        // Initialisierung Raum-Items
        //TODO: Eventuell randomizen
        castle_ruin.addItem(bow);
        castle_ruin.addItem(shield);
        woodland.addItem(banana);
        castle.addItem(sword);
        cave.addItem(apple);
        underwater_temple.addItem(boomerang);

        // Initialisierung Zugänge (Gates) -> Map
        Gate gateCastle_ruinWoodland = new Gate(castle_ruin, woodland, false);
        Gate gateWoodlandCastle = new Gate(woodland, castle, false);
        Gate gateCastle_ruinCave = new Gate(castle_ruin, cave, false);
        Gate gateCaveDesert = new Gate(cave, desert, false);
        Gate gateDesertUnderwater_temple = new Gate(desert, underwater_temple, false);

        Gate gateTemple_of_timeWoodlandEast = new Gate(temple_of_time, woodland, false);
        Gate gateShadow_dungeonWoodland = new Gate(shadow_dungeon, woodland, false);
        Gate gateSpirit_dungeonCave = new Gate(spirit_dungeon, cave, true);
        Gate gateDesert_dungeonDesert = new Gate(desert_dungeon, desert, true);

        // Initialisierung Enemy
        Enemy bokoblin = new Enemy("Bokoblin", 1, 1, 10, bow);
        Enemy moblin = new Enemy("moblin", 1, 1, 10, sword);
        Enemy lynel = new Enemy("lynel", 1, 1, 10, shield);

        // Festlegen von Himmelsrichtungen
        castle_ruin.setDirections(gateCastle_ruinWoodland, gateCastle_ruinCave, null, null);
        woodland.setDirections(gateShadow_dungeonWoodland, gateTemple_of_timeWoodlandEast, gateCastle_ruinWoodland, gateWoodlandCastle);
        castle.setDirections(null, gateWoodlandCastle, null, null);
        cave.setDirections(gateSpirit_dungeonCave, gateCaveDesert, null, gateCastle_ruinCave);
        desert.setDirections(null, gateDesert_dungeonDesert, gateDesertUnderwater_temple, gateCaveDesert);
        underwater_temple.setDirections(gateDesertUnderwater_temple, null, null, null);

        temple_of_time.setDirections(null, null, null, gateTemple_of_timeWoodlandEast);
        shadow_dungeon.setDirections(null, null, gateShadow_dungeonWoodland, null);
        spirit_dungeon.setDirections(null, null, gateSpirit_dungeonCave, null);
        desert_dungeon.setDirections(null, null, null, gateDesert_dungeonDesert);

        // Item auffüllen
        player.addItem(apple);
        // Startposition festlegen
        player.setCurrentLocation(castle_ruin);
    }

    // Methode für "loading screens
    public static void loading(long limit) {

        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (elapsedTime < limit) {

            elapsedTime = (new Date()).getTime() - startTime;
        }
    }
}
