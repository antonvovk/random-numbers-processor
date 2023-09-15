package com.avovk.randomnumbersprocessor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

    var resultNumbersTextValue by remember {
        mutableStateOf("")
    }
    var actualSumTextValue by remember {
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
                modifier = Modifier.weight(1f),
                value = rangeMin,
                onValueChange = { text ->
                    if (text.isNotBlank()) {
                        rangeMin = text
                    }
                })
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Верхня межа") },
                modifier = Modifier.weight(1f),
                value = rangeMax,
                onValueChange = { text ->
                    if (text.isNotBlank()) {
                        rangeMax = text
                    }
                })
        }
        Row(
            modifier.fillMaxWidth()
        ) {
            OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Кількість чисел") },
                modifier = Modifier.weight(1f),
                value = amountOfNumbers,
                onValueChange = { text ->
                    if (text.isNotBlank()) {
                        amountOfNumbers = text
                    }
                })
        }
        Row(
            modifier.fillMaxWidth()
        ) {
            OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Бажана сума") },
                modifier = Modifier.weight(1f),
                value = desiredSum,
                onValueChange = { text ->
                    if (text.isNotBlank()) {
                        desiredSum = text
                    }
                })
        }
        Row(
            modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                val processor = CalculatorProcessor(
                    BigDecimal(rangeMin),
                    BigDecimal(rangeMax),
                    amountOfNumbers.toInt(),
                    desiredSum.toInt()
                )
                val calculationResult = processor.calculate()

                val actualSum = DecimalNumber()
                val stringBuilder = StringBuilder()
                for (resultNumber in calculationResult) {
                    actualSum.add(resultNumber)
                    stringBuilder.append(resultNumber.toString()).append("\n")
                }

                actualSumTextValue = actualSum.toString()
                resultNumbersTextValue = stringBuilder.toString()
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Обчислити")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier.fillMaxWidth()
        ) {
            Text(text = "Фактична сума: ", fontWeight = FontWeight.Bold)
            Text(text = actualSumTextValue, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier.fillMaxWidth()
        ) {
            Text(text = "Результат: ", fontWeight = FontWeight.Bold)
        }
        Row(
            modifier.fillMaxWidth()
        ) {
            Text(text = resultNumbersTextValue)
        }
    }
}
