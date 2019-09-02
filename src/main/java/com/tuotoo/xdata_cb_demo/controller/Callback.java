package com.tuotoo.xdata_cb_demo.controller;

import com.tuotoo.xdata_cb_demo.model.Article;
import com.tuotoo.xdata_cb_demo.model.Body;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

@RestController
@RequestMapping("/")
public class Callback {
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void cb(@RequestBody Body body) {
        System.out.println("method: " + body.Method);
        System.out.println("headImg: " + body.MP.HeadHDImgURL);
        System.out.println("nickname: " + body.MP.NickName);
        for (Article article : body.Articles) {
            try {
                if (article.JPG != null) {
                    new FileOutputStream("./" + article.Title + ".jpg")
                            .write(Base64.getDecoder().decode(article.JPG));
                }
                if (article.TXT != null) {
                    new FileOutputStream("./" + article.Title + ".txt")
                            .write(Base64.getDecoder().decode(article.TXT));
                }
                if (article.HTML != null) {
                    new FileOutputStream("./" + article.Title + ".html")
                            .write(Base64.getDecoder().decode(article.HTML));
                }
                if (article.HTML != null) {
                    new FileOutputStream("./" + article.Title + ".pdf")
                            .write(Base64.getDecoder().decode(article.PDF));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
