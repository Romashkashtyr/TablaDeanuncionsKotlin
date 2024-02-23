package com.example.tabladeanuncionskotlin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.tabladeanuncionskotlin.databinding.ActivityMainBinding
import com.example.tabladeanuncionskotlin.dialoghelper.DialogConst
import com.example.tabladeanuncionskotlin.dialoghelper.DialogHelper
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
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

    private fun init() {
        //drawerLayout = findViewById(R.id.drawerLayout)
        //toolbar = findViewById(R.id.toolbar)
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.mainContent.toolbar , R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)

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
                Toast.makeText(this, "Pressed id_sign_out", Toast.LENGTH_LONG).show()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
       return true
    }
}