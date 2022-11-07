package com.znczCxtcGkj.entity;

public class HaoMaZhuangTai {
	
	public static final String PAI_DUI_ZHONG_TEXT="排队中";
	public static final String JIAO_HAO_ZHONG_TEXT="叫号中";
	public static final String YI_GUO_HAO_TEXT="已过号";
	public static final String SHOU_LI_ZHONG_TEXT="受理中";
	public static final String YI_WAN_CHENG_TEXT="已完成";
	public static final String QU_XIAO_TEXT="取消";
			
	private Integer id;//号码状态id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public Integer getPx() {
		return px;
	}
	public void setPx(Integer px) {
		this.px = px;
	}
	private String mc;//名称
	private Integer px;//排序

}
