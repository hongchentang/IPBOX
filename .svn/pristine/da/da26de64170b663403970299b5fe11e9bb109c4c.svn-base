package com.hcis.ipr.oauth.utils;

import com.hcis.ipanther.core.utils.StringUtils;

/**
 * @author zhw
 * @date 2019/11/27
 **/
public class ResolveUtils {

    public static String resolve(String source, String split, Integer model){
        if(StringUtils.isNotEmpty(source) && model != null){
            String[] strs = source.split(split);

            if(model == 0){
                if(strs.length > 0){
                    return strs[0];
                }
            }else if(model == 1){
                if(strs.length > 1){
                    return strs[1];
                }
            }

        }

        return "";
    }
}
