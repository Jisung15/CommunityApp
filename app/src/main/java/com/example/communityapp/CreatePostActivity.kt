package com.example.communityapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.communityapp.databinding.ActivityCreatePostBinding
import com.google.firebase.firestore.FirebaseFirestore

class CreatePostActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCreatePostBinding.inflate(layoutInflater) }
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAddPost.setOnClickListener {
            val profileImage = R.drawable.ic_launcher_background
            val heartImage = R.drawable.ic_launcher_background
            val answerImage = R.drawable.ic_launcher_background
            val idText = binding.etPostTitle.text.toString()
            val content = binding.etPostContent.text.toString()
            val heartCount = "0"
            val answerCount = "0"

            showDialog(profileImage, heartImage, answerImage, idText, content, heartCount, answerCount)
        }
    }

    private fun showDialog(profileImage: Int, heartImage: Int, answerImage: Int, idText: String, content: String, heartCount: String, answerCount: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("게시글을 추가하시겠습니까?")
            .setCancelable(false)
            .setPositiveButton("추가") { dialog, id ->
                val item = Item (profileImage, heartImage, answerImage, idText, content, heartCount, answerCount)
                savePostToFirestore(item)
            }
            .setNegativeButton("취소") { dialog, id ->
                binding.etPostTitle.text.clear()
                binding.etPostContent.text.clear()
                finish()
            }

        builder.create()
        builder.show()
    }

    private fun savePostToFirestore(item: Item) {
        val postRef = db.collection("posts").document(item.idText)
        postRef.set(item)
            .addOnSuccessListener {
                Toast.makeText(this, "게시글을 업로드하였습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent()
                intent.putExtra("newPost", item)
                setResult(RESULT_OK, intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "에러 발생!: $e", Toast.LENGTH_SHORT).show()
            }
    }
}
