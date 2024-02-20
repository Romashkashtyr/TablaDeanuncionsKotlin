package com.example.tabladeanuncionskotlin.dialoghelper

import android.app.AlertDialog
import com.example.tabladeanuncionskotlin.MainActivity
import com.example.tabladeanuncionskotlin.R
import com.example.tabladeanuncionskotlin.databinding.SignDialogBinding

class DialogHelper(act: MainActivity) {
    private val act = act

    fun createSignDialog(index: Int){
        val builder = AlertDialog.Builder(act) // вызвали билдер у алерт диалога
        // и передали в него контекст через активити
        val rootDialogElement = SignDialogBinding.inflate(act.layoutInflater) // надули разметку sign_dialog
        val view = rootDialogElement.root // получили у нее рут элемент
        if(index == DialogConst.SIGN_UP_STATE){
            rootDialogElement.tvSignTitle.text = act.resources.getString(R.string.ac_sign_up)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_up_action)
        } else {
            rootDialogElement.tvSignTitle.text = act.resources.getString(R.string.ac_sign_in)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_in_action)
        }
        builder.setView(view) // добавили разметку в билдер
        builder.show() // показали- запустили билдер
    }
}