package com.example.loginactivity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.annotation.RequiresApi
import java.security.MessageDigest
import java.util.*
import kotlin.concurrent.timerTask
import com.google.android.gms.common.util.IOUtils.toByteArray
import android.content.pm.PackageInfo
import android.util.Log.e
import android.util.Base64
import androidx.fragment.app.FragmentActivity
import java.security.NoSuchAlgorithmException
import com.google.android.gms.common.util.IOUtils.toByteArray




class Splash_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash_)

        /* try {
         val info = packageManager.getPackageInfo(
             "com.example.loginactivity",
             PackageManager.GET_SIGNATURES)
         for (signature in info.signatures) {
             val md = MessageDigest.getInstance("SHA")
             md.update(signature.toByteArray())
             Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
         }
     } catch (e: PackageManager.NameNotFoundException) {

     } catch (e: NoSuchAlgorithmException) {

     }*/
        val timer = Timer()
        timer.schedule(timerTask {
            goToMainActivity()
        },1000)
    }
    private fun goToMainActivity(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


}
