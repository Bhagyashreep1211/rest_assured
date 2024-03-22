package DSA;

public class DSA {{
	try {
	    // Code that might throw an exception
	    int result = 10 / 0; // This will throw an ArithmeticException
	} catch (ArithmeticException e) {
	    // Handling the exception
	    System.out.println("Error: " + e.getMessage());
	}
}
}