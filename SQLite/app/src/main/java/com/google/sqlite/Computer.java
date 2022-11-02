package com.google.sqlite;

public class Computer {
    private String idComputer;
    private String ComputerName;
    private String idCategory;

    public Computer(String idComputer, String computerName, String idCategory) {
        this.idComputer = idComputer;
        ComputerName = computerName;
        this.idCategory = idCategory;
    }

    public String getIdComputer() {
        return idComputer;
    }

    public void setIdComputer(String idComputer) {
        this.idComputer = idComputer;
    }

    public String getComputerName() {
        return ComputerName;
    }

    public void setComputerName(String computerName) {
        ComputerName = computerName;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }
}
