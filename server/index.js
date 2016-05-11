/* CREDENTIALS */
var messageBirdKey= 'test_WKqa20Mhy44OIWt9Rv9TiDCKU';
var messageBirdLiveKey='live_cLn2Ja8D4QEsaJmVD55WUubNk';

// Twilio Credentials 
var testSid = '89c38b2ab799fbfe3dc535ff5e28cf07';
var testToken = '89c38b2ab799fbfe3dc535ff5e28cf07';
var accountSid = 'AC9a306c9608a1a8276a29b4016a108b85'; 
var authToken = 'b32465896bc3a465c0fa73bb00974c99';
var twilioPhone = '+14023164493';
var numberSID = 'PN464bae924276a482587b40a6121537f9';
ctexi_sid = 'SK40e15a5d237ca8e1512fc37c562a73f8';
ctexi_secret = 'kejoiS8RPTwc9mofJqftuzBCMC3l7Ua9';

// Import the HTTP module
var http = require('http');
var twilio = require('twilio')(numberSID, testToken); 
var messageBird = require('messagebird')(messageBirdKey);
var express = require('express');
var logger = require('morgan');
var app = express();
var helper = require('./helper');
//app.use(logger());
// Define the port to listen to (80 is the standard for http:// over IP)
const PORT=8000; 

// List of drivers
var drivers = [];
var drivers_list={};

// URL decoder
function decode(str) {
     return unescape(str.replace(/\_/g, ' '));
}

function findClosestDriver(userData){
    console.log("Finding closest driver for rider");
    var rider_lat = userData['lat'];
    var rider_lon = userData['lon'];
    var tmp_driver, tmp_lat, tmp_lon; 
    var closest_driver;
    var minimum_distance = 1000000000;;
    for (key in drivers_list){
        tmp_driver = drivers_list[key];
        console.log("temporary driver is :\n");
        console.log(tmp_driver);
        tmp_lat = tmp_driver['lat'];
        tmp_lon = tmp_driver['lon'];
        if (minimum_distance>(helper.calculateDistance(rider_lat,rider_lon,tmp_lat,tmp_lon))) {
            closest_driver = tmp_driver;
            console.log("closest driver found");
        }
    }

    return closest_driver;

}


function passengerRequestsDriver(userData){
    var closest_driver = findClosestDriver(userData);
    var riderMessage;
    var driverMessage;
    
    if (closest_driver!=null){
        riderMessage=createRiderMessage(closest_driver);
        driverMessage= createDriverMessage(userData);
        console.log("Sending rider message");
        sendMessage(userData['phone'] , riderMessage);
        sendMessage(closest_driver['phone'] , driverMessage);
    }
}


function createRiderMessage(userData){
    
    var riderMessage = "We found a driver for you:\n";
    riderMessage += "Driver: "+ userData['name'] + "\n";
    riderMessage += "Contact: " + userData['phone'] + "\n";
    riderMessage += "Lat,Lng: " +userData['lat']+","+userData['lon']+"\n";

    return riderMessage;
}

function createDriverMessage(riderData){
    var driver_message;

    driver_message = "We found a rider for you:\n";
    driver_message += "Rider: " + riderData['name'] + "\n";
    driver_message += "Contact: "+ riderData['phone']+ "\n";
    driver_message += "Lat,Lng: " + riderData['lat'] + ","+riderData['lon'] + "\n";
    driver_message += "Message: " + riderData['message'] + "\n";

    return driver_message;
}

function driverRequestsRider(userData){
    return;
}

/* Handles Passenger Requests*/
function handlePassengerRequest(userData){
    if (userData['type']=='R'){
        passengerRequestsDriver(userData);
    }
}


function doesDriverExist(driverData){
    var key = driverData['phone'];
    var exists = false;
    if (driverData['phone'] in drivers_list){
        drivers_list[key]['lat'] = driverData['lat'];
        drivers_list[key]['lon'] = driverData['lon'];
        drivers_list[key]['name'] = driverData['name'];
        console.log('updating driver \n');
        return true;
    }
    // for (var i = 0; i < drivers.length && !exists; i++) {
    //     var old_driver_str = drivers[i];
    //     var old_driver = JSON.parse(old_driver_str);
    //     //console.log("Checking against: " + old_driver['phone']);
    //     if (old_driver['phone'] == driverData['phone']) {
    //         exists = true;
    //         /*Updating Driver Information */
    //         old_driver['lat'] = new_driver['lat'];
    //         old_driver['lon'] = new_driver['lon'];
    //         old_driver['name'] = new_driver['name'];
    //         console.log('updateDriver');
    //         return true;
    //     }
    // }
    return false;
}


function handleDriverRequest(driverData){
    if (!doesDriverExist(driverData)){
        registerDriver(driverData);
    }

    if(driverData['type']=='S'){
        driverRequestsRider(driverData);
    }

}

function registerDriver(driverData) {
    var new_driver = {};
    
    new_driver['lat']   = driverData['lat'];
    new_driver['lon']   = driverData['lon'];
    new_driver['phone'] = driverData['phone'];
    new_driver['name']  = driverData['name'];
    //console.log("Incoming phone number: " + phone);
    drivers.push(JSON.stringify(new_driver));
    drivers_list[driverData['phone']] = new_driver;
    console.log(drivers_list);
    console.log('registered Driver');
}

// A handler so that the main handleRequest function can be swapped out with the SMS functionality easily
function handleList(data_list) {
    console.log("In handle_list");
    var info = {};
    info['type']    = data_list[0];
    info['lat']     = parseFloat(data_list[1]);
    info['lon']     = parseFloat(data_list[2]);
    info['phone']   = data_list[3];
    info['message'] = data_list[4];
    info['name']    = data_list[5];

    /*Checks for request type and calls requestHandlers accordingly*/
    if (info['type'] == 'S' || info['type'] == 'U') {
        handleDriverRequest(info); // for now both go to the same function
    }

    if (info['type'] == 'D'){
        handleDriverResponse(info);
    }
    if (info['type'] == 'R'){
        handlePassengerRequest(info);
    }
    // This response is for debugging purposes and will eventually change.
    var response_text = info['type']+'|'+info['lat']+'|'+info['lon']+'|'+info['phone']+'|'+info['message']+'|'+info['name'];
    
    return response_text;
}



function sendTwilioMessage(msg, sender, receiver){
    var params = { 
        to: sender, 
        from: receiver, 
        body: msg,   
    }

    twilio.messages.create( params, function(err, message) { 
        console.log(err);
        console.log(message.sid); 
    }); 
}

function sendMessage(recipients, message){
    var params = {
        'originator': 'MessageBird',
        'recipients': recipients, // recipients is an array
        'body': message,
    };

    messageBird.messages.create(params, function (err, data) {
    if (err) {
        return console.log(err);
    }
    console.log(data);
    });
}

// The function which handles requests and send response
function handleRequest(request, response){
    var raw_data = decode(request.url.slice(1));
    var data_list = raw_data.split('|');
    var response_text = '';
    if (data_list.length == 6) {
        response_text = handleList(data_list);
    } else {
        response_text = 'ERROR: Incorrect number of fields.\n';
    }
    response.write(response_text, 'ascii');
    response.end();
}

//Create the server
app.all('*',handleRequest)
var server = http.createServer(app);

// Start the server
server.listen(process.env.PORT || PORT, function(){
    // Triggered when server is listening.
    console.log('Server listening on: http://localhost:%s', PORT);
});

//sendMessage(['971563052935'], 'MessageBird %0a working %0a finally');
//sendTwilioMessage("Testing twilio's message service", twilioPhone, '971563052935');
