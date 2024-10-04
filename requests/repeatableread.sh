#!/bin/bash
for i in {1..5}
  do
    curl -X POST -H "Content-Type: application/json" -d '{"title": "REPEATABLE_READ", "likes": 1}' "http://localhost:8080/api/history/repeatableRead" > /dev/null & # send out a curl request, the & indicates not to wait for the response.
  done
