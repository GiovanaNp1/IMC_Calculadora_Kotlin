package com.natane.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val genderSpinner = arrayListOf("Selecione o sexo","Feminino","Masculino")
        val adapterGender = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, genderSpinner)
        spnGender.adapter = adapterGender

        skbHeight.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                txvHeight.text = "$i"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        skbWeight.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                txvWeight.text = "$i"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(applicationContext,"start tracking",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(applicationContext,"stop tracking",Toast.LENGTH_SHORT).show()
            }
        })


        btnCalc.setOnClickListener {
            var weight = (skbWeight.progress)
            var height = ((skbHeight.progress).toDouble())/100
            var imc = (weight/(height*height)).toInt()

            if (weight > 0 &&  height > 0 && spnGender.selectedItem != "Selecione o sexo" ) {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Resultado")
                builder.setMessage("De acordo com a sua altura : $height e o seu peso : $weight O resultado do seu calculo de IMC é igual à: $imc")
                val dialog: AlertDialog = builder.create()
                dialog.show()
            } else {
                Toast.makeText(this@MainActivity,"Por favor, preencha todos os campos",Toast.LENGTH_LONG).show()
            }
        }


        btnTable.setOnClickListener {
           if (spnGender.selectedItem == "Feminino") {
               startActivity(Intent(this@MainActivity, TabelaFemininoActivity::class.java))
           } else if (spnGender.selectedItem == "Masculino"){
               startActivity(Intent(this@MainActivity, TabelaMasculinoActivity::class.java))
           } else {
               Toast.makeText(this@MainActivity,"Por favor, preencha todos os campos",Toast.LENGTH_LONG).show()
           }

        }
    }
}

