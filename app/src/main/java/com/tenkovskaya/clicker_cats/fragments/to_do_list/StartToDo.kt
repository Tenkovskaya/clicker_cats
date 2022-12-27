package com.tenkovskaya.clicker_cats.fragments.to_do_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tenkovskaya.clicker_cats.databinding.FragmentNewTaskBinding


class StartToDo : AppCompatActivity() {
    class MainActivity : AppCompatActivity() {
        private lateinit var binding: FragmentNewTaskBinding
        private lateinit var taskViewModel: TaskViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = FragmentNewTaskBinding.inflate(layoutInflater)
            setContentView(binding.root)
            taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
            binding.newTaskButton.setOnClickListener {
                NewTask().show(supportFragmentManager, "newTaskTag")
            }

            taskViewModel.name.observe(this) {
                binding.taskName.text = String.format("Task Name: %s", it)
            }
            taskViewModel.desc.observe(this) {
                binding.taskDesc.text = String.format("Task Desc: %s", it)
            }
        }
    }
}
