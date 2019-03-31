package com.example.administrator.mvpdemo.service.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.example.administrator.mvpdemo.service.entity.Book;
import com.example.administrator.mvpdemo.service.entity.Movies;
import com.example.administrator.mvpdemo.service.manager.DataManager;
import com.example.administrator.mvpdemo.service.view.BookView;
import com.example.administrator.mvpdemo.service.view.MovieView;
import com.example.administrator.mvpdemo.service.view.View;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class BookPresenter implements Presenter {
    private DataManager  manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookView mBookView;

    private MovieView mMoviesView;
    private Book mBook;
    private Movies mMovies;
    public BookPresenter (Context mContext){
        this.mContext = mContext;
    }
    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
        if(mBookView!=null)
            mBookView = null;
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
//        mBookView = (BookView)view;
        mMoviesView = (MovieView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intetn) {
}
    public void getSearchBooks(String name,String tag,int start,int count){

        mCompositeSubscription.add(manager.getSearchBooks(name,tag,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if (mBook != null){
                            mBookView.onSuccess(mBook);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBookView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(Book book) {
                        mBook = book;
                    }
                })
        );
    }
    public void getTopMovies(int start,int count){

        mCompositeSubscription.add(manager.getTopMovies(start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movies>() {
                    @Override
                    public void onCompleted() {
                        if (mMovies != null){
                            mMoviesView.onSuccess(mMovies);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBookView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(Movies movies) {
                        mMovies = movies;
                    }


                })
        );
    }
}
