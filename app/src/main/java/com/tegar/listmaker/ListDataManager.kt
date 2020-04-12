package com.tegar.listmaker

import android.content.Context
import android.preference.PreferenceManager

//pass a context to ListDataManager
class ListDataManager(private val context: Context) {

    //to persist the list to sharedPreferences
    fun saveList(list: TaskList) {
        //1 Get a reference to the app's default PreferenceManager, .edit() for put key - value to sharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()

        //2 create key and value for sharedPreference
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())

        //3 write changes to listMaker's SharedPreferences file
        sharedPreferences.apply()
    }

    //read list from SharedPreferences
    fun readList(): ArrayList<TaskList> {
        // 1 get sharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        // 2 get all content of sharedPreferences (a MAP)
        val sharedPreferencesContent = sharedPreferences.all

        val taskLists = ArrayList<TaskList>()

        // 3. iterate list from sharedPreferences
        for (taskList in sharedPreferencesContent) {
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemsHashSet)
            taskLists.add(list)
        }
        // 4. return taskLists
        return taskLists
    }
}