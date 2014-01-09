import 'dart:html';

import 'package:stomp/stomp.dart';
import "package:stomp/websocket.dart" show connect;

StompClient _stompClient;
DivElement logDiv;

void main() {
  logDiv = querySelector("#output");
  logDiv
    ..text = "Dart is running";

  /**
   * Changed ws://localhost:8080/gs/ws/
   * to ws://localhost:8080/gs/ws/websocket
   * 
   * because otherwise the sockJS will return the 200 that dart/websocket
   * doesn't expect.  This is due to SockJS on the server, not stomp or
   * dart. 
   * 
   * http://procbits.com/connecting-to-a-sockjs-server-from-native-html5-websocket
   * 
   * TODO: Update to use sockjs, and change this back to 
   *  ws://localhost:8080/gs/ws/
   */
  connect("ws://localhost:8080/gs/ws/websocket").then((StompClient stomp) {
    _stompClient = stomp;
    _stompClient.subscribeString("0", "/topic/employees", printEmployeesTopicResponses);
  });
  
  ButtonElement loadEmployeeBtn = querySelector('#loadEmployeeBtn');
  loadEmployeeBtn.onClick.listen(loadEmployee);
  
}

void loadEmployee(MouseEvent evt) {
  String id = "3";
  /**
   * sendJson is broken in the library. Forked it to revel fire and augmented the
   * pubspec to use the git lib.
   * 
   * Logged with author:
   * https://github.com/rikulo/stomp/issues/6
   * 
   * Patched to send proper application/json header.
   * 
   * https://github.com/revelfire/stomp/
   * 
   * https://github.com/revelfire/stomp/commit/cc8c17c21ab58f1bb23c524e123df32787c01f5b
   * https://github.com/revelfire/stomp/commit/53a46589de2da7702f2ac21fb63114ee7bbdca48
   * 
   */
  _stompClient.sendJson("/app/loadEmployee", {"requestObject": id});  
}

void printEmployeesTopicResponses(headers, message) {
  print("Received $message"); 
  logDiv
    ..text = "Received $message";
}

//class EmployeeCrudHandler {
//  
//}