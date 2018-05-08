package none.sbaixas.laboratorio5;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.location.Location;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Sebastian on 17-04-18.
 */

@Entity(foreignKeys = @ForeignKey(entity = Question.class,
        parentColumns = "questionId",
        childColumns = "questionId",
        onDelete = CASCADE))

public class Answer {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int answerId;
    private int questionId;
    private String answerText;
    private double locationx;
    private double locationy;

    public Answer(){

    }
    public int getAnswerId(){
        return this.answerId;
    }
    public int getQuestionId(){
        return this.questionId;
    }
    public String getAnswerText(){
        return this.answerText;
    }
    public double getLocationx(){
        return this.locationx;
    }
    public double getLocationy(){
        return this.locationy;
    }

    public void setAnswerId(int answerId){
        this.answerId = answerId;
    }
    public void setQuestionId(int questionId){
        this.questionId = questionId;
    }
    public void setAnswerText(String answerText){
        this.answerText = answerText;
    }
    public void setLocationx(double location){
        this.locationx = location;
    }
    public void setLocationy(double location){
        this.locationy = location;
    }


}
