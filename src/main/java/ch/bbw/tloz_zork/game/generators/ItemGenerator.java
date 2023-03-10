package ch.bbw.tloz_zork.game.generators;

import ch.bbw.tloz_zork.items.HealingItem;
import ch.bbw.tloz_zork.items.Item;
import ch.bbw.tloz_zork.items.WeaponItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Generates a random item from a list of items
 * @author Yao Kaiser
 */
public class ItemGenerator {
    private ArrayList<WeaponItem> weapons;
    private ArrayList<HealingItem> foods;
    private Random random;
    private final String WEAPONS_FILE = "src/main/resources/data/weapons.json";
    private final String FOOD_FILE = "src/main/resources/data/food.json";


    /**
     * @throws FileNotFoundException
     */
    public ItemGenerator() throws FileNotFoundException {
        Gson gson = new Gson();
        this.weapons = gson.fromJson(new FileReader(WEAPONS_FILE), new TypeToken<ArrayList<WeaponItem>>(){}.getType());
        this.foods = gson.fromJson(new FileReader(FOOD_FILE), new TypeToken<ArrayList<HealingItem>>(){}.getType());
        random = new Random();
    }
    /**
     * @return a random item from the list of items
     */
    public ArrayList<Item> getRandomItems() {
        ArrayList<Item> items = new ArrayList<>();
        int numberOfItems = random.nextInt(4);
        for (int i = 0; i < numberOfItems; i++) {
            if (random.nextBoolean()) {
                items.add(getRandomWeapon());
            } else {
                items.add(getRandomFood());
            }
        }
        return items;
    }
    /**
     * @return a random weapon from the list of weapons
     */
    public WeaponItem getRandomWeapon() {
        return weapons.get(random.nextInt(weapons.size()));
    }
    /**
     * @return a random food from the list of foods
     */
    public HealingItem getRandomFood() {
        return foods.get(random.nextInt(foods.size()));
    }
    /**
     * @param numberOfWeapons
     * @return a list of random weapons
     */
    public ArrayList<WeaponItem> getRandomWeapons(int numberOfWeapons) {
        ArrayList<WeaponItem> randomWeapons = new ArrayList<>();
        for (int i = 0; i < numberOfWeapons; i++) {
            randomWeapons.add(getRandomWeapon());
        }
        return randomWeapons;
    }
    /**
     * @param numberOfFoods
     * @return a list of random foods
     */
    public ArrayList<HealingItem> getRandomFoods(int numberOfFoods) {
        ArrayList<HealingItem> randomFoods = new ArrayList<>();
        for (int i = 0; i < numberOfFoods; i++) {
            randomFoods.add(getRandomFood());
        }
        return randomFoods;
    }
}
