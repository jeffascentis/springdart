import "dart:html";
import 'package:js/js.dart' as js;

main() {

  querySelector("#load").onClick.listen(
      (event) => send());

  /*
  // JavaScript
  stompClient.subscribe('/topic/employees', function(greeting){
    showEmployeeResponse(JSON.parse(greeting.body));
  });

  // Old way of doing it in Dart that was removed
  js.context["stompClient.subscribe"]("/topic/employees", new js.Callback.many((message) {
    print(message);
  }));

  */

//  js.context["stompClient.subscribe"]("/topic/employees", new js.Callback.once((message) {
//    print(message);
//  }));

}

send() {
  String ret;
  try {
    ret = js.context["loadEmployee"]();
  }
  catch (exception) {
    String error = "JS call failed: " + exception.toString();
  }

  print(ret);

}
