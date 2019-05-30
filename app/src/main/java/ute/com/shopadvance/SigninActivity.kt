package ute.com.shopadvance

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {

    private val TAG: String? = SigninActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        btn_signup.setOnClickListener { view ->
            signUp()
        }
        btn_login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = ed_email.text.toString()
        val password = ed_password.text.toString()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    setResult(Activity.RESULT_OK)
                    finish()
                } else {
                    AlertDialog.Builder(this@SigninActivity)
                        .setTitle("LogIn")
                        .setMessage(it.exception?.message)
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
    }

    private fun signUp() {
        val email = ed_email.text.toString()
        val password = ed_password.text.toString()
        Log.i(TAG, "email: $email");
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    AlertDialog.Builder(this@SigninActivity)
                        .setTitle("SignIn")
                        .setMessage("AccountCreated")
                        .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                            setResult(Activity.RESULT_OK)
                            finish()
                        })
                        .show()
                } else {
                    AlertDialog.Builder(this@SigninActivity)
                        .setTitle("SignIn")
                        .setMessage(task.exception?.message)
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
    }
}
