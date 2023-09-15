package com.avovk.randomnumbersprocessor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avovk.randomnumbersprocessor.ui.theme.RandomNumbersProcessorTheme
import java.math.BigDecimal
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomNumbersProcessorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    RandomNumbersProcessorTheme {
        Calculator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator(modifier: Modifier = Modifier) {
    var rangeMin by remember {
        mutableStateOf("")
    }
    var rangeMax by remember {
        mutableStateOf("")
    }
    var amountOfNumbers by remember {
        mutableStateOf("")
    }
    var desiredSum by remember {
        mutableStateOf("")
    }

    var resultNumbers by remember {
        mutableStateOf(listOf<DecimalNumber>())
    }
    var actualSumTextValue by remember {
        mutableStateOf("")
    }
    var executionTime by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier.fillMaxWidth()
        ) {
            OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Нижня межа") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                value = rangeMin,
                onValueChange = { text ->
                    rangeMin = text
                })
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Верхня межа") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                value = rangeMax,
                onValueChange = { text ->
                    rangeMax = text
                })
        }
        Row(
            modifier.fillMaxWidth()
        ) {
            OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Кількість чисел") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                value = amountOfNumbers,
                onValueChange = { text ->
                    amountOfNumbers = text
                })
        }
        Row(
            modifier.fillMaxWidth()
        ) {
            OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Бажана сума") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                value = desiredSum,
                onValueChange = { text ->
                    desiredSum = text
                })
        }
        Row(
            modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    executionTime = measureTimeMillis {
                        try {
                            val processor = CalculatorProcessor(
                                BigDecimal(rangeMin),
                                BigDecimal(rangeMax),
                                amountOfNumbers.toInt(),
                                desiredSum.toInt()
                            )
                            val calculationResult = processor.calculate()

                            val actualSum = DecimalNumber()
                            for (resultNumber in calculationResult) {
                                actualSum.add(resultNumber)
                            }

                            actualSumTextValue = actualSum.toString()
                            resultNumbers = calculationResult.toList()
                        } catch (e: Exception) {
                            actualSumTextValue = "Помилка"
                            resultNumbers = emptyList()
                        }
                    }.toString() + " ms"
                }, modifier = Modifier.weight(1f)
            ) {
                Text(text = "Обчислити")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier.fillMaxWidth()
        ) {
            Text(text = "Фактична сума: $actualSumTextValue", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Час обчислення: $executionTime", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier.fillMaxWidth()
        ) {
            Text(text = "Результат: ", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(1.dp))
        LazyColumn {
            items(resultNumbers) { resultNumber ->
                Text(text = resultNumber.toString())
            }
        }
    }
}
