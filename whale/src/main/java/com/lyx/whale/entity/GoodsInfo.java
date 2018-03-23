package com.lyx.whale.entity;

import java.util.List;


import com.kennycason.kumo.WordFrequency;

public class GoodsInfo implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GoodsInfo() {
    }



    private String goodsId;

    private String goodsName;

    private String imgUrl;

    private String goodsPrice;


    private List<WordFrequency> wordFrequenciesList;

    public List<WordFrequency> getWordFrequenciesList() {
        return wordFrequenciesList;
    }

    public void setWordFrequenciesList(List<WordFrequency> wordFrequenciesList) {
        this.wordFrequenciesList = wordFrequenciesList;
    }



    public int getPosNum() {
        return posNum;
    }

    public void setPosNum(int posNum) {
        this.posNum = posNum;
    }

    public int getNegNum() {
        return negNum;
    }

    public void setNegNum(int negNum) {
        this.negNum = negNum;
    }

    public int getMidNum() {
        return midNum;
    }

    public void setMidNum(int midNum) {
        this.midNum = midNum;
    }

    private int posNum;
    private int negNum;
    private int midNum;

    @Override
    public String toString() {
        return "GoodsInfo{" +

                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", wordFrequenciesList=" + wordFrequenciesList +
                ", posNum=" + posNum +
                ", negNum=" + negNum +
                ", midNum=" + midNum +
                '}';
    }


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public GoodsInfo(String goodsId, String goodsName, String imgUrl,
                     String goodsPrice,  int posNum, int negNum,
                     int midNum, List<WordFrequency> wordFrequenciesList) {
        super();
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.imgUrl = imgUrl;
        this.goodsPrice = goodsPrice;

        this.wordFrequenciesList = wordFrequenciesList;
        this.posNum = posNum;
        this.negNum = negNum;
        this.midNum = midNum;
    }

}
