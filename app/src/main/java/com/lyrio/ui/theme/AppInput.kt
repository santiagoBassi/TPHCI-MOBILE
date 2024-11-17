package com.lyrio.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.lyrio.R

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
    hint: String? = null,
    isPassword: Boolean = false,
) {
    var passwordVisible by remember { mutableStateOf(false) } // Estado para controlar la visibilidad de la contraseña
    val visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None
    var isFocused by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) { // Usa un Column para colocar el hint encima del TextField
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = modifier.onFocusChanged { isFocused = it.isFocused },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Orange,
                focusedLabelColor = Orange,
                cursorColor = Orange,
                unfocusedLabelColor = Color.Gray
            ),
            shape = RoundedCornerShape(16.dp),
            visualTransformation = visualTransformation,
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
        if (hint != null && isFocused) { // Solo muestra el hint si está presente
            Text(
                text = hint,
                style = MaterialTheme.typography.bodySmall, // Puedes personalizar el estilo del hint
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .padding(top = 5.dp)
            )
        }
    }
}