package phone.number;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PhoneNumber {

	public static void main(String[] args) {

		String filePath = "//Users//apple//Downloads//File//Read//number.txt";
		File file = new File(filePath);
		String phoneNum = null;

		// Reading Multilined File - Its static as we know the file size already
		String[] phoneNums = new String[9];
		String phoneNumbers = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
//			phoneNum = br.readLine();
			// Multiline file reading
			for (int i = 0; i < phoneNums.length; i++) {
				phoneNums[i] = br.readLine();
			}

			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < phoneNums.length; i++) {
			
			phoneNum = phoneNums[i];
			try {
				if (phoneNum.length() != 10) {
					throw new TenDigitException(phoneNum);
				}
				if (phoneNum.substring(0, 1).equals("2") || phoneNum.substring(0, 1).equals("3")
						|| phoneNum.substring(0, 1).equals("4") || phoneNum.substring(0, 1).equals("5")
						|| phoneNum.substring(0, 1).equals("6")) {
					throw new AreaCodeException(phoneNum);
				}
				for (int n = 0; n < phoneNum.length() - 2; n++) {
					if (phoneNum.substring(n, (n + 1)).equals("9")) {
						if (phoneNum.substring((n + 1), (n + 3)).equals("11")) {
							throw new EmergencyContactViolationException(phoneNum);
						}
					}
				}

				System.out.println(phoneNum);
			} catch (TenDigitException e) {
				System.out.println("ERROR: Phone number must be of exact 10 digits length");
				System.out.println(e.toString());

			} catch (AreaCodeException e) {
				System.out.println("ERROR: Invalid Area Code");
				System.out.println(e.toString());
			} catch (EmergencyContactViolationException e) {
				System.out.println("ERROR: Invaid 911 sequence found");
				System.out.println(e.toString());

			} 
		}

	}

}

class TenDigitException extends Exception {
	String num;

	TenDigitException(String number) {
		this.num = number;
	}

	public String toString() {
		return ("TenDigitException: " + num);
	}

}

class AreaCodeException extends Exception {
	String num;

	AreaCodeException(String number) {
		this.num = number;
	}

	public String toString() {
		return ("AreaCodeException: " + num);
	}

}

class EmergencyContactViolationException extends Exception {

	String num;

	public EmergencyContactViolationException(String num) {
		this.num = num;
	}

	public String toString() {
		return ("Emergency Code Included in : " + num);
	}
}
