package com.adonai.admissiontracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.adonai.admissiontracker.R;
import com.adonai.admissiontracker.entities.Favorite;
import com.adonai.admissiontracker.entities.Statistics;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import static com.adonai.admissiontracker.Constants.University.*;

/**
 * Created by adonai on 29.06.14.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    //имя файла базы данных который будет храниться в /data/data/APPNAME/DATABASE_NAME
    private static final String DATABASE_NAME ="admissions.db";

    //с каждым увеличением версии, при нахождении в устройстве БД с предыдущей версией будет выполнен метод onUpgrade();
    private static final int DATABASE_VERSION = 2;

    //ссылки на DAO соответсвующие сущностям, хранимым в БД
    private Dao<Favorite, String> favDao = null;
    private Dao<Statistics, Integer> statDao = null;
    private final Context mContext;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    //Выполняется, когда файл с БД не найден на устройстве
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try
        {
            TableUtils.createTable(connectionSource, Favorite.class);
            TableUtils.createTable(connectionSource, Statistics.class);
        }
        catch (SQLException e){
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    //Выполняется, когда БД имеет версию отличную от текущей
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer, int newVer) {
        if(newVer == 2 && oldVer == 1) {
            db.delete("favorites", "parentInstitution = ?", new String[]{String.valueOf(SPBU.ordinal())});
            Toast.makeText(mContext, R.string.favs_invalidated, Toast.LENGTH_LONG).show();
        }
    }

    //синглтон для GoalDAO
    public Dao<Favorite, String> getFavoritesDao() throws SQLException {
        if(favDao == null)
            favDao = getDao(Favorite.class);

        return favDao;
    }

    //синглтон для GoalDAO
    public Dao<Statistics, Integer> getStatDao() throws SQLException {
        if(statDao == null)
            statDao = getDao(Statistics.class);

        return statDao;
    }

    //выполняется при закрытии приложения
    @Override
    public void close() {
        super.close();
        favDao = null;
    }
}
