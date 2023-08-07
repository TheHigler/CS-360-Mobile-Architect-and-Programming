package com.xybooks.ronbalcs360projectthree;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class WeightDatabse extends RoomDatabase {
    public abstract UserDao userDao();
}
