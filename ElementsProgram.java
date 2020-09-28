package defaultpackage;
/*
 * Creator: Nate Alberti
 * Date: 9/2020
 * Description: Reads data from a file I created that has a list of all
 * the periodic elements, their weights, types, and abundances. It has
 * four uses: displaying all the data, displaying filtered data
 * based on weight or abundance, getting all the info on a particular
 * element, and displaying a decade counter. All of the output is
 * formatted using printf.
 * This program was made for practice using records, instead of
 * using regular objects like I usually would.
 */
import java.io.IOException;
import java.io.File;
import java.util.*;
public class ElementsProgram {
	public static void main(String args[])  throws IOException {
		Scanner scan = new Scanner(System.in);
		boolean programLoop = false;
		String programLoopControl = null;
		int userOption = 0;
		boolean loopProgram;
		
		Element[] arr = ElementsProgram.loadElements();
		System.out.println("\t\t---PERIODIC ELEMENTS---");
		
		do {
			System.out.println("\nSelect an option: ");
		do {
			loopProgram = false;
			System.out.println("1). Display all data");
			System.out.println("2). Display filtered data");
			System.out.println("3). Get info on a particular element");
			System.out.println("4). Display a decade counter");
			userOption = scan.nextInt();
			
			//display all the data
			if(userOption == 1) {
				//titles of columns
				System.out.printf("%-15s", "name");
				System.out.printf("%-15s", "atomic weight");
				System.out.printf("%-25s", "chemical group");
				System.out.printf("%-15s%n%n", "abundance (mg/kg)");
				
				//prints a list of the data in a formatted table
				for(int i = 0; i < ElementsProgram.getFinalIndex(arr); i++) {
					//name of the element
					System.out.printf("%-15S", arr[i].name);
					
					//atomic weight, formatted correctly
					if((int) arr[i].weight == arr[i].weight)
						System.out.printf("%-,15.0f", arr[i].weight);
					else
						System.out.printf("%-,15.2f", arr[i].weight);
					
					//type of element, formatted correctly
					System.out.printf("%-25s", arr[i].type);
					
					//abundance in earth's crust, formatted correctly
					if(arr[i].abundance == 0)
						System.out.printf("%-8s%n", "N/A");
					else if((int) arr[i].abundance == arr[i].abundance)
						System.out.printf("%-,8.0f%n", arr[i].abundance);
					else if(arr[i].abundance < 0.001)
						System.out.printf("%-8.1e%n", arr[i].abundance);
					else
						System.out.printf("%-,8.3f%n", arr[i].abundance);
				}
			}
			
			//display filtered data
			else if(userOption == 2) {
				boolean filterLoop;
				do {
				String userSubOption = null;
				String filterLoopString = null;
				filterLoop = false;
				boolean loopCheckFilter = false;
				System.out.println("Would you like to filter by weight or abundance?");
				do {
					userSubOption = scan.next();
					loopCheckFilter = false;
					
				
				
				//***WEIGHT***
				if(userSubOption.equalsIgnoreCase("weight")) {

					System.out.println("Minimum weight: ");
					double minWeight = scan.nextDouble();
					System.out.println("Maximum weight: ");
					double maxWeight = scan.nextDouble();
					System.out.println("Sort by:\n1). Lightest to Heaviest\n2). Heaviest to lightest");
					int heavyOrLight = scan.nextInt();
					
					//elements lightest to heaviest
					if(heavyOrLight == 1 ) {
						Element[] sortLH = ElementsProgram.loadElements();
						int indexofLow = 0;
						boolean pass = false;
						
						//titles of columns
						System.out.println();
						System.out.printf("%-15s", "name");
						System.out.printf("%-15s", "atomic weight");
						System.out.printf("%-25s", "chemical group");
						System.out.printf("%-15s%n%n", "abundance (mg/kg)");
								
						do {
							indexofLow = ElementsProgram.getFinalIndex(sortLH);
							pass = false;
							for(int i = 0; i < ElementsProgram.getFinalIndex(sortLH); i++) {
								if(sortLH[i].weight <= sortLH[indexofLow].weight && sortLH[i].weight <= maxWeight && sortLH[i].weight >= minWeight && !(sortLH[i].weight == 999)) {
									indexofLow = i;
									pass = true;
								}
							} 

							//PRINTS THE ELEMENTS
							if(!(indexofLow == 118)) {
								//name of the element
								System.out.printf("%-15S", sortLH[indexofLow].name);
									
								//atomic weight, formatted correctly
								if((int) sortLH[indexofLow].weight == sortLH[indexofLow].weight)
									System.out.printf("%-,15.0f", sortLH[indexofLow].weight);
								else
									System.out.printf("%-,15.2f", sortLH[indexofLow].weight);
								
								//type of element, formatted correctly
								System.out.printf("%-25s", arr[indexofLow].type);
								
								//abundance in earth's crust, formatted correctly
								if(sortLH[indexofLow].abundance == 0)
									System.out.printf("%-8s%n", "N/A");
								else if((int) sortLH[indexofLow].abundance == sortLH[indexofLow].abundance)
									System.out.printf("%-,8.0f%n", sortLH[indexofLow].abundance);
								else if(sortLH[indexofLow].abundance < 0.001)
									System.out.printf("%-8.1e%n", sortLH[indexofLow].abundance);
								else
									System.out.printf("%-,8.3f%n", sortLH[indexofLow].abundance);
							}
							sortLH[indexofLow].weight = 999;
						} while(pass);
					}
					
					//the elements heaviest to lightest
					else if(heavyOrLight == 2) {
						Element[] sortHL = ElementsProgram.loadElements();
						int indexofLow = 0;
						boolean pass = true;
						
						//titles of columns
						System.out.println();
						System.out.printf("%-15s", "name");
						System.out.printf("%-15s", "atomic weight");
						System.out.printf("%-25s", "chemical group");
						System.out.printf("%-15s%n%n", "abundance (mg/kg)");
						
						while(pass) {
							indexofLow = 0;
							pass = false;
							for(int i = 0; i < ElementsProgram.getFinalIndex(sortHL); i++) {
								if(sortHL[i].weight > sortHL[indexofLow].weight && sortHL[i].weight <= maxWeight && sortHL[i].weight >= minWeight && !(sortHL[i].weight == 999)) {
									indexofLow = i;
									pass = true;
								}
							} 
							
							//PRINTS THE ELEMENTS
							if(sortHL[indexofLow].weight > minWeight) {
							//name of the element
							System.out.printf("%-15S", sortHL[indexofLow].name);
								
							//atomic weight, formatted correctly
							if((int) sortHL[indexofLow].weight == sortHL[indexofLow].weight)
								System.out.printf("%-,15.0f", sortHL[indexofLow].weight);
							else
								System.out.printf("%-,15.2f", sortHL[indexofLow].weight);
							
							//type of element, formatted correctly
							System.out.printf("%-25s", arr[indexofLow].type);
								
							//abundance in earth's crust, formatted correctly
							if(sortHL[indexofLow].abundance == 0)
								System.out.printf("%-8s%n", "N/A");
							else if((int) sortHL[indexofLow].abundance == sortHL[indexofLow].abundance)
								System.out.printf("%-,8.0f%n", sortHL[indexofLow].abundance);
							else if(sortHL[indexofLow].abundance < 0.001)
								System.out.printf("%-8.1e%n", sortHL[indexofLow].abundance);
							else
								System.out.printf("%-,8.3f%n", sortHL[indexofLow].abundance);
							sortHL[indexofLow].weight = 999;
							}
							
						}
					}
				} //end of filtering by weight
				
				else if(userSubOption.equalsIgnoreCase("abundance")) {

					System.out.println("Minimum abundance: ");
					double minAbun = scan.nextDouble();
					System.out.println("Maximum abundance: ");
					double maxAbun = scan.nextDouble();
					System.out.println("Sort by:\n1). Rare to Common\n2). Common to Rare");
					int commonOrRare = scan.nextInt();
					
					//elements lightest to heaviest
					if(commonOrRare == 1 ) {
						Element[] sortRare = new Element[200];
						sortRare = ElementsProgram.loadElements();
						int indexofLow = 0;
						boolean pass = false;
						
						//titles of columns
						System.out.println();
						System.out.printf("%-15s", "name");
						System.out.printf("%-15s", "atomic weight");
						System.out.printf("%-25s", "chemical group");
						System.out.printf("%-15s%n%n", "abundance (mg/kg)");
								
						do {
							indexofLow = ElementsProgram.getFinalIndex(sortRare);
							pass = false;
							for(int i = 0; i < ElementsProgram.getFinalIndex(sortRare); i++) {
								if(sortRare[i].abundance <= sortRare[indexofLow].abundance && sortRare[i].abundance <= maxAbun && sortRare[i].abundance >= minAbun && !(sortRare[i].abundance == 999.999)) {
									indexofLow = i;
									pass = true;
								}
							} 

							//PRINTS THE ELEMENTS
							if(!(indexofLow == 118)) {
								//name of the element
								System.out.printf("%-15S", sortRare[indexofLow].name);
									
								//atomic weight, formatted correctly
								if((int) sortRare[indexofLow].weight == sortRare[indexofLow].weight)
									System.out.printf("%-,15.0f", sortRare[indexofLow].weight);
								else
									System.out.printf("%-,15.2f", sortRare[indexofLow].weight);
								
								//type of element, formatted correctly
								System.out.printf("%-25s", arr[indexofLow].type);
									
								//abundance in earth's crust, formatted correctly
								if(sortRare[indexofLow].abundance == 0)
									System.out.printf("%-8s%n", "N/A");
								else if((int) sortRare[indexofLow].abundance == sortRare[indexofLow].abundance)
									System.out.printf("%-,8.0f%n", sortRare[indexofLow].abundance);
								else if(sortRare[indexofLow].abundance < 0.001)
									System.out.printf("%-8.1e%n", sortRare[indexofLow].abundance);
								else
									System.out.printf("%-,8.3f%n", sortRare[indexofLow].abundance);
							}
							sortRare[indexofLow].abundance = 999.999;
						} while(pass);
					}
					
					//the elements heaviest to lightest
					else if(commonOrRare == 2) {
						Element[] sortCommon = ElementsProgram.loadElements();
						int indexofLow = 0;
						boolean pass = true;
						
						//titles of columns
						System.out.println();
						System.out.printf("%-15s", "name");
						System.out.printf("%-15s", "atomic weight");
						System.out.printf("%-25s", "chemical group");
						System.out.printf("%-15s%n%n", "abundance (mg/kg)");
						
						while(pass) {
							indexofLow = ElementsProgram.getFinalIndex(sortCommon) - 1;
							pass = false;
							for(int i = 0; i < ElementsProgram.getFinalIndex(sortCommon); i++) {
								if(sortCommon[i].abundance > sortCommon[indexofLow].abundance && sortCommon[i].abundance <= maxAbun && sortCommon[i].abundance >= minAbun && !(sortCommon[i].abundance == 999.999)) {
									indexofLow = i;
									pass = true;
								}
							} 
							
							//PRINTS THE ELEMENTS
							if(sortCommon[indexofLow].abundance > minAbun) {
							//name of the element
							System.out.printf("%-15S", sortCommon[indexofLow].name);
								
							//atomic weight, formatted correctly
							if((int) sortCommon[indexofLow].weight == sortCommon[indexofLow].weight)
								System.out.printf("%-,15.0f", sortCommon[indexofLow].weight);
							else
								System.out.printf("%-,15.2f", sortCommon[indexofLow].weight);
							
							//type of element, formatted correctly
							System.out.printf("%-25s", arr[indexofLow].type);
								
							//abundance in earth's crust, formatted correctly
							if(sortCommon[indexofLow].abundance == 0)
								System.out.printf("%-8s%n", "N/A");
							else if((int) sortCommon[indexofLow].abundance == sortCommon[indexofLow].abundance)
								System.out.printf("%-,8.0f%n", sortCommon[indexofLow].abundance);
							else if(sortCommon[indexofLow].abundance < 0.001)
								System.out.printf("%-8.1e%n", sortCommon[indexofLow].abundance);
							else
								System.out.printf("%-,8.3f%n", sortCommon[indexofLow].abundance);
							sortCommon[indexofLow].abundance = 999.999;
							}
						}
					}
					
					
					
					
				} //end of filtering by abundance
				else {
					loopCheckFilter = true;
					System.out.println("ONLY enter \"name,\" \"weight,\" or \"abundance.\"");
					System.out.println("Retype answer: ");
					}
				} while(loopCheckFilter);
				
				System.out.println("\n\nWould you like to filter by another parameter? (y/n): ");
				filterLoopString = scan.next();
				if(filterLoopString.equalsIgnoreCase("y"))
					filterLoop = true;
				
				//resetting all arrays to original array
				} while(filterLoop);
			} //end of option 2
			
			//option 3
			else if(userOption == 3) {
				boolean anotherElement = false;
				do {
					Element[] chooseElement = ElementsProgram.loadElements();
					String userElement = null;
					boolean correctSpelling = true;
					String loopElement = null;
					do {
						correctSpelling = true;
						System.out.println("Enter the name of the element you'd like to retrieve: ");
						userElement = scan.next();
						
						//find the correct element
						int match = -1;
						for(int i = 0; i < ElementsProgram.getFinalIndex(chooseElement); i++) {
							if(chooseElement[i].name.equalsIgnoreCase(userElement)) {
								match = i;
							}
						}
						
						if(match == -1) {
							correctSpelling = false;
							System.out.println("Element not found. Please try again.");
						}
						
						else {
						//PRINTS DATA FOR THE ELEMENT
						//titles of columns
						System.out.println();
						System.out.printf("%-15s", "name");
						System.out.printf("%-15s", "atomic weight");
						System.out.printf("%-25s", "chemical group");
						System.out.printf("%-15s%n%n", "abundance (mg/kg)");
						
						//name of the element
						System.out.printf("%-15S", chooseElement[match].name);
							
						//atomic weight, formatted correctly
						if((int) chooseElement[match].weight == chooseElement[match].weight)
							System.out.printf("%-,15.0f", chooseElement[match].weight);
						else
							System.out.printf("%-,15.2f", chooseElement[match].weight);
						
						//type of element, formatted correctly
						System.out.printf("%-25s", arr[match].type);
							
						//abundance in earth's crust, formatted correctly
						if(chooseElement[match].abundance == 0)
							System.out.printf("%-8s%n", "N/A");
						else if((int) chooseElement[match].abundance == chooseElement[match].abundance)
							System.out.printf("%-,8.0f%n", chooseElement[match].abundance);
						else if(chooseElement[match].abundance < 0.001)
							System.out.printf("%-8.1e%n", chooseElement[match].abundance);
						else
							System.out.printf("%-,8.3f%n", chooseElement[match].abundance);
						
						System.out.println("\n\n");
						}
					} while(!(correctSpelling));
					
					System.out.println("Search for another element? (y/n): ");
					loopElement = scan.next();
					if(loopElement.equalsIgnoreCase("y"))
						anotherElement = true;
					else
						anotherElement = false;
				} while(anotherElement);
			}
			
			//DECADE COUNTER
			else if(userOption == 4) {
				
				Element[] arr2 = ElementsProgram.loadElements();
				int[] integers = new int[10];
				for(int i = 0; i < integers.length; i++) {
					integers[i] = 999;
				}
				String nextStr = null;
				System.out.println("Enter ten elements: ");
				
				for(int i = 0; i < integers.length; i++) {
					boolean loopDecade = false;
					do {
						loopDecade = false;
						System.out.println((i+1) + "). ");
						nextStr = "";
						nextStr = scan.next();
						for(int e = 0; e < ElementsProgram.getFinalIndex(arr2); e++) {
							if(nextStr.equalsIgnoreCase(arr2[e].name))
								integers[i] = (int) arr2[e].weight;
						}
						if(integers[i] == 999) {
							System.out.println("No element with that name exists, re-enter: ");
							loopDecade = true;
						}
					} while(loopDecade);
				}
				ElementsProgram.decadeCounter(integers);	
			}
			
			else {
				loopProgram = true;
				System.out.println("Please only enter a digit 1-4.");
				System.out.println("Try again: ");
			} 
		} while(loopProgram);
			
			System.out.println("\n\nRestart the program? (y/n): ");
			programLoopControl = scan.next();
			if(programLoopControl.equalsIgnoreCase("y"))
				programLoop = true;
			else if(programLoopControl.equalsIgnoreCase("n"))
				programLoop = false;
		} while(programLoop);
		System.out.println("---program complete---");
		scan.close();
	}
	
