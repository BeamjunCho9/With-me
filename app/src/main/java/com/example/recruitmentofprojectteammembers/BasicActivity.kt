package com.example.recruitmentofprojectteammembers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmentofprojectteammembers.databinding.ActivityBasicBinding
import data.PostModel

private lateinit var binding: ActivityBasicBinding

class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        binding = ActivityBasicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var postList = arrayListOf<PostModel>()

        // member_id 받아옴
        var usrID = intent.getIntExtra("member_id", 0);

        var searchCont : String

        // 리사이클러뷰 역순 설정
        val manager = LinearLayoutManager(this@BasicActivity)
        manager.reverseLayout = true
        manager.stackFromEnd = true

        // 리사이클러뷰 어댑터 선언
        binding.bsRecyclerPost.layoutManager = manager
        val recyclerAdapter = RecyclerAdapterBS()
        binding.bsRecyclerPost.adapter = recyclerAdapter
        // 리사이클러뷰 아이템 공백 설정 클래스 적용
        binding.bsRecyclerPost.addItemDecoration(recyclerDecoration(40))

        // 게시물 등록시 해당 게시물의 제목 받아오기
        val startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val postTitle = result.data?.getStringExtra("postTitle").toString()
                val postContent = result.data?.getStringExtra("postContent").toString()
                postList.add(PostModel(postTitle, postContent))
                recyclerAdapter.submitList(postList.toList())
            }
        }


        // 내가 쓴 글 버튼 클릭 동작
        binding.bsCheckMyPost.setOnClickListener(){

            val intent = Intent(this, MypostActivity::class.java)
            startActivity(intent)

        }

        // + 버튼 클릭 동작
        binding.bsPostingBtn.setOnClickListener(){

            val intent = Intent(this, PostcontentActivity::class.java)
            intent.putExtra("member_id", usrID)
            startForResult.launch(intent)

        }

        // 검색 버튼 클릭 동작
        binding.bsSearchBtn.setOnClickListener(){

            searchCont = binding.bsSearchEdt.text.toString()
            val intent = Intent(this, SearchResActivity::class.java)
            intent.putExtra("search", searchCont)
            startActivity(intent)

        }

    }

}