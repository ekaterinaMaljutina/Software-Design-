package ru.spbau.mit.gameObject.GameObject;


public class Attributes {
    private int healf;
    private int attack;

    public Attributes(int healf, int attack) {
        this.healf = healf;
        this.attack = attack;
    }

    public int getHealf() {
        return healf;
    }

    public int getAttack() {
        return attack;
    }

    public void subHealf(int delta) {
        healf -= delta;
        if (healf < 0) {
            healf = 0;
        }
    }

    public void sumHealf(int delta) {
        healf += delta;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Attributes) {
            Attributes attributes = (Attributes) obj;
            return healf == attributes.getHealf() && attack == attributes.getAttack();
        }
        return false;
    }
}

