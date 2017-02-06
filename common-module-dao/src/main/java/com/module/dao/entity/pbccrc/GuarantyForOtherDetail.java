/**
 * 
 */
package com.module.dao.entity.pbccrc;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="t_guaranty")
@NamedQuery(name="GuarantyForOtherDetail.findAll", query="SELECT e FROM GuarantyForOtherDetail e")
public class GuarantyForOtherDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	/**
	 * guaranteedPerson : »Æ³É
	 * guaranteedPersonIdNum : **************2315
	 * otherGuaranteeAmount : 500000
	 * realPrincipal : 500000
	 * actualDay : 2015.11.30
	 */
	@Column(name = "currency")
	private String guaranteedPerson;

	@Column(name = "guaranteed_person_id_num")
	private String guaranteedPersonIdNum;

	@Column(name = "other_guarantee_amount")
	private String otherGuaranteeAmount;

	@Column(name = "real_principal")
	private String realPrincipal;

	@Column(name = "actual_day")
	private String actualDay;


	@ManyToOne
	@JoinColumn(name="json_id")
	private PlainPbccrcJson plainPbccrcJson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGuaranteedPerson() {
		return guaranteedPerson;
	}

	public void setGuaranteedPerson(String guaranteedPerson) {
		this.guaranteedPerson = guaranteedPerson;
	}

	public String getGuaranteedPersonIdNum() {
		return guaranteedPersonIdNum;
	}

	public void setGuaranteedPersonIdNum(String guaranteedPersonIdNum) {
		this.guaranteedPersonIdNum = guaranteedPersonIdNum;
	}

	public String getOtherGuaranteeAmount() {
		return otherGuaranteeAmount;
	}

	public void setOtherGuaranteeAmount(String otherGuaranteeAmount) {
		this.otherGuaranteeAmount = otherGuaranteeAmount;
	}

	public String getRealPrincipal() {
		return realPrincipal;
	}

	public void setRealPrincipal(String realPrincipal) {
		this.realPrincipal = realPrincipal;
	}

	public String getActualDay() {
		return actualDay;
	}

	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}

	public PlainPbccrcJson getPlainPbccrcJson() {
		return plainPbccrcJson;
	}

	public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
		this.plainPbccrcJson = plainPbccrcJson;
	}
}
