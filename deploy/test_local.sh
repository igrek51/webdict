#!/bin/bash
set -ex
cd `dirname $0`

mkdir -p ./volumes/db

docker-compose build
docker-compose up --force-recreate
