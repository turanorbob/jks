/**
 * Created by liaojian on 10/14/16.
 */
var url = 'ws://' + window.location.host + '/ws/conn';
console.log(url);
var ws = new WebSocket(url);
ws.onopen = function () {
    console.log('connect');
}
ws.onclose = function () {
    console.log('disconnect');
}
ws.onmessage = function (event) {
    var div = document.createElement('div');
    div.innerHTML = event.data;
    document.getElementById('content').appendChild(div);
}
ws.onerror = function (event) {
    console.log('error');
}