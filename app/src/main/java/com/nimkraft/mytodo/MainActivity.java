package com.nimkraft.mytodo;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
  Dialog mydialog;
  DatabaseHelper mdatabase;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mydialog=new Dialog(this);
   mdatabase=new DatabaseHelper(this);
    android.support.v7.widget.Toolbar toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle("MyTodo");
   // toolbar.inflateMenu(R.menu.top_menu);
    setSupportActionBar(toolbar);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.top_menu,menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id=item.getItemId();

    switch (id){
      case R.id.addbutton:
        Toast.makeText(getApplicationContext(),"id:"+id,Toast.LENGTH_SHORT).show();
        View v1 = getWindow().getDecorView().getRootView();
        showpopup(v1);
        //

        //
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  /*
      @Override
      public boolean onOptionsItemSelected(MenuItem item) {

        Toast.makeText(getApplicationContext(),item.getItemId(),Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){
          case R.id.addbutton:
            Toast.makeText(getApplicationContext(),"Here",Toast.LENGTH_SHORT).show();
           showpopup();
           return true;
           //break;
        }
        return super.onOptionsItemSelected(item);
      }*/
  public void showpopup(View v){
    TextView tclose;
    Button addbutton;
    final EditText title;
    //mydialog.setContentView(R.layout.add_item);
    mydialog.setContentView(R.layout.add_item);

    //mydialog.setContentView(R.layout.add_item);
    title=(EditText)mydialog.findViewById(R.id.title) ;
    addbutton=(Button)mydialog.findViewById(R.id.add);
    addbutton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String t=title.getText().toString();
        if(t.equals("")){
          Toast.makeText(getApplicationContext(),"Enter some values",Toast.LENGTH_SHORT).show();
        }else{
          boolean insert=mdatabase.addData(t);
          if(insert){
            Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainActivity.this,ListActivity.class);
            startActivity(i);
          }else{
            Toast.makeText(getApplicationContext(),"Not Inserted",Toast.LENGTH_SHORT).show();
          }
        }
      }
    });
    tclose=(TextView)mydialog.findViewById(R.id.close);
    tclose.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        mydialog.dismiss();
      }
    });
    mydialog.show();
  }




}
