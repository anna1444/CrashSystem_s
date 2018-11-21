package sample.anna.Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Material;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBHandler extends  ConfigsDB {
    Connection dbConnection;
    static DBHandler dbHandler = new DBHandler();

    public static DBHandler getDbHandler() {
        return dbHandler;
    }

    public DBHandler() {
        try {
            getDbConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void getDbConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://localhost:3306/crash_db?useSSL=false";
        // String connectionString = "jdbc:mysql://" + DBHost + ":" + DBPort + "/" + DBName;

        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, DBUser, DBPass);
    }

    public void signUpUser(User user) {
        String insert = "INSERT " + "INTO " + Const.ROLE_TABLE + "(" + Const.ROLE_USERNAME + "," + Const.ROLE_PASSWORD + "," + Const.ROLE_IDPOST + ")"
                + "VALUES(?,?,?)";
        // PreparedStatement prSt = null;
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());
            prSt.setInt(3, user.getIdPost());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.ROLE_TABLE + " WHERE " + Const.ROLE_USERNAME + "=? AND " + Const.ROLE_PASSWORD + "=?";

        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());

            resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                User userbuf = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                return userbuf;
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addMatherial(Matherials matherials) {
        String insert = "INSERT " + "INTO " + Const.MATHERIALS_TABLE + "(" + Const.MATHERIALS_NAME + "," + Const.MATHERIALS_PRICE + "," + Const.MATHERIALS_DERIVER + ")"
                + "VALUES(?,?,?)";
        // PreparedStatement prSt = null;
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            prSt.setString(1, matherials.getName());
            prSt.setInt(2, matherials.getPrice());
            prSt.setString(3, matherials.getDeriver());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getSetMatherials() {
        ResultSet setMat = null;
        String select = "SELECT " + "(" + Const.MATHERIALS_NAME + ") " + "FROM " + Const.MATHERIALS_TABLE;

        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            setMat = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setMat;
    }

    public ResultSet getAllSetMatherials() {
        ResultSet setMat = null;
        String select = "SELECT " + "* " + "FROM " + Const.MATHERIALS_TABLE;
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            setMat = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setMat;

    }

    public void updateMatherial(Matherials matherials) {
        String update = "UPDATE " + Const.MATHERIALS_TABLE + " SET " + Const.MATHERIALS_PRICE + "=?" + " WHERE " + Const.MATHERIALS_NAME + "=?";
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(update);
            prSt.setInt(1, matherials.getPrice());
            prSt.setString(2, matherials.getName());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteMatherial(Matherials matherials) {
        String delete = "DELETE " + "FROM " + Const.MATHERIALS_TABLE + " WHERE " + Const.MATHERIALS_NAME + "=?";
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(delete);
            prSt.setString(1, matherials.getName());
            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBrigade(Brigade brigade) {
        String insert = "INSERT " + "INTO " + Const.BRIGADE_TABLE + "(" + Const.BRIGADE_ID + "," + Const.BRIGADE_IDCHANGE + ")"
                + "VALUES(?,?)";
        // PreparedStatement prSt = null;
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            prSt.setInt(1, brigade.getNumber());
            prSt.setInt(2, brigade.getNumberChange());
            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getSetBrigades() {
        ResultSet setBr = null;
        String select = "SELECT " + "(" + Const.BRIGADE_ID + ") " + "FROM " + Const.BRIGADE_TABLE;
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            setBr = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setBr;

    }   // Получения айдишников

    public ResultSet getAllSetBrigades() {
        ResultSet setBr = null;
        String select = "SELECT " + "* " + "FROM " + Const.BRIGADE_TABLE;
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            setBr = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setBr;

    }    // получение всей таблицы бригад

    public void updateBrigade(Brigade brigade) {
        String update = "UPDATE " + Const.BRIGADE_TABLE + " SET " + Const.BRIGADE_IDCHANGE + "=?" + " WHERE " + Const.BRIGADE_ID + "=?";
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(update);
            prSt.setInt(1, brigade.getNumberChange());
            prSt.setInt(2, brigade.getNumber());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteBrigade(Brigade brigade) {
        String delete = "DELETE " + "FROM " + Const.BRIGADE_TABLE + " WHERE " + Const.BRIGADE_ID + "=?";
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(delete);
            prSt.setInt(1, brigade.getNumber());
            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getSetWorkers() {
        ResultSet setWorkers = null;
        String select = "SELECT " + "(" + Const.WORKER_SURNAME + ") " + "FROM " + Const.WORKER_TABLE;
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            setWorkers = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setWorkers;
    }

    public void addWorker(Worker worker) {
        String insert = "INSERT " + "INTO " + Const.WORKER_TABLE + "(" + Const.WORKER_NAME + "," + Const.WORKER_SURNAME + "," + Const.WORKER_LASTNAME + "," +
                Const.WORKER_TELEPHONE + "," + Const.WORKER_BIRTHDAY + "," + Const.WORKER_IDPOST + "," +
                Const.WORKER_IDBRIGADE + ")" + "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            prSt.setString(1, worker.getName());
            prSt.setString(2, worker.getSurname());
            prSt.setString(3, worker.getLastname());
            prSt.setString(4, worker.getTelephone());
            prSt.setString(5, worker.getBirthday());
            prSt.setInt(6, worker.getIdPost());
            prSt.setInt(7, worker.getIdBrigade());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getAllSetWorkers() {
        ResultSet setWork = null;
        String select = "SELECT " + "* " + "FROM " + Const.WORKER_TABLE;
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            setWork = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setWork;

    }

    public void updateWorker(Worker worker) {
        String update = "UPDATE " + Const.WORKER_TABLE + " SET " + Const.WORKER_TELEPHONE + "=?" + "," + Const.WORKER_IDBRIGADE + "=?" +
                " WHERE " + Const.WORKER_SURNAME + "=?";
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(update);
            prSt.setString(1, worker.getTelephone());
            prSt.setInt(2, worker.getIdBrigade());
            prSt.setString(3, worker.getSurname());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteWorker(Worker worker) {
        String delete = "DELETE " + "FROM " + Const.WORKER_TABLE + " WHERE " + Const.WORKER_SURNAME + "=?";
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(delete);
            prSt.setString(1, worker.getSurname());
            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addRegistrationCrash(RegistrationCrash crash) {
        String insert = "INSERT " + "INTO " + Const.REGIST_TABLE + "(" + Const.REGIST_THEME + "," + Const.REGIST_REGION + "," + Const.REGIST_ADDRESS + "," + Const.REGIST_DATE + ")"
                + "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            prSt.setString(1, crash.getDescription());
            prSt.setString(2, crash.getRegion());
            prSt.setString(3, crash.getAddress());
            prSt.setString(4, crash.getDate());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getSetRegisr(){
        ResultSet setRegist = null;
        String select = "SELECT " + "(" + Const.REGIST_ID + ") " + "FROM " + Const.REGIST_TABLE;
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            setRegist = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setRegist;
    }

    public ResultSet getAllSetRegist(){
        ResultSet setRegist = null;
        String select = "SELECT " + "* " + "FROM " + Const.REGIST_TABLE;
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            setRegist = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setRegist;

    }

    public void addRepair(Repair repair){
        String insert = "INSERT " + "INTO " + Const.REPAIR_TABLE + "(" + Const.REPAIR_IDWORKER + "," + Const.REPAIR_IDMATHERIALS + "," + Const.REPAIR_AMOUNT + "," +
                Const.REPAIR_IDREGIST + "," + Const.REPAIR_PRICE + "," + Const.REPAIR_DATEBEGIN + "," +
                Const.REPAIR_DATEFINISH + ")" + "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            prSt.setString(1, repair.getIdWorker());
            prSt.setString(2, repair.getIdMatherials());
            prSt.setInt(3, repair.getAmountMat());
            prSt.setInt(4, repair.getIdRegist());
            prSt.setDouble(5, repair.getPrice());
            prSt.setString(6, repair.getDate_begin());
            prSt.setString(7, repair.getDate_finish());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}





