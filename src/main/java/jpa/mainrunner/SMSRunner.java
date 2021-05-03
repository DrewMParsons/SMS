package jpa.mainrunner;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import jpa.entitymodels.Course;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner
{
	public static Scanner scanner = new Scanner(System.in);
	public static StudentService stuSrv = new StudentService();
	public static CourseService courSrv = new CourseService();
	public static String email = "";
	public static String password = "";
	public static int choice = 0;

	public static void main(String[] args)
	{
		email = logInToSMS();
		displayClasses(email, false);
		registerClasses(email);
		displayClasses(email, false);
		System.out.println("You have been signed out");
		quitSMS();
		
		
	}

	public static void registerClasses(String email)
	{
	
		try
		{
			boolean isValid=false;
			while(!isValid) {
			System.out.println("\n1.Register to Class");
			System.out.println("2.Logout");
			choice = scanner.nextInt();
			if (choice == 2)
			{
				quitSMS();
			}
			if (choice == 1)
			{
				displayClasses(email, true);
			}
			System.out.println("\nWhich Course?(enter number): \n");
			choice = scanner.nextInt();
			isValid = stuSrv.registerStudentToCourse(email, choice);
			}
		} catch (InputMismatchException e)
		{
			System.out.println("That is not a valid input. Please enter a number");
		}
		
	}

	public static void displayClasses(String email, boolean allCourses)
	{
		System.out.print(allCourses ? "\nAll Classes:\n" : "\nMy Classes:\n");
		List<Course> courses = allCourses ? courSrv.getAllCourses() : stuSrv.getStudentCourses(email);
		if (!courses.isEmpty())
		{
			System.out.printf("%n%-2s %-50s %-50s%n", "#", "COURSE NAME", "INSTRUCTOR NAME");
			for (Course c : courses)
			{
				System.out.printf("%n%-2d %-50s %-50s", c.getCid(), c.getcName(), c.getcInstructorName());
			}
			System.out.println();
		}
	}

	public static String logInToSMS()
	{

		boolean isValid = false;
		while (!isValid)
		{
			loginDisplay();
			try {
			choice = scanner.nextInt();
			} catch (InputMismatchException e)
			{
				System.out.println("That is not a valid input.");
				quitSMS();
			}
			if (choice != 1)
			{
				quitSMS();
			}

			else if (choice == 1)
			{
				scanner.nextLine();
				System.out.print("\nEnter your email:\n");
				email = scanner.nextLine();
				System.out.print("\nEnter your password\n");
				password = scanner.nextLine();
				isValid = stuSrv.validateStudent(email, password);
				if (isValid)
				{
					System.out.println("valid login");
					break;
				} else
				{
					System.out.println("invalid login");
				}
			}
		}
		return email;
	}

	public static void quitSMS()
	{
		System.out.println("Goodbye");
		scanner.close();
		System.exit(0);
	}

	public static void loginDisplay()
	{
		System.out.println("Are you a: ");
		System.out.println("1.Student");
		System.out.println("2.quit");
		System.out.println("Please enter 1 or 2");
	}

}
