package by.roodxx.district.core.data.location;

/**
 * Created by roodxx on 2.8.16.
 */
public class Place {

    private final String id;

    private final String name;

    private final Location location;

    public Place(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "{\"Place\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"location\":" + location
                + "}}";
    }
}
