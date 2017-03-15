#!/bin/bash

mvn exec:java -Dexec.mainClass="CommentCleaner" -Dexec.args="$1"
