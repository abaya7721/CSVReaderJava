# CSVReaderJava

## Functions
Reads data from a CSV file.
Displays all records from the CSV file.
Display records filtered by category.


## Implementation
1. Class FileReader
	* String filePath
	* Opens a csv file using a path
	* Uses try catch to handle opening file with resources and catch IO exception if file not found
	* Reads from the opened file
	* Get header
	* Gets all other lines in file
	* Creates List<String> records from the file to handle operations for it
	
2. Class RecordService
	* FileReader instance
	* String category
	* Determines the available categories
	* Method to get and set category from the available categories in the csv file
	* Creates a list records by checking equality of category in parameter and the records in the file
		- Goes through each string in list and checks using contains(category)
	* Returns the List<String> records

3. Class View
	* RecordService
	* Display all records or filtered records
	

	