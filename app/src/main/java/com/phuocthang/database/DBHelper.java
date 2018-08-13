package com.phuocthang.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.phuocthang.model.Category;
import com.phuocthang.model.Place;
import com.phuocthang.model.Rating;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by THANH_UIT on 4/23/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Mydatabase.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table "+ CategoryTable.NAME+" " +
                        "("+CategoryTable.ID+" long primary key, "
                        +CategoryTable.CATEGORY_NAME+" text)"
        );

        db.execSQL(
                "create table "+PlaceTable.NAME+" " +
                        "("+PlaceTable.ID+" long primary key, "
                        +PlaceTable.CATEGORY_ID+" integer,"
                        +PlaceTable.PLACE_NAME+" text,"
                        +PlaceTable.LATITUDE+" double, "
                        +PlaceTable.LONGITUDE+ " double,"
                        +PlaceTable.ADDRESS+" text,"
                        +PlaceTable.IMAGE_URL+" text)"
        );

        db.execSQL(
                "create table "+RatingTable.NAME+" " +
                        "("+RatingTable.ID+" long primary key, "
                        +RatingTable.PLACE_ID+" long,"
                        +RatingTable.DELICIOUS+" integer,"
                        +RatingTable.CHEAP+ " integer)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+RatingTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS "+CategoryTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PlaceTable.NAME);
        onCreate(db);
    }

    // Place table
    public boolean insertPlace (Place place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long id=new Date().getTime();
        contentValues.put(PlaceTable.ID, id);
        contentValues.put(PlaceTable.CATEGORY_ID,place.getCategory_id());
        contentValues.put(PlaceTable.PLACE_NAME,place.getPlaceName());
        contentValues.put(PlaceTable.LATITUDE,place.getLatitude());
        contentValues.put(PlaceTable.LONGITUDE,place.getLongitude());
        contentValues.put(PlaceTable.ADDRESS,place.getAddress());
        contentValues.put(PlaceTable.IMAGE_URL,place.getImage_Url());
        db.insert(PlaceTable.NAME, null, contentValues);
        return true;
    }

    public Place getDataPlace(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query="select * from "+PlaceTable.NAME+" where "+PlaceTable.ID+"=?";
        Log.d("DBHelper",query);
        Cursor res =  db.rawQuery( query, new String[]{id.toString()} );
        res.moveToFirst();
        return new Place(res.getLong(res.getColumnIndex(PlaceTable.ID)),
                res.getInt(res.getColumnIndex(PlaceTable.CATEGORY_ID)),
                res.getString(res.getColumnIndex(PlaceTable.PLACE_NAME)),
                res.getDouble(res.getColumnIndex(PlaceTable.LATITUDE)),
                res.getDouble(res.getColumnIndex(PlaceTable.LONGITUDE)),
                res.getString(res.getColumnIndex(PlaceTable.ADDRESS)),
                res.getString(res.getColumnIndex(PlaceTable.IMAGE_URL)));
    }


    public int numberOfRowsPlace(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PlaceTable.NAME);
        return numRows;
    }

    public boolean updatePlace (Place place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlaceTable.CATEGORY_ID,place.getCategory_id());
        contentValues.put(PlaceTable.PLACE_NAME,place.getPlaceName());
        contentValues.put(PlaceTable.LATITUDE,place.getLatitude());
        contentValues.put(PlaceTable.LONGITUDE,place.getLongitude());
        contentValues.put(PlaceTable.ADDRESS,place.getAddress());
        contentValues.put(PlaceTable.IMAGE_URL,place.getImage_Url());
        db.update(PlaceTable.NAME, contentValues, PlaceTable.ID+" = ? ", new String[] { Long.toString(place.getId()) } );
        return true;
    }

    public Integer deletePlace (Long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PlaceTable.NAME,
                PlaceTable.ID+" = ? ",
                new String[] { Long.toString(id) });
    }

    public ArrayList<Place> getAllPlaces() {
        ArrayList<Place> array_list = new ArrayList<Place>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PlaceTable.NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Place(res.getLong(res.getColumnIndex(PlaceTable.ID)),res.getInt(res.getColumnIndex(PlaceTable.CATEGORY_ID)),res.getString(res.getColumnIndex(PlaceTable.PLACE_NAME)),res.getDouble(res.getColumnIndex(PlaceTable.LATITUDE)),res.getDouble(res.getColumnIndex(PlaceTable.LONGITUDE)),res.getString(res.getColumnIndex(PlaceTable.ADDRESS)),res.getString(res.getColumnIndex(PlaceTable.IMAGE_URL))));
            res.moveToNext();
        }

        return array_list;
    }

    // Rating table
    public boolean insertRating (Rating rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long id=new Date().getTime();
        contentValues.put(RatingTable.ID, id);
        contentValues.put(RatingTable.PLACE_ID,rating.getPlace_id());
        contentValues.put(RatingTable.DELICIOUS,rating.getRating_delicious());
        contentValues.put(RatingTable.CHEAP,rating.getRating_cheap());
        db.insert(RatingTable.NAME, null, contentValues);
        return true;
    }

    public Rating getDataRating(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query="select * from "+RatingTable.NAME+" where "+RatingTable.ID+"=?";
        Cursor res =  db.rawQuery( query, new String[]{id.toString()} );
        res.moveToFirst();
        return new Rating(res.getLong(res.getColumnIndex(RatingTable.ID)),
                res.getLong(res.getColumnIndex(RatingTable.PLACE_ID)),
                res.getInt(res.getColumnIndex(RatingTable.DELICIOUS)),
                res.getInt(res.getColumnIndex(RatingTable.CHEAP)));
    }


    public int numberOfRowsRating(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, RatingTable.NAME);
        return numRows;
    }

    public boolean updateRating (Rating rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RatingTable.PLACE_ID,rating.getPlace_id());
        contentValues.put(RatingTable.DELICIOUS,rating.getRating_delicious());
        contentValues.put(RatingTable.CHEAP,rating.getRating_cheap());
        db.update(RatingTable.NAME, contentValues, RatingTable.ID+" = ? ", new String[] { Long.toString(rating.getId()) } );
        return true;
    }

    public Integer deleteRating (Long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(RatingTable.NAME,
                RatingTable.ID+" = ? ",
                new String[] { Long.toString(id) });
    }

    public ArrayList<Rating> getAllRatings() {
        ArrayList<Rating> array_list = new ArrayList<Rating>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+RatingTable.NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Rating(res.getLong(res.getColumnIndex(RatingTable.ID)),
                    res.getLong(res.getColumnIndex(RatingTable.PLACE_ID)),
                    res.getInt(res.getColumnIndex(RatingTable.DELICIOUS)),
                    res.getInt(res.getColumnIndex(RatingTable.CHEAP))));
            res.moveToNext();
        }

        return array_list;
    }

    // Category table
    public boolean insertCategory (Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long id=new Date().getTime();
        contentValues.put(CategoryTable.ID, id);
        contentValues.put(CategoryTable.CATEGORY_NAME,category.getCategoryName());
        db.insert(CategoryTable.NAME, null, contentValues);
        return true;
    }

    public Category getDataCategory(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query="select * from "+CategoryTable.NAME+" where "+CategoryTable.ID+"=?";
        Log.d("DBHelper",query);
        Cursor res =  db.rawQuery( query, new String[]{id.toString()} );
        res.moveToFirst();
        return new Category(res.getInt(res.getColumnIndex(CategoryTable.ID)),
                res.getString(res.getColumnIndex(CategoryTable.CATEGORY_NAME)));
    }


    public int numberOfRowsCategory(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CategoryTable.NAME);
        return numRows;
    }

    public boolean updateCategory (Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoryTable.CATEGORY_NAME,category.getCategoryName());
        db.update(CategoryTable.NAME, contentValues, CategoryTable.ID+" = ? ", new String[] { Long.toString(category.getId()) } );
        return true;
    }

    public Integer deleteCategory (Long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CategoryTable.NAME,
                CategoryTable.ID+" = ? ",
                new String[] { Long.toString(id) });
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> array_list = new ArrayList<Category>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CategoryTable.NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Category(res.getInt(res.getColumnIndex(CategoryTable.ID)),
                    res.getString(res.getColumnIndex(CategoryTable.CATEGORY_NAME))));
            res.moveToNext();
        }

        return array_list;
    }

    public int getIdByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String query="select * from "+CategoryTable.NAME+" where "+CategoryTable.CATEGORY_NAME+"=?";
        Log.d("DBHelper",query);
        Cursor res =  db.rawQuery( query, new String[]{name} );
        res.moveToFirst();
        return res.getInt(res.getColumnIndex(CategoryTable.ID));
    }
}
