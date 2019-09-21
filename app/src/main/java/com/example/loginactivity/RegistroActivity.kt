package com.example.loginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var TAG = "RegistroActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        btAceptar.setOnClickListener {
            var intent = Intent()
            var email_registro = etRegister_Email.text.toString()
            var pass_registro = etRegister_Pass.text.toString()
            var pass_registro2 = et_Register_Pass2.text.toString()


            if(email_registro.isEmpty()){
                Toast.makeText(this,"registre el correo",Toast.LENGTH_SHORT).show()}
            else if(pass_registro.isEmpty()){Toast.makeText(this,"registre contraseña",Toast.LENGTH_SHORT).show()}
            else if(pass_registro.length <6){Toast.makeText(this,"La contraseña debe contener al menos 6 caracteres",Toast.LENGTH_SHORT).show()}
            else if (pass_registro2.isEmpty()){Toast.makeText(this,"confirmar contraseña",Toast.LENGTH_SHORT).show()}
            else if (!(pass_registro.equals(pass_registro2))){Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()}
            else{
                //intent.putExtra("useremail",email_registro)
                //intent.putExtra("userpass",pass_registro)
                //setResult(Activity.RESULT_OK,intent)
                registerUser()
                //Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show()
                //finish()
            }
        }
    }

    private fun registerUser() {
        auth.createUserWithEmailAndPassword(etRegister_Email.text.toString(), etRegister_Pass.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    //goToMainActivity()
                    auth.signOut()
                    goToLoginActivity()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, task.exception?.message.toString(),
                        Toast.LENGTH_SHORT).show()

                }


            }
    }

    private fun goToMainActivity() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToLoginActivity() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Registro no exitoso",Toast.LENGTH_SHORT).show()
    }

}
