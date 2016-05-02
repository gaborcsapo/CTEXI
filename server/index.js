/* CREDENTIALS */
var messageBirdKey= 'test_WKqa20Mhy44OIWt9Rv9TiDCKU';


// Import the HTTP module
var http = require('http');
var twilio = require('twilio');
var messageBird = require('messagebird')(messageBirdKey);

// Define the port to listen to (80 is the standard for http:// over IP)
const PORT=8080; 

// List of drivers
var drivers = [];

// URL decoder
function decode(str) {
     return unescape(str.replace(/\_/g, ' '));
}

function registerDriver(lat, lon, phone, name) {
    var new_driver = {};
    var exists = false;
    new_driver['lat']   = lat;
    new_driver['lon']   = lon;
    new_driver['phone'] = phone;
    new_driver['name']  = name;
    console.log("Incoming phone number: " + phone);
    for (var i = 0; i < drivers.length && !exists; i++) {
        var old_driver_str = drivers[i];
        var old_driver = JSON.parse(old_driver_str);
        //console.log("Checking against: " + old_driver['phone']);
        if (old_driver['phone'] == new_driver['phone']) {
            exists = true;
            old_driver['lat'] = new_driver['lat'];
            old_driver['lon'] = new_driver['lon'];
            old_driver['name'] = new_driver['name'];
            console.log('updateDriver');
        }
    }
    if (!exists) {
        drivers.push(JSON.stringify(new_driver));
        console.log('registerDriver');
    }
    console.log('\n');
}

// A handler so that the main handleRequest function can be swapped out with the SMS functionality easily
function handleList(data_list) {
    var info = {};
    info['type']    = data_list[0];
    info['lat']     = data_list[1];
    info['lon']     = data_list[2];
    info['phone']   = data_list[3];
    info['message'] = data_list[4];
    if (info['type'] == 'S' || info['type'] == 'U') {
        registerDriver(info['lat'], info['lon'], info['phone'], info['message']); // for now both go to the same function
    }
    // This response is for debugging purposes and will eventually change.
    var response_text = "Parsed input line for line (separated by '|' characters):\n";
    for (var key in info) {
        var tmp_str = key + '\t' + info[key] + '\n';
        response_text += tmp_str;
    }
    return response_text;
}

// The function which handles requests and send response
function handleRequest(request, response){
    var raw_data = decode(request.url.slice(1));
    var data_list = raw_data.split('|');
    var response_text = '';
    if (data_list.length == 5) {
        response_text = handleList(data_list);
    } else {
        response_text = 'ERROR: Incorrect number of fields.\n';
    }
    response.write(response_text, 'ascii');
    response.end();
}

function calculateDistance(destination, point){
    var squareSum =Math.pow((destination.lat - point.lat),2) + Math.pow((destination.lng - point.lng));
    return (Math.sqrt(squareSum));

}

function sendMessage(recipients, message){
    var params = {
        'originator': 'MessageBird',
        'recipients': recipients, // recipients is an array
        'body': message
    };

    messageBird.messages.create(params, function (err, data) {
    if (err) {
        return console.log(err);
    }
    console.log(data);
    });
}

//Create the server
var server = http.createServer(handleRequest);

// Start the server
server.listen(process.env.PORT || PORT, function(){
    // Triggered when server is listening.
    console.log('Server listening on: http://localhost:%s', PORT);
});

//sendMessage(['971563052935'], 'MessageBird working');
