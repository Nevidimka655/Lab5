package com.nevidimka655.lab5.adapters.holders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nevidimka655.lab5.databinding.ItemGroupBinding
import com.nevidimka655.lab5.entities.StudentGroup

class GroupsHolder(val recyclerBinding: ItemGroupBinding): ViewHolder(recyclerBinding.root) {

    fun bind(studentGroup: StudentGroup) = with(recyclerBinding) {
        root.text = studentGroup.name
    }

}