package com.example.ddi

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextCustom(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Black,
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
        label = { Text(text = label) },
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.Light
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
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.Light
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
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.Light
            )
        }
    )
    return text.text
}

@Composable
fun ButtonCustom(
    text: String,
    onClick: () -> Unit,
    width: Dp = 180.dp,
    height: Dp = 64.dp,
    color: Color = White,
    textAlign: TextAlign = TextAlign.Center
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.elevatedButtonColors(containerColor = color),
        shape = RoundedCornerShape(10),
        border = BorderStroke(1.dp, Black),
        modifier = Modifier
            .width(width)
            .height(height)
    ) {
        Text(
            text,
            color = Black,
            fontSize = 24.sp,
            textAlign = textAlign
        )
    }
}