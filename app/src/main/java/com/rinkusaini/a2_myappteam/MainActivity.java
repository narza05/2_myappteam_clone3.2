package com.rinkusaini.a2_myappteam;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.Fragment;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.android.material.button.MaterialButton;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private TextView email;
    private TextView password;

    private MaterialButton loginbtn;
    private MaterialButton signupbtn;

    boolean salesman = false;
    boolean employee = false;
    boolean admin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        RadioButton employee = findViewById(R.id.radioButton1);
        RadioButton salesman = findViewById(R.id.radioButton2);
        RadioButton admin = findViewById(R.id.radioButton3);

         email = findViewById(R.id.username);
         password = findViewById(R.id.password);
         loginbtn = findViewById(R.id.loginbtn);
         signupbtn = findViewById(R.id.signupbtn);

//        String email = username.getText().toString();
//        String password = password1.getText().toString();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }

    public void login(){
        auth = FirebaseAuth.getInstance();

        System.out.println(email.getText().toString());
        System.out.println(password.getText().toString());
        auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Fragment homefragment = new HomeFragment();
                try {
                    if (task.isSuccessful() && employee) {
                        Toast.makeText(MainActivity.this, "Employee Login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, SecondActivity_employee.class));
//                        try{
//                            FragmentTransaction movetohome = getSupportFragmentManager().beginTransaction();
//                            movetohome.replace(R.id.mainactivity, homefragment).commit();
//                        }
//                        catch (Exception e){
//                            System.out.println(e);
//                        }
                    }

                    else if (task.isSuccessful() && salesman){
                        Toast.makeText(MainActivity.this, "Salesman Login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, SecondActivity_salesman.class));
                    }

                    else if (task.isSuccessful() && admin){
                        Toast.makeText(MainActivity.this, "Admin Login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, SecondActivity_admin.class));
                    }

                    else {
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    public void signup(){
        auth = FirebaseAuth.getInstance();

//        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
//                    username.setError("Email empty");
//                    password1.setError("password empty");
//                }
//                else{

        System.out.println(email.getText().toString());
        System.out.println(password.getText().toString());
            auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Fragment homefragment = new HomeFragment();
                    try {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, SecondActivity_employee.class));
//                            FragmentTransaction movetohome = getSupportFragmentManager().beginTransaction();
//                            movetohome.replace(R.id.mainactivity, homefragment).commit();
                        } else {
                            Toast.makeText(MainActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            });
//                }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = auth.getCurrentUser();
//        if(user == null){
//            startActivity(new Intent(MainActivity.this, Loginfragment_HOME.class));
//        }
//    }

    public void onclick(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radioButton1:
                if (checked){
                    employee = true;
                    salesman = false;
                    admin = false;
                    Toast.makeText(this, "employee", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.radioButton2:
                if (checked){
                    employee = false;
                    salesman = true;
                    admin = false;
                    Toast.makeText(this, "salesman", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.radioButton3:
                if (checked){
                    employee = false;
                    salesman = false;
                    admin = true;
                    Toast.makeText(this, "admin", Toast.LENGTH_SHORT).show();
                    break;
                }

        }
    }
}
