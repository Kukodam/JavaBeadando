package sample;


public class Hero{

    short Health;
    short Attack;
    short InitativeValue;

    double magicResistance;
    double armor;
    double evade;
    double crit;

    public short getHealth() {
        return Health;
    }

    public void setHealth(short health) {
        Health = health;
    }

    public short getAttack() {
        return Attack;
    }

    public void setAttack(short attack) {
        Attack = attack;
    }

    public short getInitativeValue() {
        return InitativeValue;
    }

    public void setInitativeValue(short initativeValue) {
        InitativeValue = initativeValue;
    }

    public double getMagicResistance() {
        return magicResistance;
    }

    public void setMagicResistance(double magicResistance) {
        this.magicResistance = magicResistance;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getEvade() {
        return evade;
    }

    public void setEvade(double evade) {
        this.evade = evade;
    }

    public double getCrit() {
        return crit;
    }

    public void setCrit(double crit) {
        this.crit = crit;
    }
}

