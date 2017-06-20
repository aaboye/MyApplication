package com.ren.tarena.xiaohua.entity;

import java.util.List;

/**
 * Created by Renshaobo on 2017/6/11.
 */

public class Funny {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allPages":161,"ret_code":0,"contentlist":[{"title":"好吧！你成功的恐吓到我了","img":"http://sc0.hao123img.com/data/9edfb5e02db99c0504f20dd9f06bbcfa_430","type":2,"ct":"2017-06-15 14:43:45.377"},{"title":"这只是一张单纯的福利图","img":"http://sc2.hao123img.com/data/8ec1d77b072a7e9f8bc748293f3ed82f_430","type":2,"ct":"2017-06-15 13:30:34.885"},{"title":"最喜欢看一段了~","img":"http://sc3.hao123img.com/data/a280ebc126a374a39bc6f4b0f8b489fc_0","type":2,"ct":"2017-06-15 13:30:34.592"},{"title":"后面妹子身材更赞","img":"http://sc4.hao123img.com/data/af7ef348c062a56b04a014f6385b0113_0","type":2,"ct":"2017-06-15 13:17:23.849"},{"title":"最右边的妹子你赢了~成功的引起了我的注意","img":"http://sc4.hao123img.com/data/401262de9ae77158d7cf71986cdc3bd4_430","type":2,"ct":"2017-06-14 16:16:58.776"},{"title":"没想到你是这样的树懒","img":"http://www.zbjuran.com/uploads/allimg/170614/1529423539-0.jpg","type":2,"ct":"2017-06-14 16:00:28.583"},{"title":"你到底要闹哪样！","img":"http://sc4.hao123img.com/data/b3c8e40464d8ad0d1d4ce0a2f0588874_430","type":2,"ct":"2017-06-14 13:20:53.490"},{"title":"右边的妹纸你稳稳的赢了！~","img":"http://sc0.hao123img.com/data/3e142a3909d5feb14e8a24629377e4c3_430","type":2,"ct":"2017-06-14 12:30:04.382"},{"title":"怪不得，镜头都不敢往下拍~","img":"http://sc3.hao123img.com/data/5b8344b9c83801d4685807e440a97fc8_430","type":2,"ct":"2017-06-14 12:17:55.433"},{"title":"橘子看上去挺甜","img":"http://www.zbjuran.com/uploads/allimg/170614/112AB441-0.jpg","type":2,"ct":"2017-06-14 12:00:24.862"},{"title":"程序员和市场部的下班了","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614111917.jpg","type":2,"ct":"2017-06-14 12:00:24.044"},{"title":"妹子你的比例不对啊","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614105605.jpg","type":2,"ct":"2017-06-14 11:30:25.814"},{"title":"妹子好身材","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614104G8.jpg","type":2,"ct":"2017-06-14 11:30:25.814"},{"title":"再瞅个试试","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614105J3.jpg","type":2,"ct":"2017-06-14 11:30:25.042"},{"title":"腿上的猫咪真可爱","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614103G9.jpg","type":2,"ct":"2017-06-14 11:00:24.743"},{"title":"凹凸曼：抽到亮灯","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614103307.jpg","type":2,"ct":"2017-06-14 11:00:22.484"},{"title":"尴尬、懵逼、卧槽","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614103118.jpg","type":2,"ct":"2017-06-14 11:00:22.453"},{"title":"主播，这笔好玩么","img":"http://sc3.hao123img.com/data/9eec7f32b9ddbc6e817083134ae44ce5_0","type":2,"ct":"2017-06-13 17:17:13.459"},{"title":"佛跳墙的英文","img":"http://www.zbjuran.com/uploads/allimg/170613/2-1F613155924591.jpg","type":2,"ct":"2017-06-13 16:30:22.591"},{"title":"高考，就是这么自信!","img":"http://sc1.hao123img.com/data/f92209d2f11cedb5dcf919f793421ad9_430","type":2,"ct":"2017-06-13 16:15:13.207"}],"currentPage":1,"allNum":3214,"maxResult":20}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * allPages : 161
         * ret_code : 0
         * contentlist : [{"title":"好吧！你成功的恐吓到我了","img":"http://sc0.hao123img.com/data/9edfb5e02db99c0504f20dd9f06bbcfa_430","type":2,"ct":"2017-06-15 14:43:45.377"},{"title":"这只是一张单纯的福利图","img":"http://sc2.hao123img.com/data/8ec1d77b072a7e9f8bc748293f3ed82f_430","type":2,"ct":"2017-06-15 13:30:34.885"},{"title":"最喜欢看一段了~","img":"http://sc3.hao123img.com/data/a280ebc126a374a39bc6f4b0f8b489fc_0","type":2,"ct":"2017-06-15 13:30:34.592"},{"title":"后面妹子身材更赞","img":"http://sc4.hao123img.com/data/af7ef348c062a56b04a014f6385b0113_0","type":2,"ct":"2017-06-15 13:17:23.849"},{"title":"最右边的妹子你赢了~成功的引起了我的注意","img":"http://sc4.hao123img.com/data/401262de9ae77158d7cf71986cdc3bd4_430","type":2,"ct":"2017-06-14 16:16:58.776"},{"title":"没想到你是这样的树懒","img":"http://www.zbjuran.com/uploads/allimg/170614/1529423539-0.jpg","type":2,"ct":"2017-06-14 16:00:28.583"},{"title":"你到底要闹哪样！","img":"http://sc4.hao123img.com/data/b3c8e40464d8ad0d1d4ce0a2f0588874_430","type":2,"ct":"2017-06-14 13:20:53.490"},{"title":"右边的妹纸你稳稳的赢了！~","img":"http://sc0.hao123img.com/data/3e142a3909d5feb14e8a24629377e4c3_430","type":2,"ct":"2017-06-14 12:30:04.382"},{"title":"怪不得，镜头都不敢往下拍~","img":"http://sc3.hao123img.com/data/5b8344b9c83801d4685807e440a97fc8_430","type":2,"ct":"2017-06-14 12:17:55.433"},{"title":"橘子看上去挺甜","img":"http://www.zbjuran.com/uploads/allimg/170614/112AB441-0.jpg","type":2,"ct":"2017-06-14 12:00:24.862"},{"title":"程序员和市场部的下班了","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614111917.jpg","type":2,"ct":"2017-06-14 12:00:24.044"},{"title":"妹子你的比例不对啊","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614105605.jpg","type":2,"ct":"2017-06-14 11:30:25.814"},{"title":"妹子好身材","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614104G8.jpg","type":2,"ct":"2017-06-14 11:30:25.814"},{"title":"再瞅个试试","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614105J3.jpg","type":2,"ct":"2017-06-14 11:30:25.042"},{"title":"腿上的猫咪真可爱","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614103G9.jpg","type":2,"ct":"2017-06-14 11:00:24.743"},{"title":"凹凸曼：抽到亮灯","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614103307.jpg","type":2,"ct":"2017-06-14 11:00:22.484"},{"title":"尴尬、懵逼、卧槽","img":"http://www.zbjuran.com/uploads/allimg/170614/2-1F614103118.jpg","type":2,"ct":"2017-06-14 11:00:22.453"},{"title":"主播，这笔好玩么","img":"http://sc3.hao123img.com/data/9eec7f32b9ddbc6e817083134ae44ce5_0","type":2,"ct":"2017-06-13 17:17:13.459"},{"title":"佛跳墙的英文","img":"http://www.zbjuran.com/uploads/allimg/170613/2-1F613155924591.jpg","type":2,"ct":"2017-06-13 16:30:22.591"},{"title":"高考，就是这么自信!","img":"http://sc1.hao123img.com/data/f92209d2f11cedb5dcf919f793421ad9_430","type":2,"ct":"2017-06-13 16:15:13.207"}]
         * currentPage : 1
         * allNum : 3214
         * maxResult : 20
         */

        private int allPages;
        private int ret_code;
        private int currentPage;
        private int allNum;
        private int maxResult;
        private List<ContentlistBean> contentlist;

        public int getAllPages() {
            return allPages;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getAllNum() {
            return allNum;
        }

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public List<ContentlistBean> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<ContentlistBean> contentlist) {
            this.contentlist = contentlist;
        }

        public static class ContentlistBean {
            /**
             * title : 好吧！你成功的恐吓到我了
             * img : http://sc0.hao123img.com/data/9edfb5e02db99c0504f20dd9f06bbcfa_430
             * type : 2
             * ct : 2017-06-15 14:43:45.377
             */

            private String title;
            private String img;
            private int type;
            private String ct;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCt() {
                return ct;
            }

            public void setCt(String ct) {
                this.ct = ct;
            }
        }
    }
}
