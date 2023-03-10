package cn.jasonhu.commons.Utils;

import cn.jasonhu.commons.dto.CompareJsonResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;

public class CompareObjectUtils {

    private static final String OLD_VALUE = "oldValue";
    private static final String NEW_VALUE = "newValue";

    /**
     * @param oldStr 源JSONStr
     * @param newStr 目标JSONStr
     * @return
     */
    public static List<CompareJsonResult> compareJsonObjectWithStr(String oldStr, String newStr) {
        //将字符串转换为json对象
        JSON oldJson = JSON.parseObject(oldStr);
        JSON newJson = JSON.parseObject(newStr);
        return compareJsonObjectWithJSON(oldJson, newJson, new ArrayList<>());
    }

    /**
     * @param oldJson        源JSON
     * @param newJson        目标JSON
     * @param notContainList 不包含的字段
     * @return
     */
    public static List<CompareJsonResult> compareJsonObjectWithJSON(JSON oldJson, JSON newJson,
            List<String> notContainList) {
        //递归遍历json对象所有的key-value，将其封装成path:value格式进行比较
        Map<String, Object> oldMap = new LinkedHashMap<>();
        Map<String, Object> newMap = new LinkedHashMap<>();
        convertJsonToMap(oldJson, "", oldMap);
        convertJsonToMap(newJson, "", newMap);
        Map<String, Object> differenceMap = compareMap(oldMap, newMap);
        //将最终的比较结果把不相同的转换为json对象返回
        List<CompareJsonResult> compareJsonResult = convertMapToJson(differenceMap, notContainList);
        return compareJsonResult;
    }

    /**
     * 比较对象，获取所有比较后的字段
     *
     * @param oldObject
     * @param newObject
     * @param <T>
     * @return
     */
    public static <T> List<CompareJsonResult> compareJsonObject(T oldObject, T newObject) {
        return compareJsonObject(oldObject, newObject, new ArrayList<>());
    }

    /**
     * 比较对象，获取比较修改并且过滤后的字段
     *
     * @param oldObject
     * @param newObject
     * @param notContainList
     * @param <T>
     * @return
     */
    public static <T> List<CompareJsonResult> compareJsonObject(T oldObject, T newObject,
            List<String> notContainList) {
        //将字符串转换为json对象
        JSON oldJson = toJSON(oldObject);
        JSON newJson = toJSON(newObject);
        return compareJsonObjectWithJSON(oldJson, newJson, notContainList);
    }

    /**
     * 对象转JSON
     *
     * @param obj
     * @param <T>
     * @return
     */
    private static <T> JSON toJSON(T obj) {
        return JSON.parseObject(JSON.toJSONString(obj));
    }

