**Dirctory details** 
***

    Front-ui  : contains the java based desktop client application to send the post request to Ballerina service
         source_file : source code for the application
    ship_note : Ballerina package contains the services and function calls
         Package.md  : package details file
         lib.bal     : contains the function to call Facebook messenger API
         main.bal    : Service initialization function with welcome note
         service.bal : contains the following service endpoints.
                           * Get data and insert into database from java client app
                           * Get shipping status update and API call for Facebook messenger
    sqlexport : contains the exported mysql database file. 

**Requirements**
***

    1. Any kind of localserver(XAMPP,LAMP) should have been installed.
    2. mysql server should have been initialized with port 3306
    3. Java SDK should have been installed and configured.
    4. Ballerina should be installed.

**How to run**
***

    1. Download the repository and extract.
    2. Import the extracted sqlfile to the mysql server.
    3. open the command line inside the root folder.
    4. run command `sudo ballerina run ship_note` in the command line
    5. goto front-ui/executable
    6. run the admin_porta.jar
    7. Click the get details button.
    8. Select a customer from the dropdown.
    9. Change the shipping status and click update button.
    10. A state change message will be sent to that user.

    
