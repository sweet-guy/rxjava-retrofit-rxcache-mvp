package com.wdcloud.myrxjavaorretrofit.entity;

import com.wdcloud.myrxjavaorretrofit.base.BaseEntity;

import java.util.List;

/**
 * Created by Umbrella on 2018/12/6.
 */

public class MyBooks extends BaseEntity{

    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * id : 1
         * book_name : 天宫之城
         * buy_date : 637948800000
         * book_num : 0
         * book_author : 宫崎骏
         * price : 23.0
         * count : 100
         * catgory : 动漫
         */

        private int id;
        private String book_name;
        private long buy_date;
        private int book_num;
        private String book_author;
        private double price;
        private int count;
        private String catgory;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBook_name() {
            return book_name;
        }

        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }

        public long getBuy_date() {
            return buy_date;
        }

        public void setBuy_date(long buy_date) {
            this.buy_date = buy_date;
        }

        public int getBook_num() {
            return book_num;
        }

        public void setBook_num(int book_num) {
            this.book_num = book_num;
        }

        public String getBook_author() {
            return book_author;
        }

        public void setBook_author(String book_author) {
            this.book_author = book_author;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getCatgory() {
            return catgory;
        }

        public void setCatgory(String catgory) {
            this.catgory = catgory;
        }

        @Override
        public String toString() {
            return "ItemsBean{" +
                    "id=" + id +
                    ", book_name='" + book_name + '\'' +
                    ", buy_date=" + buy_date +
                    ", book_num=" + book_num +
                    ", book_author='" + book_author + '\'' +
                    ", price=" + price +
                    ", count=" + count +
                    ", catgory='" + catgory + '\'' +
                    '}';
        }
    }
}
