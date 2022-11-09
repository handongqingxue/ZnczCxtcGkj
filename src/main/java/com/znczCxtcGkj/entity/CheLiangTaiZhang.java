package com.znczCxtcGkj.entity;

public class CheLiangTaiZhang {

	public static final int JIN_CHANG=1;
	public static final int CHU_CHANG=2;
	
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getJczp() {
		return jczp;
	}
	public void setJczp(String jczp) {
		this.jczp = jczp;
	}
	public String getCcsj() {
		return ccsj;
	}
	public void setCcsj(String ccsj) {
		this.ccsj = ccsj;
	}
	public String getCczp() {
		return cczp;
	}
	public void setCczp(String cczp) {
		this.cczp = cczp;
	}
	public Long getDdId() {
		return ddId;
	}
	public void setDdId(Long ddId) {
		this.ddId = ddId;
	}
	private String jcsj;
	private String jczp;
	private String ccsj;
	private String cczp;
	private Long ddId;
}
