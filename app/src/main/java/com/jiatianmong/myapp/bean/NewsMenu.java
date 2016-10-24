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
public class NewsMenu {

	public int error_code;
	public String reason;
	public Result result;

	@Override
	public String toString() {
		return "NewsMenu{" +
				"error_code=" + error_code +
				", reason='" + reason + '\'' +
				", result=" + result +
				'}';
	}

	public class Result {
		@Override
		public String toString() {
			return "result{" +
					"stat='" + stat + '\'' +
					", data=" + data +
					'}';
		}

		public String stat;

		public ArrayList<NewsTabData> data;



	}

	// 页签的对象
	public class NewsTabData {
		public String author_name;
		public String date;
		public String realtype;
		public String thumbnail_pic_s;
		public String thumbnail_pic_s02;
		public String thumbnail_pic_s03;
		public String title;
		public String type;
		public String uniquekey;
		public String url;

		@Override
		public String toString() {
			return "NewsTabData{" +
					"author_name='" + author_name + '\'' +
					", date='" + date + '\'' +
					", realtype='" + realtype + '\'' +
					", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
					", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
					", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
					", title='" + title + '\'' +
					", type='" + type + '\'' +
					", uniquekey='" + uniquekey + '\'' +
					", url='" + url + '\'' +
					'}';
		}
	}

}
