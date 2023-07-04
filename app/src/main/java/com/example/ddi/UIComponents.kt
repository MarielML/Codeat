package com.example.ddi

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ddi.ui.theme.violetaClaro

@Composable
fun TextCustom(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = White,
    fontSize: TextUnit = 30.sp,
    textAlign: TextAlign = TextAlign.Left
) {
    Text(text = text, modifier = modifier, color = color, fontSize = fontSize, textAlign = textAlign)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldCustom(
    label: String,
    placeholder: String
): String {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = label, color = White) },
        textStyle = TextStyle(color = White),
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.Light,
                color = White
            )
        }
    )
    return text.text
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldPasswordCustom(
    label: String,
    placeholder: String
): String {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = label, color = White) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        textStyle = TextStyle(color = White),
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.Light,
                color = White
            )
        }
    )
    return text.text
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldEmailCustom(
    label: String,
    placeholder: String
): String {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = label, color = White) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        textStyle = TextStyle(color = White),
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.Light,
                color = White
            )
        }
    )
    return text.text
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCustomDescripcion(
    label: String,
    placeholder: String,
    width: Dp = 500.dp,
    height: Dp= 180.dp
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = label, color = White) },
        textStyle = TextStyle(color = White),
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.Light,
                color = White
            )
        },
        modifier = Modifier
            .width(width)
            .height(height)
    )
}

@Composable
fun ButtonCustom(
    text: String,
    onClick: () -> Unit,
    width: Dp = 180.dp,
    height: Dp = 64.dp,
    color: Color = violetaClaro,
    fontSize: Int = 24,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier
        .width(width)
        .height(height)
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.elevatedButtonColors(containerColor = color),
        shape = RoundedCornerShape(10)
    ) {
        Text(
            text,
            color = White,
            fontSize = fontSize.sp,
            textAlign = textAlign
        )
    }
}