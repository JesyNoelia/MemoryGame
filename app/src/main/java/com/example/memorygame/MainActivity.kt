package com.example.memorygame

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.example.memorygame.databinding.ActivityMainBinding
import com.example.memorygame.databinding.CardsImagesBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    var turno:Int = 1
    var puntosP1: Int =0
    var puntosP2: Int =0

    var primeraCarta: Int = 0
    var segundaCarta: Int =0
    var primerClick: Int= 0
    var segundoClick: Int=0
    var numeroCarta: Int = 1

    var cartas = ArrayList<Int>(listOf(11,12,13,14,15,21,22,23,24,25))

    var image11: Int=0
    var image12: Int=0
    var image13: Int=0
    var image14: Int=0
    var image15: Int=0

    var image21: Int=0
    var image22: Int=0
    var image23: Int=0
    var image24: Int=0
    var image25: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //1- setUp UI
        setUpUi()

        //2- Cargar las cartas
        cargarCartas()

        //3- Barajar las cartas (ya existe una libreria que baraja las cartas)
        cartas.shuffle()

        //4- setUp OnClickListener
        setUpOnClickListener()
    }




    private fun setUpUi(){
        binding.maTvPlayer1.setTextColor(Color.GREEN)
        binding.maTvPlayer1.setTypeface(null, Typeface.BOLD)

        binding.maTvPlayer2.setTextColor(Color.GRAY)
        binding.maTvPlayer2.setTypeface(null, Typeface.NORMAL)
    }

    private fun cargarCartas(){
        image11= R.drawable.ic_bike
        image12= R.drawable.ic_car
        image13= R.drawable.ic_boat
        image14= R.drawable.ic_plane
        image15= R.drawable.ic_bus

        image21= R.drawable.ic_bike
        image22= R.drawable.ic_car
        image23= R.drawable.ic_boat
        image24= R.drawable.ic_plane
        image25= R.drawable.ic_bus
    }


    private fun setUpOnClickListener(){
        binding.icludeId.im11.setOnClickListener(){
        var carta: Int = 0
        asignarImagenalaCarta(binding.icludeId.im11, carta)
        }

        binding.icludeId.im12.setOnClickListener(){
            var carta: Int = 1
            asignarImagenalaCarta(binding.icludeId.im12, carta)
        }

        binding.icludeId.im13.setOnClickListener(){
            var carta: Int = 2
            asignarImagenalaCarta(binding.icludeId.im13, carta)
        }

        binding.icludeId.im21.setOnClickListener(){
            var carta: Int = 3
            asignarImagenalaCarta(binding.icludeId.im21, carta)
        }

        binding.icludeId.im22.setOnClickListener(){
            var carta: Int = 4
            asignarImagenalaCarta(binding.icludeId.im22, carta)
        }

        binding.icludeId.im23.setOnClickListener(){
            var carta: Int = 5
            asignarImagenalaCarta(binding.icludeId.im23, carta)
        }

        binding.icludeId.im31.setOnClickListener(){
            var carta: Int = 6
            asignarImagenalaCarta(binding.icludeId.im31, carta)
        }

        binding.icludeId.im32.setOnClickListener(){
            var carta: Int = 7
            asignarImagenalaCarta(binding.icludeId.im32, carta)
        }

        binding.icludeId.im33.setOnClickListener(){
            var carta: Int = 8
            asignarImagenalaCarta(binding.icludeId.im33, carta)
        }

        binding.icludeId.im41.setOnClickListener(){
            var carta: Int = 9
            asignarImagenalaCarta(binding.icludeId.im41, carta)
        }
    }

    private fun asignarImagenalaCarta(image: ImageView, carta: Int) {
        when(cartas[carta]){
            11 -> image.setImageResource(image11)
            12 -> image.setImageResource(image12)
            13 -> image.setImageResource(image13)
            14 -> image.setImageResource(image14)
            15 -> image.setImageResource(image15)

            21 -> image.setImageResource(image21)
            22 -> image.setImageResource(image22)
            23 -> image.setImageResource(image23)
            24 -> image.setImageResource(image24)
            25 -> image.setImageResource(image25)
        }

        if(numeroCarta == 1){
            primeraCarta = cartas[carta]
            if(primeraCarta>20){
                primeraCarta -= 10
            }
            numeroCarta=2
            primerClick = carta
            //esto hace que solo sea clicklabe una sola vez
            image.isEnabled = false
        }else if(numeroCarta == 2){
            segundaCarta = cartas[carta]
            if(segundaCarta>20){
                segundaCarta -= 10
            }
            numeroCarta=1
            segundoClick = carta

            binding.icludeId.im11.isEnabled = false
            binding.icludeId.im12.isEnabled = false
            binding.icludeId.im13.isEnabled = false
            binding.icludeId.im21.isEnabled = false
            binding.icludeId.im22.isEnabled = false
            binding.icludeId.im23.isEnabled = false
            binding.icludeId.im31.isEnabled = false
            binding.icludeId.im32.isEnabled = false
            binding.icludeId.im33.isEnabled = false
            binding.icludeId.im41.isEnabled = false

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                comprobarCorrecto()
            }, 1000)
        }
    }

    private fun comprobarCorrecto() {
        if(primeraCarta == segundaCarta){
            when(primerClick){
                //si son iguales las cartas las hace invisibles
                0-> binding.icludeId.im11.visibility = View.INVISIBLE
                1-> binding.icludeId.im12.visibility = View.INVISIBLE
                2-> binding.icludeId.im13.visibility = View.INVISIBLE
                3-> binding.icludeId.im21.visibility = View.INVISIBLE
                4-> binding.icludeId.im22.visibility = View.INVISIBLE
                5-> binding.icludeId.im23.visibility = View.INVISIBLE
                6-> binding.icludeId.im31.visibility = View.INVISIBLE
                7-> binding.icludeId.im32.visibility = View.INVISIBLE
                8-> binding.icludeId.im33.visibility = View.INVISIBLE
                9-> binding.icludeId.im41.visibility = View.INVISIBLE


            }
            when(segundoClick){
                0-> binding.icludeId.im11.visibility = View.INVISIBLE
                1-> binding.icludeId.im12.visibility = View.INVISIBLE
                2-> binding.icludeId.im13.visibility = View.INVISIBLE
                3-> binding.icludeId.im21.visibility = View.INVISIBLE
                4-> binding.icludeId.im22.visibility = View.INVISIBLE
                5-> binding.icludeId.im23.visibility = View.INVISIBLE
                6-> binding.icludeId.im31.visibility = View.INVISIBLE
                7-> binding.icludeId.im32.visibility = View.INVISIBLE
                8-> binding.icludeId.im33.visibility = View.INVISIBLE
                9-> binding.icludeId.im41.visibility = View.INVISIBLE
            }

            if(turno == 1){
                puntosP1++
                binding.maTvPlayer1.setText("Player 1: "+ puntosP1)
            }else{
                puntosP2++
                binding.maTvPlayer2.setText("Player 2: " + puntosP2)
            }
        }else{
            //no ha acertado, las cartas no son iguales
            binding.icludeId.im11.setImageResource(R.drawable.ic_box)
            binding.icludeId.im12.setImageResource(R.drawable.ic_box)
            binding.icludeId.im13.setImageResource(R.drawable.ic_box)
            binding.icludeId.im21.setImageResource(R.drawable.ic_box)
            binding.icludeId.im22.setImageResource(R.drawable.ic_box)
            binding.icludeId.im23.setImageResource(R.drawable.ic_box)
            binding.icludeId.im31.setImageResource(R.drawable.ic_box)
            binding.icludeId.im32.setImageResource(R.drawable.ic_box)
            binding.icludeId.im33.setImageResource(R.drawable.ic_box)
            binding.icludeId.im41.setImageResource(R.drawable.ic_box)

            if(turno == 1){
                turno = 2
                binding.maTvPlayer1.setTextColor(Color.GRAY)
                binding.maTvPlayer1.setTypeface(null, Typeface.NORMAL)

                binding.maTvPlayer2.setTextColor(Color.RED)
                binding.maTvPlayer2.setTypeface(null, Typeface.BOLD)
            }else{
                turno = 1
                binding.maTvPlayer1.setTextColor(Color.GREEN)
                binding.maTvPlayer1.setTypeface(null, Typeface.BOLD)

                binding.maTvPlayer2.setTextColor(Color.GRAY)
                binding.maTvPlayer2.setTypeface(null, Typeface.NORMAL)
            }
        }
        binding.icludeId.im11.isEnabled = true
        binding.icludeId.im12.isEnabled = true
        binding.icludeId.im13.isEnabled = true
        binding.icludeId.im21.isEnabled = true
        binding.icludeId.im22.isEnabled = true
        binding.icludeId.im23.isEnabled = true
        binding.icludeId.im31.isEnabled = true
        binding.icludeId.im32.isEnabled = true
        binding.icludeId.im33.isEnabled = true
        binding.icludeId.im41.isEnabled = true

        finPartida()
    }

    private fun finPartida() {
        //cuando todas las cartas estan invisibles es xq termino la partida
        if(binding.icludeId.im11.visibility == View.INVISIBLE &&
            binding.icludeId.im12.visibility == View.INVISIBLE &&
            binding.icludeId.im13.visibility == View.INVISIBLE &&
            binding.icludeId.im21.visibility == View.INVISIBLE &&
            binding.icludeId.im22.visibility == View.INVISIBLE &&
            binding.icludeId.im23.visibility == View.INVISIBLE &&
            binding.icludeId.im31.visibility == View.INVISIBLE &&
            binding.icludeId.im32.visibility == View.INVISIBLE &&
            binding.icludeId.im33.visibility == View.INVISIBLE &&
            binding.icludeId.im41.visibility == View.INVISIBLE){

            var alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle("Fin de la Partida")
            alertDialog.setMessage("Player 1: " + puntosP1 + "\nPlayer 2: " + puntosP2)
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK"){
                dialogInterface, i ->
                var intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            alertDialog.show()
        }
    }
}