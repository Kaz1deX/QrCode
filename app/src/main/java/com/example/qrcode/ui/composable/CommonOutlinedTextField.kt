package com.example.qrcode.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.qrcode.ui.theme.SmallTextStyle

@Composable
fun CommonOutlinedTextField(
    value: String,
    onValueChange: (value: String) -> Unit,
    textLabel: String,
    textPlaceholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        textStyle = SmallTextStyle,
        singleLine = true,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(
                text = textLabel,
                style = SmallTextStyle,
                maxLines = 1
            )
        },
        placeholder = {
            Text(
                text = textPlaceholder,
                style = SmallTextStyle,
                maxLines = 1
            )
        },
        shape = RoundedCornerShape(10.dp),
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,

            unfocusedPlaceholderColor = MaterialTheme.colorScheme.primary,
            focusedPlaceholderColor = MaterialTheme.colorScheme.primary,

            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,

            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp)
    )
}