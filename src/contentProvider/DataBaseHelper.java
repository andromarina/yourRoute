package contentProvider;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.*;


public class DataBaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    //public static final String DB_PATH = "/data/data/contentProvider/databases/";

    public static final String DB_NAME = "db-blob.sqlite";
    private static final int DATABASE_VERSION = 2;
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    final private String LOG_TAG = this.getClass().getSimpleName();

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {
        Log.d(LOG_TAG, "create DB was called");
        boolean dbExist = checkDataBase();

        if (dbExist) {

            Log.d(LOG_TAG, "DB exists");
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getWritableDatabase();
            Log.d(LOG_TAG, "get writable DB was called");
            try {

                copyDataBase();

            } catch (IOException e) {
                Log.d(LOG_TAG, "Error copying DB");
                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {

            checkDB = SQLiteDatabase.openDatabase(myContext.getDatabasePath(DB_NAME).getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);

        } catch (SQLiteException e) {
            Log.d(LOG_TAG, "DB doesn't exist yet");
            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();
            Log.d(LOG_TAG, "DB was closed");
        }

        return (checkDB != null);
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {
        try {
            //Open your local db as the input stream
            AssetManager am = myContext.getAssets();
            InputStream myInput = am.open(DB_NAME);
            Log.d(LOG_TAG, "DB was opened for copying");


            //Open the empty db as the output stream
            isDirectoryExists();
            OutputStream myOutput = new FileOutputStream(myContext.getDatabasePath(DB_NAME));
            Log.d(LOG_TAG, "file output stream was opened");
            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            Log.d(LOG_TAG, "DB was copied");
            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Throwable ex) {
            Log.d(LOG_TAG, "error copying: " + ex);
        }
    }


    public void isDirectoryExists() {
        String dbpath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        Log.d(LOG_TAG, "dbpath " + dbpath);
        File dir = new File(dbpath + "/databases/");
        if (dir.exists() && dir.isDirectory()) {
            Log.d(LOG_TAG, "directory exists");
        } else {
            Log.d(LOG_TAG, "Directory doesn't exist");
        }
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        myDataBase = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        if (newVersion > oldVersion) {
            Log.d(LOG_TAG, "create DB was called");
            try {
                myContext.deleteDatabase(DB_NAME);
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}