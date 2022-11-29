package com.znczCxtcGkj.entity;

public class DingDan {

	/**
	 * 待上磅
	 */
	public static final Integer DAI_SHANG_BANG=1;
	/**
	 * 上磅中
	 */
	public static final Integer SHANG_BANG_ZHONG=2;
	/**
	 * 待称重
	 */
	public static final Integer DAI_CHENG_ZHONG=3;
	/**
	 * 称重中
	 */
	public static final Integer CHENG_ZHONG_ZHONG=4;
	/**
	 * 待下磅
	 */
	public static final Integer DAI_XIA_BANG=5;
	/**
	 * 下磅中
	 */
	public static final Integer XIA_BANG_ZHONG=6;
	/**
	 * 已完成
	 */
	public static final Integer YI_WAN_CHENG=7;
	
	public static final Integer SONG_YUN=1;
	public static final Integer QU_YUN=2;
	
	private Long id;//订单id（主键）
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDdh() {
		return ddh;
	}
	public void setDdh(String ddh) {
		this.ddh = ddh;
	}
	public Integer getLxlx() {
		return lxlx;
	}
	public void setLxlx(Integer lxlx) {
		this.lxlx = lxlx;
	}
	public Float getYzxzl() {
		return yzxzl;
	}
	public void setYzxzl(Float yzxzl) {
		this.yzxzl = yzxzl;
	}
	public Float getSjzl() {
		return sjzl;
	}
	public void setSjzl(Float sjzl) {
		this.sjzl = sjzl;
	}
	public Float getZlceb() {
		return zlceb;
	}
	public void setZlceb(Float zlceb) {
		this.zlceb = zlceb;
	}
	public Integer getDdztId() {
		return ddztId;
	}
	public void setDdztId(Integer ddztId) {
		this.ddztId = ddztId;
	}
	public String getDdztMc() {
		return ddztMc;
	}
	public void setDdztMc(String ddztMc) {
		this.ddztMc = ddztMc;
	}
	public String getJhysrq() {
		return jhysrq;
	}
	public void setJhysrq(String jhysrq) {
		this.jhysrq = jhysrq;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Float getJszl() {
		return jszl;
	}
	public void setJszl(Float jszl) {
		this.jszl = jszl;
	}
	public Integer getBs() {
		return bs;
	}
	public void setBs(Integer bs) {
		this.bs = bs;
	}
	public Integer getKs() {
		return ks;
	}
	public void setKs(Integer ks) {
		this.ks = ks;
	}
	public Integer getCkcs() {
		return ckcs;
	}
	public void setCkcs(Integer ckcs) {
		this.ckcs = ckcs;
	}
	public Float getPz() {
		return pz;
	}
	public void setPz(Float pz) {
		this.pz = pz;
	}
	public Float getMz() {
		return mz;
	}
	public void setMz(Float mz) {
		this.mz = mz;
	}
	public Float getJz() {
		return jz;
	}
	public void setJz(Float jz) {
		this.jz = jz;
	}
	public Float getDfgbpz() {
		return dfgbpz;
	}
	public void setDfgbpz(Float dfgbpz) {
		this.dfgbpz = dfgbpz;
	}
	public Float getDfgbmz() {
		return dfgbmz;
	}
	public void setDfgbmz(Float dfgbmz) {
		this.dfgbmz = dfgbmz;
	}
	public Float getDfgbjz() {
		return dfgbjz;
	}
	public void setDfgbjz(Float dfgbjz) {
		this.dfgbjz = dfgbjz;
	}
	public String getDfbdzp() {
		return dfbdzp;
	}
	public void setDfbdzp(String dfbdzp) {
		this.dfbdzp = dfbdzp;
	}
	public String getDfgbsj() {
		return dfgbsj;
	}
	public void setDfgbsj(String dfgbsj) {
		this.dfgbsj = dfgbsj;
	}
	public String getEwm() {
		return ewm;
	}
	public void setEwm(String ewm) {
		this.ewm = ewm;
	}
	public Integer getYssId() {
		return yssId;
	}
	public void setYssId(Integer yssId) {
		this.yssId = yssId;
	}
	public String getYssMc() {
		return yssMc;
	}
	public void setYssMc(String yssMc) {
		this.yssMc = yssMc;
	}
	public Integer getWzlxId() {
		return wzlxId;
	}
	public void setWzlxId(Integer wzlxId) {
		this.wzlxId = wzlxId;
	}
	public String getWzlxMc() {
		return wzlxMc;
	}
	public void setWzlxMc(String wzlxMc) {
		this.wzlxMc = wzlxMc;
	}
	public Integer getWzId() {
		return wzId;
	}
	public void setWzId(Integer wzId) {
		this.wzId = wzId;
	}
	public String getWzMc() {
		return wzMc;
	}
	public void setWzMc(String wzMc) {
		this.wzMc = wzMc;
	}
	public Integer getFhdwId() {
		return fhdwId;
	}
	public void setFhdwId(Integer fhdwId) {
		this.fhdwId = fhdwId;
	}
	public String getFhdwMc() {
		return fhdwMc;
	}
	public void setFhdwMc(String fhdwMc) {
		this.fhdwMc = fhdwMc;
	}
	public Integer getShdwId() {
		return shdwId;
	}
	public void setShdwId(Integer shdwId) {
		this.shdwId = shdwId;
	}
	public String getShdwMc() {
		return shdwMc;
	}
	public void setShdwMc(String shdwMc) {
		this.shdwMc = shdwMc;
	}
	public Integer getCyclId() {
		return cyclId;
	}
	public void setCyclId(Integer cyclId) {
		this.cyclId = cyclId;
	}
	public String getCyclCph() {
		return cyclCph;
	}
	public void setCyclCph(String cyclCph) {
		this.cyclCph = cyclCph;
	}
	public Integer getCysjId() {
		return cysjId;
	}
	public void setCysjId(Integer cysjId) {
		this.cysjId = cysjId;
	}
	public String getCysjXm() {
		return cysjXm;
	}
	public void setCysjXm(String cysjXm) {
		this.cysjXm = cysjXm;
	}
	public String getCysjSfzh() {
		return cysjSfzh;
	}
	public void setCysjSfzh(String cysjSfzh) {
		this.cysjSfzh = cysjSfzh;
	}
	private String ddh;//订单号
	public Integer getXddztId() {
		return xddztId;
	}
	public void setXddztId(Integer xddztId) {
		this.xddztId = xddztId;
	}
	public String getXddztMc() {
		return xddztMc;
	}
	public void setXddztMc(String xddztMc) {
		this.xddztMc = xddztMc;
	}
	public Integer getYjzt() {
		return yjzt;
	}
	public void setYjzt(Integer yjzt) {
		this.yjzt = yjzt;
	}
	public Integer getXyjzt() {
		return xyjzt;
	}
	public void setXyjzt(Integer xyjzt) {
		this.xyjzt = xyjzt;
	}
	public Integer getEjzt() {
		return ejzt;
	}
	public void setEjzt(Integer ejzt) {
		this.ejzt = ejzt;
	}
	public Integer getXejzt() {
		return xejzt;
	}
	public void setXejzt(Integer xejzt) {
		this.xejzt = xejzt;
	}
	public String getBjsj() {
		return bjsj;
	}
	public void setBjsj(String bjsj) {
		this.bjsj = bjsj;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getCcsj() {
		return ccsj;
	}
	public void setCcsj(String ccsj) {
		this.ccsj = ccsj;
	}
	public Integer getYjbfh() {
		return yjbfh;
	}
	public void setYjbfh(Integer yjbfh) {
		this.yjbfh = yjbfh;
	}
	public Integer getEjbfh() {
		return ejbfh;
	}
	public void setEjbfh(Integer ejbfh) {
		this.ejbfh = ejbfh;
	}
	public Long getHmId() {
		return hmId;
	}
	public void setHmId(Long hmId) {
		this.hmId = hmId;
	}
	private Integer xddztId;//新订单状态id
	private String xddztMc;//新订单状态名称
	private Integer yjzt=0;//一检状态(1.待上磅 2.上磅中 3.已完成)
	private Integer xyjzt=0;//新一检状态(1.待上磅 2.上磅中 3.已完成)
	private Integer ejzt=0;//二检状态(1.待上磅 2.上磅中 3.已完成)
	private Integer xejzt=0;//新二检状态(1.待上磅 2.上磅中 3.已完成)
	private Integer lxlx;//流向类型
	private Float yzxzl;//预装卸重量
	private Float sjzl;//实际重量
	private Float zlceb;//重量差额比
	private Integer ddztId;//订单状态id
	private String ddztMc;//订单状态名称
	private String jhysrq;//计划运输日期
	private String bz;//备注
	private Float jszl;//实际重量
	private Integer bs;//包数
	private Integer ks;//块数
	private Integer ckcs=0;//出卡次数
	private Float pz;//皮重
	private Float mz;//毛重
	private Float jz;//净重
	private Float dfgbpz;//对方过磅皮重
	private Float dfgbmz;//对方过磅毛重
	private Float dfgbjz;//对方过磅净重
	private String dfbdzp;//对方磅单照片
	private String dfgbsj;//对方过磅时间
	private String ewm;//二维码
	private Integer yssId;//运输商id
	private String yssMc;
	private Integer wzlxId;
	private String wzlxMc;
	private Integer wzId;//物资id
	private String wzMc;
	private Integer fhdwId;//发货单位id
	private String fhdwMc;
	private Integer shdwId;//收货单位id
	private String shdwMc;
	private Integer cyclId;//承运车辆id
	private String cyclCph;//承运车辆车牌号
	private Integer cysjId;//承运司机id
	private String cysjXm;//承运司机姓名
	private String cysjSfzh;//承运司机身份证号
	private String bjsj;//编辑时间
	private String jcsj;
	private String ccsj;
	private Integer yjbfh=0;//一检磅房号
	private Integer ejbfh=0;//二检磅房号
	private Long hmId;//号码id
}
