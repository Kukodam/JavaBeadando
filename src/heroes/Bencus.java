package heroes;

import sample.Hero;

import java.util.Random;


public class Bencus extends Hero {
    /*
        Ide nagy Attack damagere és kis HPra gondoltam, és inkább abiliti alapú hero legyen.
        Alapjáraton legyen 10% crit chance ami ami azt jelenti (base damage * 1.1)
     */

    protected boolean APCooldown = false;
    protected boolean PHCooldown = false;
    protected boolean PH2Cooldown = false;


    public Bencus ()
    {
        setAttack(150);
        setHealth(200);
        setCrit(15);
        setArmor(20);
        setMagicResistance(20);
        setInitativeValue(2);
        setEvade(0);
    }

    public void AlfoldPower()
    {
        /*
            megnöveli az attack damage-et a következő támadásig 40%-al
            2 kör cooldown
         */
        setAttack(getAttack()*1.4);

        APCooldown = true;
    }

    public void Placeholder(Hero Target)
    {
        /*
            X-et sebez egy általa kiválaszott Heroba + 50% esély van arra hogy talál.
            1 kör cooldown
         */

        double abilityDamage = 20;
        Random number = new Random();

        if(number.nextBoolean())
        {
            abilityDamage = abilityDamage + (abilityDamage * (getCrit()/100));
        }
    }

    public void ROAR()
    {
        /*
            Duplázza a crit százalék értékét.
            2 kör cooldown.
         */
    }


}
