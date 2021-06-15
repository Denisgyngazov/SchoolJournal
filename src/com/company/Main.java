package com.company;

import Services.DataBase;
import Services.TableObject;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            DataBase dataBase = new DataBase();
            TableObject tableObject = new TableObject(dataBase);
            tableObject.run();
        } catch (SQLException e) {
            System.out.println("Ошибка SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не найден");
        }
    }
}
