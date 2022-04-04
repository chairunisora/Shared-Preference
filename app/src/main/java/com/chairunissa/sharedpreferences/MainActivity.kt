package com.chairunissa.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chairunissa.sharedpreferences.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            val id: Int = Integer.parseInt(binding.etInputId.text.toString())
            val name: String = binding.etInputName.text.toString()
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.putInt("key_id", id)
            editor.putString("key_name", name)
            editor.apply()
            Snackbar.make(binding.root, "Data Saved", Snackbar.LENGTH_LONG).show()
//            Toast.makeText(this@MainActivity, "Data Saved", Toast.LENGTH_SHORT).show()
        }

        binding.btnView.setOnClickListener {
            val sharedIdValue = sharedPreference.getInt("key_id", 0)
            val sharedNameValue = sharedPreference.getString("key_name", "defaultname")
            if (sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")) {
                binding.tvShowName.setText("default name: ${sharedNameValue}").toString()
                binding.tvShowId.setText("default id : ${sharedIdValue.toString()}")
                Toast.makeText(this, "Data View Kosong", Toast.LENGTH_SHORT).show()
            } else {
                binding.tvShowName.setText(sharedNameValue).toString()
                binding.tvShowId.setText(sharedIdValue.toString())
                Toast.makeText(this, "Data View Ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            val editor = sharedPreference.edit()
            editor.clear()
            editor.apply()
            binding.tvShowName.setText("")
            binding.tvShowId.setText("")
            Toast.makeText(this, "Data Clear", Toast.LENGTH_SHORT).show()
        }
    }
}