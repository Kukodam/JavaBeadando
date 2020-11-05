package sample;


public class Hero{

    protected double Health;
    protected double Attack;
    protected double InitativeValue;

    protected double magicResistance;
    protected double armor;
    protected double evade;
    protected double crit;

    public double getHealth() {
        return Health;
    }

    public void setHealth(double health) {
        Health = health;
    }

    public double getAttack() {
        return Attack;
    }

    public void setAttack(double attack) {
        Attack = attack;
    }

    public double getInitativeValue() {
        return InitativeValue;
    }

    public void setInitativeValue(double initativeValue) {
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

