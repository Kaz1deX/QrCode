package com.example.qrcode.navigation

import com.example.qrcode.R

sealed class Screen(val route : String, val icon : Int, val title : String) {
    data object QrCodeGenerationScreen : Screen("qr_code_generation_screen",
        title = "QrCodeGeneration", icon = R.drawable.qr_code_svgrepo_com
    )

    data object QrCodeScanScreen : Screen("qr_code_scan_screen",
        title = "QrCodeScan", icon = R.drawable.qr_code_svgrepo_com
    )
}