package com.nevidimka655.lab5.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevidimka655.lab5.MainActivity
import com.nevidimka655.lab5.MainVM
import com.nevidimka655.lab5.R
import com.nevidimka655.lab5.adapters.GroupsAdapter
import com.nevidimka655.lab5.databinding.ActivityStudentsListBinding
import com.nevidimka655.lab5.entities.StudentGroup

class StudentsListActivity : AppCompatActivity() {
    private val vm by viewModels<MainVM>()
    private val binding by lazy { ActivityStudentsListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var groupNameSave = ""
        intent.extras?.getString(MainActivity.Keys.GROUP_NAME)?.let { groupName ->
            groupNameSave = groupName
            val converted = vm.searchStudentsByGroupName(groupName).map {
                StudentGroup(name = it.name)
            } as ArrayList
            setupList(binding.list, converted)
        }
        binding.sendMsg.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, "fff@gmail.com")
                putExtra(Intent.EXTRA_TEXT, StringBuilder().apply {
                    vm.searchStudentsByGroupName(groupNameSave).forEach {
                        appendLine(it.name)
                    }
                }.toString())
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.studentsList))
            }
            startActivity(intent)
        }
    }

    private fun setupList(list: RecyclerView, students: ArrayList<StudentGroup>) = with(list) {
        layoutManager = LinearLayoutManager(this@StudentsListActivity)
        adapter = GroupsAdapter(students) { }
    }
}