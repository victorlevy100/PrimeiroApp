package com.example.primeiroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_local.view.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var locals: List<Local> = listOf(
            Local(0, "olha", -3.1028263, -60.8147652),
            Local(1, "olha2", -3.1028, -60.814),
            Local(1, "olha3", -3.1028, -60.814),
            Local(1, "olha4", -3.1028, -60.814),
            Local(1, "olha5", -3.1028, -60.814)
        )
        rvLocals.adapter = LocalAdapter(locals) {
            val intent = Intent(this, LocalDetailActivity::class.java)
                .apply {
                    putExtra(LocalDetailActivity.LOCAL_EXTRA, it)
                }
            startActivity(intent)

        }
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
            return LocalViewHolder(intView)
        }

        override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {

            holder.itemView.tvName.text = locals[position].name
            holder.itemView.setOnClickListener { onItemClick(locals[position]) }
        }

        override fun getItemCount() = locals.size

    }

}