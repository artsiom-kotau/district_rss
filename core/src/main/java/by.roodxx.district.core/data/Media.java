package by.roodxx.district.core.data;

import by.roodxx.district.core.data.location.Location;

public class Media {

    private String id;

    private final String mediaSource;

    private String mediaUrl;

    private final Location location;

    private String description;

    private Owner owner;

    private MediaType mediaType;

    private long timestamp;

    public Media(String mediaSource, Location location) {
        this.mediaSource = mediaSource;
        this.location = location;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaSource() {
        return mediaSource;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }



    @Override
    public String toString() {
        return "{\"Media\":{"
                + "\"id\":\"" + id + "\""
                + ", \"mediaSource\":\"" + mediaSource + "\""
                + ", \"mediaUrl\":\"" + mediaUrl + "\""
                + ", \"location\":" + location
                + ", \"description\":\"" + description + "\""
                + ", \"owner\":" + owner
                + ", \"mediaType\":\"" + mediaType + "\""
                + ", \"timestamp\":\"" + timestamp + "\""
                + "}}";
    }
}
