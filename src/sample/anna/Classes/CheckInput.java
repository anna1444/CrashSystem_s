package sample.anna.Classes;


public class CheckInput {

    public static boolean checkName(String name){
        boolean bool=true;
        char[] charArr = name.toCharArray();

        for(int i=0; i<charArr.length; i++){
        char c =charArr[i];
            if(Character.isDigit(c))      {
            bool=false;
            break;
        }}
        return  bool;
    }


    public static  boolean checkPrice(int price){
        boolean bool =true;
        if(price <=0){
            bool=false;
        }
        return bool;
    }

    public static boolean checkPassword(String password){
        boolean bool = true;
        if (password.length()>5){
            AlertBox.display("Ошибка", "Слишком длинный пароль");
            bool=false;
        }return  bool;
    }

    public static boolean checkNumber(String field){
        boolean bool=true;
        char[] charArr = field.toCharArray();
        for(int i=0; i<charArr.length; i++){
            char c =charArr[i];
            if(!Character.isDigit(c) )      {
                bool=false;
                break;
            }}
        return  bool;

    }

    public static  boolean checkNumberNotZero(int number){
        boolean bool=true;
        if(number==0){
            bool = false;
        }  return  bool;
    }
    }

