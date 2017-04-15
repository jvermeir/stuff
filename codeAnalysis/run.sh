#!/bin/bash

mvn exec:java -Dexec.mainClass="CommentErrorReporter" -Dexec.args="$1"
