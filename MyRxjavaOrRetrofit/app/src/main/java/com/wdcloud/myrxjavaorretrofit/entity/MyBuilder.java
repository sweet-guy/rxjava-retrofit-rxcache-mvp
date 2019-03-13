package com.wdcloud.myrxjavaorretrofit.entity;

/**
 * Created by Umbrella on 2018/12/26.
 */

public class MyBuilder {
    private final String name;
    private String password;

    private MyBuilder(Builder builder) {
        name = builder.name;
        password = builder.password;
    }

    public static final class Builder {
        private String name;
        private String password;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public MyBuilder build() {
            return new MyBuilder(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
