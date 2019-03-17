package database.c347.soi.rp.edu.sg.portfolio.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import database.c347.soi.rp.edu.sg.portfolio.R;

public class LoginActivity extends AppCompatActivity {

    static final int GOOGLE_SIGN = 123;
    FirebaseAuth mAuth;
    Button btnLogin, btnLogout, btnLoginFacebook, btnLoginPhone;
    TextView tvText;
    ImageView ivImage;
    ProgressBar pbProgressBar;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.login);
        btnLoginFacebook = (Button) findViewById(R.id.loginFacebook);
        btnLoginPhone = (Button) findViewById(R.id.loginPhone);
        btnLogout = (Button) findViewById(R.id.logout);
        tvText = (TextView) findViewById(R.id.text);
        ivImage = (ImageView) findViewById(R.id.image);
        pbProgressBar = (ProgressBar) findViewById(R.id.progress_circular);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        btnLogin.setOnClickListener(view -> SignInGoogle());
        btnLogout.setOnClickListener(view -> Logout());

        if(mAuth != null){
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
        }
    }


    void SignInGoogle(){
        pbProgressBar.setVisibility(View.VISIBLE);
        Intent signIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIntent, GOOGLE_SIGN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GOOGLE_SIGN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if(account != null){
                    //make request firebase
                    firebaseAuthWithGoogle(account);
                }
            } catch (ApiException e){
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("TAG", "firebaseAuthWithGoogle: " + account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        pbProgressBar.setVisibility(View.INVISIBLE);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()){
                        Log.d("TAG", "Sign in success");

                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Log.w("TAG", "Sign in failure", task.getException());
                        Toast.makeText(this, "SignIn Failed!", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri tst = user.getPhotoUrl();
            System.out.println("Tsr: " + tst);
            String photo = String.valueOf(user.getPhotoUrl());
            System.out.println("Photo URL: " + user.getPhotoUrl());
            tvText.append(" Info : \n");
            tvText.append(name + "\n");
            tvText.append(email + "\n");
            Picasso.get().load(photo).into(ivImage);
            btnLogin.setVisibility(View.INVISIBLE);
            btnLoginFacebook.setVisibility(View.INVISIBLE);
            btnLoginPhone.setVisibility(View.INVISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
        } else {
            tvText.setText("Firebase Login");
            Picasso.get().load(R.drawable.ic_firebase_logo).into(ivImage);
            btnLogin.setVisibility(View.VISIBLE);
            btnLoginFacebook.setVisibility(View.VISIBLE);
            btnLoginPhone.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.INVISIBLE);
        }
    }

    void Logout(){
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                   updateUI(null);
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                    updateUI(null);
                });

    }
}
