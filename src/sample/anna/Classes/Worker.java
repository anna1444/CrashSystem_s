package sample.anna.Classes;

public class Worker {
    private String name;
    private String surname;
    private String lastname;
    private String telephone;
    private String birthday;
    private int idPost;
    private int idBrigade;

    public Worker() {}

    public Worker(String name, String surname, String lastname, String telephone, String birthday, int idPost, int idBrigade) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.telephone = telephone;
        this.birthday = birthday;
        this.idPost = idPost;
        this.idBrigade = idBrigade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdBrigade() {
        return idBrigade;
    }

    public void setIdBrigade(int idBrigade) {
        this.idBrigade = idBrigade;
    }
}
