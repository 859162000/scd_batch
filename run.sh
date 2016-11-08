#!/usr/bin/env bash

#git clone http://172.18.0.15/git/batch.git
git pull origin master
mvn clean compile package install
cd batch-executor/target
unzip BatchLauncher-bin.zip
cd BatchLauncher
chmod +x bin/start.sh
