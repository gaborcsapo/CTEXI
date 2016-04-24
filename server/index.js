//Lets require/import the HTTP module
var http = require('http');

//Lets define a port we want to listen to
const PORT=80; 

//URL decoder
function decode(str) {
     return unescape(str.replace(/\_/g, " "));
}

//We need a function which handles requests and send response
function handleRequest(request, response){
    var info = {};
    var raw_data = decode(request.url.slice(1));
    var data_list = raw_data.split("|");
    if (data_list.length == 5) {
        response.write("Parsed input line for line (separated by '|' characters):\n", 'ascii');
        info['type']    = data_list[0];
        info['lat']     = data_list[1];
        info['lon']     = data_list[2];
        info['phone']   = data_list[3];
        info['message'] = data_list[4];
        for (var key in info) {
            var tmp_str = key + "\t" + info[key] + "\n";
            response.write(tmp_str, 'ascii');
        }
    } else {
        response.write("ERROR: Incorrect number of fields.\n");
    }
    response.end();
}

//Create a server
var server = http.createServer(handleRequest);

//Lets start our server
server.listen(PORT, function(){
    //Callback triggered when server is successfully listening. Hurray!
    console.log("Server listening on: http://localhost:%s", PORT);
});
