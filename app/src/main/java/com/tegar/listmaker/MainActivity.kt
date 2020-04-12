package com.tegar.listmaker

import android.os.Bundle
import android.text.InputType
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //1
    lateinit var listsRecyclerView: RecyclerView

    val listDataManager: ListDataManager = ListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        //2
//        listsRecyclerView = findViewById(R.id.lists_recyclerview)
//        listsRecyclerView.layoutManager = LinearLayoutManager(this)
//        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter()

        val lists = listDataManager.readList()
        listsRecyclerView = findViewById<RecyclerView>(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists)

        fab.setOnClickListener {
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            showCreateListDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateListDialog() {

        //select title and create string
        val dialogTitle = getString(R.string.name_of_list)
        val addBtnTitle = getString(R.string.create_list)

        //add builder dialog
        val builder = AlertDialog.Builder(this)
        //create text input
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        //set string title and text input to builder dialog
        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        //setAddButton
        builder.setPositiveButton(addBtnTitle) { dialog, _ ->
            // get value string from text input and save it to SharedPreferences
            val list = TaskList(listTitleEditText.text.toString())
            listDataManager.saveList(list)

            // recreate view list
            val recylerAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
            recylerAdapter.addList(list)

            dialog.dismiss()
        }

        //create dialog
        builder.create().show()
    }

}
