package com.example.tausif.newsviews.callbackinterface;

import com.example.tausif.newsviews.model.Article;

import java.util.List;

public interface NewsApiListener {

    void GetNewsHeadLinesResponseSuccessful (List<Article> articleList);

    void GetHeadNewsLinesResponseUnsuccessful (String message);
}
