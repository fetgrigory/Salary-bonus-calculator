package com.example.salarybonuscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.salarybonuscalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Подключение кнопки "Рассчитать"
        binding.calculateButton.setOnClickListener { calculateBonus() }
    }
    // Подключение элемента radio button
    private fun calculateBonus() {
      val bonus = binding.salary.text.toString().toDouble()
        val selectedId = binding.salaryBonusOptions.checkedRadioButtonId
        val percentage = when(selectedId) {
        R.id.options_fifteen -> 0.15
            R.id.options_ten -> 0.1
            else -> 0.05
        }
        // Расчёт премии
        var salary = bonus*percentage
        val round = binding.roundSwitch.isChecked
        // Кнопка "Округлить премию"
        if (round){
            salary = ceil(salary)
        }
        val currencySalary = NumberFormat.getCurrencyInstance().format(salary)
        binding.result.text = getString(R.string.result_amount, currencySalary)
    }
}