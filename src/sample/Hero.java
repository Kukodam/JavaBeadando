package sample;


import javafx.scene.image.Image;

public class Hero{

    private int id;
    private String name;
    private int level;
    private int exp;
    private String hero_class;
    private Stats stats;
    private Image image;
    private String[] skill = new String[2];

    public Hero(String name, String hero_class) {
        this(name, 0, hero_class);
    }

    public Hero(String name, int exp, String hero_class)
    {
        this.id = hashCode();
        this.name = name;
        this.level = 1;
        this.hero_class = hero_class;
        this.exp = exp;
        setLevel();
        setStats();
        setImage();
    }

    public Hero(int id, String name, int exp, String hero_class) {
        this(name, exp, hero_class);
        this.id = id;
    }

    //GETTER
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public String getHero_class(){ return hero_class; }

    public Stats getStats() {
        return stats;
    }

    public Image getImage(boolean isFlip) {
        return image;
    }

    public String[] getSkill() {
        return skill;
    }

    public int getCriteria() {
        return (int)Math.round(Math.pow(4 * level + 50, 2) / 10);
    }

    public int getId() {
        return id;
    }

    public boolean plusExp(int exp) {
        this.exp += exp;
        return setLevel();
    }

    //SETTER
    public void setName(String name) {
        this.name = name;
    }

    public void setHero_class(String hero_class) {
        this.hero_class = hero_class;
        setImage();
    }

    private boolean setLevel() {
        setSkills();
        if (exp < getCriteria() || this.level >= 10) return false;
        while (exp >= getCriteria()) {
            exp -= getCriteria();
            level++;
            setPlusStats(this.stats.getAtk()+ 2,
                    this.stats.getDef()+2,
                    this.stats.getHp()+10,
                    this.stats.getMaxHp()+10);
        }
        return true;
    }

    private void setSkills() {
        switch (hero_class) {
            case "JAVA":
                skill[0] = "Slaving";
                skill[1] = "BRB(4 hours ago)";
                break;
            case "C++":
                skill[0] = "Waterfall";
                skill[1] = "Waterstorm";
                break;
            case "C#":
                skill[0] = "Tornado";
                skill[1] = "Mega Tonado";
                break;
        }
    }

    private void setStats() {
        this.stats = new Stats(this.level);
    }

    private void setPlusStats(double atk, double def, double hp, double maxhp)
    {
        if(atk != 0)
        {
            this.stats.setAtk(atk);
        }
        if(def != 0)
        {
            this.stats.setDef(def);
        }
        if(hp != 0)
        {
            this.stats.setHp(hp);
        }
        if (maxhp != 0)
        {
            this.stats.setMaxHp(maxhp);
        }

    }

    private void setImage() {
        String path = "";
        switch (hero_class) {
            case "JAVA":
                path = "assets/Java.png";
                break;
            case "C++":
                path = "assets/c++.png";
                break;
            case "C#":
                path = "assets/c#.png";
                break;
        }
        this.image = new Image(path);
    }

    void setLevel(int level) {
        this.level = level;
        setStats();
    }

    public void reset() {
        this.stats = new Stats(this.level);
    }


}

