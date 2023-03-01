package ru.samsung.itacademy.mdev.internalstorageexample

import ru.samsung.itacademy.mdev.internalstorageexample.R
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var read: Button? = null
    var write: Button? = null
    var userInput: EditText? = null
    var fileContent: TextView? = null
    private val filename = "file.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        read = findViewById(R.id.read) as Button
        write = findViewById(R.id.write) as Button
        userInput = findViewById(R.id.input)
        fileContent = findViewById(R.id.content)

        read!!.setOnClickListener(this)
        write!!.setOnClickListener(this)
    }

    fun printMessage(m: String?) {
        Toast.makeText(this, m, Toast.LENGTH_LONG).show()
    }

    override fun onClick(view: View) {
        val b: Button = view as Button
        val b_text: String = b.getText().toString()
        when (b_text.toLowerCase(Locale.getDefault())) {
            "write" -> {
                writeData()
            }
            "read" -> {
                readData()
            }
        }
    }

    private fun writeData() {
        try {
            val fos: FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            val data = userInput!!.text.toString()
            fos.write(data.toByteArray())
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        userInput!!.setText("")
        printMessage("writing to file " + filename + "completed...")
    }

    private fun readData() {
        try {
            val fin: FileInputStream = openFileInput(filename)
            var a: Char
            val temp = StringBuilder()
            while (fin.read().also { a = it.toChar() } != -1) {
               temp.append(a)

            }

            fileContent!!.text = temp.toString()
            fin.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        printMessage("reading to file $filename completed..")
    }
}