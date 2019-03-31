package com.example.administrator.mvpdemo.service.view;

import com.example.administrator.mvpdemo.service.entity.Book;
import com.example.administrator.mvpdemo.service.entity.Movies;

/**
 * author:Mr.liu
 * date: 2019/3/31
 * e-mail:363137934@qq.com
 * description:
 */
public interface MovieView extends View{
    void onSuccess(Movies mMovies);
    void onError(String result);
}
