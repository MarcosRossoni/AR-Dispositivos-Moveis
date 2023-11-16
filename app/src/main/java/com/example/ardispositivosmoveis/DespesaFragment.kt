package com.example.ardispositivosmoveis

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ardispositivosmoveis.adapter.AdapterLancamento
import com.example.ardispositivosmoveis.databinding.FragmentDespesaBinding
import com.example.ardispositivosmoveis.model.Lancamento

class DespesaFragment : Fragment() {

    private var _binding: FragmentDespesaBinding? = null
    private val TAG = "MyActivity"
    private lateinit var adapterLancamento: AdapterLancamento
    private val listaLancamento: MutableList<Lancamento> = mutableListOf()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDespesaBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerviewLancamentos = binding.recyclerviewDespesa.recyclerviewLancamentos
        recyclerviewLancamentos.layoutManager = LinearLayoutManager(requireContext())
        recyclerviewLancamentos.setHasFixedSize(true)
        adapterLancamento = AdapterLancamento(requireContext(), listaLancamento)
        recyclerviewLancamentos.adapter = adapterLancamento
        itemLista()

        val toString = binding.descricaoDespesa.text
        val dataLancamento = binding.dataDespesa.text
        var text = binding.valorDespesa.text
        binding.buttonAdicionar.setOnClickListener {

            listaLancamento.add(
                Lancamento(
                binding.descricaoDespesa.text.toString(),
                binding.dataDespesa.text.toString(),
                binding.valorDespesa.text.toString())
            )

            adapterLancamento.notifyDataSetChanged()
            binding.descricaoDespesa.setText("")
            binding.dataDespesa.setText("")
            binding.valorDespesa.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun itemLista(){
        val lancamento = Lancamento("Teste", "11/10/2023", "R$100,00")
        listaLancamento.add(lancamento)
        val lancamento1 = Lancamento("Teste1", "11/10/2023", "R$100,00")
        listaLancamento.add(lancamento1)
        val lancamento2 = Lancamento("Teste2", "11/10/2023", "R$100,00")
        listaLancamento.add(lancamento2)
        val lancamento3 = Lancamento("Teste3", "11/10/2023", "R$100,00")
        listaLancamento.add(lancamento3)
    }
}