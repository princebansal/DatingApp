package headout.com.datingapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username ;
    private EditText password ;
    private Button buttonLogin ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.activity_login_tv_username);
        password = (EditText) findViewById(R.id.activity_login_tv_password);
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

        }

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
