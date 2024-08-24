package com.example.qrcode.ui.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.qrcode.R
import com.example.qrcode.source.QrCodeManager
import com.example.qrcode.ui.composable.BackIconButton
import com.example.qrcode.ui.composable.CameraView
import com.example.qrcode.ui.composable.CommonButton
import com.example.qrcode.ui.theme.QrCodeTheme
import com.example.qrcode.ui.theme.TitleTextStyle

@Composable
fun QrCodeScanScreen(
    onBackButtonPressed: () -> Unit
) {
    val qrCodeManager = QrCodeManager()

    var qrCodeState by rememberSaveable { mutableStateOf("Тут будет текст") }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )

    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        BackIconButton(
            onClick = {
                onBackButtonPressed()
            },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 15.dp, top = 30.dp)
        )

        Text(
            text = stringResource(id = R.string.qr_code_scan),
            style = TitleTextStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 100.dp)
                .padding(bottom = 20.dp)
        )

        if (hasCamPermission) {
            CameraView(
                createCameraView = { context: Context ->
                    qrCodeManager.createCameraView(
                        context = context,
                        onScanSuccess = { code: String ->
                            qrCodeState = code
                        },
                        lifecycleOwner = lifecycleOwner
                    )
                }
            )
        } else {
            Box(
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
            ) {
                Text(
                    text = stringResource(id = R.string.not_camera_permission),
                    style = TitleTextStyle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .align(Alignment.Center)
                )
            }
        }

        Text(
            text = qrCodeState,
            style = TitleTextStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
        )
    }
}

@Preview(showBackground = true, device = "spec:width=448dp,height=998dp,dpi=480")
@Composable
fun QrCodeScanScreenPreview() {
    QrCodeTheme {
        QrCodeScanScreen({})
    }
}