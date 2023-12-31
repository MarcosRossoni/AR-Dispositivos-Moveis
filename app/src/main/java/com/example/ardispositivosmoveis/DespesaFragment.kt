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
import kotlin.math.log

class DespesaFragment : Fragment() {

    private var _binding: FragmentDespesaBinding? = null
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

        binding.buttonAdicionar.setOnClickListener {

            configTextValor(binding.valorDespesa.text.toString())
            listaLancamento.add(
                Lancamento(
                    binding.descricaoDespesa.text.toString(),
                    binding.dataDespesa.text.toString(),
                    configTextValor(binding.valorDespesa.text.toString())
                )
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

    private fun configTextValor(valor: String): String {

        val indexOf = valor.indexOf(".")
        val length = valor.length
        val stringBuilder = StringBuilder(valor.replace(".", ","))

        if (indexOf < 0) {
            return "R$ " + stringBuilder.insert(length, ",00").toString()
        }

        val c = valor.substring(indexOf, length)
        if (c.length < 3){
            return "R$ " + stringBuilder.insert(length, "0")
        }

        return "R$ ${valor.replace(".", ",")}"
    }
}