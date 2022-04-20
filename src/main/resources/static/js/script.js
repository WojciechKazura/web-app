function zegar(){
var toDay= new Date();

var day=toDay.getDate();
var month=toDay.getMonth()+1;
var year=toDay.getYear();

var hour=toDay.getHours();
var minute=toDay.getMinutes();
var second=toDay.getSeconds();

document.getElementById("clock").innerHTML = "Dzisiaj mamy "+day+"/"+month+"/"+year+" | godzina "+hour+":"+minute+":"+second;
setTimeout("zegar()", 1000);
}

zegar();