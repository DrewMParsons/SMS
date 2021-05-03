package jpa.entitymodels;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "Student")
@NamedQuery(name="Student.findAll",query="SELECT s FROM Student s")
public class Student
{
	@Id
	@Column(name="email",columnDefinition="VARCHAR(50)") 
	private String sEmail;
	@Column(name="name",columnDefinition="VARCHAR(50)",nullable=false) 
	private String sName;
	@Column(name="password",columnDefinition="VARCHAR(50)",nullable=false) 
	private String sPass;
	
	@OneToMany(targetEntity=Course.class,cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Course> sCourses = new ArrayList<>();

	//Empty Constructor
	public Student()
	{
		super();
	}

	//Constructor using all fields
	public Student(String sEmail, String sName, String sPass, List<Course> sCourses)
	{
		super();
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.sCourses = sCourses;
	}
	
	public Student(String sEmail, String sName, String sPass)
	{
		super();
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		
	}

	//Getters and Setters 
	public String getsEmail()
	{
		return sEmail;
	}

	public void setsEmail(String sEmail)
	{
		this.sEmail = sEmail;
	}

	public String getsName()
	{
		return sName;
	}

	public void setsName(String sName)
	{
		this.sName = sName;
	}

	public String getsPass()
	{
		return sPass;
	}

	public void setsPass(String sPass)
	{
		this.sPass = sPass;
	}

	public List<Course> getsCourses()
	{
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses)
	{
		this.sCourses = sCourses;
	}
	
	public void addCourse(Course course) {
		sCourses.add(course);
	}
	
	public void removeCourse(Course course) {
		sCourses.remove(course);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sCourses == null) ? 0 : sCourses.hashCode());
		result = prime * result + ((sEmail == null) ? 0 : sEmail.hashCode());
		result = prime * result + ((sName == null) ? 0 : sName.hashCode());
		result = prime * result + ((sPass == null) ? 0 : sPass.hashCode());
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
		Student other = (Student) obj;
		if (sCourses == null)
		{
			if (other.sCourses != null)
				return false;
		} else if (!sCourses.equals(other.sCourses))
			return false;
		if (sEmail == null)
		{
			if (other.sEmail != null)
				return false;
		} else if (!sEmail.equals(other.sEmail))
			return false;
		if (sName == null)
		{
			if (other.sName != null)
				return false;
		} else if (!sName.equals(other.sName))
			return false;
		if (sPass == null)
		{
			if (other.sPass != null)
				return false;
		} else if (!sPass.equals(other.sPass))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Student [sEmail=" + sEmail + ", sName=" + sName + ", sPass=" + sPass + ", sCourses=" + sCourses + "]";
	}
	

}
