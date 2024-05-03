package com.example.proyectoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoquiz.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var modeloQuizlist : MutableList<Modeloquiz>
    lateinit var adaptor: QuizListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        modeloQuizlist = mutableListOf()
        getDataFromFirebase()
    }

    private fun setupRecyclerView(){
        binding.progressBar.visibility = View.GONE
        adapter = QuizListAdapter(modeloQuizlist)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun getDataFromFirebase(){

        binding.progressBar.visibility = View.VISIBLE

        FirebaseDatabase.getInstance().reference
            .get()
            .addOnSuccessListener { dataSnapshop->
                if (dataSnapshop.exists()){
                    for (snapshop in dataSnapshop.children){
                        val quizModel = snapshop.getValue(Modeloquiz::class.java)
                        if (quizModel != null) {
                            modeloQuizlist.add(quizModel)
                        }

                    }

                }
                setupRecyclerView()
            }




    }
}