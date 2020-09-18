package top.auntie.cms.util;

import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;
import org.springframework.util.StringUtils;
import top.auntie.cms.exception.CommonException;

import java.util.List;
import java.util.Map;

public class ParamsUtil {

    public static void checkParamsExist(Map params, String... keysToCheck) throws CommonException {
        String value = null;
        List<String> emptyParams = Lists.newArrayList();
        for (String key: keysToCheck) {
            value = MapUtils.getString(params, key, "");
            if (StringUtils.isEmpty(value)) {
                emptyParams.add(key);
            }
        }
        if (!emptyParams.isEmpty()){
            StringBuffer sb = new StringBuffer("参数错误：");
            for (String key: emptyParams) {
                sb.append("["+key+"] ");
            }
            sb.append("不能为空");
            throw new CommonException(sb.toString());
        }
    }

}
