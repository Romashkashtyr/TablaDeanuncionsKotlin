package com.example.tabladeanuncionskotlin.accounthelper

import android.widget.Toast
import com.example.tabladeanuncionskotlin.MainActivity
import com.example.tabladeanuncionskotlin.R
import com.google.firebase.auth.FirebaseUser

class AccountHelper(act: MainActivity) {
    private val act = act
    fun signUpWithEmail(email: String, password: String){
        if(email.isNotEmpty() && password.isNotEmpty()){
            act.mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {task ->
                if(task.isSuccessful){
                    sendEmailVerification(task.result?.user!!)

                } else {
                    Toast.makeText(act, act.resources.getString(R.string.sign_up_error), Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

    private fun sendEmailVerification(user: FirebaseUser){
        user.sendEmailVerification().addOnCompleteListener {task ->
            if(task.isSuccessful){
                Toast.makeText(act, act.resources.getString(R.string.send_verification_done), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(act, act.resources.getString(R.string.send_verification_error), Toast.LENGTH_SHORT).show()
            }

        }
    }
}