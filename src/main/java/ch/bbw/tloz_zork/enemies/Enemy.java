package ch.bbw.tloz_zork.enemies;

import ch.bbw.tloz_zork.items.Item;
import ch.bbw.tloz_zork.items.WeaponItem;

/**
 * Entity for the enemies
 * @author Yao Kaiser
 */
public class Enemy {
    private String name;
    private int health; // health points
    private int ap; // attack power
    private int crit; // critical hit chance
    protected Item item;
    private boolean isDead;

    public Enemy(String name, int health, int ap, int crit, Item item) {
        this.name = name;
        this.health = health;
        this.ap = ap;
        this.crit = crit;
        this.item = item;
        this.isDead = this.isDead;
    }
    public Enemy(String name, int health, int ap, int crit, WeaponItem testWeapon, boolean b) {
        this.name = name;
        this.health = health;
        this.ap = ap;
        this.crit = crit;
        this.item = item;
        this.isDead = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public String getHeartIcons() {
        String hearts = "";
        for (int i = 0; i < this.health; i++) {
            if (i < this.health) {
                hearts += "❤️";
            } else {
                hearts += "♡";
            }
        }
        return hearts;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean getIsDead(){
        return isDead;
    }

    public void setIsDead(boolean isDead){
        this.isDead = isDead;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "enemy='" + name + '\'' +
                ", health=" + health +
                ", ap=" + ap +
                ", crit=" + crit +
                ", item=" + item +
                '}';
    }
}
