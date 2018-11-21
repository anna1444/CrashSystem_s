package sample.anna.Classes;

public class Matherials {

    private String name;
    private int price;
    private String deriver;


    public Matherials() {}
    public Matherials(String name, int price, String deriver) {
        this.name = name;
        this.price = price;
        this.deriver = deriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDeriver() {
        return deriver;
    }

    public void setDeriver(String deriver) {
        this.deriver = deriver;
    }
}

