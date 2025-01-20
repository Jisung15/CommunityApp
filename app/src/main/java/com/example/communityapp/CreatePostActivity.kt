package com.example.communityapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.communityapp.databinding.ActivityCreatePostBinding

class CreatePostActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCreatePostBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAddPost.setOnClickListener {
            val title = binding.etPostTitle.text.toString()
            val content = binding.etPostContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                showConfirmationDialog(title, content)
            } else {
                Toast.makeText(this, "제목과 내용을 입력 후 추가 해야 합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showConfirmationDialog(title: String, content: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("게시글을 추가하시겠습니까?")
            .setCancelable(false)
            .setPositiveButton("추가") {_, _ ->
                val result = Intent().apply {
                    putExtra("POST_TITLE", title)
                    putExtra("POST_CONTENT", content)
                }
                setResult(RESULT_OK, result)
                finish()
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }

        dialogBuilder.create().show()
    }
}