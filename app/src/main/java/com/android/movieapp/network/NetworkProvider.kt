package com.android.movieapp.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkProvider {
    companion object {

        const val BaseUrl = "https://api.themoviedb.org/3/"
        const val getPopularMovies = "movie/popular"
        const val api_key = "de44f90cfc342394714ab7af0c1586f7"

//        private val spec = ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
//            .supportsTlsExtensions(true)
//            .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
//            .cipherSuites(
//                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
//                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
//                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
//                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
//                CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
//                CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
//                CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
//                CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
//                CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
//                CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA
//            )
//            .build()

//        private val client: OkHttpClient = OkHttpClient.Builder()
//            .connectionSpecs(Collections.singletonList(spec))
//            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            // .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


        fun getNetworkService(): NetworkService = retrofit.create(
            NetworkService::class.java)

    }
}