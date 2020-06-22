package com.example.primeiroapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.primeiroapp.AppDatabase
import com.example.primeiroapp.BuildConfig
import com.example.primeiroapp.DB.Local
import com.example.primeiroapp.R
import com.google.android.libraries.places.api.Places
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_local.view.*

class MainActivity : AppCompatActivity() {

    private var locals: MutableList<Local> = mutableListOf()

    private var adapter: LocalAdapter = LocalAdapter(locals) {
        Intent(this, LocalDetailActivity::class.java)
            .apply {
                putExtra(LocalDetailActivity.LOCAL_EXTRA, it)
            }.also { startActivity(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Places.initialize(applicationContext, BuildConfig.GOOGLE_API_KEY)

        rvLocals.adapter = adapter
        fabAddLocal.setOnClickListener {
//            AppDatabase.getInstace(this).localDao.insert(
//                Local(
//                    0,
//                    "Add na tora",
//                    -3.0580671,
//                    -60.0106298
//                )
//            )
            startAddLocal()
        }
    }

    companion object {
        const val ADD_LOCAL_REQUEST_CODE = 1
    }

    private fun startAddLocal() {
        Intent(this, AddLocalActivity::class.java).also {
            startActivityForResult(it, ADD_LOCAL_REQUEST_CODE)
        }
    }

    override fun onResume() {
        super.onResume()
        val updated = AppDatabase.getInstace(this).localDao.selectAll()
        updateRecycleView(updated)
    }

    private fun updateRecycleView(updated: List<Local>) {
        locals.clear()
        locals.addAll(updated)
        adapter.notifyDataSetChanged()
        sholdDisplayEmptyView(locals.isEmpty())
    }

    private fun sholdDisplayEmptyView(isEmpty: Boolean) {
        emptyView.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }


    class LocalAdapter(
        private val locals: List<Local>,
        private val onItemClick: (Local) -> Unit
    ) :
        RecyclerView.Adapter<LocalAdapter.LocalViewHolder>() {
        class LocalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
            val intView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_local, parent, false)
            return LocalViewHolder(
                intView
            )
        }

        override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {

            holder.itemView.tvName.text = locals[position].name
            holder.itemView.setOnClickListener { onItemClick(locals[position]) }
        }

        override fun getItemCount() = locals.size

    }

}