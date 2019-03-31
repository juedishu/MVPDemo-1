package com.example.administrator.mvpdemo.service;



import com.example.administrator.mvpdemo.service.entity.Book;
import com.example.administrator.mvpdemo.service.entity.Movies;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public interface RetrofitService {
    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q") String name,
                                    @Query("tag") String tag, @Query("start") int start,
                                    @Query("count") int count);

    /**
     * 适用于参数比较多的情况，目前未使用
     * @param map
     * @return
     */
    @GET("book/search")
    Observable<Book>  getSearchBookMap(@QueryMap Map<String, String> map);


    /**
     * 热映电影top250接口
     */
    @GET("movie/top250")
    Observable<Movies> getTopMovies(@Query("start") int start,
                                    @Query("count") int count);
}
