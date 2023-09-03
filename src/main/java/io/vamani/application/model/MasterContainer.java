package io.vamani.application.model;

import java.io.Serializable;

public class MasterContainer implements Serializable {

	private String paraCode1;
	private String paraCode2;
	private String paraCode3;
	private String paraCode4;
	private String paraCode5;
	private double paraQty1;
	private double paraQty2;
	private double paraQty3;
	private double paraQty4;
	private double paraQty5;
	
	public String getParaCode1() {
		return paraCode1;
	}
	public void setParaCode1(String paraCode1) {
		this.paraCode1 = paraCode1;
	}
	public String getParaCode2() {
		return paraCode2;
	}
	public void setParaCode2(String paraCode2) {
		this.paraCode2 = paraCode2;
	}
	public String getParaCode3() {
		return paraCode3;
	}
	public void setParaCode3(String paraCode3) {
		this.paraCode3 = paraCode3;
	}
	public String getParaCode4() {
		return paraCode4;
	}
	public void setParaCode4(String paraCode4) {
		this.paraCode4 = paraCode4;
	}
	public String getParaCode5() {
		return paraCode5;
	}
	public void setParaCode5(String paraCode5) {
		this.paraCode5 = paraCode5;
	}
	public double getParaQty1() {
		return paraQty1;
	}
	public void setParaQty1(double paraQty1) {
		this.paraQty1 = paraQty1;
	}
	public double getParaQty2() {
		return paraQty2;
	}
	public void setParaQty2(double paraQty2) {
		this.paraQty2 = paraQty2;
	}
	public double getParaQty3() {
		return paraQty3;
	}
	public void setParaQty3(double paraQty3) {
		this.paraQty3 = paraQty3;
	}
	public double getParaQty4() {
		return paraQty4;
	}
	public void setParaQty4(double paraQty4) {
		this.paraQty4 = paraQty4;
	}
	public double getParaQty5() {
		return paraQty5;
	}
	public void setParaQty5(double paraQty5) {
		this.paraQty5 = paraQty5;
	}
	public MasterContainer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paraCode1 == null) ? 0 : paraCode1.hashCode());
		result = prime * result + ((paraCode2 == null) ? 0 : paraCode2.hashCode());
		result = prime * result + ((paraCode3 == null) ? 0 : paraCode3.hashCode());
		result = prime * result + ((paraCode4 == null) ? 0 : paraCode4.hashCode());
		result = prime * result + ((paraCode5 == null) ? 0 : paraCode5.hashCode());
		long temp;
		temp = Double.doubleToLongBits(paraQty1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(paraQty2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(paraQty3);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(paraQty4);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(paraQty5);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MasterContainer other = (MasterContainer) obj;
		if (paraCode1 == null) {
			if (other.paraCode1 != null)
				return false;
		} else if (!paraCode1.equals(other.paraCode1))
			return false;
		if (paraCode2 == null) {
			if (other.paraCode2 != null)
				return false;
		} else if (!paraCode2.equals(other.paraCode2))
			return false;
		if (paraCode3 == null) {
			if (other.paraCode3 != null)
				return false;
		} else if (!paraCode3.equals(other.paraCode3))
			return false;
		if (paraCode4 == null) {
			if (other.paraCode4 != null)
				return false;
		} else if (!paraCode4.equals(other.paraCode4))
			return false;
		if (paraCode5 == null) {
			if (other.paraCode5 != null)
				return false;
		} else if (!paraCode5.equals(other.paraCode5))
			return false;
		if (Double.doubleToLongBits(paraQty1) != Double.doubleToLongBits(other.paraQty1))
			return false;
		if (Double.doubleToLongBits(paraQty2) != Double.doubleToLongBits(other.paraQty2))
			return false;
		if (Double.doubleToLongBits(paraQty3) != Double.doubleToLongBits(other.paraQty3))
			return false;
		if (Double.doubleToLongBits(paraQty4) != Double.doubleToLongBits(other.paraQty4))
			return false;
		if (Double.doubleToLongBits(paraQty5) != Double.doubleToLongBits(other.paraQty5))
			return false;
		return true;
	}
	
	
}
