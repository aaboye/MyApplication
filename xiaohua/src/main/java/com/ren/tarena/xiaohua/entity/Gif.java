package com.ren.tarena.xiaohua.entity;

import java.util.List;

/**
 * Created by tarena on 2017/6/17.
 */

public class Gif {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allPages":13,"ret_code":0,"contentlist":[{"title":"遇到这些情况真是想打人","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q025132L.gif","type":3,"ct":"2017-06-08 11:00:06.651"},{"title":"速度与激情9找你去拍准没错","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q02109149.gif","type":3,"ct":"2017-06-08 11:00:06.446"},{"title":"舞蹈赞的幼儿园老师","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60P95I6296.gif","type":3,"ct":"2017-06-08 10:30:13.695"},{"title":"<b>超有爱的调皮好友","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q00642F2.gif","type":3,"ct":"2017-06-08 10:30:13.452"},{"title":"舞蹈赞的幼儿园老师","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60P95I6296.gif","type":3,"ct":"2017-06-08 10:30:13.451"},{"title":"真是网吧的清流啊","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q00133936.gif","type":3,"ct":"2017-06-08 10:30:13.445"},{"title":"<b>超有爱的调皮好友","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q00642F2.gif","type":3,"ct":"2017-06-08 10:30:13.358"},{"title":"下面就是要\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/170607/1H25S2I-0.gif","type":3,"ct":"2017-06-07 18:00:09.201"},{"title":"动漫美食你想吃哪个","img":"http://www.zbjuran.com/uploads/allimg/170607/1F33161S-0.gif","type":3,"ct":"2017-06-07 17:30:08.935"},{"title":"家里的椅子都被他打坏了几十张了","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60GA123538.gif","type":3,"ct":"2017-06-07 17:30:08.935"},{"title":"突然有种特别大胆的想法","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G63614537.gif","type":3,"ct":"2017-06-07 17:00:11.205"},{"title":"感觉被人家赢了二十年","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G62612129.gif","type":3,"ct":"2017-06-07 17:00:09.101"},{"title":" 一个能干的都没有","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G6325L47.gif","type":3,"ct":"2017-06-07 17:00:08.915"},{"title":"我猜你又有大胆的想法了","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G631411O.gif","type":3,"ct":"2017-06-07 17:00:08.818"},{"title":"开心的时候就笑吧","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G62032219.gif","type":3,"ct":"2017-06-07 17:00:08.669"},{"title":"高考结束以后","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G62425253.gif","type":3,"ct":"2017-06-07 17:00:08.505"},{"title":"<b>这样度过的才算是真正的夏天","img":"http://www.zbjuran.com/uploads/allimg/170607/1643253947-0.gif","type":3,"ct":"2017-06-07 17:00:08.363"},{"title":"这是什么运动动作吗","img":"http://www.zbjuran.com/uploads/allimg/170603/2-1F603110053450.gif","type":3,"ct":"2017-06-07 02:00:17.180"},{"title":"<b>大尺度动态图","img":"http://www.zbjuran.com/uploads/allimg/170517/2-1F51GU41T24.gif","type":3,"ct":"2017-06-07 02:00:16.858"},{"title":"这锻炼方式也是厉害","img":"http://www.zbjuran.com/uploads/allimg/170603/2-1F603110JV02.gif","type":3,"ct":"2017-06-07 02:00:16.788"}],"currentPage":1,"allNum":241,"maxResult":20}
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
         * allPages : 13
         * ret_code : 0
         * contentlist : [{"title":"遇到这些情况真是想打人","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q025132L.gif","type":3,"ct":"2017-06-08 11:00:06.651"},{"title":"速度与激情9找你去拍准没错","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q02109149.gif","type":3,"ct":"2017-06-08 11:00:06.446"},{"title":"舞蹈赞的幼儿园老师","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60P95I6296.gif","type":3,"ct":"2017-06-08 10:30:13.695"},{"title":"<b>超有爱的调皮好友","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q00642F2.gif","type":3,"ct":"2017-06-08 10:30:13.452"},{"title":"舞蹈赞的幼儿园老师","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60P95I6296.gif","type":3,"ct":"2017-06-08 10:30:13.451"},{"title":"真是网吧的清流啊","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q00133936.gif","type":3,"ct":"2017-06-08 10:30:13.445"},{"title":"<b>超有爱的调皮好友","img":"http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q00642F2.gif","type":3,"ct":"2017-06-08 10:30:13.358"},{"title":"下面就是要\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/170607/1H25S2I-0.gif","type":3,"ct":"2017-06-07 18:00:09.201"},{"title":"动漫美食你想吃哪个","img":"http://www.zbjuran.com/uploads/allimg/170607/1F33161S-0.gif","type":3,"ct":"2017-06-07 17:30:08.935"},{"title":"家里的椅子都被他打坏了几十张了","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60GA123538.gif","type":3,"ct":"2017-06-07 17:30:08.935"},{"title":"突然有种特别大胆的想法","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G63614537.gif","type":3,"ct":"2017-06-07 17:00:11.205"},{"title":"感觉被人家赢了二十年","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G62612129.gif","type":3,"ct":"2017-06-07 17:00:09.101"},{"title":" 一个能干的都没有","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G6325L47.gif","type":3,"ct":"2017-06-07 17:00:08.915"},{"title":"我猜你又有大胆的想法了","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G631411O.gif","type":3,"ct":"2017-06-07 17:00:08.818"},{"title":"开心的时候就笑吧","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G62032219.gif","type":3,"ct":"2017-06-07 17:00:08.669"},{"title":"高考结束以后","img":"http://www.zbjuran.com/uploads/allimg/170607/2-1F60G62425253.gif","type":3,"ct":"2017-06-07 17:00:08.505"},{"title":"<b>这样度过的才算是真正的夏天","img":"http://www.zbjuran.com/uploads/allimg/170607/1643253947-0.gif","type":3,"ct":"2017-06-07 17:00:08.363"},{"title":"这是什么运动动作吗","img":"http://www.zbjuran.com/uploads/allimg/170603/2-1F603110053450.gif","type":3,"ct":"2017-06-07 02:00:17.180"},{"title":"<b>大尺度动态图","img":"http://www.zbjuran.com/uploads/allimg/170517/2-1F51GU41T24.gif","type":3,"ct":"2017-06-07 02:00:16.858"},{"title":"这锻炼方式也是厉害","img":"http://www.zbjuran.com/uploads/allimg/170603/2-1F603110JV02.gif","type":3,"ct":"2017-06-07 02:00:16.788"}]
         * currentPage : 1
         * allNum : 241
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
             * title : 遇到这些情况真是想打人
             * img : http://www.zbjuran.com/uploads/allimg/170608/2-1F60Q025132L.gif
             * type : 3
             * ct : 2017-06-08 11:00:06.651
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
