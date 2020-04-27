<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--用iframe来获取图片，避免不同的流程图大小不一致导致流程节点定位错乱问题 --%>
<iframe frameBorder="0" width="100%" height="${empty height?'95%':height}" scrolling="yes" src="${activiti_explorer_url}/service/flow/trace?app=${app}&procInstId=${procInstId}">
</iframe>