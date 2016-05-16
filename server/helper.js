function calculateDistance(destination_lat, destination_lon, point_lat, point_lon){
    console.log("in calculate distance");
    var squareSum =Math.pow((destination_lat - point_lat),2) + Math.pow((destination_lon - point_lon),2);
    var distance= Math.sqrt(squareSum);
    console.log("Distance is", distance);
    return (distance);

}