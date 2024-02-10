package kz.tutorial.jsonplaceholdertypicode.presentation.post_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kz.tutorial.jsonplaceholdertypicode.R
import kz.tutorial.jsonplaceholdertypicode.constants.POST_ID_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostDetailsFragment : Fragment() {

    companion object {
        fun newInstance(postId: Int): PostDetailsFragment {
            val args = Bundle()
            args.putInt(POST_ID_KEY, postId)
            val fragment = PostDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val vm: PostDetailsViewModel by viewModel {
        parametersOf(arguments?.getInt(POST_ID_KEY, 0))
    }

    lateinit var rvComments: RecyclerView
    lateinit var tvTitle: TextView
    lateinit var tvAuthor: TextView
    lateinit var tvBody: TextView
    lateinit var tvShowAll: TextView

//    lateinit var adapter: PostAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        initAdapter()
        initRecycler()
        initObservers()
    }

    private fun initViews(view: View) {
        rvComments = view.findViewById(R.id.rv_comments)
        tvTitle = view.findViewById(R.id.tv_title)
        tvAuthor = view.findViewById(R.id.tv_author)
        tvBody = view.findViewById(R.id.tv_body)
        tvShowAll = view.findViewById(R.id.tv_show_all)
    }

    private fun initAdapter() {
//        adapter = PostAdapter(layoutInflater)
//        adapter.listener = ClickListener {
//            Timber.e("Post clicked = $it")
//        }
    }

    private fun initRecycler() {
//        val currentContext = context ?: return
//
//        rvPosts.adapter = adapter
//        rvPosts.layoutManager = LinearLayoutManager(currentContext)
//
//        val spaceItemDecoration = SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
//        rvPosts.addItemDecoration(spaceItemDecoration)
    }

    private fun initObservers() {
        vm.postDetailsLiveData.observe(viewLifecycleOwner) { post ->
            tvTitle.text = post.title
            tvBody.text = post.body
        }
        vm.userLiveData.observe(viewLifecycleOwner) { user ->
            tvAuthor.text = user.name
        }
    }
}