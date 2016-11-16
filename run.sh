#!/usr/bin/env bash

set -x

#git clone http://172.18.0.15/git/batch.git
git pull origin master
mvn clean compile package install
[ -d BatchLauncher ] && rm -rf BatchLauncher
unzip batch-executor/target/BatchLauncher-bin.zip
chmod +x BatchLauncher/bin/*
