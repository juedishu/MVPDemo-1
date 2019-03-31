package com.example.administrator.mvpdemo.service.baseInterceptor;


import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:Mr.liu
 * date: 2019/3/31
 * e-mail:363137934@qq.com
 * description:
 */
public class BaseNetInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        /*Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        RequestBody formBody = new FormBody.Builder()
                .add("userId", "10000")
                .add("sessionToken", "E34343RDFDRGRT43RFERGFRE")
                .add("q_version", "1.1")
                .add("device_id", "android-344365")
                .add("device_os", "android")
                .add("device_osversion","6.0")
                .add("req_timestamp", System.currentTimeMillis() + "")
                .add("app_name","forums")
                .add("sign", "md5")
                .build();

        String postBodyString = Utils.bodyToString(request.body());
        postBodyString += ((postBodyString.length() > 0) ? "&" : "") + Utils.bodyToString(formBody);
        request = requestBuilder
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"),
                        postBodyString))
                .build();
        return chain.proceed(request);*/


        /**
         * 固定请求的参数
         */
        /*Request request = chain.request();
        if (request.method().equals("POST")) {
            if (request.body() instanceof FormBody) {
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                FormBody formBody = (FormBody) request.body();
                //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }
                formBody = bodyBuilder
                        .addEncoded("clienttype", "1")
                        .addEncoded("imei", "imei")
                        .addEncoded("version", "VersionName")
                        .addEncoded("timestamp", String.valueOf(System.currentTimeMillis()))
                        .build();
                request = request.newBuilder().post(formBody).build();
            }
            return chain.proceed(request);
        }
        return null;*/




        Request originalRequest = chain.request();
        //重新构建url
        HttpUrl.Builder builder = originalRequest.url().newBuilder();
        //如果是post请求的话就把参数重新拼接一下，get请求的话就可以直接加入公共参数了
        if(originalRequest.method().equals("POST")){
            FormBody body = (FormBody) originalRequest.body();
            for(int i = 0; i < body.size();i++){
                Log.i("RequestFatory",body.name(i) + "---" + body.value(i));
                builder.addQueryParameter(body.name(i),body.value(i));
            }
        }
        //这里是我的2个公共参数
        builder.addQueryParameter("mallId","id")
                .addQueryParameter("robotNo","robotNo");
        //新的url
        HttpUrl httpUrl = builder.build();
        Request request = originalRequest.newBuilder()
                .method(originalRequest.method(),originalRequest.body())
                .url(httpUrl).build();
        return chain.proceed(request);
    }
}
