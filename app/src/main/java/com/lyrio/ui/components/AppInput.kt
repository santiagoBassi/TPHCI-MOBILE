package com.lyrio.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.styles.Orange
import com.lyrio.ui.styles.Red

@Composable
fun eyeIconPainter(): Painter = painterResource(id = R.drawable.eye)

@Composable
fun eyeOffIconPainter(): Painter = painterResource(id = R.drawable.eye_off)

@Composable
fun AppInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    hint: String? = null,
    error: String? = null,
    isError: Boolean = false,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    readOnly: Boolean = false,
    onFocusAction: (Boolean) -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val myVisualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else visualTransformation
    var isFocused by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            placeholder = { if (placeholder != null) Text(placeholder) },
            modifier = modifier.onFocusChanged {
                isFocused = it.isFocused
                onFocusAction(it.isFocused)
                                               },
            readOnly = readOnly,
            isError = isError,
            keyboardOptions = keyboardOptions,
            supportingText = {
                if(isError && error != null){
                    Text(
                        text = error,
                        color = Red,
                    )
                }
                if (hint != null && isFocused && !isError) { // Solo muestra el hint si está presente
                    Text(
                        text = hint,
                        color = Color.Gray,
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if(readOnly) Color.Gray else Orange,
                focusedLabelColor = if(readOnly) Color.Gray else Orange,
                cursorColor = if(readOnly) Color.Gray else Orange,
                unfocusedLabelColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray,
                focusedTextColor = Color.Black,
            ),
            shape = RoundedCornerShape(16.dp),
            visualTransformation = myVisualTransformation,
            trailingIcon = {
                if (isPassword) { // Solo muestra el icono en campos de contraseña
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = if (passwordVisible) eyeIconPainter() else eyeOffIconPainter(),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        )
    }
}