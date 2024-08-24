package com.example.qrcode.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qrcode.R
import com.example.qrcode.source.QrCodeManager
import com.example.qrcode.ui.composable.CommonButton
import com.example.qrcode.ui.composable.CommonOutlinedTextField
import com.example.qrcode.ui.theme.QrCodeTheme
import com.example.qrcode.ui.theme.TitleTextStyle

@Composable
fun QrCodeGenerationScreen(
    onNextButtonPressed: () -> Unit
) {
    var codeTextState by rememberSaveable { mutableStateOf("Kaz1deX") }

    val qrCodeManager = QrCodeManager()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = stringResource(id = R.string.qr_code_generation),
            style = TitleTextStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Image(
            bitmap = qrCodeManager.generateQrCode(codeTextState)!!.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = 10.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(6.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(6.dp)
                )
        )

        CommonOutlinedTextField(
            value = codeTextState,
            onValueChange = { code: String ->
                codeTextState = code
            },
            textLabel = stringResource(id = R.string.text),
            textPlaceholder = stringResource(id = R.string.enter_text),
            modifier = Modifier
                .padding(top = 5.dp)
                .padding(horizontal = 30.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false
            ),
            isError = false
        )

        CommonButton(
            text = stringResource(id = R.string.next),
            onClick = onNextButtonPressed
        )
    }
}

@Preview(showBackground = true, device = "spec:width=448dp,height=998dp,dpi=480")
@Composable
fun QrCodeGenerationScreenPreview() {
    QrCodeTheme {
        QrCodeGenerationScreen({})
    }
}