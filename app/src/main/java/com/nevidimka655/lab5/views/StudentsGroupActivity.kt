package com.nevidimka655.lab5.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.nevidimka655.lab5.MainActivity
import com.nevidimka655.lab5.MainVM
import com.nevidimka655.lab5.databinding.ActivityStudentsGroup2Binding

class StudentsGroupActivity : AppCompatActivity() {
    private val vm by viewModels<MainVM>()
    private val binding by lazy { ActivityStudentsGroup2Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.extras?.getString(MainActivity.Keys.GROUP_NAME)?.let { grpName ->
            binding.run {
                val groupObj = vm.getGroupByName(grpName)!!
                editGroupName.setText(groupObj.name)
                editTextFaculty.setText(groupObj.facultyName)
                groupFacultyName.text = groupObj.facultyName
                groupName.text = groupObj.name
                (if (groupObj.educationLevel == 0) eduLevelBachelor else eduLevelMaster)
                    .isChecked = true
                contractFlg.isChecked = groupObj.flagContractExists
                privilegeFlg.isChecked = groupObj.flagPrivilegeExists
                ok.setOnClickListener {
                    Toast.makeText(
                        this@StudentsGroupActivity,
                        groupObj.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                studentsListButton.setOnClickListener {
                    startActivity(
                        Intent(
                            this@StudentsGroupActivity,
                            StudentsListActivity::class.java
                        ).apply {
                            putExtra(MainActivity.Keys.GROUP_NAME, grpName)
                        }
                    )
                }
            }
        }
    }
}