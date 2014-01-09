import 'dart:html';

import 'package:stomp/stomp.dart';
import 'package:logging/logging.dart';
import "package:stomp/websocket.dart" show connect;
import "package:sockjs_client/sockjs.dart" as SockJS;

StompClient _stompClient;

void main() {
  DivElement loggingDiv = querySelector("#output")
    ..text = "Dart is running";
  
  _log(LogRecord l) {
    loggingDiv
    ..append(new Element.html("<code/>")..text = "${l.message}")
    ..append(new Element.html("<br>"))
    ..scrollTop += 10000;
  }
  
// Setup Logging
  Logger.root.level = Level.INFO;
  Logger.root.onRecord.listen(_log);

  final LOG = new Logger("sockjs");

  LOG.info("Starting");
  var sockjsUrl = 'http://localhost:8080/gs/ws/';
  
                                            /** This protocolsWhitelist bit is pretty cool - CM **/
  var sockjs = new SockJS.Client( sockjsUrl, protocolsWhitelist:['websocket', 'xhr-streaming'], debug: true);

  sockjs.onOpen.listen( (_) => LOG.info('[*] open ${sockjs.protocol}') );
  sockjs.onMessage.listen( (e) => LOG.info('[.] message ${e.data}') );
  sockjs.onClose.listen( (_) => LOG.info('[*] close') );

  inp.onKeyUp.listen( (KeyboardEvent e) {
    if (e.keyCode == 13) {
      LOG.info('[ ] sending ${inp.value}');
      sockjs.send(inp.value);
      inp.value = '';
    }
  });

  
  
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
   *  https://github.com/rikulo/stomp/issues/6
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
}

//class EmployeeCrudHandler {
//  
//}