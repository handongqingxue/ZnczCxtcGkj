package com.znczCxtcGkj.entity;

public class HaoMa {

	private Long id;//号码id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHm() {
		return hm;
	}
	public void setHm(String hm) {
		this.hm = hm;
	}
	public Integer getPdh() {
		return pdh;
	}
	public void setPdh(Integer pdh) {
		this.pdh = pdh;
	}
	public String getPrsj() {
		return prsj;
	}
	public void setPrsj(String prsj) {
		this.prsj = prsj;
	}
	public Integer getHmztId() {
		return hmztId;
	}
	public void setHmztId(Integer hmztId) {
		this.hmztId = hmztId;
	}
	public String getHmztMc() {
		return hmztMc;
	}
	public void setHmztMc(String hmztMc) {
		this.hmztMc = hmztMc;
	}
	public Integer getFl() {
		return fl;
	}
	public void setFl(Integer fl) {
		this.fl = fl;
	}
	public String getKsjhsj() {
		return ksjhsj;
	}
	public void setKsjhsj(String ksjhsj) {
		this.ksjhsj = ksjhsj;
	}
	public Integer getDlId() {
		return dlId;
	}
	public void setDlId(Integer dlId) {
		this.dlId = dlId;
	}
	public String getDlMc() {
		return dlMc;
	}
	public void setDlMc(String dlMc) {
		this.dlMc = dlMc;
	}
	public Long getDdId() {
		return ddId;
	}
	public void setDdId(Long ddId) {
		this.ddId = ddId;
	}
	public String getClCph() {
		return clCph;
	}
	public void setClCph(String clCph) {
		this.clCph = clCph;
	}
	private String hm;//号码
	private Integer pdh;//排队号	
	private String prsj;//排入时间
	private Integer hmztId;//号码状态 1.排队中2.已完成3.已过号4.取消5.叫号中6.受理中
	private String hmztMc;//号码状态名称
	private Integer fl;//分类 1.普通 2.其他
	private String ksjhsj;//开始叫号时间
	private Integer dlId;//队列id
	private String dlMc;//队列名称
	private Long ddId;//订单id
	private String clCph;

}
