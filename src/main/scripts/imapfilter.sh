#!/bin/bash

# runs imapfilter

RUNDIR=$(dirname $0)
CLASSPATH=
for JAR in $RUNDIR/lib/*.jar ; do
    CLASSPATH="$CLASSPATH:$JAR"
done

java -cp $CLASSPATH org.bernitt.imapfilter.ImapFilter $@

RET=$?

exit $RET

