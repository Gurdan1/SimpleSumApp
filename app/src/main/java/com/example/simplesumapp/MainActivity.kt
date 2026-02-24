package com.example.simplesumapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.simplesumapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    // Общий метод для получения чисел с проверкой
    private fun getNumbers(): Pair<Double, Double>? {
        val firstStr = binding.editNumberOne.text.toString()
        val secondStr = binding.editNumberTwo.text.toString()

        if (firstStr.isEmpty() || secondStr.isEmpty()) {
            Toast.makeText(this, "Заполните оба поля", Toast.LENGTH_SHORT).show()
            binding.tvResult.text = "Ошибка ввода"
            return null
        }

        val num1 = firstStr.toDoubleOrNull()
        val num2 = secondStr.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Неверное число", Toast.LENGTH_SHORT).show()
            binding.tvResult.text = "Ошибка формата"
            return null
        }
        return Pair(num1, num2)
    }

    // Очистка полей и сброс результата
    private fun clearFields() {
        binding.editNumberOne.text.clear()
        binding.editNumberTwo.text.clear()
        binding.tvResult.text = "Результат: —"
    }

    // Обновление строки операции
    private fun updateOperationDisplay(text: String) {
        binding.tvOperation.text = "Операция: $text"
    }
}