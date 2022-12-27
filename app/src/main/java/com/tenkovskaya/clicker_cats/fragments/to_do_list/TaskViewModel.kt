package com.tenkovskaya.clicker_cats.fragments.to_do_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel()
{
    var name = MutableLiveData<String>()
    var desc = MutableLiveData<String>()
}