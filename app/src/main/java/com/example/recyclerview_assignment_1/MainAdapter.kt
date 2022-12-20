package com.example.recyclerview_assignment_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recyclerview_assignment_1.databinding.CustomDialogUserDetailsBinding
import com.example.recyclerview_assignment_1.databinding.MainRowLayoutBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var dataList = emptyList<User>()

    class MainViewHolder(val binding: MainRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            MainRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.txtUserName.text = dataList[position].userName
        holder.binding.txtUserPhoneNumber.text = dataList[position].phoneNumber
        holder.binding.imgUserProfile.load(dataList[position].profileImage)

        holder.binding.mainRowLayout.animation =
            (AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_view_anim))

        holder.binding.imgDetails.setOnClickListener {

            val inflater = LayoutInflater.from(holder.itemView.context)
            val binding = CustomDialogUserDetailsBinding.inflate(inflater)

            binding.txtUserNumber.text = "Cell : ${dataList[position].phoneNumber}"
            binding.txtUserVillage.text = "Village : ${dataList[position].villageName}"
            binding.txtPostOfficeName.text = "Post Office : ${dataList[position].postOfficeName}"
            binding.txtDistrictName.text = "District : ${dataList[position].districtName}"
            binding.txtDivisionName.text = "Division : ${dataList[position].divisionName}"
            binding.txtCountryName.text = "Country : ${dataList[position].countryName}"

            binding.imgUserProfile.load(dataList[position].profileImage)

            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setView(binding.root)
            builder.setTitle(dataList[position].userName)
            builder.setNegativeButton("Close") {_,_ ->}.create().show()

        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(user: List<User>) {
        val mainDiffutil = MainDiffutil(dataList, user)
        val mainDiffResult = DiffUtil.calculateDiff(mainDiffutil)
        this.dataList = user
        mainDiffResult.dispatchUpdatesTo(this)
    }
}