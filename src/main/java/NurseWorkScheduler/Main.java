package org.example;

public class Main {
    public static void main(String[] args) {
        DataBaseClient dataBaseClient = new DataBaseClient();
        dataBaseClient.connect();
        dataBaseClient.readRecords(1);
    }
}