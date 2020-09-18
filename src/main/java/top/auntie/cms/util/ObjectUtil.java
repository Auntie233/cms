package top.auntie.cms.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@Component
public class ObjectUtil {

    private static final Log logger = LogFactory.getLog(ObjectUtil.class);

    public static Map<String, Object> objectToMap(Object obj) {
        try {
            if (obj == null) {
                return new HashMap<>();
            }
            if (obj instanceof Map) {
                return (Map<String, Object>) obj;
            }
            Map<String, Object> map = new HashMap<>();
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                map.put(fieldName, value);
            }
            return map;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new HashMap<>();
        }
    }

    public static Map<String, Object> objectToMap(Object obj, Boolean isUnderline) {
        try {
            if (obj==null) {
                return new HashMap<>();
            }
            if (obj instanceof Map){
                return (Map<String, Object>) obj;
            }
            Map<String, Object> map = new HashMap<>();
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                if (isUnderline) {
                    fieldName = camelToUnderline(fieldName);
                }
                map.put(fieldName, value);
            }
            return map;
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    private static String camelToUnderline(String fieldName) {
        if (fieldName==null||"".equals(fieldName.trim())){
            return "";
        }
        int len=fieldName.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=fieldName.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static <T> T mapToObject(Map map, Class<T> clazz, boolean isUnderLine) throws Exception {
        Map<String,Object> transMap = Maps.newTreeMap();
        if (isUnderLine){
            for (Object key: map.keySet()) {
                transMap.put(underlineToCamel(key.toString()), map.get(key));
            }
        } else {
            transMap = map;
        }
        T obj = clazz.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor: propertyDescriptors){
            Method setter = propertyDescriptor.getWriteMethod();
            if (setter!=null){
                try {
                    Class propertiesClass = propertyDescriptor.getPropertyType();
                    Object property = transMap.get(propertyDescriptor.getName());
                    if (ClassUtils.isAssignable(propertiesClass,Date.class)&&(property instanceof String)){
                        property = DateUtils.parseDate((String) property,new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","yyyy-MM-dd HH:mm:ss.S"});
                    } else if (ClassUtils.isAssignable(propertiesClass,Number.class)&&(property instanceof String)){
                        String propertyStr = (String) property;
                        property = NumberUtils.createNumber(StringUtils.isEmpty(propertyStr)?"0":propertyStr);
                    } else if (ClassUtils.isAssignable(propertiesClass,Boolean.class)&&(property instanceof String)){
                        String propertyStr = (String) property;
                        property = BooleanUtils.toBoolean(StringUtils.isEmpty(propertyStr)?"false":propertyStr);
                    } else if (ClassUtils.isInnerClass(propertiesClass)) {
                        property = mapToObject(objectToMap(property), propertiesClass, isUnderLine);
                    }
                    if (propertiesClass == BigDecimal.class){
                        setter.invoke(obj, new BigDecimal(String.valueOf(property)));
                    } else {
                        setter.invoke(obj, propertiesClass.cast(property));
                    }
                } catch (Exception e){
                    logger.error(setter.getName()+" failed", e);
                    throw e;
                }
            }
        }
        return obj;
    }

    public static String underlineToCamel(String underLineStr){
        if (StringUtils.isEmpty(underLineStr)){
            return "";
        }
        String[] strs = underLineStr.split("_");
        StringBuffer stringBuffer = new StringBuffer(strs[0].toLowerCase());
        for (int i=1;i<strs.length;i++){
            stringBuffer.append(StringUtils.capitalize(strs[i].toLowerCase()));
        }
        return stringBuffer.toString();
    }

    public static Map<String, Object> objectToMapWithDateFormat(Object obj, String format) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            if (value instanceof Date){
                value = DateFormatUtils.format((Date)value, format);
            }
            if (value == null){
                value = "";
            }
            map.put(fieldName, value);
        }
        return map;
    }

    public static void checkRequiredParams(Map map, Object... params) throws Exception {
        Object value = null;
        Set<String> errParams = new HashSet();
        for (Object param: params){
            value = map.get(param);
            if (map.get(param)==null||"".equals(value)){
                errParams.add(param.toString());
            }
        }
        if (errParams.isEmpty()){
            return;
        }
        throw new Exception("参数[" + StringUtils.join(errParams, ",") + "]不能为空");
    }

    public static List<Map<String,Object>> convertListMap(List<Object> list){
        List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
        for(Object obj:list){
            Class c = obj.getClass();
            Field[] f = c.getDeclaredFields();
            Map<String,Object> map=new HashMap<String, Object>();
            for(Field fie : f){
                try {
                    fie.setAccessible(true);
                    map.put(fie.getName(), fie.get(obj));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            for(Field fie : c.getSuperclass().getDeclaredFields()){
                try {
                    fie.setAccessible(true);
                    map.put(fie.getName(), fie.get(obj));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            maps.add(map);
        }
        return maps;
    }
}


