package com.tegar.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter(private val lists: ArrayList<TaskList>) : RecyclerView.Adapter<ListSelectionViewHolder>() {

    val listTitles = arrayOf("Kotlin", "Java", "Golang", "Ruby", "Python", "Javascript", "C", "C++", "Scala", "Pascal", "Objective C", "")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {

        //LayoutFilter = untuk membuat layout. (sebuah utilitas sistem yg digunakan untuk menginisialisasi sebuah layout file XML kepada object view yg sesuai)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent, false) //mencoba u/ mengembangkan layout yg diinginkan dgn menset nama layout dan parent viewgrouop sehingga view dapat merujuk ke parent viewgroup
        //attachToRoot => menemtukan apakah view dilampirkan kepada parent(ViewGroup)
        //             => dipass false karena layoutnya dilampirkan pada RecyclerView

        return ListSelectionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position + 1).toString()
//        holder.listTitle.text = listTitles[position]
        holder.listTitle.text = lists.get(position).name
    }

    fun addList(list: TaskList) {
        lists.add(list)

        //to inform the adapter that updated the data source and update the recyclerview
        notifyItemInserted(lists.size-1)
    }

}