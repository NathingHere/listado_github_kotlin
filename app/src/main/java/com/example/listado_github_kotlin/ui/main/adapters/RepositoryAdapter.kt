package com.example.listado_github_kotlin.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Repository
import com.example.listado_github_kotlin.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class RepositoryAdapter(var repositories: List<Repository> = listOf(), val layoutInflater: LayoutInflater,  private val onClick:(Repository) -> Unit): RecyclerView.Adapter<RepositoryAdapter.RepositoryHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryAdapter.RepositoryHolder {

        return RepositoryHolder(ItemListBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: RepositoryAdapter.RepositoryHolder, position: Int) {
        holder.bindRepository(repositories[position])
    }

    fun setLista (lista:List<Repository>) {
        repositories = lista
        notifyDataSetChanged()
    }

    inner class RepositoryHolder(private val itemListView:ItemListBinding):RecyclerView.ViewHolder(itemListView.root) {
        fun bindRepository(repository:Repository) {
            with(itemListView) {
                tvName.text = repository.name
                tvDescription.text = repository.description
                tvId.text = repository.id.toString()
                Picasso.get().load(repository.owner.avatarUrl).into(imageApp)

                root.setOnClickListener {
                    onClick.invoke(repository)
                }

            }
        }
    }

}