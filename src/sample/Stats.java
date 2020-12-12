package sample;

public class Stats {
    private double atk;
    private double def;
    private double hp;
    private double maxHp;

    Stats(int level)
    {
        atk = 14*(level + 4);
        def = 10 * (level+2);
        hp = 10* (level + 100);
        maxHp = hp;
    }

    public void setDamage(double factor) {
        this.hp -= factor;
    }

    public double getAtk() {
        return atk;
    }

    public double getDef() {
        return def;
    }


    public double getHp() {
        return hp;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public void setAtk(double atk) { this.atk = atk; }

    public void setDef(double def) { this.atk = def; }

    public void setHp(double hp) { this.atk = hp; }

    public void setMaxHp(double mhp) { this.maxHp = mhp; }

}
