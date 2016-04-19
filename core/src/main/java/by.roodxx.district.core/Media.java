package by.roodxx.district.core;

import java.util.Collection;

public class Media {
    private final String mediaUrl;

    private final Location location;

    private String description;

    private Collection<String> tags;

    private Owner owner;

    public Media(String mediaUrl, Location location) {
        this.mediaUrl = mediaUrl;
        this.location = location;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public Location getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
