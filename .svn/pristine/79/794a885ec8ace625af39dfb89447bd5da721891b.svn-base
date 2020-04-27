package com.hcis.ipanther.common.dict.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hcis.ipanther.common.dict.dao.DictEntryDao;
import com.hcis.ipanther.common.dict.dao.DictTypeDao;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.utils.PropertiesLoader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;

public class DictionaryUtils
{
  private static DictTypeDao dictTypeDao = (DictTypeDao)BeanLocator.getBean("dictTypeDao");

  private static DictEntryDao dictEntryDao = (DictEntryDao)BeanLocator.getBean("dictEntryDao");

  private static RedisTemplate redisTemplate = (RedisTemplate)BeanLocator.getBean("redisTemplate");

  private static String appName = PropertiesLoader.get("dictionary.app.key");

  public static void init(String dictTypeCode) {
    redisTemplate.setValueSerializer(redisTemplate.getDefaultSerializer());
    ValueOperations valueOper = redisTemplate.opsForValue();
    String key = "dictEntryMap_" + appName + "_" + dictTypeCode;
    redisTemplate.delete(key);
  }

  public static void init(String[] dictTypeCodes) {
    redisTemplate.setValueSerializer(redisTemplate.getDefaultSerializer());
    ValueOperations valueOper = redisTemplate.opsForValue();
    List keys = Lists.newArrayList();
    for (String dictTypeCode : dictTypeCodes) {
      String key = "dictEntryMap_" + appName + "_" + dictTypeCode;
      if (!keys.contains(key)) {
        keys.add(key);
      }
    }
    redisTemplate.delete(keys);
  }

  public static Map<String, DictEntry> getEntryMap(String dictTypeCode) {
    Map entryMap = Maps.newHashMap();
    if (StringUtils.isNotBlank(dictTypeCode)) {
      redisTemplate.setHashValueSerializer(new JacksonJsonRedisSerializer(DictEntry.class));
      HashOperations hashOper = redisTemplate.opsForHash();
      String key = "dictEntryMap_" + appName + "_" + dictTypeCode;
      if (redisTemplate.hasKey(key).booleanValue()) {
        entryMap = hashOper.entries(key);
      } else {
        DictEntry dictEntry = new DictEntry();
        dictEntry.setDictTypeCode(dictTypeCode);
        entryMap = dictEntryDao.selectByObjectForMap(dictEntry);
        if ((entryMap != null) && (!entryMap.isEmpty())) {
          hashOper.putAll(key, entryMap);
        }
      }
    }
    return entryMap;
  }

  public static Map<String, DictEntry> getEntryMap(String dictTypeCode, String parentValue) {
    Map<String,DictEntry> entryMap = getEntryMap(dictTypeCode);
    Map resultMap = Maps.newHashMap();
    for (DictEntry dictEntry : entryMap.values()) {
      if (parentValue.equals(dictEntry.getParentValue())) {
        resultMap.put(dictEntry.getDictValue(), dictEntry);
      }
    }
    return resultMap;
  }

  public static Map<String, DictEntry> getEntryMapNotParent(String dictTypeCode) {
    Map<String,DictEntry> entryMap = getEntryMap(dictTypeCode);
    Map resultMap = Maps.newHashMap();
    for (DictEntry dictEntry : entryMap.values()) {
      if (StringUtils.isEmpty(dictEntry.getParentValue())) {
        resultMap.put(dictEntry.getDictValue(), dictEntry);
      }
    }
    return resultMap;
  }

  public static List<DictEntry> getEntryList(String dictTypeCode) {
    Map entryMap = getEntryMap(dictTypeCode);
    return getSortEntryList(entryMap);
  }

  public static List<DictEntry> getEntryListNotParent(String dictTypeCode) {
    Map entryMap = getEntryMapNotParent(dictTypeCode);
    return getSortEntryList(entryMap);
  }

  public static List<DictEntry> getEntryList(String dictTypeCode, String parentValue) {
    Map entryMap = getEntryMap(dictTypeCode, parentValue);
    return getSortEntryList(entryMap);
  }

  private static List<DictEntry> getSortEntryList(Map<String, DictEntry> entryMap) {
    List entryList = Lists.newArrayList();
    for (DictEntry dictEntry : entryMap.values()) {
      entryList.add(dictEntry);
    }
    Collections.sort(entryList, new Comparator<DictEntry>() {
      public int compare(DictEntry o1, DictEntry o2) {
        if ((o1.getSortNo() != null) && (o2.getSortNo() != null)) {
          int result = o1.getSortNo().compareTo(o2.getSortNo());
          if (result != 0) {
            return result;
          }
        }
        return o1.getDictValue().compareTo(o2.getDictValue());
      }
    });
    return entryList;
  }

