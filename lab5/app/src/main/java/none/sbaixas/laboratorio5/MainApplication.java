package none.sbaixas.laboratorio5;

import android.app.Application;
import android.arch.persistence.room.Room;


/**
 * Created by Sebastian on 03-04-18.
 */

public class MainApplication extends Application {
    private static final String DATABASE_NAME = "forms_db";
    public static FormDatabase formDatabase;
    @Override
    public void onCreate(){
        super.onCreate();
        formDatabase = Room.databaseBuilder(getApplicationContext(),
                FormDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

}


