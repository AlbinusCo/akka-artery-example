import json
import http.client

jolokiaUrl = "127.0.0.1:7071"

jolokiaUri = "/jolokia/"

payload = {
    "type": "EXEC",
    "mbean": "akka:type=Cluster",
    "operation": "down",
    "arguments": ["akka://akka-artery-example@node2:2552"]
}

requestMethod = "POST"


def sendHttpRequest():
    connection = http.client.HTTPConnection(jolokiaUrl)
    connection.request(requestMethod, jolokiaUri,  json.dumps(payload))
    response = connection.getresponse()
    response_data = response.read()
    print(response_data)


sendHttpRequest()
