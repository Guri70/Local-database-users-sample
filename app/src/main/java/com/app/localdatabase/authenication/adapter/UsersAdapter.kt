package com.app.localdatabase.authenication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.localdatabase.databinding.ItemUserBinding
import com.app.localdatabase.listener.OnItemClickListener
import com.app.localdatabase.roomDatabase.UserModel


class UsersAdapter constructor(
    private val list: List<UserModel>,
    private val onOnItemClickListener: OnItemClickListener<UserModel>
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var isDataOpen = false;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.binding.apply {
            tvName.text = model.name
            tvFullName.text = "Name : " + model.name
            tvEmail.text = "Email : " + model.email
            tvPhone.text = "Phone : " + model.phone
            tvGender.text = "Gender : " + model.gender
            tvDob.text = "DOB : " + model.dob

            holder.itemView.setOnClickListener {
                isDataOpen = !isDataOpen

                if (isDataOpen)
                    layoutData.visibility = View.VISIBLE
                else
                    layoutData.visibility = View.GONE
            }

            holder.itemView.setOnLongClickListener {
                onOnItemClickListener.onClickNotify(model, holder.adapterPosition)
                true
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}