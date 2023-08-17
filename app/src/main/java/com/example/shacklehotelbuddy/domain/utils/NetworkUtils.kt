package com.example.shacklehotelbuddy.domain.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

object NetworkUtils {

    fun isConnected(context: Context): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)?.let { cm ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
                capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false ||
                        capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false  ||
                        capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) ?: false
            } else {
                val activeNetwork = cm.activeNetworkInfo
                return if ((activeNetwork?.isConnected == true) && (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                                activeNetwork.type == ConnectivityManager.TYPE_MOBILE)) {
                    true
                } else {
                    cm.getNetworkInfo(ConnectivityManager.TYPE_VPN)?.isConnected ?: false
                }
            }
        } ?: true
    }
}