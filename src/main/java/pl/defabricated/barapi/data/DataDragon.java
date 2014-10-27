package pl.defabricated.barapi.data;

public class DataDragon {

    private boolean cMessage, cHealth, cTime;

    private String message;
    private float health;
    private long time;

    public void setMessage(String message) { this.message = message; cMessage = true; }
    public void setHealth(float health) { this.health = health; cHealth = true; }
    public void setTime(long time) { this.time = time; cTime = true; }

    public String getMessage() { return message; }
    public float getHealth() { return health; }
    public long getTime() { return time; }

}
