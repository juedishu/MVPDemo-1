package com.example.administrator.mvpdemo.service.manager;

import android.content.Context;


import com.example.administrator.mvpdemo.service.RetrofitService;
import com.example.administrator.mvpdemo.service.RetrofitHelper;
import com.example.administrator.mvpdemo.service.entity.Book;
import com.example.administrator.mvpdemo.service.entity.Movies;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public  Observable<Book> getSearchBooks(String name,String tag,int start,int count){
        return mRetrofitService.getSearchBooks(name,tag,start,count);
    }

    /**
     * 取到电影的数据
     * @param start
     * @param count
     * @return
     */
    public  Observable<Movies> getTopMovies(int start, int count){
        return mRetrofitService.getTopMovies(start,count);
    }

    /**
     * 如果是多参数情况
     */
    public  Observable<Book> getSearchBooks(HashMap<String,String> map){
        return mRetrofitService.getSearchBookMap(map);
    }


}
