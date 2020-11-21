package com.cxp.okhttp;

import lombok.extern.java.Log;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author : cheng
 * @date : 2020-10-24 08:28
 */
@Log
public class TestOkHttp {

    private OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool())
            .build();

    @Test
    public void testGet() throws IOException {
        String url = "http://wwww.baidu.com";
        Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);
        //同步请求
        String body = call.execute().body().string();
        System.out.println(body);
    }

    @Test
    public void testGetAsync() throws InterruptedException {
        String url = "http://wwww.baidu.com";
        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void testPostSubmitJson() throws IOException, InterruptedException {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String requestBody = "{\"name\":\"红楼梦\"}";
        Request request = new Request.Builder().url("http://192.168.8.118:8200/monogodb-basic/book/listByBook")
                .post(RequestBody.create(mediaType, requestBody)).build();
        //异步请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info(response.protocol() + " " +response.code() + " " + response.message());
                System.out.println(response.body().string());
            }
        });

        //同步请求
        String body = okHttpClient.newCall(request).execute().body().string();
        System.out.println(body);

        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * post提交表单
     * @throws IOException
     */
    @Test
    public void testPostSubmitForm() throws IOException {
        FormBody formBody = new FormBody.Builder().add("name", "红楼梦").build();
        Request request = new Request.Builder().url("http://192.168.8.118:8200/monogodb-basic/book/listByBookForm")
                .post(formBody).build();
        String body = okHttpClient.newCall(request).execute().body().string();
        System.out.println(body);
    }

    @Test
    public void testAuthorized() throws IOException {
        String credential = Credentials.basic("jesse", "password1");
        OkHttpClient client = okHttpClient.newBuilder()
                .authenticator(((route, response) -> {
                    System.out.println("Authenticating for response: " + response);
                    System.out.println("Challenges: " + response.challenges());
                    return response.request().newBuilder()
                            .header("Authorization", credential).build();
                }))
                .build();
        Request request = new Request.Builder().url("").build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
