package com.nimkraft.mytodo;

import android.database.Cursor;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


  DatabaseHelper mdatabasehelper;
  ListView listView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);

    listView=(ListView)findViewById(R.id.listview);
    mdatabasehelper=new DatabaseHelper(this);

    populateList();
  }

  private void populateList() {
    Cursor data=mdatabasehelper.getData();
    ArrayList<String> listdata=new ArrayList<>();
    while (data.moveToNext()){
      listdata.add(data.getString(1));
    }
    ListAdapter listAdapter=new ArrayAdapter<>(this,R.layout.listview_item,listdata);
    listView.setAdapter(listAdapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView=(TextView) view.findViewById(R.id.item);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        //Updated Later
      }
    });
  }
}
