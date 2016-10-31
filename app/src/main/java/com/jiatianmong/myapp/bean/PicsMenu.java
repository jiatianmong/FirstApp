package com.jiatianmong.myapp.bean;

import java.util.ArrayList;

/**
 * 分类信息封装
 * 
 * 使用Gson解析时,对象书写技巧: 1. 逢{}创建对象, 逢[]创建集合(ArrayList) 2. 所有字段名称要和json返回字段高度一致
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class PicsMenu {

	public String bdFmtDispNum;
	public String bdIsClustered;

	@Override
	public String toString() {
		return "PicsMenu{" +
				"bdFmtDispNum='" + bdFmtDispNum + '\'' +
				", bdIsClustered='" + bdIsClustered + '\'' +
				", bdSearchTime='" + bdSearchTime + '\'' +
				", data=" + data +
				", simid='" + simid + '\'' +
				", ObjURL='" + ObjURL + '\'' +
				", width='" + width + '\'' +
				", token='" + token + '\'' +
				'}';
	}

	public String bdSearchTime;
	public ArrayList<PicsTabData> data;
	// 页签的对象
	public class PicsTabData {
		public String bdSourceName;
		public String fromPageTitle;
		public String fromPageTitleEnc;

		@Override
		public String toString() {
			return "PicsTabData{" +
					"bdSourceName='" + bdSourceName + '\'' +
					", fromPageTitle='" + fromPageTitle + '\'' +
					", fromPageTitleEnc='" + fromPageTitleEnc + '\'' +
					", fromURL='" + fromURL + '\'' +
					", middleURL='" + middleURL + '\'' +
					", replaceUrl=" + replaceUrl +
					'}';
		}

		public String fromURL;
		public String middleURL;



		public ArrayList<REPLACEURL> replaceUrl;
	}

	private class REPLACEURL {
		public String FromURL;
		public String ObjURL;

		@Override
		public String toString() {
			return "REPLACEURL{" +
					"FromURL='" + FromURL + '\'' +
					", ObjURL='" + ObjURL + '\'' +
					'}';
		}
	}

	public String simid;
	public String ObjURL;
	public String width;
	public String token;
}
