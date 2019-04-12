import json
import http.client

jolokiaUrls = [
    "127.0.0.1:7071",
    "127.0.0.1:7072",
    "127.0.0.1:7073"
]

jolokiaUri = "/jolokia/read/akka:type=Cluster/ClusterStatus"

requestMethod = "GET"


def sendHttpRequest(jolokiaUrl):
    connection = http.client.HTTPConnection(jolokiaUrl)
    connection.request(requestMethod, jolokiaUri)
    response = connection.getresponse()
    response_data = response.read()
    str_response_data = response_data.decode("utf-8")
    json_response_data = json.loads(str_response_data)
    response_value = json_response_data['value']
    response_value_json = json.loads(response_value)
    return response_value_json


def getClusterStatus():
    for jolokiaUrl in jolokiaUrls:
        response = sendHttpRequest(jolokiaUrl)
        members = response['members']
        unreachables = response['unreachable']
        self_address = response['self-address']
        print(self_address + ":")
        print("\t members:")
        for member in members:
            address = member['address']
            status = member['status']
            print("\t\t address: " + address + " -> status: " + status)
        print("\t unreachables:")
        for unreachable in unreachables:
            print("\t\t " + unreachable)


getClusterStatus()
