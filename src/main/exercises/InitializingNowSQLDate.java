package main.exercises;

public class InitializingNowSQLDate {

    public static void main(String[] args) {
        java.sql.Date CE_SLP_DT = new java.sql.Date(System.currentTimeMillis());
        System.out.println(CE_SLP_DT);
    }

}
