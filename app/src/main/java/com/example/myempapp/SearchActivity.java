package com.example.myempapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {
    Button btnView;
    DatabaseHelper myDb;
    Button btnsearch;
   EditText input1,editTextsearch;
     Button back;
//  //  String showMessage;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        myDb = new DatabaseHelper(this);
//
        btnsearch = (Button)findViewById(R.id.button_search);
        btnView = (Button)findViewById(R.id.button_view);
        editTextsearch = (EditText)findViewById(R.id.editText_search);

        back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewAll();

        search();

    }
    public void viewAll(){
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                    showMes("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n");
                    buffer.append("Surname :"+ res.getString(2)+"\n");
                    buffer.append("Department :"+ res.getString(3)+"\n");
                    buffer.append("Email :"+ res.getString(4)+"\n\n");
                }

                //show data
                showMes("Employee Data",buffer.toString());
            }
        });
    }

    public void search(){
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = editTextsearch.getText().toString();

                Cursor res = myDb.search(search);
                if (res.getCount() ==0){
                    Object showMessage;
                    showMes("error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n");
                    buffer.append("Surname :"+ res.getString(2)+"\n");
                    buffer.append("Department :"+ res.getString(3)+"\n");
                    buffer.append("Email :"+ res.getString(4)+"\n\n");
                }

                //show data
                showMes("Employee Data",buffer.toString());
            }
        });
    }

    public void showMes(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}