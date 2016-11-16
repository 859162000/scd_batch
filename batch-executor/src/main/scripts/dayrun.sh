#!/usr/bin/env bash

set -x

# BatchLauncher
BATCH_HOME=$(cd $(dirname $0);cd ..;pwd)
cd $BATCH_HOME

bin/start.sh -n dayRedeemSchedule