package com.example.tabladeanuncionskotlin


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.tabladeanuncionskotlin.databinding.ActivityMainBinding
import com.example.tabladeanuncionskotlin.dialoghelper.DialogConst
import com.example.tabladeanuncionskotlin.dialoghelper.DialogHelper
import com.example.tabladeanuncionskotlin.dialoghelper.GoogleAccConst
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception


class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
    private lateinit var tvAccount: TextView
//    private lateinit var drawerLayout:DrawerLayout
  //  private lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityMainBinding
    private val dialogHelper = DialogHelper(this)
    val mAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == GoogleAccConst.GOOGLE_SIGN_IN_REQUEST_CODE){
//            Log.d("MyLog", "Sign in result")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if(account != null){
                    dialogHelper.accHelper.signInFirebaseWithGoogle(account.idToken!!)
                }

            } catch (e: ApiException) {
                Log.d("MyLog", "Api error ${e.message}")

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        uiUpdate(mAuth.currentUser)
    }

    private fun init() {
        //drawerLayout = findViewById(R.id.drawerLayout)
        //toolbar = findViewById(R.id.toolbar)
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.mainContent.toolbar , R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        tvAccount = binding.navView.getHeaderView(0).findViewById(R.id.tvAccountEmail)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.id_my_ads -> {
                Toast.makeText(this, "Pressed id_my_ads", Toast.LENGTH_LONG).show()
            }
            R.id.id_car -> {
                Toast.makeText(this, "Pressed id_car", Toast.LENGTH_LONG).show()
            }
            R.id.id_pc -> {
                Toast.makeText(this, "Pressed id_pc", Toast.LENGTH_LONG).show()
            }
            R.id.id_sign_up -> {
                dialogHelper.createSignDialog(DialogConst.SIGN_UP_STATE)
            }
            R.id.id_sign_in -> {
                dialogHelper.createSignDialog(DialogConst.SIGN_IN_STATE)
            }
            R.id.id_sign_out -> {
                uiUpdate(null)
                mAuth.signOut()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
       return true
    }

    fun uiUpdate(user: FirebaseUser?){
        tvAccount.text = if(user == null){
            resources.getString(R.string.not_reg)
        } else {
            user.email
        }

    }
}