package com.example.ardispositivosmoveis.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ardispositivosmoveis.databinding.LancamentoItemBinding
import com.example.ardispositivosmoveis.model.Lancamento

class AdapterLancamento(private val context: Context, private val listLancamento: MutableList<Lancamento>) :
    RecyclerView.Adapter<AdapterLancamento.LancamentoViewHolder>() {

        class LancamentoViewHolder(binding: LancamentoItemBinding) : RecyclerView.ViewHolder(binding.root) {
            val descricaoLancamento = binding.descricaoLancamento

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LancamentoViewHolder {
        val itemLista = LancamentoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return LancamentoViewHolder(itemLista)
    }

    override fun getItemCount(): Int {
        return listLancamento.size
    }

    override fun onBindViewHolder(holder: LancamentoViewHolder, position: Int) {
        holder.descricaoLancamento.text = listLancamento[position].descricao
    }
}