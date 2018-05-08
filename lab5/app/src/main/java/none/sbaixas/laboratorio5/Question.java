package none.sbaixas.laboratorio5;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Sebastian on 17-04-18.
 */

@Entity(foreignKeys = @ForeignKey(entity = Forms.class,
                                    parentColumns = "formId",
                                    childColumns = "formId",
                                    onDelete = CASCADE))

public class Question {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int questionId;
    private int formId;
    private String questionType;
    private String questionText;

    public Question(){
    }

    public int getQuestionId(){
        return this.questionId;
    }

    public int getFormId(){
        return this.formId;
    }
    public String getQuestionType(){
        return this.questionType;
    }
    public String getQuestionText(){
        return this.questionText;
    }
    public void setQuestionId(int questionId){
        this.questionId = questionId;
    }
    public void setFormId(int formId){
        this.formId = formId;
    }
    public void setQuestionType(String questionType){
        this.questionType = questionType;
    }
    public void setQuestionText(String questionText){
        this.questionText = questionText;
    }
}
