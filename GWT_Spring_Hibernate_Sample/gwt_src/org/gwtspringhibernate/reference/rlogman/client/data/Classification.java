package org.gwtspringhibernate.reference.rlogman.client.data;


import com.google.gwt.user.client.rpc.IsSerializable;


public class Classification
		implements IsSerializable {

	private short classificationId;
	private String classificationName;
	private String description;
	private char mandatory;
	private char multiple;

	/** default constructor */
	public Classification() {
	}

	/** minimal constructor */
	public Classification(short classificationId, String classificationName,
		char mandatory, char multiple) {
		this.classificationId = classificationId;
		this.classificationName = classificationName;
		this.mandatory = mandatory;
		this.multiple = multiple;
	}

	/** full constructor */
	public Classification(short classificationId, String classificationName,
		String description, char mandatory, char multiple/*,
		Set classificationValues*/) {
		this.classificationId = classificationId;
		this.classificationName = classificationName;
		this.description = description;
		this.mandatory = mandatory;
		this.multiple = multiple;
	}

	// Property accessors
	public short getClassificationId() {
		return this.classificationId;
	}

	public void setClassificationId(short classificationId) {
		this.classificationId = classificationId;
	}

	public String getClassificationName() {
		return this.classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getMandatory() {
		return this.mandatory;
	}

	public void setMandatory(char mandatory) {
		this.mandatory = mandatory;
	}

	public char getMultiple() {
		return this.multiple;
	}

	public void setMultiple(char multiple) {
		this.multiple = multiple;
	}
}
