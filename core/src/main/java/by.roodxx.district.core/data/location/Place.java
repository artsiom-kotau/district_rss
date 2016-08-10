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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;

        Place place = (Place) o;

        if (id != null ? !id.equals(place.id) : place.id != null) return false;
        if (name != null ? !name.equals(place.name) : place.name != null) return false;
        return location != null ? location.equals(place.location) : place.location == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
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
