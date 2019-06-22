package com.dov.templateapp.webservice.common
/*
* To be uncommented later if needed
 */

class KtRxErrorHandlingCallAdapterFactory // : CallAdapter.Factory()
{
    /*
    private lateinit var original: RxJava2CallAdapterFactory
    private fun RxErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create()
    }
    companion object {
        fun create(): CallAdapter.Factory {
            return KtRxErrorHandlingCallAdapterFactory()
        }
    }

    @SuppressWarnings("unchecked")
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<Observable<*>, Observable<*>>? {
        return RxCallAdapterWrapper(
            retrofit,
            original.get(returnType, annotations, retrofit) as CallAdapter<Observable<*>, Observable<*>>?
        )
    }


    internal class RxCallAdapterWrapper(private val retrofit: Retrofit, private val wrapped: CallAdapter<Observable<*>, Observable<*>>?) :
        CallAdapter<Observable<*>,Observable<*>> {


        override fun responseType(): Type {
            return wrapped?.responseType()!!
        }


        @ParametersAreNonnullByDefault
        override fun adapt(call: Call<Observable<*>>): Observable<*> {
            val observable = wrapped?.adapt(call)
       /*  return observable!!.onErrorResumeNext(object : Function<Throwable, Observable<*>>{
             override fun apply(t: Throwable): Observable<*> {
                 return Observable.error<*>(asRetrofitException(t))
             }
         })*/
            return Observable.empty()
        }


        private fun asRetrofitException(throwable: Throwable): KtRetrofitException {
            // We had non-200 http error
            if (throwable is HttpException) {
                val httpException = throwable as HttpException
                val response = httpException.response()
                return KtRetrofitException.httpError(response.raw().request().url().toString(), response, retrofit)
            }
            // A network error happened
            return if (throwable is IOException) {
                KtRetrofitException.networkError(throwable as IOException)
            } else{
                // We don't know what happened. We need to simply convert to an unknown error
                KtRetrofitException.unexpectedError(throwable)
            }

        }
    }
    */
}