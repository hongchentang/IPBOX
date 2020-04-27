package com.hcis.ipr.cms.info.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hcis.ipanther.core.entity.BaseEntity;

public class CmsInfo extends BaseEntity{

	private static final long serialVersionUID = -5862255892428794582L;

	private String siteId;

    private String channelId;

    private String title;

    private String shorTtitle;

    private String titleColor;

    private String titleBlod;

    private String source;

    private String author;

    private String description;

    private String tags;

    private String img;

    private String url;

    private String attchs;

    private Date addTime;

    private String templetId;

    private String isHot;

    private String isTop;

    private Date topEndTime;

    private Long clickNum;

    private String addUser;

    private String state;

    private String isSign;

    private String video;

    private String isComment;

    private String openTimeType;

    private Date openEndTime;

    private String openType;

    private String htmlIndexNum;

    private String isImgs;

    private String auditState;

    private String content;
    
    private String channelName;
    
    private String templetName;
    
    private List<String>  imgTitle;

    private List<Long>  imgOrder;
    
    private String channelFolder;
    
    private String htmlFileName;
    
    private String channelPageMark;
	private long channelIndexNum;
    private String channelParentId;
    private String channelParPageMark;
    
    private List<String> channelIds;
    
    private String pageUrl;
    
    private String sitePath;
    
    private String type;//公告类型 InfoContants.CMS_INFO_

    private String regionsCode;//区域编码
    
    private String dateFormat;
    
    private String showTitle;//列表显示的标题
    private int showTitleLen;//列表显示的标题长度
    
    private String isNew;
    
    private String courseId;//课程id
     
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId == null ? null : siteId.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getShorTtitle() {
    	if (showTitleLen>0) {
			if (StringUtils.isEmpty(shorTtitle)) {
				shorTtitle=title;
			}
			if (shorTtitle.length()>showTitleLen) {
				shorTtitle=shorTtitle.substring(0,showTitleLen);
			}
		}
        return shorTtitle;
    }

    public void setShorTtitle(String shorTtitle) {
        this.shorTtitle = shorTtitle == null ? null : shorTtitle.trim();
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor == null ? null : titleColor.trim();
    }

    public String getTitleBlod() {
        return titleBlod;
    }

    public void setTitleBlod(String titleBlod) {
        this.titleBlod = titleBlod == null ? null : titleBlod.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAttchs() {
        return attchs;
    }

    public void setAttchs(String attchs) {
        this.attchs = attchs == null ? null : attchs.trim();
    }

    public Date getAddTime() {
    	/*if(addTime==null){
    		addTime=new Date();
    	}*/
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getTempletId() {
        return templetId;
    }

    public void setTempletId(String templetId) {
        this.templetId = templetId == null ? null : templetId.trim();
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot == null ? null : isHot.trim();
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop == null ? null : isTop.trim();
    }

    public Date getTopEndTime() {
        return topEndTime;
    }

    public void setTopEndTime(Date topEndTime) {
        this.topEndTime = topEndTime;
    }

    public Long getClickNum() {
        return clickNum;
    }

    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser == null ? null : addUser.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
        this.isSign = isSign == null ? null : isSign.trim();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment == null ? null : isComment.trim();
    }

    public String getOpenTimeType() {
        return openTimeType;
    }

    public void setOpenTimeType(String openTimeType) {
        this.openTimeType = openTimeType == null ? null : openTimeType.trim();
    }

    public Date getOpenEndTime() {
        return openEndTime;
    }

    public void setOpenEndTime(Date openEndTime) {
        this.openEndTime = openEndTime;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    public String getHtmlIndexNum() {
        return htmlIndexNum;
    }

    public void setHtmlIndexNum(String htmlIndexNum) {
        this.htmlIndexNum = htmlIndexNum == null ? null : htmlIndexNum.trim();
    }

    public String getIsImgs() {
        return isImgs;
    }

    public void setIsImgs(String isImgs) {
        this.isImgs = isImgs == null ? null : isImgs.trim();
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState == null ? null : auditState.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getTempletName() {
		if(templetId!=null){
			if(StringUtils.contains(templetId,"/")){
				templetName=StringUtils.substringAfterLast(templetId, "/");
			}else{
				templetName=templetId;
			}
		}
		return templetName;
	}

	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}

	public List<String> getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(List<String> imgTitle) {
		this.imgTitle = imgTitle;
	}

	public List<Long> getImgOrder() {
		return imgOrder;
	}

	public void setImgOrder(List<Long> imgOrder) {
		this.imgOrder = imgOrder;
	}

	public String getChannelFolder() {
		if (channelPageMark!=null && channelPageMark.trim().length()>0) {
			channelFolder=channelPageMark;
		}else if (channelIndexNum>0) {
			channelFolder=String.valueOf(channelIndexNum);
		}else {
			channelFolder=channelId;
		}
		return channelFolder;
	}

	public void setChannelFolder(String channelFolder) {
		this.channelFolder = channelFolder;
	}

	public String getHtmlFileName() {
		if (htmlIndexNum!=null) {
			htmlFileName=htmlIndexNum;
		}else {
			htmlFileName=id;
		}
		return htmlFileName;
	}

	public void setHtmlFileName(String htmlFileName) {
		this.htmlFileName = htmlFileName;
	}

	public String getChannelParentId() {
		return channelParentId;
	}

	public void setChannelParentId(String channelParentId) {
		this.channelParentId = channelParentId;
	}

	public String getChannelPageMark() {
		return channelPageMark;
	}

	public void setChannelPageMark(String channelPageMark) {
		this.channelPageMark = channelPageMark;
	}

	public long getChannelIndexNum() {
		return channelIndexNum;
	}

	public void setChannelIndexNum(long channelIndexNum) {
		this.channelIndexNum = channelIndexNum;
	}

	public String getChannelParPageMark() {
		return channelParPageMark;
	}

	public void setChannelParPageMark(String channelParPageMark) {
		this.channelParPageMark = channelParPageMark;
	}

	public String getPageUrl() {
		//判断是否有外部链接
		if (url!=null && url.trim().length()>0) {
			pageUrl=url;
		}else {
			SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
			SimpleDateFormat mmFormat=new SimpleDateFormat("MM");
			pageUrl=(sitePath!=null?sitePath:"")+getChannelFolder()+"/info/"+(yearFormat.format(getAddTime()))+"/"+(mmFormat.format(getAddTime()))+"/"+getHtmlFileName()+".html";
		}
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getSitePath() {
		return sitePath;
	}

	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public List<String> getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(List<String> channelIds) {
		this.channelIds = channelIds;
	}

	public String getShowTitle() {
		if (StringUtils.isEmpty(showTitle)) {
			//默认为标题
			showTitle=this.title;
			//判断是否有短标题
			if (StringUtils.isNotEmpty(shorTtitle)) {
				showTitle=shorTtitle;
			}
			//判断标题长度
			if (showTitleLen>0 && showTitle.length()>showTitleLen) {
				showTitle=showTitle.substring(0, showTitleLen);
			}
			//添加标题颜色
			showTitle="<font color='"+titleColor+"'>"+showTitle+"</font>";
			//判断是否粗体
			if ("01".equals(titleBlod)) {
				showTitle="<b>"+showTitle+"</b>";
			}
		}
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public int getShowTitleLen() {
		return showTitleLen;
	}

	public void setShowTitleLen(int showTitleLen) {
		this.showTitleLen = showTitleLen;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegionsCode() {
		return regionsCode;
	}

	public void setRegionsCode(String regionsCode) {
		this.regionsCode = regionsCode;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
}