package com.nevidimka655.lab5.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.nevidimka655.lab5.adapters.holders.GroupsHolder
import com.nevidimka655.lab5.databinding.ItemGroupBinding
import com.nevidimka655.lab5.entities.StudentGroup

class GroupsAdapter(
    private val list: ArrayList<StudentGroup>,
    private val clickCallback: (pos: Int) -> Unit
) : Adapter<GroupsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GroupsHolder(
        ItemGroupBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).also { holder ->
        holder.recyclerBinding.root
            .setOnClickListener { clickCallback(holder.absoluteAdapterPosition) }
    }

    override fun onBindViewHolder(holder: GroupsHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}