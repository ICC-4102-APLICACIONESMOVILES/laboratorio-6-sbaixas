package none.sbaixas.laboratorio5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    boolean logged_in;
    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.email_text_edit);
        passwordEditText = findViewById(R.id.password_text_edit);
        networkManager = NetworkManager.getInstance(this);
        logged_in = false;
    }

    private void getForms(){
        networkManager.getForms(new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
            try {
               JSONArray array = response.getJSONArray("0");
                for (int i = 0; i < array.length(); i++) {
                    final JSONObject current = array.getJSONObject(i);
                            Forms form = new Forms();
                            form.setUserName(current.getString("name"));
                            form.setDate(current.getString("updated_at"));
                            form.setCategory(current.getString("name"));
                            form.setComment(current.getString("name"));
                            MainApplication.formDatabase.daoAccess().insertOnlySingleForm(form);

                }
                System.out.println(response);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                System.out.println(error);
            }
        });
    }

    public void onLoginClick(View view){

        try {
            final Activity currentActivity = this;
            networkManager.login(emailEditText.getText().toString(), passwordEditText.getText().toString(), new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    getForms();
                    JSONObject headers = response.optJSONObject("headers");
                    String token = headers.optString("Authorization", "");
                    Intent result = new Intent();
                    result.putExtra("email", emailEditText.getText().toString());
                    result.putExtra("password", passwordEditText.getText().toString());
                    result.putExtra("token", token);
                    setResult(Activity.RESULT_OK, result);
                    currentActivity.finish();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error
                    System.out.println(error);
                    Context context = getApplicationContext();
                    CharSequence text = "Invalid Credentials";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
