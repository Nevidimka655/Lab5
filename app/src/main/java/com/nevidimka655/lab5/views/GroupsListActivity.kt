package com.nevidimka655.lab5.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nevidimka655.lab5.MainActivity
import com.nevidimka655.lab5.MainVM
import com.nevidimka655.lab5.R
import com.nevidimka655.lab5.adapters.GroupsAdapter
import com.nevidimka655.lab5.databinding.ActivityGroupsListBinding
import com.nevidimka655.lab5.databinding.DialogCreateBinding
import com.nevidimka655.lab5.entities.StudentGroup
import com.nevidimka655.lab5.studentGroups2


class GroupsListActivity : AppCompatActivity() {
    private val vm by viewModels<MainVM>()
    private val binding by lazy { ActivityGroupsListBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupList(binding.list)
        setupFAB(binding.fab)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.groups_menu, menu)
        val text = StringBuilder().apply {
            studentGroups2.forEach {
                appendLine(it.name)
            }
        }.toString()
        val menuItem = menu.findItem(R.id.action_share)
        val shareActionProvider =
            MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/*"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        shareActionProvider.setShareIntent(intent)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_group -> {
                binding.fab.callOnClick()
                super.onOptionsItemSelected(item)
            }
            else -> true
        }
    }

    private fun setupList(list: RecyclerView) = with(list) {
        layoutManager = LinearLayoutManager(this@GroupsListActivity)
        adapter = GroupsAdapter(studentGroups2) { pos ->
            startActivity(
                Intent(this@GroupsListActivity, StudentsGroupActivity::class.java).apply {
                    putExtra(MainActivity.Keys.GROUP_NAME, vm.studentGroups[pos].name)
                }
            )
        }
    }

    private fun setupFAB(fab: FloatingActionButton) = with(fab) {
        val dialogView = DialogCreateBinding.inflate(layoutInflater, null, false)
        val dialog = MaterialAlertDialogBuilder(this@GroupsListActivity)
            .setTitle("Створення групи")
            .setView(dialogView.root)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val name = dialogView.groupNumber.editText!!.text.toString()
                val facultyName = dialogView.facultyName.editText!!.text.toString()
                if (name.isNotBlank() && facultyName.isNotBlank()) StudentGroup(
                    name = name,
                    facultyName = facultyName
                ).run {
                    studentGroups2.add(this)
                    binding.list.adapter?.notifyItemInserted(studentGroups2.size)
                }
            }.setNegativeButton(android.R.string.cancel, null)
            .create()
        setOnClickListener {
            dialog.show()
        }
    }
}