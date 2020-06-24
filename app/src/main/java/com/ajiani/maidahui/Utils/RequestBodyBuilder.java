package com.ajiani.maidahui.Utils;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author WangZequan
 * @time 2019/5/4 15:35
 * @describe retrofit post(提交json)请求 RequestBody构造器
 */
public class RequestBodyBuilder {

    private RequestBodyBuilder() {
    }

    public static RequestObjBodyBuilder objBuilder() {
        return new RequestObjBodyBuilder();
    }

    public static class RequestObjBodyBuilder {
        private JSONObject jsonObject = new JSONObject();

        private RequestObjBodyBuilder() {

        }

        public RequestObjBodyBuilder add(String key, String value) {

            return this;
        }

        public RequestObjBodyBuilder add(String key, int value) {
            try {
                jsonObject.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        /**
         * {
         *
         * @param name:[     "",
         *                   "",
         *                   ""
         *                   ]
         *                   }
         * @param name
         * @param stringList
         * @return
         */
        public RequestObjBodyBuilder addStringArray(String name, List<String> stringList) {
            try {
                JSONArray jsonArray = new JSONArray();
                for (String s : stringList) {
                    jsonArray.put(s);
                }
                jsonObject.put(name, jsonArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public RequestObjBodyBuilder addSingleFieldObjectArray(String arrName, String valueName, List<String> list) {
            try {
                JSONArray jsonArray = new JSONArray();
                for (String s : list) {
                    JSONObject jsonObject1 = new JSONObject().put(valueName, s);
                    jsonArray.put(jsonObject1);
                }
                jsonObject.put(arrName, jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public RequestObjBodyBuilder addObjectArray(String arrName, JSONArray jsonArray) {
            try {
                jsonObject.put(arrName, jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public RequestObjBodyBuilder put(Map<String, String> map) {
            for (String key : map.keySet()) {
                try {
                    jsonObject.put(key, map.get(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public RequestObjBodyBuilder getPageBuilder(int page, int limit) {
            return add("page", page)
                    .add("limit", limit);
        }

        public RequestBody getPageBuild(int page, int limit) {
            return add("page", page)
                    .add("limit", limit)
                    .build();
        }

        @NonNull
        @CheckResult
        public RequestBody build() {
            MediaType parse = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(parse, jsonObject.toString());
            return body;
        }
    }









    public static  RequestArrBodyBuilder arrBuilder() {
        return new RequestArrBodyBuilder();
    }

    public static class RequestArrBodyBuilder  {
        private JSONArray jsonArray = new JSONArray();
        private JSONObject jsonObject = new JSONObject();

        private RequestArrBodyBuilder() {

        }


        public RequestArrBodyBuilder addString(String s) {
            try {
                jsonObject.put("",jsonArray.put(s));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }


        public RequestBody buildObjArr(String s, JSONArray jsonArray ) {
            this.jsonArray=jsonArray;
            return build(s);
        }



        @NonNull
        @CheckResult
        public RequestBody build(String s) {
            MediaType parse = MediaType.parse("application/json; charset=utf-8");
            try {
                jsonObject.put(s,jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(parse, jsonObject.toString());
            return body;
        }
    }

}
