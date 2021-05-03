package jpa.entitymodels;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
@NamedQuery(name="Course.findAll",query="SELECT c FROM Course c")
public class Course
{
	@Id
	@Column(name="id")
	private int cid;
	@Column(name="name",columnDefinition="VARCHAR(50)",nullable=false) 
	private String cName;
	@Column(name="instructor",columnDefinition="VARCHAR(50)",nullable=false) 
	private String cInstructorName;
	

	
	//Empty Constructor
	public Course()
	{
		super();
	}


	//Constructor using all fields
	public Course(int cid, String cName, String cInstructorName)
	{
		super();
		this.cid = cid;
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}


	//Getters and Setters
	public int getCid()
	{
		return cid;
	}


	public void setCid(int cid)
	{
		this.cid = cid;
	}


	public String getcName()
	{
		return cName;
	}


	public void setcName(String cName)
	{
		this.cName = cName;
	}


	public String getcInstructorName()
	{
		return cInstructorName;
	}


	public void setcInstructorName(String cInstructorName)
	{
		this.cInstructorName = cInstructorName;
	}

	
	@Override
	public String toString()
	{
		return "Course [cid=" + cid + ", cName=" + cName + ", cInstructorName=" + cInstructorName + "]";
	}


	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cInstructorName == null) ? 0 : cInstructorName.hashCode());
		result = prime * result + ((cName == null) ? 0 : cName.hashCode());
		result = prime * result + cid;
		return result;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (cInstructorName == null)
		{
			if (other.cInstructorName != null)
				return false;
		} else if (!cInstructorName.equals(other.cInstructorName))
			return false;
		if (cName == null)
		{
			if (other.cName != null)
				return false;
		} else if (!cName.equals(other.cName))
			return false;
		if (cid != other.cid)
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
