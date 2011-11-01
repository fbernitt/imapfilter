#!/bin/bash

MVNREPO="$HOME/.m2/repository"

CLASSPATH="$MVNREPO/commons-cli/commons-cli/1.1/commons-cli-1.1.jar:$MVNREPO/commons-digester/commons-digester/2.0/commons-digester-2.0.jar:$MVNREPO/commons-beanutils/commons-beanutils/1.8.0/commons-beanutils-1.8.0.jar:$MVNREPO/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar:$MVNREPO/groovy/groovy-all/1.1-rc-1/groovy-all-1.1-rc-1.jar:$MVNREPO/junit/junit/3.8.1/junit-3.8.1.jar:$MVNREPO/javax/mail/mail/1.4.1/mail-1.4.1.jar:$MVNREPO/javax/activation/activation/1.1/activation-1.1.jar:$MVNREPO/commons-lang/commons-lang/2.4/commons-lang-2.4.jar:$MVNREPO/joda-time/joda-time/1.6/joda-time-1.6.jar:$MVNREPO/javamaildir/javamaildir/0.4-pre-9/javamaildir-0.4-pre-9.jar"

IMAPFILTERJAR=`find /home/fbe/.m2/repository/org/bernitt/imapfilter/ -name "imapfilter*.jar"`

CLASSPATH="$CLASSPATH:$IMAPFILTERJAR"


java -cp $CLASSPATH org.bernitt.imapfilter.ImapFilter $@

RES=$?

exit $RES
