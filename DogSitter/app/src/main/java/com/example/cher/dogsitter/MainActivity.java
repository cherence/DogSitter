package com.example.cher.dogsitter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        /* If a user is currently authenticated, display a logout menu */
//        if (this.mAuthData != null) {
//            getMenuInflater().inflate(R.menu.main, menu);
//            return true;
//        } else {
//            return false;
//        }
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_logout) {
//            logout();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * Unauthenticate from Firebase and Facebook where necessary.
     */
    private void logout(){
        if (this.mAuthData != null) {
            /* logout of Firebase */
            mFirebaseRef.unauth();
            /* Logout of any of the Frameworks. This step is optional, but ensures the user is not logged into
             * Facebook/Google+ after logging out of Firebase. */
            if (this.mAuthData.getProvider().equals("facebook")) {
                /* Logout from Facebook */
                LoginManager.getInstance().logOut();
            }
            setAuthenticatedUser(null);
        }
    }

    /**
     * Once a user is logged in, take the mAuthData provided from Firebase and "use" it.
     * @param authData
     */
    private void setAuthenticatedUser(AuthData authData){
        if (authData != null){
            //keep the login button hidden
            String name = (String) authData.getProviderData().get("displayName");
            Intent intent = new Intent(MainActivity.this, PetInfoActivity.class);
            startActivity(intent);
            if (name != null){
                mLoggedInStatusTextView.setText("Logged in as " + name + " (" + authData.getProviderData() + ")");
            }
        } else {
            //If no authenticated user show the login button
            mFacebookLoginButton.setVisibility(View.VISIBLE);
        }
        mAuthData = authData;
        //invalidate options menu to hide/show the logout button
        //supportInvalidateOptionsMenu(); <<uncomment when toolbar is made
    }

    /**
     * Utility class for authentication results
     */
    private class AuthResultHandler implements Firebase.AuthResultHandler {
        private final String provider;

        public AuthResultHandler(String provider) {
            this.provider = provider;
        }

        @Override
        public void onAuthenticated(AuthData authData) {
            mAuthProgressDialog.hide();
            Log.i(TAG, "onAuthenticated:" + provider + " auth successful");
            setAuthenticatedUser(authData);
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            mAuthProgressDialog.hide();
            showErrorDialog(firebaseError.toString());
        }
    }

    /**
     * Show errors to users
     */
    private void showErrorDialog(String message){
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void onFacebookAccessTokenChange(AccessToken token){
        if (token != null){
            mAuthProgressDialog.show();
            mFirebaseRef.authWithOAuthToken("facebook", token.getToken(), new AuthResultHandler("facebook"));
        } else {
            //Logged out of facebook and currently authenticated with firebase using Facebook, so do a logout
            if (this.mAuthData != null && this.mAuthData.getProvider().equals("facebook")){
                mFirebaseRef.unauth();
                setAuthenticatedUser(null);
            }
        }
    }
}


//facebook app id 243406302682623
//facebook app secret 040b13edefb76aefd66f3c4416e2b95e