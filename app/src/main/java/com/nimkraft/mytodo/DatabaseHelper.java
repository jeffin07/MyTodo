package com.nimkraft.mytodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jeffin on 14-03-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

  public static final String TABLE_NAME="todolist";
  public static  final String COL0="ID";
  public static final String COL1="ITEM";
  public DatabaseHelper(Context context) {
    super(context, TABLE_NAME, null,1);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String createTable="CREATE TABLE "+TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL1+" TEXT )";
    sqLiteDatabase.execSQL(createTable);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }
  public boolean addData(String item){
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues contentValues=new ContentValues();
    contentValues.put(COL1,item);

    long result=db.insert(TABLE_NAME,null,contentValues);
    if(result==-1){
      return false;}
      else{
      return true;
    }
  }
  public Cursor getData(){
    SQLiteDatabase db=this.getWritableDatabase();
    String query="SELECT * FROM "+TABLE_NAME;
    Cursor c=db.rawQuery(query,null);
    return  c;
  }
  /*
  public Cursor getItemId(String name){
    SQLiteDatabase db=this.getWritableDatabase();
    String query="";
  }*/
}
