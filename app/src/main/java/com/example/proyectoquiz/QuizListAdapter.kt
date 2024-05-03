package com.example.proyectoquiz

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoquiz. databinding.QuizItemBinding



class QuizListAdapter(private val modeloQuizlist : List<Modeloquiz>) :
    RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {

    class  MyViewHolder(private val binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model : Modeloquiz){
            binding.apply {
                quizTitleText.text = model.title
                quizSubtitleText.text = model.subtitle
                quizTimeText.text = model.time + "min"
                root.setOnClickListener {
                    val intent  = Intent(root.context,QuizActivity::class.java )
                    QuizActivity.questionModelList = model.questionList
                    QuizActivity.time = model.time
                    root.context.startActivity(intent)
                }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = QuizItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MyViewHolder(binding)
    }



    override fun getItemCount(): Int {
        return modeloQuizlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(modeloQuizlist[position])
    }
}