package com.example.tabladeanuncionskotlin.dialoghelper

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.example.tabladeanuncionskotlin.MainActivity
import com.example.tabladeanuncionskotlin.R
import com.example.tabladeanuncionskotlin.accounthelper.AccountHelper
import com.example.tabladeanuncionskotlin.databinding.SignDialogBinding

class DialogHelper(act: MainActivity) {
    private val act = act
    val accHelper = AccountHelper(act)

    fun createSignDialog(index: Int){
        val builder = AlertDialog.Builder(act) // вызвали билдер у алерт диалога
        // и передали в него контекст через активити
        val rootDialogElement = SignDialogBinding.inflate(act.layoutInflater) // надули разметку sign_dialog
        val view = rootDialogElement.root // получили у нее рут элемент
        builder.setView(view) // добавили разметку в билдер

        setDialogState(index, rootDialogElement)
        
        val dialog = builder.create()
        rootDialogElement.btSignUpIn.setOnClickListener {
            setOnClickSignUpIn(index, rootDialogElement, dialog)
        }

        rootDialogElement.btForgetP.setOnClickListener {
            setOnClickResetPassword(rootDialogElement, dialog)
        }

        rootDialogElement.btGooglesignIn.setOnClickListener {
            accHelper.signInWithGoogle()
            dialog.dismiss()
        }

        dialog.show() // показали- запустили билдер
    }

    private fun setOnClickResetPassword(rootDialogElement: SignDialogBinding, dialog: AlertDialog?) {
        if(rootDialogElement.edSignEmail.text.isNotEmpty()){
            act.mAuth.sendPasswordResetEmail(rootDialogElement.edSignEmail.text.toString())
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(act, R.string.email_reset_password_was_sent, Toast.LENGTH_LONG)
                            .show()
                }
        }
            dialog?.dismiss()
        } else {
            rootDialogElement.tvDialogMessage.visibility = View.VISIBLE

        }

    }

    private fun setOnClickSignUpIn(index: Int, rootDialogElement: SignDialogBinding, dialog: AlertDialog?) {
        dialog?.dismiss()
        if(index == DialogConst.SIGN_UP_STATE){
            accHelper.signUpWithEmail(rootDialogElement.edSignEmail.text.toString(),
                rootDialogElement.edSignPassword.text.toString())
        } else {
            accHelper.signInWithEmail(rootDialogElement.edSignEmail.text.toString(),
                rootDialogElement.edSignPassword.text.toString())

        }

    }

    private fun setDialogState(index: Int, rootDialogElement: SignDialogBinding) {
        if(index == DialogConst.SIGN_UP_STATE){
            rootDialogElement.tvSignTitle.text = act.resources.getString(R.string.ac_sign_up)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_up_action)
        } else {
            rootDialogElement.tvSignTitle.text = act.resources.getString(R.string.ac_sign_in)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_in_action)
            rootDialogElement.btForgetP.visibility = View.VISIBLE
        }
    }
}