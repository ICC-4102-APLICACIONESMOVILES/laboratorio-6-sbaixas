package none.sbaixas.laboratorio5;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Sebastian on 03-04-18.
 */
@Dao
public interface DaoAccess {
    @Insert
    void insertOnlySingleForm(Forms forms);
    @Insert
    void insertMultipleForms (List<Forms> FormsList);
    @Query("SELECT * FROM Forms WHERE formId = :formId")
    Forms fetchOneFormsbyFormId(int formId);
    @Query("SELECT * FROM Forms")
    List<Forms> fetchAllForms();
    @Update
    void updateForm(Forms forms);
    @Delete
    void deleteForm(Forms forms);
    @Query("DELETE FROM Forms")
    void nukeTable();
    @Insert
    void insertOnlySingleAnswer(Answer answer);
}