	//basic class structure for the data
	static class Element {
		public String name;
		public double weight;
		public String type;
		public double abundance;
	}
	
	//loads the data from the text file into an array
	public static Element[] loadElements() {
		int i = 0;
		Element[] arr = new Element[250];
		File file = new File("elementsdata2.txt");
		//do/while loop for reading the data
		try {
			Scanner reader = new Scanner(file);
			do {
				arr[i] = new Element();
				arr[i].name = reader.next();
				arr[i].weight = reader.nextDouble();
				arr[i].type = reader.next();
				arr[i].abundance = reader.nextDouble();
				i++;
			} while(reader.hasNext());
			reader.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return arr;
	}
	
	//returns the index of the last item in the array which contains data
	public static int getFinalIndex(Element[] arr) {
		int index = 0;
		try {
			for(int i = 0; i < arr.length; i++) {
				if(arr[i].weight == 999)
					index = i;
			}
		} catch(Exception e) {
				System.out.print("");
			}
		return index;
	}
	
	//divides a range into 10 parts and prints a table with the amount of data in each part
	public static void decadeCounter(int[] samples) {
		//initializing values
		int[] finalArr = new int[10];
		int min = 9999999;
		int max = 0;
		int decadeWidth = 0;
		int range = 0;
		String[] counter = new String[10];
		for(int i = 0; i < finalArr.length; i++) {
			finalArr[i] = 0;
		}
		for(int i = 0; i < counter.length; i++) {
			counter[i] = "";
		}
		
		//finding min and max and range and decade width
		for(int i = 0; i < samples.length; i++) {
			if(samples[i] < min)
				min = samples[i];
			if(samples[i] > max)
				max = samples[i];
		} range = (int) Math.abs(max-min);
		decadeWidth = range/10;
		if(!(range%10 == 0))
			decadeWidth++;
		
		//counting the number of values in each decade
		for(int i = 0; i < samples.length; i++) {
			int decadeIndex =  (int) (((int) samples[i] - min)/(decadeWidth));
			finalArr[decadeIndex]++;
			counter[decadeIndex] += "*";
		}
		
		//printing results
		System.out.printf("%-7s", "decade");
		System.out.printf("%-2s", ":");
		System.out.printf("%-9s", "counter");
		for(int i = 0; i < finalArr.length; i++) {
			System.out.printf("\n%-8s", " " + decadeWidth*(i+1));
			System.out.printf("%2s", "");
			System.out.printf("%-9s", counter[i]);
		}
	}
	
}
