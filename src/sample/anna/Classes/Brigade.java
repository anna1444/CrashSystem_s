package sample.anna.Classes;


public class Brigade {
    private int number;
    private int numberChange;


    public Brigade(int number, int numberChange) {
        this.number = number;
        this.numberChange = numberChange;
    }
    public Brigade(){
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberChange() {
        return numberChange;
    }

    public void setNumberChange(int numberChange) {
        this.numberChange = numberChange;
    }
}
