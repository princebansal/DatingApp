package headout.com.datingapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final String registerURL="http://datacomm.azurewebsites.net/registerStudyJams.php";

    private EditText username ;
    private EditText password ;
    private RadioGroup genderGroup ;
    private Button buttonLogin ;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.activity_login_tv_username);
        password = (EditText) findViewById(R.id.activity_login_tv_password);
        genderGroup= (RadioGroup) findViewById(R.id.gender_group);
        buttonLogin = (Button) findViewById(R.id.activity_login_btn);

        setInit();
    }

    private void setInit() {
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_login_btn:
                login();
        }
    }

    private void login() {

        boolean isCorrect= validateData();


        if(isCorrect){
            makeLoginRequest();
        }

    }

    private void makeLoginRequest() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                registerURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("volleyRespose", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username.getText().toString());
                map.put("password", password.getText().toString());
                map.put("gender", genderGroup.getCheckedRadioButtonId() == R.id.activity_login_rb_male ? "male" : "female");
                return super.getParams();
            }
        };





    }

    private boolean validateData() {

        if(TextUtils.isEmpty(password.getText().toString())){
           password.setError("Invalid");
            return false;
        }

        if(TextUtils.isEmpty(username.getText().toString())){
            username.setError("Empty");
            return false;
        }

        if(!username.getText().toString().contains("@")){
            username.setError("Invalid");
            return false;
        }

        return true;




    }
}
