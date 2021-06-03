  package com.example.myempapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

  public class DeleteActivity extends AppCompatActivity {
      private EditText input1;
      Button back,reset,btnView;
      DatabaseHelper myDb;
      EditText editId;

      Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
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


            }
        });
        input1 = findViewById(R.id.editText_id);
        myDb = new DatabaseHelper(this);
        editId = (EditText) findViewById(R.id.editText_id);
        btnView = (Button)findViewById(R.id.button_view);
        btnDelete = (Button)findViewById(R.id.button_delete);
        viewAll();
        DeleteData();
    }
      public void DeleteData(){
          btnDelete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Integer deletedRows = myDb.deleteData(editId.getText().toString());
                  if(deletedRows > 0)
                      Toast.makeText(DeleteActivity.this,"Data Deleted successfully",Toast.LENGTH_LONG).show();
                  else
                      Toast.makeText(DeleteActivity.this,"Data was not deleted ",Toast.LENGTH_LONG).show();
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