#!/usr/bin/env bash

LINES="\n\n"
SPLIT="$LINES==============================$LINES"

printf ${LINES}

curl -XGET http://127.0.0.1:7071/jolokia/read/akka:type=Cluster/ClusterStatus \
    python3 -c "import sys, json; print(json.load(sys.stdin)['name'])"

printf ${SPLIT}

curl -XGET http://127.0.0.1:7072/jolokia/read/akka:type=Cluster/ClusterStatus

printf ${SPLIT}

curl -XGET http://127.0.0.1:7073/jolokia/read/akka:type=Cluster/ClusterStatus

printf ${LINES}