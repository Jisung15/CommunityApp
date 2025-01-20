package com.example.communityapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.communityapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val itemList = mutableListOf<Item>()
    private lateinit var adapter : ItemListAdapter
    private val db = FirebaseFirestore.getInstance()

    private val start: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val newPost = result.data?.getParcelableExtra<Item>("newPost")
            newPost?.let {
                itemList.add(it)
                adapter.submitList(itemList.toList())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

            adapter = ItemListAdapter(itemList) { item ->
                deletePost(item)
            }
            recyclerView.adapter = adapter

            floatingButton.setOnClickListener {
                start.launch(Intent(this@MainActivity, CreatePostActivity::class.java))
            }

            fetchPosts()
        }
    }

    private fun fetchPosts() {
        db.collection("posts")
            .get()
            .addOnSuccessListener { result ->
                itemList.clear()
                for (document in result) {
                    val post = document.toObject(Item::class.java)
                    itemList.add(post)
                }
                adapter.submitList(itemList.toList())
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "에러 발생! : $exception", Toast.LENGTH_SHORT).show()
            }
    }

    private fun deletePost(item: Item) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("정말 ${item.idText} 님의 이 게시글을 삭제하시겠습니까?")
            .setCancelable(false)
            .setPositiveButton("삭제") { dialog, id ->
                db.collection("posts")
                    .document(item.idText)
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(this, "게시글이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                        itemList.remove(item)
                        adapter.submitList(itemList.toList())
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this, "에러 발생!: $exception", Toast.LENGTH_SHORT).show()
                    }
            }
            .setNegativeButton("취소") { dialog, id ->
                dialog.dismiss()
            }

        builder.create()
        builder.show()
    }
}