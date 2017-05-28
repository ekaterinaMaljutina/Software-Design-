package ru.spbau.mit.gameObject.GameObject;


public class Attributes {
    private int healf = 0;
    private int attack = 0;
    private int defense = 0;

    public Attributes(int healf, int attack, int defense) {
        this.healf = healf;
        this.attack = attack;
        this.defense = defense;
    }

    public int getHealth() {
        return healf;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    /**
     * Increase/decrease health parameter
     * Health can't be less than 0.
     *
     * @param delta number of health
     */
    public void subHealf(int delta) {
        healf = subValue(delta, healf);
    }

    /**
     * Increase/decrease attack parameter
     * Attack can't be less than 0.
     *
     * @param delta number of attack
     */
    public void subAttack(int delta) {
        attack = subValue(delta, attack);
    }

    /**
     * Increase/decrease defence parameter
     * Defence can't be less than 0.
     *
     * @param delta number of defense
     */
    public void subDefense(int delta) {
        defense = subValue(delta, defense);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Attributes) {
            Attributes attributes = (Attributes) obj;
            return healf == attributes.getHealth() && attack == attributes.getAttack();
        }
        return false;
    }

    private int subValue(int delta, int value) {
        int res = value - delta;
        if (res < 0) {
            res = 0;
        }
        return res;
    }
}

