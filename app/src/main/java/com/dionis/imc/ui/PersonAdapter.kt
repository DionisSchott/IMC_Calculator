package com.dionis.imc.ui

import android.annotation.SuppressLint
import android.app.Person
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dionis.imc.databinding.ItemPersonDataBinding
import com.dionis.imc.model.PersonData

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.Holder>() {

    private var personItem = emptyList<PersonData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemPersonDataBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false), )
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
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var personData: PersonData

        fun bind(personData: PersonData) {
            this.personData = personData


            binding.tvId.text = personData.id.toString()
            binding.tvNome.text = personData.nome
            binding.tvAltura.text = personData.altura.toString()
            binding.tvPeso.text = personData.peso.toString()
            binding.tvResultado.text = personData.resultado.toString()



        }

    }


}
