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

