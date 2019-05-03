import json
import http.client

url = "127.0.0.1:8559"
uri = "/cluster/members"

requestMethod = "GET"


def printClusterCoordinator():
    connection = http.client.HTTPConnection(url)
    connection.request(requestMethod, uri)
    response = connection.getresponse()
    response_data = response.read()
    str_response_data = response_data.decode("utf-8")
    json_response_data = json.loads(str_response_data)
    print(json_response_data["oldest"])


printClusterCoordinator()
