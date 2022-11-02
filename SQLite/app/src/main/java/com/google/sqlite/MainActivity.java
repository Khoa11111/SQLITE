package com.google.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Database ComputerManagerment
        database = new Database(this, "ComputerManagerment",null,1);

        // Create table Category
        database.QueryData("CREATE TABLE IF NOT EXISTS Category(" +
                                "idCagetory char(10) not null PRIMARY KEY," +
                                "CategoryName nvarchar(50) not null)");

        // Create table Computer
        database.QueryData("CREATE TABLE IF NOT EXISTS Computer(" +
                                "idComputer char(10) not null PRIMARY KEY," +
                                "nameComputer varchar(50) not null," +
                                "idCagetory char(10) not null," +
                                "CONSTRAINT fk_idCategery FOREIGN KEY(idCagetory) REFERENCES Category(idCagetory))");

        // Insert data into Category
        /*database.QueryData("INSERT INTO Category" +
                                " VALUES('Ctg01','Dell')," +
                                        "('Ctg02','Asur')," +
                                        "('Ctg03','MSI')");*/

        // select from Category
        /*Cursor data = database.GetData("SELECT * FROM Category");
        while(data.moveToNext()){
            String name = data.getString(1);
            Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        }*/

        // Insert into Computer
        /*database.QueryData("INSERT INTO Computer" +
                                " VALUES('D01','Dell Latitude 3490','Ctg01')," +
                                        "('D02','Dell Latitude E7470','Ctg01')," +
                                        "('D03','Dell Precision M5510','Ctg01')," +
                                        "('A01','Asus X515','Ctg02')," +
                                        "('A02','Asus Zenbook 14','Ctg02')," +
                                        "('A03','Asus Experbook B3402','Asur')," +
                                        "('M01','MSI Katana GF66','Ctg03')," +
                                        "('M02','MSI GF63','Ctg03')," +
                                        "('M03','MSI Mordern 14','Ctg03')");*/

    }
}