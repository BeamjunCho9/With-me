package network

import data.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    // 회원 가입
    @POST("project/join")
    fun requestSignup(
        // 서버에 보낼 값 정의
        @Body signupData : SignupData
    ) : Call<Signup>   // 서버에서 받아오는 값 정의

    // 로그인
    @POST("project/login")
    fun requestLogin(
        @Body loginData : LoginData
    ) : Call<Login>

    // 게시물 등록
    @POST("project/write")
    fun requestPosting(
        @Body postData: PostData
    ) : Call<Posting>

    // 게시물 수정
    @PUT("project/write/modify/{post_id}")
    fun requestRevise(
        @Body postData: PostUpdateData,
        @Path("post_id", encoded = true) postId : Int
    ) : Call<Posting>

    // 게시물 삭제
    @DELETE("project/write/deletePost/{deletePostId}")
    fun requestDelete(
        @Path("deletePostId", encoded = true) deletePostID : Int
    ) : Call<Posting>

    // 댓글 작성
    @POST("/postReply/")
    fun requestReply(
        @Field("replyContent") replyContent : String
    ) : Call<Reply>




}