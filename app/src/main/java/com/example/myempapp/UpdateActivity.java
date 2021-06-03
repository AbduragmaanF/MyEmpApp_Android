package com.example.myempapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private EditText input1,input2,input3,input4, input5;
    Button btnAddData, back,reset,btnView;
    DatabaseHelper myDb;
    EditText editName,editSurname,editDepartment,editEmail,editId;

    Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        reset=findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input1.setText("");
                input2.setText("");
                input3.setText("");
                input4.setText("");
                input5.setText("");

            }
        });
        input1 = findViewById(R.id.editText_name);
        input2 = findViewById(R.id.editText_surname);
        input3 = findViewById(R.id.editText_department);
        input4 = findViewById(R.id.editText_email);
        input5 = findViewById(R.id.editText_id);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editDepartment = (EditText)findViewById(R.id.editText_department);
        editEmail = (EditText)findViewById(R.id.editText_email);
        editId = (EditText) findViewById(R.id.editText_id);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnView = (Button)findViewById(R.id.button_view);
        btnUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);


        viewAll();
        UpdateData();

    }


    public void UpdateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(editId.getText().toString(),editName.getText().toString(),editSurname.getText().toString(),editDepartment.getText().toString(),
                        editEmail.getText().toString());
                if(isUpdate == true)
                    Toast.makeText(UpdateActivity.this,"Data Updated successfully",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UpdateActivity.this,"Data was not updated ",Toast.LENGTH_LONG).show();

            }
        });
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
    public void showMes(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}