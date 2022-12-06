package com.dionis.imc.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dionis.imc.databinding.ItemPersonDataBinding
import com.dionis.imc.model.PersonData

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.Holder>() {

    lateinit var onLongItemClicked: (PersonData) -> Unit
    lateinit var onItemClicked: (PersonData) -> Unit
    private var personItem = emptyList<PersonData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemPersonDataBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false),
            onItemClicked, onLongItemClicked
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(person: List<PersonData>) {
        this.personItem = person
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(personItem[position])
    }

    override fun getItemCount(): Int {
        return personItem.size
    }

    class Holder(
        private val binding: ItemPersonDataBinding,
        private var onItemClicked: (PersonData) -> Unit,
        private var onLongItemClicked: (PersonData) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var personData: PersonData

        fun bind(personData: PersonData) {
            this.personData = personData



            binding.tvNome.text = personData.nome
            binding.tvAltura.text = personData.altura.toString()
            binding.tvPeso.text = personData.peso.toString()
            binding.tvResultado.text = personData.resultado.toString()
            binding.tvData.text = personData.data

            binding.root.setOnClickListener {
                onItemClicked.invoke(personData)
            }

            binding.root.setOnLongClickListener {
                onLongItemClicked.invoke(personData)
                true
            }

        }


    }
}
