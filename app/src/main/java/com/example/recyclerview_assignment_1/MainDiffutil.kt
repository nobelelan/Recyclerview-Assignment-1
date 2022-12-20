package com.example.recyclerview_assignment_1

import androidx.recyclerview.widget.DiffUtil

class MainDiffutil(
    private val oldList: List<User>,
    private val newList: List<User>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].userName == newList[newItemPosition].userName
                && oldList[oldItemPosition].phoneNumber == newList[newItemPosition].phoneNumber
                && oldList[oldItemPosition].villageName == newList[newItemPosition].postOfficeName
                && oldList[oldItemPosition].districtName == newList[newItemPosition].districtName
                && oldList[oldItemPosition].divisionName == newList[newItemPosition].divisionName
                && oldList[oldItemPosition].countryName == newList[newItemPosition].countryName
    }
}