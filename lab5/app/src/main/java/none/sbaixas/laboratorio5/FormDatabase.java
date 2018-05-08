package none.sbaixas.laboratorio5;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Sebastian on 03-04-18.
 */


@Database(entities = {Forms.class, Answer.class, Question.class}, version = 1, exportSchema = false)
public abstract class FormDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess() ;
}
