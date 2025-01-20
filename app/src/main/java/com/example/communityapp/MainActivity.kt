package com.example.communityapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.communityapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val itemListAdapter by lazy { ItemListAdapter() }
    private val createResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val title = result.data?.getStringExtra("POST_TITLE") ?: ""
            val content = result.data?.getStringExtra("POST_CONTENT") ?: ""

            if (title.isNotBlank() && content.isNotBlank()) {
                val newItem = Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, title, content, "13", "21")
                val currentList = itemListAdapter.currentList.toMutableList()
                currentList.add(newItem)
                itemListAdapter.submitList(currentList)
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
            recyclerView.adapter = itemListAdapter
            recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

            floatingButton.setOnClickListener {
                val intent = Intent(this@MainActivity, CreatePostActivity::class.java)
                createResult.launch(intent)
            }
        }

        val item = listOf(
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21"),
            Item(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, "id1", "대충 내용 하나", "13", "21")
        )

        itemListAdapter.submitList(item)

        itemListAdapter.setOnItemLongClickListener { position ->
            val itemPosition = itemListAdapter.currentList[position]
            showDeleteDialog(itemPosition, position)
        }
    }

    private fun showDeleteDialog(item: Item, position: Int) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("게시글 삭제")
            .setMessage("게시글 ${item.idText}을(를) 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                val currentList = itemListAdapter.currentList.toMutableList()
                currentList.removeAt(position)
                itemListAdapter.submitList(currentList)
                Toast.makeText(this, "게시글이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("취소", null)
            .create()

        dialog.show()
    }
}