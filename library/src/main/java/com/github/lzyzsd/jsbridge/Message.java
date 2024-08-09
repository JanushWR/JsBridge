package com.github.lzyzsd.jsbridge;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * data of bridge
 * @author haoqing
 *
 */
public class Message {

    private String callbackId; //callbackId
    private String responseId; //responseId
    private String responseData; //responseData
    private String data; //data of message
    private String handlerName; //name of handler

    private final static String CALLBACK_ID_STR = "callbackId";
    private final static String RESPONSE_ID_STR = "responseId";
    private final static String RESPONSE_DATA_STR = "responseData";
    private final static String DATA_STR = "data";
    private final static String HANDLER_NAME_STR = "handlerName";

    public String getResponseId() {
        return responseId;
    }
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }
    public String getResponseData() {
        return responseData;
    }
    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
    public String getCallbackId() {
        return callbackId;
    }
    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHandlerName() {
        return handlerName;
    }
    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String toJson() {
        return  new Gson().toJson(this);
//        JSONObject jsonObject= new JSONObject();
//        try {
//            jsonObject.put(CALLBACK_ID_STR, getCallbackId());
//            jsonObject.put(DATA_STR, getData());
//            jsonObject.put(HANDLER_NAME_STR, getHandlerName());
//            jsonObject.put(RESPONSE_DATA_STR, getResponseData());
//            jsonObject.put(RESPONSE_ID_STR, getResponseId());
//            String oldSyyle = jsonObject.toString();
//            String newStyle = new Gson().toJson(this);
//            Log.d("MyLog", "oldStyle: " + jsonObject.toString());
//            Log.d("MyLog", "oldStyle: " + newStyle);
//            Log.d("MyLog", "equal: " + oldSyyle.equals(newStyle));
//
//
//            return jsonObject.toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
    }


    public static List<Message> toArrayList(String jsonStr){
        String  a = "[{\"handlerName\":\"submitFromWeb\",\"data\":{\"param\":\"123\"},\"callbackId\":\"cb_7_1723131799905\"}]";

        List<Message> list = new ArrayList<Message>();
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for(int i = 0; i < jsonArray.length(); i++){
                Message m = new Message();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                m.setHandlerName(jsonObject.has(HANDLER_NAME_STR) ? jsonObject.getString(HANDLER_NAME_STR):null);
                m.setCallbackId(jsonObject.has(CALLBACK_ID_STR) ? jsonObject.getString(CALLBACK_ID_STR):null);
                m.setResponseData(jsonObject.has(RESPONSE_DATA_STR) ? jsonObject.getString(RESPONSE_DATA_STR):null);
                m.setResponseId(jsonObject.has(RESPONSE_ID_STR) ? jsonObject.getString(RESPONSE_ID_STR):null);
                m.setData(jsonObject.has(DATA_STR) ? jsonObject.getString(DATA_STR):null);
                list.add(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<Message> newJsonList = new Gson().fromJson(jsonStr, new TypeToken<List<Message>>(){}.getType());
        List<Message> newList = list;
        Log.d("MyLog", newJsonList.toString());

        return list;
    }
}
