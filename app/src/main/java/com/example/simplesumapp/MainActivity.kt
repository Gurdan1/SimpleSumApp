package com.example.simplesumapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simplesumapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    enum class Operation(val description: String) {
        ADD("сложение"),
        SUBTRACT("вычитание")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Обработка кнопки "Сложить"
        binding.btnSum.setOnClickListener {
            performOperation(Operation.ADD)
        }

        // Обработка кнопки "Вычесть"
        binding.btnSubtract.setOnClickListener {
            performOperation(Operation.SUBTRACT)
        }

        // Обработка кнопки "Очистить" (клик)
        binding.btnClear.setOnClickListener {
            clearFields()
            binding.tvOperation.text = "Операция: —"
        }

        // Обработка кнопки "Очистить" (долгий клик)
        binding.btnClear.setOnLongClickListener {
            clearFields()
            binding.tvOperation.text = "Операция: —"
            Toast.makeText(this, "Поля очищены долгим нажатием", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun getNumbers(): Pair<Double, Double>? {
        val str1 = binding.editNumberOne.text.toString()
        val str2 = binding.editNumberTwo.text.toString()

        if (str1.isEmpty() || str2.isEmpty()) {
            Toast.makeText(this, "Заполните оба поля", Toast.LENGTH_SHORT).show()
            binding.tvResult.text = "Ошибка ввода"
            return null
        }

        val num1 = str1.toDoubleOrNull()
        val num2 = str2.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Неверное число", Toast.LENGTH_SHORT).show()
            binding.tvResult.text = "Ошибка формата"
            return null
        }
        return Pair(num1, num2)
    }

    private fun performOperation(op: Operation) {
        val numbers = getNumbers() ?: return
        val (num1, num2) = numbers

        val result = when (op) {
            Operation.ADD -> num1 + num2
            Operation.SUBTRACT -> num1 - num2
        }

        // Обновляем операцию
        binding.tvOperation.text = "Операция: ${op.description}"

        // Выводим результат, без дробной части если целое
        val resultText = if (result == result.toInt().toDouble()) {
            "Результат: ${result.toInt()}"
        } else {
            "Результат: $result"
        }
        binding.tvResult.text = resultText
    }

    private fun clearFields() {
        binding.editNumberOne.text.clear()
        binding.editNumberTwo.text.clear()
        binding.tvResult.text = "Результат: —"
    }
}