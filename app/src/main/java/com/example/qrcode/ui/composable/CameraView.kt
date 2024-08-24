package com.example.qrcode.ui.composable

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.qrcode.source.QrCodeManager

@Composable
fun CameraView(
    createCameraView: (context: Context) -> PreviewView
) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 10.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(6.dp))
            .border(
                width = 3.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(6.dp)
            ),
        factory = { context ->
            createCameraView(context)
        }
    )
}