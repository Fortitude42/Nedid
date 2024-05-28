package kz.tutorial.nedid.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kz.tutorial.nedid.R
import kz.tutorial.nedid.presentation.extensions.openEmailWithAddress
import kz.tutorial.nedid.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {
    private val vm: UsersViewModel by viewModel()

    lateinit var rvUsers: ViewPager2

    lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        initAdapter()
        initRecycler()
        initObservers()
    }

    private fun initViews(view: View) {
        rvUsers = view.findViewById(R.id.viewPager)
    }

    private fun initAdapter() {
        usersAdapter = UsersAdapter(layoutInflater) { email ->
            context?.openEmailWithAddress(email)
        }
    }

    private fun initRecycler() {
        val currentContext = context ?: return

        rvUsers.adapter = usersAdapter
//        rvUsers.layoutManager = LinearLayoutManager(currentContext)

        val spaceItemDecoration =
            SpaceItemDecoration(verticalSpaceInDp = 4, horizontalSpaceInDp = 16)
        rvUsers.addItemDecoration(spaceItemDecoration)
    }

    private fun initObservers() {
        vm.usersLiveData.observe(viewLifecycleOwner) {
            usersAdapter.setData(it)
        }
    }
}