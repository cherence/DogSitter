package com.example.cher.dogsitter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mLoggedInStatusTextView;
    //A dialog that is presented until the Firebase authentication finished.
    private ProgressDialog mAuthProgressDialog;
    //A reference to the Firebase
    private Firebase mFirebaseRef;
    //Data from the authenticated user
    private AuthData mAuthData;
    //Listener for Firebase session changes
    private Firebase.AuthStateListener mAuthStateListener;
    //The login button for Facebook
    private LoginButton mFacebookLoginButton;
    //The callback manager for Facebook
    private CallbackManager mFacebookCallbackManager;
    //Used to track user logging in/out off Facebook
    private AccessTokenTracker mFacebookAccessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        //Load the Facebook login button and set up the tracker to monitor access token changes
        mFacebookCallbackManager = CallbackManager.Factory.create();
        mFacebookAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.i(TAG, "onCurrentAccessTokenChanged: the token has changed");
                MainActivity.this.onFacebookAccessTokenChange(currentAccessToken);
            }
        };
        /* Create the Firebase ref that is used for all authentication with Firebase */
        mFirebaseRef = new Firebase("https://dogsitter.firebaseio.com/");
        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading");
        mAuthProgressDialog.setMessage("Authenticating with Facebook...");
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();

        mAuthStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                mAuthProgressDialog.hide();
                setAuthenticatedUser(authData);
            }
        };

        //Check if the user is authenticated with Firebase already. If this is the case we can set the authenticated user and hide hide any login buttons
        mFirebaseRef.addAuthStateListener(mAuthStateListener);

    }

    /**
     * TextView that is used to display information about the logged in user.
     *
     */
    private void initializeViews(){
        mFacebookLoginButton = (LoginButton) findViewById(R.id.facebookLogin_button_id);
        mLoggedInStatusTextView = (TextView) findViewById(R.id.loginStatus_textView_id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // if user logged in with Facebook, stop tracking their token
        if (mFacebookAccessTokenTracker != null){
            mFacebookAccessTokenTracker.stopTracking();
        }
        // if changing configurations, stop tracking firebase session.
        mFirebaseRef.removeAuthStateListener(mAuthStateListener);
    }

    /**
     * This method fires when any startActivityForResult finishes. The requestCode maps to
     * the value passed into startActivityForResult. Keeps track of the Facebook login session.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    
}


//facebook app id 243406302682623
//facebook app secret 040b13edefb76aefd66f3c4416e2b95e