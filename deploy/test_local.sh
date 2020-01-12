#!/bin/bash
set -ex
cd `dirname $0`

docker-compose build
docker-compose run --rm webdict
