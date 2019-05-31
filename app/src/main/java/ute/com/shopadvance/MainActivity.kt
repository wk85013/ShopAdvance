package ute.com.shopadvance

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity(), FirebaseAuth.AuthStateListener {


    private val TAGG: String? = MainActivity::class.java.simpleName
    private val RC_SIGNIN: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        verify_email.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                //送出EMAIL驗證信
                if (task.isSuccessful) {
                    Snackbar.make(it, "Verify email sent", Snackbar.LENGTH_LONG).show()
                } else {
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("Verify")
                        .setMessage(task.exception?.message)
                        .setPositiveButton("OK", null)
                        .show()

                }

            }
        }
    }

    override fun onAuthStateChanged(auth: FirebaseAuth) {
        val user = auth.currentUser
        Log.i(TAGG, "user: ${user?.uid}");
        if (user != null) {
            user_info.setText("Email:${user.email} / ${user.isEmailVerified}")//驗證EMAIL
            verify_email.visibility = if (user.isEmailVerified) View.GONE else View.VISIBLE
        } else {
            user_info.setText("Not login")
            verify_email.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(this)//Auth狀態傾聽器
    }

    override fun onStop() {
        super.onStop()
        FirebaseAuth.getInstance().removeAuthStateListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_signout -> {
                FirebaseAuth.getInstance().signOut()//登出

                true
            }
            R.id.action_signin -> {
//                startActivityForResult(Intent(this, SigninActivity::class.java), RC_SIGNIN)
                val whiteList = listOf("tw", "hk")
                //客製登入畫面
                val customSignupLayout = AuthMethodPickerLayout.Builder(R.layout.sign_up_layout)
                    .setEmailButtonId(R.id.btn_signup_email)
                    .setFacebookButtonId(R.id.btn_signup_facebook)
                    .setGoogleButtonId(R.id.btn_signup_google)
                    .setPhoneButtonId(R.id.btn_signup_sms)
                    .build()
                val intent = AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(
                        Arrays.asList(
                            AuthUI.IdpConfig.EmailBuilder().build(),//EMAIL登入
                            AuthUI.IdpConfig.GoogleBuilder().build(),//GOOGLE帳號登入
                            AuthUI.IdpConfig.FacebookBuilder().build(),//FACEBOOK帳號登入
                            AuthUI.IdpConfig.PhoneBuilder().setDefaultCountryIso("tw").//預設國家
                                setWhitelistedCountries(whiteList).//可用國家白名單
                                build()//簡訊登入

                        )
                    )
                    .setIsSmartLockEnabled(false)
                    .setLogo(android.R.drawable.ic_menu_save)//LOGO
                    .setTheme(R.style.SignUp)//顏色樣式
                    .setAuthMethodPickerLayout(customSignupLayout)//客製登入畫面
                    .build()
                startActivityForResult(intent, RC_SIGNIN)
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