  public static String getEntryOptions(String dictTypeCode) {
    List entryList = getEntryList(dictTypeCode);
    return getOptionsString(entryList);
  }

  public static String getEntryOptionsNotParent(String dictTypeCode) {
    List entryList = getEntryListNotParent(dictTypeCode);
    return getOptionsString(entryList);
  }

  public static String getEntryOptions(String dictTypeCode, String parentValue) {
    List entryList = getEntryList(dictTypeCode, parentValue);
    return getOptionsString(entryList);
  }

  private static String getOptionsString(List<DictEntry> entryList) {
    StringBuffer entryOptions = new StringBuffer();
    for (DictEntry entry : entryList) {
      if (!ObjectUtils.toString(entry.getIsHidden()).equals("Y")) {
        entryOptions.append("<option value='").append(entry.getDictValue()).append("'>");
        entryOptions.append(entry.getDictName()).append("</option>");
      }
    }
    return entryOptions.toString();
  }

  public static String getEntryOptionsSelected(String dictTypeCode, String defaultValue) {
    List entryList = getEntryList(dictTypeCode);
    return getOptionsSelectedString(defaultValue, entryList);
  }

  public static String getEntryOptionsSelected(String dictTypeCode, String defaultValue, int toIndex)
  {
    List entryList = getEntryList(dictTypeCode);
    entryList = entryList.subList(0, toIndex);
    return getOptionsSelectedString(defaultValue, entryList);
  }

  public static String getEntryOptionsSelectedNotParent(String dictTypeCode, String defaultValue) {
    List entryList = getEntryListNotParent(dictTypeCode);
    return getOptionsSelectedString(defaultValue, entryList);
  }

  public static String getEntryOptionsSelected(String dictTypeCode, String parentValue, String defaultValue) {
    List entryList = getEntryList(dictTypeCode, parentValue);
    return getOptionsSelectedString(defaultValue, entryList);
  }

  private static String getOptionsSelectedString(String defaultValue, List<DictEntry> entryList) {
    StringBuffer entryOptions = new StringBuffer();
    for (DictEntry entry : entryList) {
      if (!ObjectUtils.toString(entry.getIsHidden()).equals("Y")) {
        entryOptions.append("<option value='").append(entry.getDictValue()).append("'");
        if ((StringUtils.isNotEmpty(defaultValue)) && (defaultValue.equals(entry.getDictValue()))) {
          entryOptions.append(" selected ");
        }
        entryOptions.append(">").append(entry.getDictName()).append("</option>");
      }
    }
    return entryOptions.toString();
  }

  public static String getEntryName(String dictTypeCode, String dictValue) {
    Map entryMap = getEntryMap(dictTypeCode);
    if ((entryMap != null) && (entryMap.containsKey(dictValue))) {
      return ((DictEntry)entryMap.get(dictValue)).getDictName();
    }
    return "";
  }

  public static String getEntryValue(String dictTypeCode, String dictEntryName) {
    Map<String,DictEntry> entryMap = getEntryMap(dictTypeCode);
    if (entryMap != null) {
      for (DictEntry dictEntry : entryMap.values()) {
        if (dictEntry.getDictName().equals(dictEntryName)) {
          return dictEntry.getDictValue();
        }
      }
    }
    return "";
  }

  public static DictEntry getEntry(String dictTypeCode, String dictValue) {
    Map entryMap = getEntryMap(dictTypeCode);
    if ((entryMap != null) && (entryMap.containsKey(dictValue))) {
      return (DictEntry)entryMap.get(dictValue);
    }
    return null;
  }

  public static String getAllParentName(String dictTypeCode, String dictValue) {
    DictEntry dictEntry = getEntry(dictTypeCode, dictValue);
    StringBuilder parentNameSum = new StringBuilder();
    while ((dictEntry != null) && (StringUtils.isNotEmpty(dictEntry.getParentValue()))) {
      dictEntry = getEntry(dictEntry.getDictTypeCode(), dictEntry.getParentValue());
      parentNameSum.append(dictEntry.getDictName() + ",");
    }
    return StringUtils.removeEnd(parentNameSum.toString(), ",");
  }

  public static String getAllParentValue(String dictTypeCode, String dictValue) {
    DictEntry dictEntry = getEntry(dictTypeCode, dictValue);
    List dictValues = Lists.newArrayList();
    while ((dictEntry != null) && (StringUtils.isNotEmpty(dictEntry.getParentValue()))) {
      dictEntry = getEntry(dictEntry.getDictTypeCode(), dictEntry.getParentValue());
      dictValues.add(dictEntry.getDictValue());
    }

    if (!dictValues.isEmpty()) {
      Collections.reverse(dictValues);
      try {
        return JsonUtil.toJson(dictValues);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return "{}";
  }
}