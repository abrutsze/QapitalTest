package com.example.task.fragment.home

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task.MainActivity
import com.example.task.base.FragmentBaseMVVM
import com.example.task.base.utils.viewBinding
import com.example.task.databinding.FragmentActivityBinding
import com.example.task.utils.gone
import com.example.task.utils.hasNetwork
import com.example.task.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel


class ActivitiesListFragment :
    FragmentBaseMVVM<ActivitiesListViewModel, FragmentActivityBinding>() {
    override val viewModel: ActivitiesListViewModel by viewModel()
    override val binding: FragmentActivityBinding by viewBinding()
    private var imagesAdapter = ActivitiesListAdapter()
    var isLoading = false
    override fun initView() {
        with(binding) {
            rvData.apply {
                context?.let {
                    layoutManager =
                        LinearLayoutManager(it)
                    setHasFixedSize(true)
                    adapter = imagesAdapter
                }
            }
            rvData.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    if (!isLoading) {
                        if (linearLayoutManager != null &&
                            linearLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.getChatDataList.value?.size?.minus(
                                1
                            )
                        ) {
                            viewModel.getActivityList()
                            isLoading = true
                        }
                    }
                }
            })
        }
        loadData()
    }
    private fun loadData(){
        context?.let {
            if (it.hasNetwork()) {
                viewModel.getActivityList()
            } else {
                viewModel.loadDataDb()
                Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun initViewClickListeners() {
        binding.emptyData.setOnClickListener {
            loadData()
        }
    }
    override fun observes() {
        observe(viewModel.getChatDataList) {
            imagesAdapter.submitList(it)
            isLoading = false
        }
        observe(viewModel.errorNullData) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        observe(viewModel.notMoreItem) {
            Toast.makeText(context, "Load more data is empty", Toast.LENGTH_SHORT).show()
            isLoading = false
        }
        observe(viewModel.dbDataIsEmpty) {
            if(it){
                binding.emptyData.visible()
            }else{
                binding.emptyData.gone()
            }
        }
        observe(viewModel.loadingData) {
            if (it)
                binding.vLoadingData.visibility = View.VISIBLE
            else
                binding.vLoadingData.visibility = View.GONE
        }
    }

    override fun navigateUp() {
        (activity as MainActivity).finish()
    }
}