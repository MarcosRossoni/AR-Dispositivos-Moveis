package com.example.ardispositivosmoveis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ardispositivosmoveis.adapter.AdapterLancamento
import com.example.ardispositivosmoveis.databinding.FragmentDespesaBinding
import com.example.ardispositivosmoveis.databinding.FragmentReceitasBinding
import com.example.ardispositivosmoveis.model.Lancamento

class ReceitasFragment : Fragment() {

    private var _binding: FragmentReceitasBinding? = null
    private lateinit var adapterLancamento: AdapterLancamento
    private val listaLancamento: MutableList<Lancamento> = mutableListOf()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReceitasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerviewLancamentos = binding.recyclerviewReceita.recyclerviewLancamentos
        recyclerviewLancamentos.layoutManager = LinearLayoutManager(requireContext())
        recyclerviewLancamentos.setHasFixedSize(true)
        adapterLancamento = AdapterLancamento(requireContext(), listaLancamento)
        recyclerviewLancamentos.adapter = adapterLancamento

        binding.buttonAdicionar.setOnClickListener {

            configTextValor(binding.valorReceita.text.toString())
            listaLancamento.add(
                Lancamento(
                    binding.descricaoReceita.text.toString(),
                    binding.dataReceita.text.toString(),
                    configTextValor(binding.valorReceita.text.toString())
                )
            )

            adapterLancamento.notifyDataSetChanged()
            binding.descricaoReceita.setText("")
            binding.dataReceita.setText("")
            binding.valorReceita.setText("")
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