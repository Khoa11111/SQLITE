package com.google.sqlite;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ComputerList extends AppCompatActivity {

    Database database;
    ListView lvComputer;
    ImageView imgDelete, imgEdit;
    ArrayList<Computer> computerArrayList;
    ComputerAdapter computerAdapter;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.computer_list);

        imgDelete = (ImageView) findViewById(R.id.imgDelete);
        imgEdit = (ImageView) findViewById(R.id.imgEdit);

        lvComputer = (ListView) findViewById(R.id.ComputerListView);
        computerArrayList = new ArrayList<>();

        computerAdapter = new ComputerAdapter(this, R.layout.computer_list_detail, computerArrayList);
        lvComputer.setAdapter(computerAdapter);

        //use Database ComputerManagerment
        database = new Database(this, "ComputerManagerment",null,1);

        GetDataComputer();
    }

    public void DialogSua(String idComputer,String name){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);

        final EditText edtComputerName = (EditText) dialog.findViewById(R.id.txtName_update);
        Button btnXacnhan = (Button) dialog.findViewById(R.id.btn_XacNhan_update);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_Huy_update);

        edtComputerName.setText(name);

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tenmoi = edtComputerName.getText().toString().trim();
                database.QueryData("UPDATE Computer SET nameComputer = '"+ Tenmoi +"' WHERE idComputer = '"+ idComputer +"'");
                Toast.makeText(ComputerList.this, "Đã Sửa", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataComputer();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogXoa(String idComputer){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa laptop này hay không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.QueryData("DELETE FROM Computer WHERE idComputer = '"+idComputer+"'");
                Toast.makeText(ComputerList.this, "Đã Xóa", Toast.LENGTH_SHORT).show();
                GetDataComputer();
            }
        });

        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogXoa.show();
    }

    // Select from Computer
    private void GetDataComputer(){
        Cursor dataComputer = database.GetData("select * from Computer");
        computerArrayList.clear();
        while(dataComputer.moveToNext()){
            String idComputer = dataComputer.getString(0);
            String name = dataComputer.getString(1);
            String idCategory = dataComputer.getString(2);
            computerArrayList.add(new Computer(idComputer,name,idCategory));
        }
        computerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_computer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menuAdd){
            DialogThem();
        }

        return super.onOptionsItemSelected(item);
    }

    private void DialogThem(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_computer);

        final EditText edtIdComputer = (EditText) dialog.findViewById(R.id.txtId);
        final EditText edtComputerName = (EditText) dialog.findViewById(R.id.txtName);
        final EditText edtIdCategory = (EditText) dialog.findViewById(R.id.txtCategory);
        Button btnThem = (Button) dialog.findViewById(R.id.btn_Them);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_Huy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idComputer = edtIdComputer.getText().toString();
                String ComputerName = edtComputerName.getText().toString();
                String idCategory = edtIdCategory.getText().toString();
                database.QueryData("INSERT INTO Computer" +
                        " VALUES('"+ idComputer +"','"+ ComputerName +"','"+ idCategory +"')");
                Toast.makeText(ComputerList.this, "Đã Thêm", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataComputer();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
