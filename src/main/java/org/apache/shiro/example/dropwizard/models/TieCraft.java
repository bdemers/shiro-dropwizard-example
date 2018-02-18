package org.apache.shiro.example.dropwizard.models;

public class TieCraft {

    private String serial;

    private String craftClass;

    private String model;

    public TieCraft() {
        // empty to allow for bean access
    }

    public TieCraft(String serial, String craftClass, String model) {
        this.serial = serial;
        this.craftClass = craftClass;
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getCraftClass() {
        return craftClass;
    }

    public void setCraftClass(String craftClass) {
        this.craftClass = craftClass;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
