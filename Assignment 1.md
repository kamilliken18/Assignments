



import java.util.Scanner;

public class BookwormBills{

	public static void main (String[] args){
		
		boolean customersPresent = true;
		int customerCounter = 0; 
	
		do { //starts loop for customers 
			customerCounter++; //counts up customers each time loop restarts
			
		//conditions for loop continuation
		System.out.println("Is there anyone else in line? Please enter -1 for Yes and 2 for no.");
		Scanner customerInput = new Scanner(System.in);
		int customersInLine = customerInput.nextInt();
			if(customersInLine == -1) {
				customersPresent = true;
			}
			else if (customersInLine == 2){
				customersPresent = false;
				System.out.println("Good bye.");
				System.exit(0);
			} 
			else {
				System.out.println("That is not an acceptable response. Please enter a -1 for yes and a 2 for no.");
				customersInLine = customerInput.nextInt();
			}
			
		//Introductory information section, split up by sentence to aid format on output.
		System.out.println("Welcome to Bookworm Bills. Thanks for shopping with us.");
		System.out.println("Are you ready to check out? Let's get started.");
	
		// Sets up the input interface, prompts for user input and displays errors for invalid information.
		Scanner input = new Scanner(System.in); 
		System.out.print("How many books have you selected? ");
		int numBooks = input.nextInt(); 
			while (numBooks < 0) {
				System.out.print("That is not a real number of books! Please re-enter ");
				numBooks = input.nextInt();
				}
		System.out.print("How many paintings of books have you selected? ");
		int numPaintings = input.nextInt(); 
			while (numPaintings < 0) {
				System.out.print("That is not a real number of paintings! Please re-enter ");
				numPaintings = input.nextInt();
				}
		System.out.print("How many bookmarks have you selected? ");
		int numBookmarks = input.nextInt();
			while (numBookmarks < 0) {
				System.out.print("That is not a real number of bookmarks! Please re-enter ");
				numBookmarks = input.nextInt();
				}
	
		// Checks that the information is correct.
		System.out.println("Your current order: ");
		System.out.print("You have selected "+ numBooks+" books at $5.00 each.");
		System.out.print("You have selected "+numPaintings+" paintings at $100.00 each.");
		System.out.println("You have selected a total of "+numBookmarks+" bookmarks, which are $5.00 for each pack of six and $1.00 individually.");
		System.out.print("Is this correct? ");
		System.out.println("Please enter 1 for yes and a 0 for no.");
	
		// Allows user to indicate whether they need to make changes.
		int correctionResponse = input.nextInt();

			while (correctionResponse > 2 || correctionResponse < 0) {
				System.out.println("Error, please re-enter a 1 for yes or a 0 for no.");
				correctionResponse = input.nextInt();
			}
		// Corrects information if needed.
			if (correctionResponse == 0) {
				int correctionResponseTwo = 0;
				System.out.println("What would you like to change? Please enter 1 for book quantity, 2 for painting quantity, and 3 for bookmark quantity.If there is nothing you'd like to change and you reached this message in error, please enter 4.");
				correctionResponseTwo = input.nextInt();
					if (correctionResponseTwo == 1) { 
						System.out.println("Please enter new number of books. ");
						numBooks = input.nextInt();
						System.out.println("Please enter 1 to confirm. ");
						correctionResponse = input.nextInt();
					}
					else if (correctionResponseTwo == 2) { 
						System.out.println("Please enter new number of paintings. ");
						numPaintings = input.nextInt();
						System.out.println("Please enter 1 to confirm. ");
						correctionResponse = input.nextInt();
					}
					else if (correctionResponseTwo == 3) { 
						System.out.println("Please enter new number of bookmarks. ");
						numBookmarks = input.nextInt();
						System.out.println("Please enter 1 to confirm. ");
						correctionResponse = input.nextInt();
					}
					else if (correctionResponseTwo == 4) { 
						System.out.println("Please enter 1 to confirm. ");
						correctionResponse = input.nextInt();
					}
					else {
						System.out.println("That is not a valid input. Please enter 1 for book quantity, 2 for painting quantity, 3 for bookmark quantity and 4 to exit.");
						correctionResponseTwo = input.nextInt();
						}
					}
		// Does a quick calculation for the conversion of bookmarks to bookmark packs and singlets.
		int bookmarkPacks = numBookmarks / 6;
		int singleBookmarks = numBookmarks % 6;

		// Establishes the prices and computes the subtotal and discount (if applicable).
		float bookPrice = (float) 5.00, paintingPrice = (float) 100.00, singleBookmarkPrice = (float) 1.00, bookmarkPackPrice = (float) 5.00;
		float subtotalPrice = (bookmarkPackPrice * (float)bookmarkPacks)+(bookPrice * (float)numBooks)+(paintingPrice * (float)numPaintings)+(singleBookmarkPrice * (float)singleBookmarks);
		float discountedSubtotalPrice = (float) 0.00, discountItself = (float) 0.00, taxedDiscountSubtotal = (float) 0.00, taxedSubtotal = (float) 0.00;
		float taxedDiscountedTotal = (float) 0.00, change = (float) 0.00, taxedTotal = (float) 0.00;
		double  payment = 0.00;
		
			if (customerCounter % 3 != 0) {
				taxedSubtotal = (float) (subtotalPrice * 0.07);
				taxedTotal = (float) (subtotalPrice+ taxedSubtotal);
				}
			else {
				discountedSubtotalPrice = (float) (subtotalPrice * 0.9);
				taxedDiscountSubtotal = (float) (discountedSubtotalPrice * 0.07);
				discountItself = (float) (subtotalPrice - discountedSubtotalPrice);
				taxedDiscountedTotal = (float) (discountedSubtotalPrice + taxedDiscountSubtotal);
				}
		// Receipt time! Below is itemized list of purchases that only lists the item if it is purchased.
		if (correctionResponse == 1) {
			System.out.println("*******************************");
			
			if (numBooks > 0) {
				System.out.printf(numBooks+" Book(s):		$%.2f%n",((float)numBooks)* bookPrice);
			}
			if (numPaintings > 0) {
				System.out.printf(numPaintings+" Painting(s):		$%.2f%n",((float)numPaintings)*paintingPrice);
			}
			if (bookmarkPacks > 0) {
				System.out.printf(bookmarkPacks+" Bookmark Pack(s):	$%.2f%n",((float)bookmarkPacks)*bookmarkPackPrice);
			}
			if (singleBookmarks > 0) {
				System.out.printf(singleBookmarks+" Individual Bookmark(s): $%.2f%n",((float)singleBookmarks)*singleBookmarkPrice);
				}
			// Summed outputs from list of purchases above and payment request.
			if (discountedSubtotalPrice> 0) {
				System.out.println("*******************************");
				System.out.println("You won a 10% discount!");
				System.out.printf("Discount amount:			 $%.2f%n",discountItself);
				System.out.printf("Discounted Subtotal:     		$%.2f%n",discountedSubtotalPrice);
				System.out.printf("Tax: 					 $%.2f%n",taxedDiscountSubtotal);
				System.out.printf("Total: 					 $%.2f%n",taxedDiscountedTotal);
				System.out.print("Enter payment without a dollar sign:    $");
				payment = input.nextDouble();
					while (payment < taxedDiscountedTotal) {
						System.out.print("not enough money, please re-enter payment: $");
						payment = input.nextDouble();
					}
						System.out.printf("Your change is:  $%.2f%n",((float)payment) - taxedDiscountedTotal);
						System.out.println("*******************************");

				}
			else {
				System.out.println("*******************************");
				System.out.println("You did not get a discount! Better luck next time!");
				System.out.printf("Subtotal:				$%.2f%n",subtotalPrice);
				System.out.printf("Tax:					$%.2f%n",taxedSubtotal);
				System.out.printf("Total: 					$%.2f%n",taxedTotal);
				System.out.print("Enter payment without a dollar sign:    $");
				payment = input.nextDouble();
					while (payment < taxedTotal) {
						System.out.print("Not enough money, please re-enter payment: $");
						payment = input.nextDouble();
					}
						System.out.printf("Your change is:  $%.2f%n",((float)payment) - taxedTotal);
						System.out.println("*******************************");
				}
		}

	} 
	while (customersPresent);

}

}
