package kz.tutorial.nedid.data.network

import GetSubResponse
import kz.tutorial.nedid.data.entity.AlbumRemote
import kz.tutorial.nedid.data.entity.PhotoRemote
import kz.tutorial.nedid.domain.entity.Comment
import kz.tutorial.nedid.domain.entity.Post
import kz.tutorial.nedid.domain.entity.User
import kz.tutorial.nedid.domain.request.AddCommentRequest
import kz.tutorial.nedid.domain.request.AddPostRequest
import kz.tutorial.nedid.domain.request.EditUserRequest
import kz.tutorial.nedid.domain.request.LoginRequest
import kz.tutorial.nedid.domain.request.RegisterRequest
import kz.tutorial.nedid.domain.response.GetPostLikedResponse
import kz.tutorial.nedid.domain.response.GetPostLikesResponse
import kz.tutorial.nedid.domain.response.LoginResponse
import kz.tutorial.nedid.domain.response.GetPostsResponse
import kz.tutorial.nedid.domain.response.GetUserSubscriptionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MainApi {

    @GET("posts")
    suspend fun getPosts(): List<Post>
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): Post
    @GET("posts/user/{id}")
    suspend fun getPostsByUserId(@Path("id") userId: Int): GetPostsResponse

    @GET("posts/recommended_posts")
    suspend fun getRecommendedPostsForUser(@Header("Authorization") token: String?): List<Post>
    @GET("posts/{id}/comments")
    suspend fun getPostComments(@Path("id") postId: Int): List<Comment>
    @GET("posts/{id}/like_count")
    suspend fun getPostLikes(@Path("id") postId: Int): GetPostLikesResponse
    @GET("posts/{id}/liked")
    suspend fun getPostLiked(@Path("id") postId: Int,
                             @Header("Authorization") token: String?): GetPostLikedResponse
    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int): User

    @GET("users/{id}/check_subscription")
    suspend fun checkUserSubscription(@Path("id") userId: Int,
                                      @Header("Authorization") token: String?):GetUserSubscriptionResponse

    @GET("users/{id}/subscribers")
    suspend fun getSubscribers(@Path("id") userId: Int):GetSubResponse

    @GET("users/{id}/subscriptions")
    suspend fun getSubscriptions(@Path("id") userId: Int):GetSubResponse

    @GET("albums")
    suspend fun getAlbums(): List<AlbumRemote>
    @GET("photos")
    suspend fun getAllPhotos(): List<PhotoRemote>
    @GET("users")
    suspend fun getUsers(): List<User>
    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    @POST("users/signup")
    suspend fun register(@Body request: RegisterRequest): Response<Any>

    @POST("users/{id}/subscribe")
    suspend fun subscribe(@Path("id") userId: Int,
                                    @Header("Authorization") token: String?)

    @POST("users/{id}/unsubscribe")
    suspend fun unsubscribe(@Path("id") userId: Int,
                          @Header("Authorization") token: String?)

    @POST("posts/add")
    suspend fun addPost(
        @Header("Authorization") token: String?,
        @Body request: AddPostRequest
    ): Response<Any>
    @POST("posts/{id}/like")
    suspend fun likePost(
        @Path("id") postId: Int,
        @Header("Authorization") token: String?
    ) : Response<Any>
    @POST("posts/{id}/unlike")
    suspend fun unlikePost(
        @Path("id") postId: Int,
        @Header("Authorization") token: String?
    ) : Response<Any>

    @POST("posts/{id}/add_view")
    suspend fun addViewPost(
        @Path("id") postId: Int,
        @Header("Authorization") token: String?
    ) : Response<Any>

    @POST("comments/add")
    suspend fun addComment(@Header("Authorization") token: String?,
                           @Body request: AddCommentRequest): Response<Any>

    @PUT("users/edit_profile")
    suspend fun editUser(
        @Header("Authorization") token: String?,
        @Body request: EditUserRequest
    ): Response<Any>

}