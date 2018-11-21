package sample.anna.Classes;

/**
 * Created by Anna on 21.11.2018.
 */
public class Repair {
    private String idWorker;
    private String idMatherials;
    private int amountMat;
    private int idRegist;
    private double price;
    private String date_begin;
    private String date_finish;

    public Repair() {}

    public Repair(String idWorker, String idMatherials, int amountMat, int idRegist, double price, String date_begin, String date_finish) {
        this.idWorker = idWorker;
        this.idMatherials = idMatherials;
        this.amountMat = amountMat;
        this.idRegist = idRegist;
        this.price = price;
        this.date_begin = date_begin;
        this.date_finish = date_finish;
    }

    public String getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(String idWorker) {
        this.idWorker = idWorker;
    }

    public String getIdMatherials() {
        return idMatherials;
    }

    public void setIdMatherials(String idMatherials) {
        this.idMatherials = idMatherials;
    }

    public int getAmountMat() {
        return amountMat;
    }

    public void setAmountMat(int amountMat) {
        this.amountMat = amountMat;
    }

    public int getIdRegist() {
        return idRegist;
    }

    public void setIdRegist(int idRegist) {
        this.idRegist = idRegist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(String date_begin) {
        this.date_begin = date_begin;
    }

    public String getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(String date_finish) {
        this.date_finish = date_finish;
    }
}
