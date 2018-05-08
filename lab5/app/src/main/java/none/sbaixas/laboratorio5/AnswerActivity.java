package none.sbaixas.laboratorio5;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class AnswerActivity extends AppCompatActivity {
    private Forms form;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        int id = getIntent().getIntExtra("id", -1);
        form = MainApplication.formDatabase.daoAccess().fetchOneFormsbyFormId(id);
        TextView tv = findViewById(R.id.description_text_view);
        tv.setText(form.getComment());
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    public void onLoginClick(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(final Location location) {
                        if(location != null){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Answer a = new Answer();
                                    a.setAnswerText("Answer Text");
                                    a.setLocationx(location.getLongitude());
                                    a.setLocationy(location.getLatitude());
                                    MainApplication.formDatabase.daoAccess().insertOnlySingleAnswer(a);
                                    MainApplication.formDatabase.daoAccess().insertOnlySingleForm(form);
                                }
                            }).start();
                        }
                    }
                });
    }

}
