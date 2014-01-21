package com.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.joe.entity.IdEntity;


//JPA标识
@Entity
@Table(name = "tb_realestate_task")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RealEstateTask extends IdEntity {
	/** 描述  */
	private static final long serialVersionUID = -2677876323206901899L;

	@NotBlank
	@Length(max=32)
	@Column(name="task_name",length=32, nullable=false)
	private String taskName;
	
	@Length(max=255)
	@Column(length=255)
	private String description;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
