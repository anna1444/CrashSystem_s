package sample.anna.Classes;

public class User {
    private String username;
    private String password;
    private int idPost;

    public User(String username, String password, int idPost) {
        this.username = username;
        this.password = password;
        this.idPost = idPost;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdPost() {
        return idPost;
    }
}
