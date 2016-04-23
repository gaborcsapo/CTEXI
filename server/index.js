//Lets require/import the HTTP module
var http = require('http');

//Lets define a port we want to listen to
const PORT=80; 

//We need a function which handles requests and send response
function handleRequest(request, response){
    var raw_data = request.url.slice(1);
    var data_list = raw_data.split("|");
    var response_str = "Parsed input line for line (separated by '|' characters):\n";
    for (var i = 0; i < data_list.length; i ++) {
        response_str += i + " " + data_list[i] + "\n";
    }
    response.end(response_str);
}

//Create a server
var server = http.createServer(handleRequest);

//Lets start our server
server.listen(PORT, function(){
    //Callback triggered when server is successfully listening. Hurray!
    console.log("Server listening on: http://localhost:%s", PORT);
});
