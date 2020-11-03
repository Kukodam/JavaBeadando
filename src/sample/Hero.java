package sample;


public class Hero{
    short xIndex;
    short yIndex;
    short Health;
    short Attack;
    short Movement;
    short InitativeValue;


    public short getxIndex() {
        return xIndex;
    }

    public void setxIndex(short xIndex) {
        this.xIndex = xIndex;
    }

    public short getyIndex() {
        return yIndex;
    }

    public void setyIndex(short yIndex) {
        this.yIndex = yIndex;
    }

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

    public short getMovement() {
        return Movement;
    }

    public void setMovement(short movement) {
        Movement = movement;
    }

    public short getInitativeValue() {
        return InitativeValue;
    }

    public void setInitativeValue(short initativeValue) {
        InitativeValue = initativeValue;
    }

}

