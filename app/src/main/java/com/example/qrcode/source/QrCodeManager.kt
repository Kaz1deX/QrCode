package com.example.qrcode.source

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

class QrCodeManager() {
    fun generateQrCode(text: String): Bitmap? {
        val qrgEncoder = QRGEncoder(text, null, QRGContents.Type.TEXT, 600)
        try {
            return qrgEncoder.getBitmap(0)
        } catch (e: Exception) {
            Log.v("QrCodeException", e.toString())
        }
        return null
    }

    fun createCameraView(
        context: Context,
        onScanSuccess: (String) -> Unit,
        lifecycleOwner: LifecycleOwner
    ): PreviewView {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        val previewView = PreviewView(context)
        val preview = Preview.Builder().build()
        val selector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        preview.surfaceProvider = previewView.surfaceProvider
        val imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
            .build()
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(context),
            QrCodeAnalyzer { result ->
                onScanSuccess(result)
            }
        )
        try {
            cameraProviderFuture.get().bindToLifecycle(
                lifecycleOwner,
                selector,
                preview,
                imageAnalysis
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return previewView
    }
}