package com.example.dbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {
    EditText Rollno,Name,Marks;
    Button Insert,Delete,Update,View,ViewAll;

    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rollno = (EditText) findViewById(R.id.Rollno);
        Name = (EditText) findViewById(R.id.Name);
        Marks = (EditText) findViewById(R.id.Marks);
        Insert = (Button) findViewById(R.id.Insert);
        Delete = (Button) findViewById(R.id.Delete);
        Update = (Button) findViewById(R.id.Update);
        View = (Button) findViewById(R.id.View);
        ViewAll = (Button) findViewById(R.id.ViewAll);

        Insert.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        View.setOnClickListener(this);
        ViewAll.setOnClickListener(this);

        //creating database table
        db = openOrCreateDatabase("Student DB", Context.MODE_PRIVATE, null);
        db.execSQL("Create Table if not Exists student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");
    }
      public void onClick(View view)
      {
          //Insert Records to a table
          if(view==Insert) {
              //checking for empty fields
              if (Rollno.getText().toString().trim().length() == 0 ||
                      Name.getText().toString().trim().length() == 0 ||
                      Marks.getText().toString().trim().length() == 0) {
                  ShowMessage("Error", "Please enter all values");
                  return;
              }

              db.execSQL("INSERT INTO student VALUES('" + Rollno.getText() + "','" + Name.getText() +
                      "','" + Marks.getText() + "');");
              showMessage("Success", "record Added");
              cleartext();
          }
          //Deleting a record from student table
          if(view==Delete) {
              if (Rollno.getText().toString().trim().length() == 0){
                  showMessage("Error", "Enter the roll number");
              return;
          }
          Cursor c=db.rawQuery("SELECT *FROM student WHERE rollno='"+Rollno.getText()+"'",null);
          if(c.moveToFirst()) {
              db.execSQL("DELETE FROM student WHERE rollno='"+Rollno.getText()+"'");
              showMessage("Success","record deleted");
          }
          else {
              showMessage("Error", "Invalid rollno");
          }
          clearText();

    }
          if(view==Update) {
              if (Rollno.getText().toString().trim().length() == 0){
                  showMessage("Error", "Enter the roll number");
                  return;
              }
              Cursor c=db.rawQuery("SELECT *FROM student WHERE rollno='"+Rollno.getText()+"'",null);
              if(c.moveToFirst()) {
                  db.execSQL("DELETE FROM student WHERE rollno='"+Rollno.getText()+"'");
                  showMessage("Success","record deleted");
              }
              else {
                  showMessage("Error", "Invalid rollno");
              }
              clearText();
              meApp();

          }



}
public void showMessage(String title,String message){
        Builder builder=new Builder(this);
    }
}

public void meApp(){showMessage("e","ia ma done");}