package Models;

import java.util.List;
import java.util.UUID;

public class Board {

    UUID id;

    String name;

    String privacy;

    String url;

    List<User> members;

    List<CustomList> customLists;

    public Board() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<CustomList> getCustomLists() {
        return customLists;
    }

    public void setCustomLists(List<CustomList> customLists) {
        this.customLists = customLists;
    }
}
