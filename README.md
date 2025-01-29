# CSVReaderJava

#### Functions
Reads data from a CSV file.
Displays all records from the CSV file.
Display records when filtered by category.


Implementation
// Class Reader
	- Opens a csv file using a path
	- Uses try catch to handle opening file with resources and catch IO exception if file not found
	- Reads from the opened file
	- Closes the file
	- Creates List<String> records from the file to handle operations for it
	
// Class RecordHandler
	- Uses Reader instance
	- Declares a String category
	- Determines the available categories
	- Method to get and set category from the available categories in the csv file
	- Displays records by checking equality of category in parameter and the records in the file
		- Goes through each string in list and checks using contains(category)
	- Display all records by going through the List<String> records
	

	