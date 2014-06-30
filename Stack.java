import java.util.Vector;


class InvalidRegionCodeException extends Exception{
	// can be added later.
}
public class Stack{
	
	Vector reg_one_stack;
	Vector reg_two_stack;
	Vector reg_three_stack;
	Vector reg_four_stack;
	Vector reg_five_stack;
	InvalidRegionCodeException invalidregioncodeexception;
	
	public void fill_stack(Student student) throws InvalidRegionCodeException{
		switch(student.region_code){
		case 1:
			reg_one_stack.add(student);
			break;
		case 2:
			reg_two_stack.add(student);
			break;
		case 3:
			reg_three_stack.add(student);
			break;
		case 4:
			reg_four_stack.add(student);
			break;
		case 5:
			reg_five_stack.add(student);
			break;
		default:
			throw invalidregioncodeexception;
		}
		
	}
}
