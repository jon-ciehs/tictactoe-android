package android.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.tictactoe.databinding.ActivityMainBinding
import android.widget.Button
import android.widget.GridLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGrid.setOnClickListener(::onCellClicked) // Asignamos comportamiento a los botones

        //modelo = Tablero() // Instanciamos el modelo en el momento en que se lanza la activity
    }

    private fun onCellClicked(button: Button?) {

    }

    /** Función de extensión que recibe como parámetro una lambda
     * Se exitende GridLayout para que tenga este método, el cual recorre todos los
     * Button que la componen (asunción) y para cada uno de ellos le asigna la función
     * pasada a su OnClickListener */
    private fun GridLayout.setOnClickListener(onClick: (Button) -> Unit ) {
        for (i in 0 until childCount) {
            val boton = getChildAt(i) as Button
            boton.setOnClickListener {
                onClick(boton)
            }
        }
    }
}