package com.myTestApp;

import java.util.List;

public class ListItem {
    private String imgUrl;
    private String word;
    private String des;

    public ListItem(){
        //기본 생성자
    }

    public ListItem(String imgUrl, String word, String des){
        this.imgUrl = imgUrl;
        this.word = word;
        this.des = des;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    // toString() 메서드를 재정의하여 객체를 문자열로 변환하는 방식을 지정할 수도 있습니다.
    @Override
    public String toString() {
        return word;
    }
}
