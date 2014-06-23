package ntu.asu.rduboveckij.model.test3;

/**
 * @author andrus.god
 * @since 6/23/2014
 */
public class Authorisation {
    private long id;
    private int priority;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
