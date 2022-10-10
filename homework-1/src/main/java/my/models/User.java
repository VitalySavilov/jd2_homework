package my.models;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String telNumber;
    private String emale;

    public String getName() {
        return name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getEmale() {
        return emale;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setEmale(String emale) {
        this.emale = emale;
    }
}
