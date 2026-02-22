package components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Integer input with +/- buttons (OpenYarnStash row counter style).
 * Simple: [−] [value] [+]
 */
@Composable
fun IntegerInput(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    step: Int = 1,
    min: Int = Int.MIN_VALUE,
    max: Int = Int.MAX_VALUE
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = {
                val newValue = (value - step).coerceIn(min, max)
                onValueChange(newValue)
            },
            enabled = value > min
        ) {
            Icon(Icons.Default.Remove, contentDescription = "Decrease")
        }

        var textValue by remember(value) { mutableStateOf(value.toString()) }

        BasicTextField(
            value = textValue,
            onValueChange = { newText ->
                textValue = newText
                newText.toIntOrNull()?.let { newValue ->
                    onValueChange(newValue.coerceIn(min, max))
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.width(60.dp)
        ) { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (textValue.isEmpty()) {
                    Text(
                        text = value.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                innerTextField()
            }
        }

        IconButton(
            onClick = {
                val newValue = (value + step).coerceIn(min, max)
                onValueChange(newValue)
            },
            enabled = value < max
        ) {
            Icon(Icons.Default.Add, contentDescription = "Increase")
        }
    }
}

/**
 * Double input with +/- buttons (OpenYarnStash row counter style).
 * Simple: [−] [value] [+]
 */
@Composable
fun DoubleInput(
    value: Double,
    onValueChange: (Double) -> Unit,
    modifier: Modifier = Modifier,
    step: Double = 0.1,
    min: Double = 0.0,
    max: Double = Double.MAX_VALUE,
    decimals: Int = 1
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = {
                val newValue = (value - step).coerceIn(min, max)
                onValueChange(newValue)
            },
            enabled = value > min
        ) {
            Icon(Icons.Default.Remove, contentDescription = "Decrease")
        }

        var textValue by remember(value) { mutableStateOf(String.format("%.${decimals}f", value)) }

        BasicTextField(
            value = textValue,
            onValueChange = { newText ->
                textValue = newText
                newText.toDoubleOrNull()?.let { newValue ->
                    onValueChange(newValue.coerceIn(min, max))
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.width(80.dp)
        ) { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (textValue.isEmpty()) {
                    Text(
                        text = String.format("%.${decimals}f", value),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                innerTextField()
            }
        }

        IconButton(
            onClick = {
                val newValue = (value + step).coerceIn(min, max)
                onValueChange(newValue)
            },
            enabled = value < max
        ) {
            Icon(Icons.Default.Add, contentDescription = "Increase")
        }
    }
}
