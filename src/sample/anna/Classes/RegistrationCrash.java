package sample.anna.Classes;

public class RegistrationCrash {

    private String description;
    private String region;
    private String address;
    private String date;


    public RegistrationCrash(){}


    public RegistrationCrash(String description, String region, String address, String date) {
        this.description = description;
        this.region = region;
        this.address = address;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