    /**
     * 将json数据转换为map存储用于比较
     */
    private static void convertJsonToMap(Object json, String root, Map<String, Object> resultMap) {
        if (json instanceof JSONObject) {
            JSONObject jsonObject = ((JSONObject) json);
            Iterator iterator = jsonObject.keySet().iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = jsonObject.get(key);
                String newRoot = "".equals(root) ? key + "" : root + "." + key;
                if (value instanceof JSONObject || value instanceof JSONArray) {
                    convertJsonToMap(value, newRoot, resultMap);
                } else {
                    resultMap.put(newRoot, value);
                }
            }
        } else if (json instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) json;
            for (int i = 0; i < jsonArray.size(); i++) {
                Object vaule = jsonArray.get(i);
                String newRoot = "".equals(root) ? "[" + i + "]" : root + ".[" + i + "]";
                if (vaule instanceof JSONObject || vaule instanceof JSONArray) {
                    convertJsonToMap(vaule, newRoot, resultMap);
                } else {
                    resultMap.put(newRoot, vaule);
                }
            }
        }
    }

    /**
     * 比较两个map，返回不同数据
     */
    private static Map<String, Object> compareMap(Map<String, Object> oldMap,
            Map<String, Object> newMap) {
        //遍历newMap，将newMap的不同数据装进oldMap，同时删除oldMap中与newMap相同的数据
        campareNewToOld(oldMap, newMap);
        //將舊的有新的沒有的數據封裝數據結構存在舊的裡面
        campareOldToNew(oldMap);
        return oldMap;
    }

    /**
     * 將舊的有新的沒有的數據封裝數據結構存在舊的裡面
     */
    private static void campareOldToNew(Map<String, Object> oldMap) {
        //统一oldMap中newMap不存在的数据的数据结构，便于解析
        for (Iterator<Map.Entry<String, Object>> it = oldMap.entrySet().iterator();
                it.hasNext(); ) {
            Map.Entry<String, Object> item = it.next();
            String key = item.getKey();
            Object value = item.getValue();
            int lastIndex = key.lastIndexOf(".");
            if (!(value instanceof Map)) {
                Map<String, Object> differenceMap = new HashMap<>();
                differenceMap.put(OLD_VALUE, value);
                differenceMap.put(NEW_VALUE, "");
                oldMap.put(key, differenceMap);
            }
        }
    }

    /**
     * 將新的map與舊的比較，並將數據統一存在舊的裡面
     */
    private static void campareNewToOld(Map<String, Object> oldMap, Map<String, Object> newMap) {
        for (Iterator<Map.Entry<String, Object>> it = newMap.entrySet().iterator();
                it.hasNext(); ) {
            Map.Entry<String, Object> item = it.next();
            String key = item.getKey();
            Object newValue = item.getValue();
            Map<String, Object> differenceMap = new HashMap<>();
            int lastIndex = key.lastIndexOf(".");
            String lastPath = key.substring(lastIndex + 1).toLowerCase();
            if (oldMap.containsKey(key)) {
                Object oldValue = oldMap.get(key);
                if (newValue.equals(oldValue)) {
                    oldMap.remove(key);
                    continue;
                } else {
                    differenceMap.put(OLD_VALUE, oldValue);
                    differenceMap.put(NEW_VALUE, newValue);
                    oldMap.put(key, differenceMap);
                }
            } else {
                differenceMap.put(OLD_VALUE, "");
                differenceMap.put(NEW_VALUE, newValue);
                oldMap.put(key, differenceMap);
            }
        }
    }

    /**
     * 将已经找出不同数据的map根据key的层级结构封装成json返回
     *
     * @return
     */
    private static List<CompareJsonResult> convertMapToJson(Map<String, Object> map,
            List<String> notContainList) {
        JSONObject resultJSONObject = new JSONObject();
        for (Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> item = it.next();
            String key = item.getKey();
            Object value = item.getValue();
            String[] paths = key.split("\\.");
            int i = 0;
            Object remarkObject = null;//用於深度標識對象
            int indexAll = paths.length - 1;
            while (i <= paths.length - 1) {
                String path = paths[i];
                if (i == 0) {
                    //初始化对象标识
                    if (resultJSONObject.containsKey(path)) {
                        remarkObject = resultJSONObject.get(path);
                    } else {
                        if (indexAll > i) {
                            if (paths[i + 1].matches("\\[[0-9]+\\]")) {
                                remarkObject = new JSONArray();
                            } else {
                                remarkObject = new JSONObject();
                            }
                            resultJSONObject.put(path, remarkObject);
                        } else {
                            resultJSONObject.put(path, value);
                        }
                    }
                    i++;
                    continue;
                }
                if (path.matches("\\[[0-9]+\\]")) {//匹配集合对象
                    int startIndex = path.lastIndexOf("[");
                    int endIndext = path.lastIndexOf("]");
                    int index = Integer.parseInt(path.substring(startIndex + 1, endIndext));
                    if (indexAll > i) {
                        if (paths[i + 1].matches("\\[[0-9]+\\]")) {
                            while (((JSONArray) remarkObject).size() <= index) {
                                if (((JSONArray) remarkObject).size() == index) {
                                    ((JSONArray) remarkObject).add(index, new JSONArray());
                                } else {
                                    ((JSONArray) remarkObject).add(null);
                                }
                            }
                        } else {
                            while (((JSONArray) remarkObject).size() <= index) {
                                if (((JSONArray) remarkObject).size() == index) {
                                    ((JSONArray) remarkObject).add(index, new JSONObject());
                                } else {
                                    ((JSONArray) remarkObject).add(null);
                                }
                            }
                        }
                        remarkObject = ((JSONArray) remarkObject).get(index);
                    } else {
                        while (((JSONArray) remarkObject).size() <= index) {
                            if (((JSONArray) remarkObject).size() == index) {
                                ((JSONArray) remarkObject).add(index, value);
                            } else {
                                ((JSONArray) remarkObject).add(null);
                            }
                        }
                    }
                } else {
                    if (indexAll > i) {
                        if (paths[i + 1].matches("\\[[0-9]+\\]")) {
                            if (!((JSONObject) remarkObject).containsKey(path)) {
                                ((JSONObject) remarkObject).put(path, new JSONArray());
                            }
                        } else {
                            if (!((JSONObject) remarkObject).containsKey(path)) {
                                ((JSONObject) remarkObject).put(path, new JSONObject());
                            }
                        }
                        remarkObject = ((JSONObject) remarkObject).get(path);
                    } else {
                        ((JSONObject) remarkObject).put(path, value);
                    }
                }
                i++;
            }
        }
        return dealResultWithJsonObject(resultJSONObject, notContainList);
    }

    /**
     * 将数据转换为对象
     *
     * @param resultJSONObject
     * @param notContainList
     * @return
     */
    private static List<CompareJsonResult> dealResultWithJsonObject(JSONObject resultJSONObject,
            List<String> notContainList) {
        List<CompareJsonResult> resultList = new ArrayList<>();

        Map<String, Object> objectMap = resultJSONObject.getInnerMap();
        for (String key : objectMap.keySet()) {
            if (!notContainList.contains(key)) {
                CompareJsonResult result = new CompareJsonResult();
                result.setKey(key);
                Object valObj = objectMap.get(key);
                Map<String, Object> valueMap = JSON.parseObject(JSON.toJSONString(valObj));
                if (valueMap.containsKey(OLD_VALUE)) {
                    result.setOldValue(valueMap.get(OLD_VALUE).toString());
                }
                if (valueMap.containsKey(NEW_VALUE)) {
                    result.setNewValue(valueMap.get(NEW_VALUE).toString());
                }
                resultList.add(result);
            }
        }
        return resultList;
    }

    @Data
    @Accessors(chain = true)
    private static class TestCase {

        private String name;
        private String value;
    }

    public static void main(String[] args) {
        TestCase oldCase = new TestCase().setName("Jason").setValue("100");
        TestCase newCase = new TestCase().setName("Jason1").setValue("1000");
        System.out.println(CompareObjectUtils.compareJsonObject(oldCase, newCase));
    }
}
