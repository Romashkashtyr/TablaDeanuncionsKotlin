package com.example.tabladeanuncionskotlin.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tabladeanuncionskotlin.R
import com.example.tabladeanuncionskotlin.act.EditAdsAct
import org.w3c.dom.Text

class RcViewDialogSpinnerAdapter(var tvSelection: TextView, var dialog :AlertDialog, ): RecyclerView.Adapter<RcViewDialogSpinnerAdapter.SpViewHolder>() {

    private val mainList = ArrayList<String>()

    inner class SpViewHolder(itemView: View, var tvSelection: TextView, var dialog: AlertDialog): RecyclerView.ViewHolder(itemView), OnClickListener{

        private var itemText = ""
        fun setData(text: String){
            val tvSpItem = itemView.findViewById<TextView>(R.id.tvSpItem)
            tvSpItem.text = text
            itemText = text
            itemView.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            tvSelection.text = itemText
            dialog.dismiss()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sp_list_item, parent, false)
        return SpViewHolder(view, tvSelection, dialog)
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    override fun onBindViewHolder(holder: SpViewHolder, position: Int) {
        holder.setData(mainList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(list: ArrayList<String>){
        mainList.clear()
        mainList.addAll(list)
        notifyDataSetChanged()
    }
}