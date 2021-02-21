package com.example.task.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domian.interactors.ActivityListInteractor

import com.example.entity.Result
import com.example.entity.localmodels.ActivityItem
import com.example.task.base.viewmodel.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class ActivitiesListViewModel(
    private val activityListInteractor: ActivityListInteractor
) : BaseViewModel() {

    private val _getChatDataList by lazy { MutableLiveData<MutableList<ActivityItem>>() }
    val getChatDataList: LiveData<MutableList<ActivityItem>> = _getChatDataList
    private val _loadingData by lazy { MutableLiveData<Boolean>() }
    val loadingData: LiveData<Boolean> = _loadingData
    private val _errorNullData by lazy { MutableLiveData<String>() }
    val errorNullData get() = _errorNullData
    private val _notMoreItem by lazy { MutableLiveData<Unit>() }
    val notMoreItem get() = _notMoreItem
    private val _dbDataIsEmpty by lazy { MutableLiveData<Boolean>() }
    val dbDataIsEmpty get() = _dbDataIsEmpty

    fun loadDataDb() {
        activityListInteractor.getActivityFromDb()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { responseList ->
                    _dbDataIsEmpty.value = responseList.isEmpty()
                    _getChatDataList.value=responseList.toMutableList()

                },
                {
                    _errorNullData.value = it.message
                    _loadingData.value = false
                }
            )
    }
    fun getActivityList() {
        _loadingData.value = true
        activityListInteractor.getActivityResponse()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { responseList ->
                    if (responseList.isEmpty()) {
                        _notMoreItem.value=Unit
                        _loadingData.value = false
                    } else {
                        val tempList = mutableListOf<ActivityItem>()
                        val list = _getChatDataList.value
                        list?.let {
                            tempList.addAll(_getChatDataList.value!!)
                            tempList.addAll(responseList)
                            _getChatDataList.value = tempList
                        } ?: run { _getChatDataList.value = responseList.toMutableList() }
                    }
                    _loadingData.value = false

                },
                {
                    _errorNullData.value = it.message
                    _loadingData.value = false
                }
            )

    }
}

