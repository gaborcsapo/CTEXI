# CTEXI Android App

###The team
- commits made by gc1569 and STUDENT are Gabor
- wld224 Leigthon
- ma3585 Muaz

###Releases
First 
- Has the basic UI implementation, with buttons leading to the correct pages
- Displays a static response when driver is requested

Second 
- Along with a slightly improved app, we also have a basic NODEjs server

Third 
- The app was heavily improved to accomodate request and responses from the server 
- The server is not up to serve data to the app yet

Fourth 
- The app has access to location data, and sends and receives SMSs
- The server accomodates requests, but doesn't store and match participants

## Introduction

Transportation poses a huge issue in developing countries. Due to the lack of road infrastructure, efficient communication channels and reliable providers getting around in these countries can become a hassle. Our project aims to improve a specific segment of transportation: taxis. Following Professor Nyarko’s recommendation, we set as our short term goal to create a prototype of an Android app, which allows users to book cabs through text messages (SMS). The system allocates the closest available cab driver to the user and shares essential information to provide a faster and more efficient service. The service has the potential of becoming the dominant player in the taxi industry in Ghana.


## How does it work?

The problem with the current taxi booking system lies in its decentralized and inefficient nature. As explained by Prof. Yaw, taxis usually wait for their passengers at taxi ranks and the only way to request a pickup is through a human operated dispatcher center. In the current system people have to go to the city center and wait for a long time for their cabs. The application solves this problem by providing an automated central allocation system, which matches each customer with the closest taxi driver, who can pick up the person at any desired location. We have the following functionality requirements for the prototype:


### The Rider
- Downloads app, inputs his/her contact information into local database
- Clicks on “Book taxi”
- Writes a short message to specify location (i.e. opposite of the school…)
- “Requesting cab” sends the GPS coordinates and the message to the server via SMS
- Server allocates driver and sends back driver’s name and phone number via SMS
- If further specifications are needed the two parties can call each other
- Taxi arrives to the set location and takes the user to the destination
- Mobile or cash payment


### The Driver
- Downloads app and registers as a driver
- Sets availability to yes
- Waits for a request to arrive
- Request comes in with the pickup location shown on an offline map
- Sends estimated time of arrival and confirmation via SMS
- Picks up the customer and drops off at desired location
- Receives payment via mobile transfer or cash 
- Waits for the next request

These two sets of functionality will be implemented in two separate apps using the same back-end to communicate with a central server by SMS.
Initially we intend to develop the "rider" app interface for Android phones, and then implement the "driver" interface other aspects of the project if time permits it.

#Modifying the app
If you would like to edit our code, you need to install Android Studio along with the Android SDK and the Java SDK. 
Once you have these, clone the project and go to --> Select File | New | Project from Existing Sources from the main menu.
Browse to the project that you'd like to import and click next
Choose to "import the project from external model"
Android Studio walks you through all the steps to successfully import the project e.g. importing external libraries, dependencies...
Then you have to download "Volley" 
Next, you have to comment out from volley build.gradle the line "apply from: 'bintray.gradle'"
And go to File --> New --> Import Module --> [select location] and name the module "VolleyX"

Details on what each class and function does can be seenin the code. I don't want to create an object diagram, because the purpose, functionality and number of objects change frequently.

# The Server
The server runs on node.js. Currently it takes data over IP, but eventually it will be switched over to SMS.

It takes in data in the following format, where the message could be the name, or the message, depending on the request type.:
[type]|[latitude]|[longitude]|[phone#]|[message]|[name]

### Types of request:
- S : Driver sends GET request to ask for a rider, response should be the rider he was matched with
- U : Driver sends POST request to update his location in the database
- D : Driver sends POST request to let the server know he accepted or declines the deal. The info of the driver shoudl be sent to the rider
- R : Rider sends GET request to request a driver


While its functionality is being developed, the server will send a reply with information about the request, but once it is fully functional the responses will be limited to riders' requests.
