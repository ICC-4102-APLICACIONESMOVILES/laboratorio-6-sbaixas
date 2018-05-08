package none.sbaixas.laboratorio5;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Sebastian on 03-04-18.
 */
@Entity
public class Forms {
    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int formId;
    private String userName;
    private String date;
    private String category;
    private String comment;

    public Forms(){
    }

    public int getFormId(){return formId;}
    public String getUserName(){return userName;}
    public String getDate(){return date;}
    public String getCategory(){return category;}
    public String getComment(){return comment;}
    public void setFormId(int formId){this.formId = formId;}
    public void setUserName(String userName){this.userName = userName;}
    public void setDate(String date){this.date = date;}
    public void setCategory(String category){this.category = category;}
    public void setComment(String comment){this.comment = comment;}

}
