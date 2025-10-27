#!/bin/bash
cd /home/kavia/workspace/code-generation/note-management-system-180839-180893/backend_api
./gradlew checkstyleMain
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

