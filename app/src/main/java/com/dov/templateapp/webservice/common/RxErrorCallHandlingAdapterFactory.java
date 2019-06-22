package com.dov.templateapp.webservice.common;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.*;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class RxErrorCallHandlingAdapterFactory extends CallAdapter.Factory {
    private RxJava2CallAdapterFactory original;

    private RxErrorCallHandlingAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorCallHandlingAdapterFactory();
    }

    @SuppressWarnings("unchecked")
    @ParametersAreNonnullByDefault
    @Nullable
    @Override
    public CallAdapter<Observable<?>, Observable<?>> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, (CallAdapter<Observable<?>, Observable<?>>) original.get(returnType, annotations, retrofit) );
    }

    private static class RxCallAdapterWrapper implements CallAdapter<Observable<?>,Observable<?>> {
        private final Retrofit retrofit;
        private final CallAdapter<Observable<?>, Observable<?>> wrapped;

        private RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<Observable<?>, Observable<?>> wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @ParametersAreNonnullByDefault
        @SuppressWarnings("unchecked")
        @Override
        public Observable<?> adapt(Call<Observable<?>> call) {
            Observable observable=  wrapped.adapt(call);
            return observable.onErrorResumeNext(new Function<Throwable, Observable>() {

                @Override
                public Observable apply(Throwable throwable) {
                    return Observable.error(asRetrofitException(throwable));
                }
            });
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }



        private RetrofitException asRetrofitException(Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit);
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return RetrofitException.networkError((IOException) throwable);
            }
            // We don't know what happened. We need to simply convert to an unknown error
            return RetrofitException.unexpectedError(throwable);
        }
    }
}